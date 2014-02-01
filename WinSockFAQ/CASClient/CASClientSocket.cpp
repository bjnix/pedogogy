/***********************************************************************
 CASClientSocket.cpp - The async I/O code.  This is the only substantially 
	different module in the whole project, with respect to the other 
	examples based on the MFCConsole framework.

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:50 $
	
 Copyright (c) 2000 by Warren Young.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "stdafx.h"
#include "CASClientSocket.h"

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
Headsman<CCASClientSocket> gHeadsman_;


////////////////////////////////////////////////////////////////////////
// Constants

// kBufferSize must be larger than the length of kpcEchoMessage.
const int kBufferSize = 1024;
const char* kpcEchoMessage = "This is a test of the emergency data "
        "transfer system.  If this had been real a real emergency, we "
        "would have sent this data out-of-band.";
const int kEchoMessageLen = strlen(kpcEchoMessage);


//// DoWinsock /////////////////////////////////////////////////////////
// Starts up the async I/O client window, which handles all the actual
// network I/O.  Returns 0 on failure, else a pointer to the async I/O 
// window.  The window is automatically destroyed when the program 
// exits.

CNetworkDriver* DoWinsock()
{
	if (gHeadsman_.Get() == 0) {
		// No socket object yet, so create one.
		CCASClientSocket* pSock = new CCASClientSocket;
		gHeadsman_.Set(pSock);
		pSock->Create();

		return pSock;
	}
	else {
		// The window is already created!
		REPORT_PROBLEM("Sorry, CAsyncSocket client window already "
				"exists.  This program isn't smart enought to do more "
				"than one client window.");
		return 0;
	}
}


////////////////////////////////////////////////////////////////////////
// Ctor and dtor

CCASClientSocket::CCASClientSocket() :
CNetworkDriver(),
bIgnoreStart_(false)
{
}

CCASClientSocket::~CCASClientSocket()
{
}


//// Start /////////////////////////////////////////////////////////////
// Connect to server

bool CCASClientSocket::Start(const char* addr, int port)
{
	if (!bIgnoreStart_) {
		if (CAsyncSocket::Connect(addr, port)) {
			REPORT_NORMAL_STATUS("Wonder of wonders!  Asynch connect "
					"completed immediately!");
		}
		else {
			int nError = CAsyncSocket::GetLastError();
			
			if (nError == WSAEWOULDBLOCK) {
				REPORT_NORMAL_STATUS(
						"Asynchronous connection attempt started.");
			}
			else {
				REPORT_PROBLEM(
						WSAGetLastErrorMessage("Async connect failed", 
						nError));
				return false;
			}
		}

		// We're busy looking up the host, so don't let the user
		// keep calling Start -- that would just confuse us.
		bIgnoreStart_ = true;

		return true;
	}
	else {
		return false;
	}
}


//// SendEcho //////////////////////////////////////////////////////////
// Sends the echo packet to the server, waits for, and verifies reply.
// Warning: this function isn't smart enough to send data in multiple
// parts.  It assumes it can send the entire echo request at once.  This
// may or may not be a good assumption.

void CCASClientSocket::SendEcho()
{
	// Send the string to the server
	if (Send(kpcEchoMessage, kEchoMessageLen) != SOCKET_ERROR) {
		REPORT_NORMAL_STATUS("Successfully sent echo request.");
	}
	else {
		int nError = CAsyncSocket::GetLastError();

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

void CCASClientSocket::ReadReply()
{
	REPORT_NORMAL_STATUS("Reading echo reply.");
	
	char acReadBuffer[kBufferSize];
	int nTotalBytes = 0;
	while (nTotalBytes < kEchoMessageLen) {
		int nNewBytes = Receive(acReadBuffer + nTotalBytes, 
				kBufferSize - nTotalBytes);
		if (nNewBytes == SOCKET_ERROR) {
			int nError = CAsyncSocket::GetLastError();

			if (nError == WSAEWOULDBLOCK) {
				REPORT_NORMAL_STATUS("Read " << nTotalBytes << 
						" bytes from server.");
				break;
			}
			else {
				REPORT_PROBLEM(
						WSAGetLastErrorMessage("Receive() failure", 
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

bool CCASClientSocket::Stop(CWnd* pNotifyWnd, int nMessage)
{
	CNetworkDriver::Stop(pNotifyWnd, nMessage);

	if (ServiceStarted()) {
		ShutDown();
		return false;
	}
	else {
		return true;
	}
}


//// CloseSocket ///////////////////////////////////////////////////////
// Close the socket and mark it as invalid.  Only to be called from 
// within the class.

void CCASClientSocket::CloseSocket()
{
	if (m_hSocket != INVALID_SOCKET) {
		CAsyncSocket::Close();
	}
}


//// OnAsyncError //////////////////////////////////////////////////////
// Handles error codes sent to the CAsyncSocket::On* functions.  Returns
// true if there was an error, else false (meaning life is still 
// beautiful).

bool CCASClientSocket::OnAsyncError(int nErrorCode, const char* pcFunc)
{
	if (nErrorCode != 0) {
		switch (nErrorCode) {
			case WSAECONNRESET:
				REPORT_PROBLEM("Connection was aborted (" <<
						pcFunc << ")");
				CloseSocket();
				return true;

			case WSAECONNREFUSED:
				REPORT_PROBLEM("Connection was refused. (" <<
						pcFunc << ")");
				CloseSocket();
				return true;

			default:
				REPORT_PROBLEM(WSAGetLastErrorMessage(
						"Async failure notification", nErrorCode) << 
						" (" << pcFunc << ")");
		}
	}

	return false;
}


////////////////////////////////////////////////////////////////////////
// CAsyncSocket::On* overrides.  Replaces the FD_* switch statement in
// the plain API async socket example.

void CCASClientSocket::OnConnect(int nErrorCode)
{
	if (OnAsyncError(nErrorCode, "connect")) {
		return;
	}

	REPORT_NORMAL_STATUS("WSEV: connect succeeded");
}

void CCASClientSocket::OnClose(int nErrorCode)
{
	if (OnAsyncError(nErrorCode, "close")) {
		return;
	}

	REPORT_NORMAL_STATUS("WSEV: connection is closed");
	CloseSocket();
	if (pCloseNotifyWnd_) {
		pCloseNotifyWnd_->PostMessage(nCloseNotifyMsg_);
	}
}

void CCASClientSocket::OnReceive(int nErrorCode)
{
	if (OnAsyncError(nErrorCode, "receive")) {
		return;
	}

	REPORT_NORMAL_STATUS("WSEV: there's data to read");
	ReadReply();
}

void CCASClientSocket::OnSend(int nErrorCode)
{
	if (OnAsyncError(nErrorCode, "send")) {
		return;
	}

	REPORT_NORMAL_STATUS("WSEV: ready for writes");
}

