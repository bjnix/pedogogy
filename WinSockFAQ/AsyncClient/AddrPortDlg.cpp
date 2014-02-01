/***********************************************************************
 AddrPortDlg.cpp - The address/port input dialog.  No functionality
	other than basic MFC DDX/DDV stuff.

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
	
 Copyright (c) 1996-2000 by ETR..., Inc.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "stdafx.h"
#include "AddrPortDlg.h"


BEGIN_MESSAGE_MAP(CAddrPortDlg, CDialog)
	//{{AFX_MSG_MAP(CAddrPortDlg)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP();


////////////////////////////////////////////////////////////////////////
// Ctor

CAddrPortDlg::CAddrPortDlg(CWnd* pParent) : 
CDialog(CAddrPortDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CAddrPortDlg)
	sAddress_ = _T("");
	sPort_ = _T("");
	//}}AFX_DATA_INIT
}


//// DoDataExchange ////////////////////////////////////////////////////
// DDV/DDX stuff.

void CAddrPortDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CAddrPortDlg)
	DDX_Text(pDX, IDC_ADDRESS_EDIT, sAddress_);
	DDV_MaxChars(pDX, sAddress_, 100);
	DDX_Text(pDX, IDC_PORT_EDIT, sPort_);
	DDV_MaxChars(pDX, sPort_, 14);
	//}}AFX_DATA_MAP
}

