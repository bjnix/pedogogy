/***********************************************************************
 StatusReporter.h - Declares the StatusReporter class, which provides a
	 standardized way for an apps to report status and error messages.
***********************************************************************/

//----------------------------------------------------------------------
// For some bizarre reason, the compiler can forget the definitions of
// the REPORT... macros, but not the definition of the sentry, so these
// macros must go outside the sentries.
//----------------------------------------------------------------------

//// The preferred interfaces to ReportStatus(std::ostream&, ...)
// For status reports with an unspecified debug level -- it is assumed
// that an appropriate subclass can deduce the debug level from s.
#define REPORT_STATUS(s) \
		StatusReporter::GetInstance()->ReportStatus(s, __FILE__, \
        __LINE__)

// For reporting deep debugging info that is usually only put in the
// program temporarily.
#define REPORT_VERBOSE_STATUS(s) \
		StatusReporter::GetInstance()->ReportStatus( \
        StatusReporter::GetInstance()->GetStream() << s, \
		DebugLevels::kVerboseStatus, __FILE__, __LINE__)

// For reporting internal status of program, for always-present 
// debugging info.
#define REPORT_INTERNAL_STATUS(s) \
		StatusReporter::GetInstance()->ReportStatus( \
		StatusReporter::GetInstance()->GetStream() << s, \
		DebugLevels::kInternalStatus, __FILE__, __LINE__)

// For reporting status information intended for the user.
#define REPORT_NORMAL_STATUS(s) \
		StatusReporter::GetInstance()->ReportStatus( \
        StatusReporter::GetInstance()->GetStream() << s, \
		DebugLevels::kNormalStatus, __FILE__, __LINE__)

// For reporting nonfatal problems; the program should be able to
// satisfactorily resolve all issues reported at this level, even if
// it's just to guess at a course.
#define REPORT_PROBLEM(s) \
		StatusReporter::GetInstance()->ReportStatus( \
		StatusReporter::GetInstance()->GetStream() << s, \
		DebugLevels::kProblem, __FILE__, __LINE__)

// For scream-and-die situations: where the program knows that it must
// die, and wants to say something before it joins the choir invisible.
#define REPORT_FATAL_ERROR(s) \
		StatusReporter::GetInstance()->ReportStatus( \
        StatusReporter::GetInstance()->GetStream() << s, \
		DebugLevels::kFatalError, __FILE__, __LINE__)

// Simply end the program.  Someone down the line must handle a fatal
// error with an empty message as a request to quietly end the program.
#define END_PROGRAM() \
		StatusReporter::GetInstance()->ReportStatus( \
		StatusReporter::GetInstance()->GetStream() << ends, \
		DebugLevels::kFatalError, __FILE__, __LINE__)

#if !defined(STATUSREPORTER_H)
#define STATUSREPORTER_H

// The debug level stuff is out here in a namespace, because if we hide
// it in class StatusReporter, we have to access it with the scope
// resolution operator every time.  By contrast, namespaces allow us to
// remove the indirection when the code gets too wordy.
namespace DebugLevels {
	// Debug levels
	enum DebugLevel {
		// NOTE: The order and value of these constants _is_ important
		kUnspecified = 0,
		kFatalError,
		kProblem,
		kNormalStatus,		// default
		kInternalStatus,
		kVerboseStatus
	};
};

#include <ostream>

class StatusReporter 
{
public:
	//// Public types
	// Error pre-handler function pointer type.
	typedef bool (*StatusFnPtr)(DebugLevels::DebugLevel);

	// Error codes for ReportStatus() functions
	enum ErrorCode {
		kNoError,
		kIgnoreReport,
		kReportFailed,
		kDemoteErrorLevel
	};

	//// Public interface
	// Dtor
	virtual ~StatusReporter();

	// Sends streamable status reports to an appropriate outlet
	virtual ErrorCode ReportStatus(std::ostream& os,
			DebugLevels::DebugLevel eDebugLevel, const char* pcFile,
			int nLine);

    // Translates a status ID into a status message and an inferred
    // debug level.  Then, sends them on to ReportStatus(std::ostream&...).
    virtual ErrorCode ReportStatus(int nID, 
			const char* pcFile, int nLine);

	// Sets the error pre-handler function pointer.  The pre-handler
	// gets called before ReportStatus(std::ostream&, ...) begins handling
	// the error.  If the pre-handler returns false, ReportStatus bails
    // immediately -- this lets the pre-handler send the status report
	// to the bit bucket.  The pre-handler is passed the debug level on
    // the theory that it's probably the only useful bit of information
	// we can give it.
	//
	// Returns the previous pre-handler pointer.  It is good form for
	// clients to store this function pointer and call it when they've
	// had their chance at handling the status report.
	virtual StatusFnPtr SetErrorPreHandler(StatusFnPtr NewHandler)
	{
		StatusFnPtr OldHandler = ErrorPreHandler_;
		ErrorPreHandler_ = NewHandler;
		return OldHandler;
	}

	// Returns a buffered stream to hold a debug message as it is built.
	// This function is allowed to return a different value each time;
	// this allows for a pool of streams, so nested errors don't overrun
	// each other.
	std::ostream& GetStream();

	// Gives the subclass a chance to add newlines or similar to the
	// message before we display it.
	virtual const char* WrapMessage(const char* pcMessage, 
			DebugLevels::DebugLevel /* eDebugLevel */) 
	{
		return pcMessage;
	}

	// Returns the Singleton instance pointer
	static StatusReporter* GetInstance();

protected:
	//// Protected ctor, to enforce the class' Singleton nature.
	StatusReporter();

	//// Subclass interface -- the subclass overrides these to add
	//// behavior to this class.  See the discussion of Template
	//// Methods in Gamma et al. for more info on this mechanism.
	// Called by ReportStatus() when the status message is ready to be
	// displayed.
	virtual void DisplayStatusMessage(const char* pcStatusMessage,
			DebugLevels::DebugLevel eDebugLevel,
			const char* pcFile, int nLine) { }

	// Called by ReportStatus(int, ...) to allow the subclass to
	// override the default debug level logic.  For example, a subclass
	// might calculate the debug level as being the status code divided
	// by 1000.
	virtual DebugLevels::DebugLevel InferDebugLevel(int nStatusCode)
	{
		return DebugLevels::kNormalStatus;
	}

	// Given a status ID, look up its associated status message and
	// insert it into the stream.  Returns false if the lookup fails.
	virtual bool GetStatusMessage(std::ostream& os, int nID);

	// Given a stream reference, return a pointer to that buffer's
	// stream.
	virtual const char* GetBuffer(std::ostream& os);

	// Return the size of a stream buffer
	virtual int GetBufferSize();

	// Given a stream reference, mark it as unused and reset its stream
	// pointer to the beginning.  Called only by the internals of the
	// StatusReporter hierarchy when they're done with a stream.
	virtual void ReleaseStream(std::ostream& os);

	//// Internal data
	// Pointer to the current error pre-handler function
	StatusFnPtr ErrorPreHandler_;

	// Pointer to stream array used by GetStream method
	struct StreamInfo {
		std::ostream* pStream;
		bool bInUse;
	};
	StreamInfo* aStreamSet_;

	// Pointer to buffers used by the stream set
	char* pcStreamBufs_;
};

#endif // !defined(STATUSREPORTER_H)

