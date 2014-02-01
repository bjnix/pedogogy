// AddrPortDlg.h - Defines the address/port input dialog

#if !defined(AFX_ADDRPORTDLG_H__1D8777C2_5075_11D4_86BD_00600895D2A4__INCLUDED_)
#define AFX_ADDRPORTDLG_H__1D8777C2_5075_11D4_86BD_00600895D2A4__INCLUDED_

#pragma once

#include "resource.h"

class CAddrPortDlg : 
public CDialog
{
public:
	//// Public interface
	// Ctor
	CAddrPortDlg(CWnd* pParent = 0);

	//// Dialog Data
	//{{AFX_DATA(CAddrPortDlg)
	enum { IDD = IDD_ADDR_PORT };
	CString	sAddress_;
	CString	sPort_;
	//}}AFX_DATA

protected:
	//// Overrides
	//{{AFX_VIRTUAL(CAddrPortDlg)
	virtual void DoDataExchange(CDataExchange* pDX);
	//}}AFX_VIRTUAL

	//// Message map 
	//{{AFX_MSG(CAddrPortDlg)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP();
};

#endif // !defined(AFX_ADDRPORTDLG_H__1D8777C2_5075_11D4_86BD_00600895D2A4__INCLUDED_)
