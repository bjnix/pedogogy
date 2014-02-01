/***********************************************************************
 AsyncClientWnd.cpp - The async I/O code.  This is the only 
	substantially different module in the whole project, with respect
	to the basic MFCConsole framework and the other examples based on
	that framework.

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
	
 Copyright (c) 2000 by Warren Young.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "stdafx.h"
#include "AsyncClientWnd.h"

#include "Headsman.h"
#include "StatusReporter.h"

#include "ws-util.h"

#include <iostream>
#include <string.h>

using namespace std;


////////////////////////////////////////////////////////////////////////
// Globals

// Pointer to the window object -- destroys the window when the program 
// shuts down.
Headsman<CAsyncClientWnd> gHeadsman_;

// Two custom window messages we use
static int gnWSLookupMsg = RegisterWindowMessage(__FILE__ ":wslookup");
static int gnWSNotifyMsg = RegisterWindowMessage(__FILE__ ":wsnotify");


BEGIN_MESSAGE_MAP(CAsyncClientWnd, CWnd)
	//{{AFX_MSG_MAP(CAsyncClientWnd)
	ON_WM_TIMER()
	//}}AFX_MSG_MAP
	ON_REGISTERED_MESSAGE(gnWSLookupMsg, OnWinsockLookup)
	ON_REGISTERED_MESSAGE(gnWSNotifyMsg, OnWinsockNotify)
END_MESSAGE_MAP();


////////////////////////////////////////////////////////////////////////
// Constants

// kBufferSize must be larger than the length of kpcEchoMessage.
const int kBufferSize = 1024;
const char* kpcEchoMessage = "This is a test of the emergency data "
        "transfer system.  If this had been real a real emergency, we "
        "would have sent this data out-of-band.";
const int kEchoMessageLen = strlen(kpcEchoMessage);

// Timeout stuff
const int kWSTimerID = 42;
const int kWSTimeout = 3000;		// milliseconds


//// DoWinsock /////////////////////////////////////////////////////////
// Starts up the async I/O client window, which handles all the actual
// network I/O.  Returns 0 on failure, else a pointer to the async I/O 
// window.  The window is automatically destroyed when the program 
// exits.

CNetworkDriver* DoWinsock()
{
	if (gHeadsman_.Get() == 0) {
		// No network driver object yet, so create one
		CAsyncClientWnd* pWnd = new CAsyncClientWnd;
		gHeadsman_.Set(pWnd);
		pWnd->Create(0, "Invisible async client I/O window",
				WS_OVERLAPPED, CRect(CPoint(0, 0), CSize(10, 10)),
				::AfxGetMainWnd(), -1);

		return pWnd;
	}
	else {
		// The window is already created!
		REPORT_PROBLEM("Sorry, async client window already exists.  "
				"This program isn't smart enought to do more than one "
				"client window.");
		return 0;
	}
}


////////////////////////////////////////////////////////////////////////
// Ctor and dtor

CAsyncClientWnd::CAsyncClientWnd() :
CWnd(),
CNetworkDriver(),
bIgnoreStart_(false),
sd_(INVALID_SOCKET)
{
	memset(LookupResult_.acBuffer_, 0, sizeof(LookupResult_.acBuffer_));

	sin_.sin_family = AF_INET;
	sin_.sin_port = 0;
	sin_.sin_addr.s_addr = INADDR_NONE;
}

CAsyncClientWnd::~CAsyncClientWnd()
{
}


//// Start /////////////////////////////////////////////////////////////
// Connect to server

bool CAsyncClientWnd::Start(const char* addr, int port)
{
	if (bIgnoreStart_) {
		return false;
	}
	else {
		sAddress_ = addr;
		sin_.sin_port = htons(port);
		if (LookupAddress(sAddress_)) {
			// Lookup happened immediately, so bring up the connection.
			EstablishConnection();
		}
		else {
			// We're busy looking up the host, so don't let the user
			// keep calling Start -- that would just confuse us.
			bIgnoreStart_ = true;
		}

		return true;
	}
}


//// LookupAddress /////////////////////////////////////////////////////
// Given an address string, determine if it's a dotted-quad IP address
// or a domain address.  If the latter, ask DNS to resolve it.  If we
// can resolve the address immediately, we return true, else we return
// false; a window message will come along later when Winsock finishes
// resolving the address.

bool CAsyncClientWnd::LookupAddress(const char* pcHost)
{
	u_long nRemoteAddr = inet_addr(pcHost);
	if (nRemoteAddr != INADDR_NONE) {
		sin_.sin_addr.s_addr = nRemoteAddr;
		return true;
	}
	else {
		// pcHost isn't a dotted IP, so resolve it through DNS
		if (WSAAsyncGetHostByName(m_hWnd, gnWSLookupMsg,
				pcHost, (char*)&LookupResult_, 
				sizeof(LookupResult_)) != 0) {
			SetTimer(kWSTimerID, kWSTimeout, 0);
			sAttempting_.Format("Looking up host %s.", pcHost);
		}
		else {
			REPORT_PROBLEM(WSAGetLastErrorMessage(
					"Async host name lookup failed"));
		}
		return false;
	}
}


//// EstablishConnection ///////////////////////////////////////////////
// The server address is resolved now, so connect to it.  Returns false
// if we fail for some reason.

bool CAsyncClientWnd::EstablishConnection()
{
	// Create a stream socket
	sd_ = socket(AF_INET, SOCK_STREAM, 0);
	if (sd_ == INVALID_SOCKET) {
		REPORT_PROBLEM(WSAGetLastErrorMessage(
				"Failed to create the socket"));
		return false;
	}

	// Mark our socket non-blocking, and ask for asynch notifications.
	if (WSAAsyncSelect(sd_, m_hWnd, gnWSNotifyMsg,
			FD_READ | FD_WRITE | FD_CONNECT | FD_CLOSE) == 
			SOCKET_ERROR) {
		REPORT_PROBLEM(
				WSAGetLastErrorMessage("WSAAsyncSelect failed."));
		return false;
	}

	// Begin the connection attempt.  This will almost certainly
	// not complete immediately.
	if (connect(sd_, (sockaddr*)&sin_, sizeof(sockaddr_in)) ==
			SOCKET_ERROR) {
		int nError = WSAGetLastError();
		if (nError == WSAEWOULDBLOCK) {
			REPORT_NORMAL_STATUS(
					"Asynchronous connection attempt started.");
			SetTimer(kWSTimerID, kWSTimeout, 0);
			sAttempting_ = "Connecting to server";
		}
		else {
			CloseSocket();
			REPORT_PROBLEM(
					WSAGetLastErrorMessage("Async connect failed", 
					nError));
			return false;
		}
	}
	else {
		// We'll have to fake an FD_CONNECT message to ourselves.
		REPORT_NORMAL_STATUS("Wonder of wonders!  Asynch connect "
				"completed immediately!");
		PostMessage(gnWSNotifyMsg, sd_, MAKELPARAM(FD_CONNECT, 0));
	}

	return true;
}


//// SendEcho //////////////////////////////////////////////////////////
// Sends the echo packet to the server, waits for, and verifies reply.
// Warning: this function isn't smart enough to send data in multiple
// parts.  It assumes it can send the entire echo request at once.  This
// may not be a good assumption.

void CAsyncClientWnd::SendEcho()
{
	// Send the string to the server
	if (send(sd_, kpcEchoMessage, kEchoMessageLen, 0) != SOCKET_ERROR) {
		REPORT_NORMAL_STATUS("Successfully sent echo request.");
		SetTimer(kWSTimerID, kWSTimeout, 0);
		sAttempting_ = "Sending echo packet to server";
	}
	else {
		int nError = WSAGetLastError();
		if (nError == WSAEWOULDBLOCK) {
			REPORT_NORMAL_STATUS("send() would block.  Try the echo "
					"request again later.");
		}
		else {
			REPORT_PROBLEM(
					WSAGetLastErrorMessage("send() echo failure", 
					nError));
		}
	}
}


//// ReadReply /////////////////////////////////////////////////////////
// Server sent us some data, so read it and tell user about it.

void CAsyncClientWnd::ReadReply()
{
	REPORT_NORMAL_STATUS("Reading echo reply.");
	
	char acReadBuffer[kBufferSize];
	int nTotalBytes = 0;
	while (nTotalBytes < kEchoMessageLen) {
		int nNewBytes = recv(sd_, acReadBuffer + nTotalBytes, 
				kBufferSize - nTotalBytes, 0);
		if (nNewBytes == SOCKET_ERROR) {
			int nError = WSAGetLastError();
			if (nError == WSAEWOULDBLOCK) {
				REPORT_NORMAL_STATUS("Read " << nTotalBytes << 
						" bytes from server.");
				break;
			}
			else {
				REPORT_PROBLEM(
						WSAGetLastErrorMessage("recv() failure", 
						nError));
				return;
			}
		}
		else if (nNewBytes == 0) {
			REPORT_NORMAL_STATUS("Connection closed by peer.");
			CloseSocket();
			return;
		}

		nTotalBytes += nNewBytes;
	}

	if (nTotalBytes >= kEchoMessageLen) {
		// Check data for sanity
		if (strncmp(acReadBuffer, kpcEchoMessage, nTotalBytes) == 0) {
			REPORT_NORMAL_STATUS("Received echo reply, and it's okay!");
		}
		else {
			REPORT_PROBLEM("Mismatch in data received from server. "
					"Something's broken!");
		}
	}
	else {
		REPORT_NORMAL_STATUS("Was not able to recv() entire reply: "
				"more data to come, we hope.");
	}
}


//// Stop //////////////////////////////////////////////////////////////
// Close the network connection cleanly.  Return true if we succeed, or
// the connection is already down.  Else we return false and send 
// nMessage to pNotifyWnd when the conn does go down.

bool CAsyncClientWnd::Stop(CWnd* pNotifyWnd, int nMessage)
{
	CNetworkDriver::Stop(pNotifyWnd, nMessage);

	if (ServiceStarted()) {
		shutdown(sd_, 1);
		return false;
	}
	else {
		return true;
	}
}


//// CloseSocket ///////////////////////////////////////////////////////
// Close the socket and mark it as invalid.  Only to be called from 
// within the class.

void CAsyncClientWnd::CloseSocket()
{
	if (sd_ != INVALID_SOCKET) {
		closesocket(sd_);
		sd_ = INVALID_SOCKET;
	}
}


////////////////////////////////////////////////////////////////////////
// CAsyncClientWnd message handlers

LRESULT CAsyncClientWnd::OnWinsockNotify(WPARAM, LPARAM lParam)
{
	KillTimer(kWSTimerID);
	
	int nError = WSAGETASYNCERROR(lParam);
	if (nError != 0) {
		switch (nError) {
			case WSAECONNRESET:
				REPORT_PROBLEM("Connection was aborted.");
				CloseSocket();
				break;
	
			case WSAECONNREFUSED:
				REPORT_PROBLEM("Connection was refused.");
				CloseSocket();
				break;

			default:
				REPORT_PROBLEM(WSAGetLastErrorMessage(
						"Async failure notification", nError));
		}
		return 0;
	}

	switch (WSAGETSELECTEVENT(lParam)) {
		case FD_READ:
			REPORT_NORMAL_STATUS("WSEV: there's data to read");
			ReadReply();
			break;

		case FD_WRITE:
			REPORT_NORMAL_STATUS("WSEV: ready for writes");
			break;

		case FD_CONNECT:
			REPORT_NORMAL_STATUS("WSEV: connect succeeded");
			break;

		case FD_CLOSE:
			REPORT_NORMAL_STATUS("WSEV: connection is closed");
			CloseSocket();
			if (pCloseNotifyWnd_) {
				pCloseNotifyWnd_->PostMessage(nCloseNotifyMsg_, 
						(WPARAM)m_hWnd);
			}
			break;

		default:
			REPORT_PROBLEM("WSEV: Unknown event recieved: " <<
					WSAGETSELECTEVENT(lParam) << ".");
	}

	return 0;
}

LRESULT CAsyncClientWnd::OnWinsockLookup(WPARAM, LPARAM lParam)
{
	KillTimer(kWSTimerID);

	int nError = WSAGETASYNCERROR(lParam);
	if (nError == 0) {
		sin_.sin_addr.s_addr = 
				*((u_long*)LookupResult_.he_.h_addr_list[0]);
		REPORT_NORMAL_STATUS("Looked up " << (const char*)sAddress_ << 
				" successfully: " << inet_ntoa(sin_.sin_addr) << ".");

		EstablishConnection();
	}
	else {
		if (nError == WSAENOBUFS) {
			REPORT_PROBLEM("HOSTENT block too small to contain reply.");
		}
		else {
			REPORT_PROBLEM(
					WSAGetLastErrorMessage("Lookup failed", nError));
		}
	}

	bIgnoreStart_ = false;

	return 0;
}

void CAsyncClientWnd::OnTimer(UINT nIDEvent) 
{
	if (nIDEvent == kWSTimerID) {
		REPORT_PROBLEM("Timed out: " << (const char*)sAttempting_);
		KillTimer(nIDEvent);
		CloseSocket();
		bIgnoreStart_ = false;
	}
	else {
		CWnd::OnTimer(nIDEvent);
	}
}

