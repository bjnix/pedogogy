/***********************************************************************
 ConsoleStatusReporter.h - Declares a status reporter class that 
	understands how to send messages to MFCConsole's console window.
***********************************************************************/

#if !defined(CONSOLESTATUSREPORTER_H)
#define CONSOLESTATUSREPORTER_H

// For our parent class
#include "WindowsStatusReporter.h"

class ConsoleStatusReporter : 
public WindowsStatusReporter 
{
public:
	void DisplayStatusMessage(const char* pcMsg,
			DebugLevels::DebugLevel eLevel, const char* pcFile,
			int nLine);

	static StatusReporter* GetInstance();
};

#endif // !defined(CONSOLESTATUSREPORTER_H)
