/***********************************************************************
 MFCConsoleView.cpp - Manages the console-like view: you can add 
	messages to it, and it will scroll itself just like a "DOS box"
	window.

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
	
 Copyright (c) 1996-2000 by ETR..., Inc.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "stdafx.h"
#include "MFCConsoleView.h"


IMPLEMENT_DYNCREATE(CMFCConsoleView, CFormView)

BEGIN_MESSAGE_MAP(CMFCConsoleView, CFormView)
	//{{AFX_MSG_MAP(CMFCConsoleView)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP();


////////////////////////////////////////////////////////////////////////
// Constants

// Fudge factor for horizontal scrolling.  See RecalcExtent() for info.
const int kScrollFudge = 5;


////////////////////////////////////////////////////////////////////////
// Ctor and dtor

CMFCConsoleView::CMFCConsoleView() :
CFormView(CMFCConsoleView::IDD),
pOutputList_(0)
{
	//{{AFX_DATA_INIT(CMFCConsoleView)
	//}}AFX_DATA_INIT
	ListFont_.CreatePointFont(80, "MS Sans Serif");
}

CMFCConsoleView::~CMFCConsoleView()
{
	if (pOutputList_) {
		pOutputList_->Detach();
		delete pOutputList_;
	}
}


//// OutputMessage /////////////////////////////////////////////////////
// Outputs the given message to the list control

void CMFCConsoleView::OutputMessage(const char* pcMessage)
{
	if (pOutputList_ == 0) {
		// We don't have a pointer to the list control yet, so get one
		HWND hList = ::GetDlgItem(m_hWnd, LIST_MAIN);
		if (hList) {
			pOutputList_ = new CListBox();
			pOutputList_->Attach(hList);
		}
		else {
			MessageBox("Bad joss, Taipan!\n\n"
					"Murphy has inflicted the console with impotence!",
					"MFC Console");
		}
	}

	if (pOutputList_) {
		// List control exists, so output the message and scroll it into
		// view.  Also, recalculate the width of the list and repaint.
		pOutputList_->AddString(pcMessage);
		pOutputList_->SetCurSel(pOutputList_->GetCount() - 1);
		RecalcExtent();
		pOutputList_->Invalidate(FALSE);
		pOutputList_->UpdateWindow();
	}
}


//// RecalcExtent //////////////////////////////////////////////////////
// Recalculates the horizontal extent of the list box and then tells the
// list box about it.  We need to do this because the list box control
// doesn't automatically recalculate when we insert long strings.

void CMFCConsoleView::RecalcExtent()
{
	int nLongestString = 0;
	CWindowDC dc(pOutputList_);
	dc.SelectObject(&ListFont_);
	CString sTemp;
	int nNumStrings = pOutputList_->GetCount();
	for (int i = 0; i < nNumStrings; ++i) {
		pOutputList_->GetText(i, sTemp);
		nLongestString = max(nLongestString, 
				dc.GetTextExtent(sTemp).cx);
	}
	pOutputList_->SetHorizontalExtent(nLongestString + kScrollFudge);
}


/////////////////////////////////////////////////////////////////////////////
// CMFCConsoleView message handlers

void CMFCConsoleView::OnInitialUpdate() 
{
	CFormView::OnInitialUpdate();
	
    GetParentFrame()->RecalcLayout();
	ResizeParentToFit(TRUE);
}

