/***********************************************************************
 WindowsStatusReporter.cpp - Defines the WindowsStatusReporter class.  
 	It provides some skeleton code for a generic Windows-based 
	StatusReporter derivative.

 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
	
 Copyright (c) 1996-2000 by ETR..., Inc.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "stdafx.h"

#include "WindowsStatusReporter.h"
#include "Headsman.h"

using namespace std;
using namespace DebugLevels;


////////////////////////////////////////////////////////////////////////
// Imports

// Pointer to the singleton -- also handles destroying the singleton
// when the program goes away.
extern Headsman<StatusReporter> gInstance_;


//// GetInstance ///////////////////////////////////////////////////////
// Returns a pointer to the current instance of this Singleton, creating
// it if necessary.

StatusReporter* WindowsStatusReporter::GetInstance()
{
	if (gInstance_.Get() == 0) {
		gInstance_.Set(new WindowsStatusReporter());
	}
	return gInstance_.Get();
}


//// ReportStatus //////////////////////////////////////////////////////
// Handles the grunt work of loading status messages as string
// resources for subclasses.  It passes the handling of non-resource
// messages and actually displaying these messages off to the subclass.

StatusReporter::ErrorCode WindowsStatusReporter::ReportStatus(
		int nStatusCode, const char* pcFile, int nLine)
{
	// Give the superclass first chance to handle the status.  Really,
	// the superclass doesn't do much other than consult our subclass
	// through the GetStatusMessage template function.
	StatusReporter::ErrorCode eErrorCode = StatusReporter::ReportStatus(
			nStatusCode, pcFile, nLine);
	if (eErrorCode == StatusReporter::kReportFailed) {
		DebugLevel eDebugLevel = InferDebugLevel(nStatusCode);
		ostream& os = GetStream();

		// Our subclass has no special input, so try the default case:
		// loading the status message as a string resource.
		CString sTemp;
		if (sTemp.LoadString(nStatusCode)) {
			os << sTemp;
		}
		else {
			// If we get in here, some hoser probably passed us a bad
			// status code.  Ergo, the program's broken, and a "Duh, I
			// don't know" message is quite appropriate.
			os << "Error #" << GetLastError() << " while loading "
					"status message #" << nStatusCode << ". Please "
					"call technical support.";
			eDebugLevel = kFatalError;
		}

		// By now we have a textual status message of some sort.  So,
		// send it to the next handling stage.
		eErrorCode = ReportStatus(os, eDebugLevel, pcFile, nLine);
	}

    return eErrorCode;
}


//// GetStatusMessage //////////////////////////////////////////////////
// Just returns false to indicate the default behavior: no special
// status message loading performed.  We override StatusReporter's
// stupid version of this function because the default behavior is more
// useful to us.

bool WindowsStatusReporter::GetStatusMessage(ostream&, int)
{
	return false;
}

