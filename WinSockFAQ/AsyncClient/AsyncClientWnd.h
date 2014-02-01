// AsyncClientWnd.h - Declares the window and other things the basic
//      async I/O Winsock client uses.

#if !defined(AFX_ASYNCCLIENTWND_H__1D8777C3_5075_11D4_86BD_00600895D2A4__INCLUDED_)
#define AFX_ASYNCCLIENTWND_H__1D8777C3_5075_11D4_86BD_00600895D2A4__INCLUDED_

#pragma once

#include "NetworkDriver.h"

class CAsyncClientWnd : 
public CWnd,
public CNetworkDriver
{
public:
    //// Public interface
    // Ctor and dtor
	CAsyncClientWnd();
	virtual ~CAsyncClientWnd();

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

	// Is the connection to the server open?
	virtual bool ServiceStarted()
	{
		return (sd_ != INVALID_SOCKET); 
	}

	//// Overrides
	//{{AFX_VIRTUAL(CAsyncClientWnd)
	//}}AFX_VIRTUAL

protected:
	//// Message map
	//{{AFX_MSG(CAsyncClientWnd)
	afx_msg void OnTimer(UINT nIDEvent);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP();
	LRESULT OnWinsockLookup(WPARAM, LPARAM);
	LRESULT OnWinsockNotify(WPARAM, LPARAM);

	//// Internal support functions
	bool LookupAddress(const char* pcHost);
	bool EstablishConnection();
	void SendEcho();
	void ReadReply();
	void CloseSocket();

private:
	//// Internal data
	union {
		hostent he_;
		char acBuffer_[MAXGETHOSTSTRUCT];
	} LookupResult_;
	CString sAttempting_;
	bool bIgnoreStart_;
	CString sAddress_;
	sockaddr_in sin_;
	SOCKET sd_;
};

#endif // !defined(AFX_ASYNCCLIENTWND_H__1D8777C3_5075_11D4_86BD_00600895D2A4__INCLUDED_)
