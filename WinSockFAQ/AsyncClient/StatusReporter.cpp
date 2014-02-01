/***********************************************************************
 StatusReporter.cpp - Defines the base StatusReporter class, which 
	handles the basic policy decisions common to all status reporters.
	
 $Revision: 1.1 $ $Date: 2001/09/23 06:13:49 $
	
 Copyright (c) 1996-2000 by ETR..., Inc.  See the file LICENSE which 
 accompanied this software for license details.
***********************************************************************/

#include "StatusReporter.h"
#include "Headsman.h"

#include <strstream>

using namespace std;
using namespace DebugLevels;


////////////////////////////////////////////////////////////////////////
// Constants

const int kNumStreams = 8;
const int kStreamBufSize = 256;


////////////////////////////////////////////////////////////////////////
// Globals

// Pointer to the singleton -- also handles destroying the singleton
// when the program goes away.
Headsman<StatusReporter> gInstance_;


////////////////////////////////////////////////////////////////////////
// Ctor and dtor

StatusReporter::StatusReporter() :
ErrorPreHandler_(0)
{
	pcStreamBufs_ = new char[kNumStreams * kStreamBufSize];
    aStreamSet_ = new StreamInfo[kNumStreams];
    for (int i = 0; i < kNumStreams; i++) {
    	aStreamSet_[i].pStream = new ostrstream(
        		pcStreamBufs_ + (i * kStreamBufSize), kStreamBufSize);
        aStreamSet_[i].bInUse = false;
    }
}

StatusReporter::~StatusReporter()
{
	for (int i = 0; i < kNumStreams; i++) {
		delete aStreamSet_[i].pStream;
	}
	delete[] aStreamSet_;
	delete[] pcStreamBufs_;
}



//// GetInstance ///////////////////////////////////////////////////////
// Returns a pointer to the current instance of this Singleton, creating
// it if necessary.

StatusReporter* StatusReporter::GetInstance()
{
	if (gInstance_.Get() == 0) {
		gInstance_.Set(new StatusReporter());
	}
	return gInstance_.Get();
}


//// ReportStatus //////////////////////////////////////////////////////
// Handles the basic policies of status reporting.

StatusReporter::ErrorCode StatusReporter::ReportStatus(ostream& os,
		DebugLevel eDebugLevel, const char* pcFile, int nLine)
{
	static bool bPrevFatalError = false;
	ErrorCode eRetVal = kNoError;

	if (ErrorPreHandler_ != 0) {
		// Give error pre-handler a crack at the error
		if (ErrorPreHandler_(eDebugLevel) == false) {
			// Pre-handler wants us to ignore this error, so just 
			// quietly log it rather than shout it out.
			os << " (ignored level " << int(eDebugLevel) << " status)";
			eDebugLevel = kVerboseStatus;
			eRetVal = kIgnoreReport;
		}
	}

	if (eDebugLevel == kFatalError) {
		// Only give full credence to the first fatal error in order to
		// prevent "shutdown storms".
		if (bPrevFatalError == false) {
			bPrevFatalError = true;
		}
		else {
			os << " (demoted fatal error)";
			eDebugLevel = kVerboseStatus;
			eRetVal = kDemoteErrorLevel;
		}
	}

	// Almost ready to display it, so tack null on end, forcibly if need
	// be.
	const char* pcStatusMessage = GetBuffer(os);
	char* pcNewMessage = const_cast<char*>(pcStatusMessage);
	os << ends;
	pcNewMessage[GetBufferSize() - 1] = '\0';

	// Send the completed message on to the subclass, and then release
	// the resources we allocated for this message.
	pcStatusMessage = WrapMessage(pcNewMessage, eDebugLevel);
	DisplayStatusMessage(pcStatusMessage, eDebugLevel, pcFile, nLine);
	ReleaseStream(os);

    return eRetVal;
}

StatusReporter::ErrorCode StatusReporter::ReportStatus(int nID,
		const char* pcFile, int nLine)
{
	ostream& os = GetStream();
	if (GetStatusMessage(os, nID)) {
	    return ReportStatus(os, InferDebugLevel(nID), pcFile, nLine);
    }
    else {
		ReleaseStream(os);
		return kReportFailed;
	}
}


//// GetStatusMessage //////////////////////////////////////////////////
// Given a status ID, look its associated status message and insert it
// into the stream.

bool StatusReporter::GetStatusMessage(ostream& os, int nID)
{
	os << "Status code " << nID;
	return true;
}


//// GetStream /////////////////////////////////////////////////////////
// Returns the next available stream.  If none are available, preends
// a suitable whine message on the first stream and returns it.

ostream& StatusReporter::GetStream()
{
	for (int i = 0; i < kNumStreams; i++) {
		if (aStreamSet_[i].bInUse == false) {
			aStreamSet_[i].bInUse = true;
			return *(aStreamSet_[i].pStream);
		}
	}

	*(aStreamSet_[0].pStream) << "(Too many status streams were "
			"requested. Please call technical support.) ";
	return *(aStreamSet_[0].pStream);
}


//// ReleaseStream /////////////////////////////////////////////////////
// Returns the given stream to the pool of available streams, and
// resets its

void StatusReporter::ReleaseStream(ostream& os)
{
	for (int i = 0; i < kNumStreams; i++) {
		if (&os == aStreamSet_[i].pStream) {
			// Clear any error conditions (like EOF, meaning that the
			// buffer was maxed out), reset the stream pointer, and mark
			// the stream as unused.
			aStreamSet_[i].pStream->clear();
			aStreamSet_[i].pStream->seekp(0);
			aStreamSet_[i].bInUse = false;
			return;
		}
	}

	REPORT_FATAL_ERROR("Failed to return stream to pool!");
}


//// GetBuffer /////////////////////////////////////////////////////////
// Given a stream reference, return the buffer associated with it.

const char* StatusReporter::GetBuffer(ostream& os)
{
	for (int i = 0; i < kNumStreams; i++) {
		if (&os == aStreamSet_[i].pStream) {
			return pcStreamBufs_ + (i * kStreamBufSize);
		}
	}

	return 0;
}


//// GetBufferSize /////////////////////////////////////////////////////
// Return the size of a stream buffer

int StatusReporter::GetBufferSize()
{
	return kStreamBufSize;
}

