/***********************************************************************
 MFCConsoleApp.cpp - The MFCConsole application core.  Starts up the
	UI and contains core functionality.

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
	
 Copyright (c) 1996-2000 by ETR..., Inc.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "stdafx.h"
#include "MFCConsoleApp.h"

#include "MainFrm.h"
#include "MFCConsoleDoc.h"
#include "MFCConsoleView.h"

#include "AboutDlg.h"
#include "AddrPortDlg.h"

#include "NetworkDriver.h"

#include "ConsoleStatusReporter.h"


BEGIN_MESSAGE_MAP(CMFCConsoleApp, CWinApp)
	//{{AFX_MSG_MAP(CMFCConsoleApp)
	ON_COMMAND(ID_APP_ABOUT, OnAppAbout)
	ON_COMMAND(ID_PROGRAM_DO_WINSOCK, OnDoWinsock)
	ON_UPDATE_COMMAND_UI(ID_PROGRAM_DO_WINSOCK, OnUpdateDoWinsock)
	ON_COMMAND(ID_PROGRAM_ACTION, OnAction)
	ON_UPDATE_COMMAND_UI(ID_PROGRAM_ACTION, OnProgramAction)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP();


////////////////////////////////////////////////////////////////////////
// Prototypes 

extern CNetworkDriver* DoWinsock();


////////////////////////////////////////////////////////////////////////
// Constants 

// Default port to connect to on the server
const int kDefaultServerPort = 4242;


////////////////////////////////////////////////////////////////////////
// Globals

CMFCConsoleApp theApp;


////////////////////////////////////////////////////////////////////////
// Ctor

CMFCConsoleApp::CMFCConsoleApp() :
CWinApp(),
pNetworkDriver_(0)
{
}

CMFCConsoleApp::~CMFCConsoleApp() 
{
}


//// InitInstance ///////////////////////////////////////////////////////
// CMFCConsoleApp initialization

BOOL CMFCConsoleApp::InitInstance()
{
	ConsoleStatusReporter::GetInstance();

	// Register document templates
	CSingleDocTemplate* pDocTemplate;
	pDocTemplate = new CSingleDocTemplate(
			IDR_MAINFRAME,
			RUNTIME_CLASS(CMFCConsoleDoc),
			RUNTIME_CLASS(CMainFrame),
			RUNTIME_CLASS(CMFCConsoleView));
	AddDocTemplate(pDocTemplate);

	// Pull up the main window
	OnFileNew();
	if (m_pMainWnd != 0) {
		m_pMainWnd->ShowWindow(SW_SHOW);
		m_pMainWnd->UpdateWindow();
		REPORT_NORMAL_STATUS("Hello, world!");
	}
	else {
		return FALSE;
	}

    // Start Winsock up
    WSAData wsaData;
    int nCode;
    if ((nCode = WSAStartup(MAKEWORD(1, 1), &wsaData)) != 0) {
        REPORT_FATAL_ERROR("WSAStartup() returned error code " << 
				nCode << ".");
    }

	return TRUE;
}


//// ExitInstance //////////////////////////////////////////////////////
// App is shutting down.  Do last-minute cleanup stuff.

int CMFCConsoleApp::ExitInstance() 
{
	WSACleanup();

	return CWinApp::ExitInstance();
}


/////////////////////////////////////////////////////////////////////////////
// CMFCConsoleApp commands

void CMFCConsoleApp::OnAppAbout()
{
	CAboutDlg dlg;
	dlg.DoModal();
}

void CMFCConsoleApp::OnUpdateDoWinsock(CCmdUI* pCmdUI) 
{
	pCmdUI->Enable(pNetworkDriver_ == 0 || 
			!pNetworkDriver_->ServiceStarted());
}

void CMFCConsoleApp::OnDoWinsock() 
{	
	CAddrPortDlg dlg;
	dlg.sPort_.Format("%d", kDefaultServerPort);

	if (dlg.DoModal() == IDOK) {
		REPORT_NORMAL_STATUS("You asked to connect to address " <<
				(const char*)dlg.sAddress_ << ", port " << 
				(const char*)dlg.sPort_ << ".");
		if (pNetworkDriver_ == 0) {
			pNetworkDriver_ = DoWinsock();
		}
		if (!pNetworkDriver_->Start(dlg.sAddress_, atoi(dlg.sPort_))) {
			REPORT_PROBLEM("Winsock object is ignoring Start "
					"requests right now. It's either busy or buggy.");
		}
	}
}

void CMFCConsoleApp::OnAction() 
{
	pNetworkDriver_->Action();
}

void CMFCConsoleApp::OnProgramAction(CCmdUI* pCmdUI) 
{
	if (pNetworkDriver_) {
		CString sActionName = pNetworkDriver_->GetActionName();
		sActionName += "\tCtrl-A";
		pCmdUI->Enable(pNetworkDriver_->ServiceStarted());
		pCmdUI->SetText(sActionName);
	}
	else {
		pCmdUI->Enable(false);
		pCmdUI->SetText("Unknown action");
	}
}
