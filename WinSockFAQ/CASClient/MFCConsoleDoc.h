// MFCConsoleDoc.h : Manage the list of 

#if !defined(AFX_MFCConsoleDOC_H__AA4699E9_4FEB_11D1_B5CF_0020AFE30C22__INCLUDED_)
#define AFX_MFCConsoleDOC_H__AA4699E9_4FEB_11D1_B5CF_0020AFE30C22__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000


class CMFCConsoleDoc : 
public CDocument 
{
public:
	//// Dtor
	virtual ~CMFCConsoleDoc();
	
protected:
	//// Protected ctor so we create from serialization only
	CMFCConsoleDoc();
	DECLARE_DYNCREATE(CMFCConsoleDoc);

	//// Overrides
	//{{AFX_VIRTUAL(CMFCConsoleDoc)
	//}}AFX_VIRTUAL

	//// Message map
	//{{AFX_MSG(CMFCConsoleDoc)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP();
};

//{{AFX_INSERT_LOCATION}}

#endif // !defined(AFX_MFCConsoleDOC_H__AA4699E9_4FEB_11D1_B5CF_0020AFE30C22__INCLUDED_)
