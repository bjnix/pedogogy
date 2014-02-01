// MFCConsoleView.h : Manages the console-like view window.

#if !defined(AFX_MFCConsoleVIEW_H__7637CA2B_4EEE_11D1_B5CF_0020AFE30C22__INCLUDED_)
#define AFX_MFCConsoleVIEW_H__7637CA2B_4EEE_11D1_B5CF_0020AFE30C22__INCLUDED_

#pragma once

// For our dialog ID
#include "resource.h"

class CMFCConsoleView : 
public CFormView 
{
public:
	//// Dtor
	virtual ~CMFCConsoleView();

	//// Public interface
	// Send a message to the output console.
	void OutputMessage(const char* pcMessage);
	
protected: 
	//// Protected ctor so we only create from serialization
	CMFCConsoleView();
	DECLARE_DYNCREATE(CMFCConsoleView);

	//// Internal support functions
	void RecalcExtent();

	//// View data
	//{{AFX_DATA(CMFCConsoleView)
	enum { IDD = IDD_MAIN_FORM };
	//}}AFX_DATA

	//// Internal overrides
	//{{AFX_VIRTUAL(CMFCConsoleView)
	virtual void OnInitialUpdate();
	//}}AFX_VIRTUAL

	//// Event handlers
	//{{AFX_MSG(CMFCConsoleView)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP();

private:
	//// Internal data
	CListBox* pOutputList_;
	CFont ListFont_;
};

//{{AFX_INSERT_LOCATION}}

#endif // !defined(AFX_MFCConsoleVIEW_H__7637CA2B_4EEE_11D1_B5CF_0020AFE30C22__INCLUDED_)
