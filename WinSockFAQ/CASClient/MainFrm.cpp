/***********************************************************************
 MainFrm.cpp - Defines the main frame window behavior.

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
	
 Copyright (c) 1996-2000 by ETR..., Inc.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "stdafx.h"
#include "MainFrm.h"

#include "MFCConsoleView.h"
#include "MFCConsoleApp.h"

#include "NetworkDriver.h"


////////////////////////////////////////////////////////////////////////
// Globals

// Custom message: sent when conn is going down.  This lets us re-try
// the window close, if we tried once and failed.
static int gnCloseNotifyMsg = RegisterWindowMessage(
		__FILE__ ":closenotify");


IMPLEMENT_DYNCREATE(CMainFrame, CFrameWnd)

BEGIN_MESSAGE_MAP(CMainFrame, CFrameWnd)
	//{{AFX_MSG_MAP(CMainFrame)
	ON_WM_CLOSE()
	//}}AFX_MSG_MAP
	ON_REGISTERED_MESSAGE(gnCloseNotifyMsg, OnCloseNotify)
END_MESSAGE_MAP();


////////////////////////////////////////////////////////////////////////
// Ctor and dtor

CMainFrame::CMainFrame() :
CFrameWnd(),
pMFCConsoleView_(0)
{
}

CMainFrame::~CMainFrame()
{
}


//// OutputMessage /////////////////////////////////////////////////////
// Outputs the given message to the view area

void CMainFrame::OutputMessage(const char* pcMessage)
{
	ASSERT(pcMessage);

	// Get a handle to our current view
	if (pMFCConsoleView_ == 0) {
		pMFCConsoleView_ = dynamic_cast<CMFCConsoleView*>(
				GetActiveView());
	}

	// Send the message to the view, if possible, else whine about it
	if (pMFCConsoleView_) {
		pMFCConsoleView_->OutputMessage(pcMessage);
	}
	else {
		MessageBox("Hey, couldn't output a message!", "MFC Console");
	}
}


////////////////////////////////////////////////////////////////////////
// CMainFrame message handlers

BOOL CMainFrame::PreCreateWindow(CREATESTRUCT& cs)
{
	cs.style &= ~FWS_ADDTOTITLE;
	cs.style &= ~WS_THICKFRAME;
	return CFrameWnd::PreCreateWindow(cs);
}

void CMainFrame::OnClose() 
{
	// Pass close message on to the framework only if the network 
	// connection can be cleanly closed.
	CNetworkDriver* pWnd = 
			dynamic_cast<CMFCConsoleApp*>(::AfxGetApp())->GetConn();
	if ((pWnd == 0) || !pWnd->ServiceStarted()) {
		// Conn's down, so let the window close.
		CFrameWnd::OnClose();
	}
	else if (pWnd->Stop(this, gnCloseNotifyMsg)) {
		// Conn was up, and we were able to disconnect immediately, so
		// let the window close.
		CFrameWnd::OnClose();
	}
}

LRESULT CMainFrame::OnCloseNotify(WPARAM, LPARAM)
{
	OnClose();

	return 0;
}
