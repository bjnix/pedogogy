/***********************************************************************
 WindowsStatusReporter.h - Declare the WindowsStatusReporter class, 
 	which defines a skeletal Windows-based status reporter.
***********************************************************************/

#if !defined(WINDOWSSTATUSREPORTER_H)
#define WINDOWSSTATUSREPORTER_H

// For our parent class
#include "StatusReporter.h"

class WindowsStatusReporter : 
public StatusReporter 
{
public:
	//// Public interface
	// Ctor
	WindowsStatusReporter() : StatusReporter() { }
    virtual ~WindowsStatusReporter() { }

	// Send status reports to an appropriate outlet
	virtual StatusReporter::ErrorCode ReportStatus(int nStatusCode,
			const char* pcFile, int nLine);
	virtual StatusReporter::ErrorCode ReportStatus(std::ostream& os,
			DebugLevels::DebugLevel eDebugLevel, const char* pcFile,
			int nLine)
	{
		return StatusReporter::ReportStatus(os, eDebugLevel, pcFile, 
				nLine);
	}

    // Returns the Singleton instance pointer
	static StatusReporter* GetInstance();

protected:
	//// Subclass interface
	// Given a status ID, look up its associated status message and
    // insert it into the stream.  Returns false if the lookup fails.
	bool GetStatusMessage(std::ostream& os, int nID);

	// Called by ReportStatus(int, ...) so we can calculate a debug
	// level for it given the status code.
	virtual DebugLevels::DebugLevel InferDebugLevel(int nStatusCode)
	{
		return DebugLevels::DebugLevel(nStatusCode / 1000);
	}
};

#endif // !defined(WINDOWSSTATUSREPORTER_H)
