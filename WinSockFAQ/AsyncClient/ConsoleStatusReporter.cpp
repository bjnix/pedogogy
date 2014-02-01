/***********************************************************************
 ConsoleStatusReporter.cpp - A status reporter that understands 
	programs based on MFCConsole.  Basically, it sends completed status 
	reports to the console window using the main frame's OutputMessage 
	method.

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
	
 Copyright (c) 1996-2000 by ETR..., Inc.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "stdafx.h"
#include "ConsoleStatusReporter.h"
#include "Headsman.h"

#include "MainFrm.h"

#include <sstream>
#include <string>

using namespace DebugLevels;
using namespace std;


////////////////////////////////////////////////////////////////////////
// Imports

// Pointer to the singleton -- also handles destroying the singleton
// when the program goes away.
extern Headsman<StatusReporter> gInstance_;


//// DisplayStatusMessage //////////////////////////////////////////////
// Sends the status message on to the main window for actual display.

void ConsoleStatusReporter::DisplayStatusMessage(const char* pcMsg,
		DebugLevels::DebugLevel eLevel, const char* pcFile, int nLine)
{
	// Send the message on to the appropriate handler, based on its 
	// severity.
	if (eLevel == kFatalError) {
		// Say our piece in a message box and then die.
		AfxMessageBox(pcMsg, MB_OK | MB_ICONSTOP);
		if (AfxGetMainWnd() != 0) { 
			AfxGetMainWnd()->SendMessage(WM_CLOSE);
		}
	}
	else if (eLevel <= kNormalStatus) {
		static CMainFrame* pMainFrame = 0;
		if (pMainFrame == 0) {
			pMainFrame = dynamic_cast<CMainFrame*>(AfxGetMainWnd());
		}

		if (pMainFrame != 0) {
			// We have a pointer to the main frame window, so send the
			// message to it for display.
			pMainFrame->OutputMessage(pcMsg);
			if (eLevel == kProblem) {
				// Also beep, to make user aware of message
				MessageBeep(MB_ICONEXCLAMATION);
			}
		}
		else {
			// We couldn't get access to the main window, so tell user 
			// in a message box.
			AfxMessageBox(pcMsg);
		}
	}

	// Send verbose copy of the message to debug stream
	char acFilename[_MAX_FNAME];
	_splitpath(pcFile, 0, 0, acFilename, 0);
	ostringstream VerboseMessageStr;
	VerboseMessageStr << strupr(acFilename) << ", line " << nLine << 
			": " << pcMsg << endl << ends;
	OutputDebugString(VerboseMessageStr.str().c_str());
}


//// GetInstance ///////////////////////////////////////////////////////
// Set and return the singleton instance pointer.

StatusReporter* ConsoleStatusReporter::GetInstance()
{
	if (gInstance_.Get() == 0) {
		gInstance_.Set(new ConsoleStatusReporter());
	}
	return gInstance_.Get();
}
