/***********************************************************************
 MainFrm.h - Declares the main frame window.
***********************************************************************/

#if !defined(AFX_MAINFRM_H__AA4699E7_4FEB_11D1_B5CF_0020AFE30C22__INCLUDED_)
#define AFX_MAINFRM_H__AA4699E7_4FEB_11D1_B5CF_0020AFE30C22__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

// Forward references
class CMFCConsoleView;

class CMainFrame : 
public CFrameWnd 
{
public:
	//// Dtor
	virtual ~CMainFrame();

	//// Public interface
	// Sends a string out to the console window
	void OutputMessage(const char* pcMessage);

protected: 
	//// Internal support functions
	// Protected ctor so we create from serialization only
	CMainFrame();
	DECLARE_DYNCREATE(CMainFrame);

	//// Overrides
	//{{AFX_VIRTUAL(CMainFrame)
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	//}}AFX_VIRTUAL

	//// Event handlers
	//{{AFX_MSG(CMainFrame)
	afx_msg void OnClose();
	//}}AFX_MSG
	LRESULT OnCloseNotify(WPARAM, LPARAM);
	DECLARE_MESSAGE_MAP();

private:
	//// Internal data
	CMFCConsoleView* pMFCConsoleView_;
};

//{{AFX_INSERT_LOCATION}}

#endif // !defined(AFX_MAINFRM_H__AA4699E7_4FEB_11D1_B5CF_0020AFE30C22__INCLUDED_)
