#if !defined(ABOUTDLG_H)
#define ABOUTDLG_H

#include "resource.h"

class CAboutDlg : public CDialog {
public:
	CAboutDlg();

protected:
	//// Dialog Data
	//{{AFX_DATA(CAboutDlg)
	enum { IDD = IDD_ABOUTBOX };
	//}}AFX_DATA

	//// Overrides
	//{{AFX_VIRTUAL(CAboutDlg)
	virtual void DoDataExchange(CDataExchange* pDX);
	//}}AFX_VIRTUAL

	//// Message map
	//{{AFX_MSG(CAboutDlg)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP();
};

#endif // !defined(ABOUTDLG_H)
