/***********************************************************************
 MFCConsoleDoc.cpp : Pro forma document class to make MFC happy.  Since
 	all the app's "data" is stored in the view's list control, there is 
	no need for a document.  Just try telling that to MFC by using a
	bare CDocument instead of this class.  Grrrr......

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
	
 Copyright (c) 1996-2000 by ETR..., Inc.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "stdafx.h"
#include "MFCConsoleDoc.h"


IMPLEMENT_DYNCREATE(CMFCConsoleDoc, CDocument)


BEGIN_MESSAGE_MAP(CMFCConsoleDoc, CDocument)
	//{{AFX_MSG_MAP(CMFCConsoleDoc)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP();


////////////////////////////////////////////////////////////////////////
// Ctor and dtor

CMFCConsoleDoc::CMFCConsoleDoc()
{
}

CMFCConsoleDoc::~CMFCConsoleDoc()
{
}
