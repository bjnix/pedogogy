// CASClientSocket.h - Declares the window and other things the basic
//      async I/O Winsock client uses.

#if !defined(AFX_ASYNCCLIENTWND_H__1D8777C3_5075_11D4_86BD_00600895D2A4__INCLUDED_)
#define AFX_ASYNCCLIENTWND_H__1D8777C3_5075_11D4_86BD_00600895D2A4__INCLUDED_

#pragma once

#include "NetworkDriver.h"

#include <afxsock.h>

class CCASClientSocket :
public CAsyncSocket,
public CNetworkDriver
{
public:
    //// Public interface
    // Ctor and dtor
	CCASClientSocket();
	virtual ~CCASClientSocket();

	// Connect to server
	virtual bool Start(const char* addr, int port);

	// Override of generic "do something" function.  In this case, we
	// send a packet to the server.
	virtual void Action() { SendEcho(); }
	virtual const char* GetActionName() { return "Send echo request"; }

	// Close the network connection.  Return true if we can do it
	// immediately or if the conn is already down.  Return false if
	// we have to wait for the conn to come down: we'll send nMessage
	// to pNotifyWnd when the conn comes down.
	virtual bool Stop(CWnd* pNotifyWnd, int nMessage);

	// Is connection to server open?
	virtual bool ServiceStarted()
	{
		return (m_hSocket != INVALID_SOCKET); 
	}

	//// Overrides
	//{{AFX_VIRTUAL(CCASClientSocket)
	//}}AFX_VIRTUAL

protected:
	//// Internal support functions
	bool EstablishConnection();
	void SendEcho();
	void ReadReply();
	void CloseSocket();
	bool OnAsyncError(int nErrorCode, const char* pcFunc);

	// Overrides of CAsyncSocket::On* functions
	virtual void OnConnect(int nErrorCode);
	virtual void OnClose(int nErrorCode);
	virtual void OnReceive(int nErrorCode);
	virtual void OnSend(int nErrorCode);

private:
	//// Internal data
	bool bIgnoreStart_;
};

#endif // !defined(AFX_ASYNCCLIENTWND_H__1D8777C3_5075_11D4_86BD_00600895D2A4__INCLUDED_)
