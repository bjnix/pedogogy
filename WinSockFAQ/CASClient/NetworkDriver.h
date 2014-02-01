// NetworkDriver.h - A CWnd that holds a Winsock connection.  Used when
//		outsiders need to be able to close such a window down cleanly,
//		including closing the network connection

#if !defined(NETWORKDRIVER_H)
#define NETWORKDRIVER_H

#include <winsock.h>

class CNetworkDriver
{
public:
    //// Public interface
    // Ctor and dtor
	CNetworkDriver() :
	pCloseNotifyWnd_(0),
	nCloseNotifyMsg_(0)
	{
	}
	virtual ~CNetworkDriver() { }

	// Start up the service (connect or listen)
	virtual bool Start(const char* addr, int port) = 0;

	// Do some unspecified action.  Ugly, but required to be able to
	// derive lots of unrelated sublcasses that all "do something"
	// with the network.
	virtual void Action() = 0;
	virtual const char* GetActionName() = 0;

	// Override this to handle custom network shutdown.
	virtual bool Stop(CWnd* pNotifyWnd, int nMessage)
	{
		pCloseNotifyWnd_ = pNotifyWnd;
		nCloseNotifyMsg_ = nMessage;

		return false;
	}

	// Connection established, or server listening?
	virtual bool ServiceStarted() = 0;

protected:
	//// Subclass-visible data
	CWnd* pCloseNotifyWnd_;
	int nCloseNotifyMsg_;
};

#endif // !defined(NETWORKDRIVER_H)
