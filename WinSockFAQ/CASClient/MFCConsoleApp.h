// MFCConsoleApp.h - Declares the app core class.

#if !defined(AFX_MFCConsole_H__AA4699E3_4FEB_11D1_B5CF_0020AFE30C22__INCLUDED_)
#define AFX_MFCConsole_H__AA4699E3_4FEB_11D1_B5CF_0020AFE30C22__INCLUDED_

#pragma once

class CNetworkDriver;

class CMFCConsoleApp : 
public CWinApp 
{
public:
	//// Public interface
	// Ctor and dtor
	CMFCConsoleApp();
	~CMFCConsoleApp();

	// Return a pointer to the network driver object.
	CNetworkDriver* GetConn() { return pNetworkDriver_; }

	//// Overrides
	//{{AFX_VIRTUAL(CMFCConsoleApp)
	virtual BOOL InitInstance();
	virtual int ExitInstance();
	//}}AFX_VIRTUAL

protected:
	//// Message map
	//{{AFX_MSG(CMFCConsoleApp)
	afx_msg void OnAppAbout();
	afx_msg void OnDoWinsock();
	afx_msg void OnUpdateDoWinsock(CCmdUI* pCmdUI);
	afx_msg void OnAction();
	afx_msg void OnProgramAction(CCmdUI* pCmdUI);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP();

private:
	//// Internal data
	CNetworkDriver* pNetworkDriver_;
};

//{{AFX_INSERT_LOCATION}}

#endif // !defined(AFX_MFCConsole_H__AA4699E3_4FEB_11D1_B5CF_0020AFE30C22__INCLUDED_)
