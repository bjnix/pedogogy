/***********************************************************************
 rascheck.cpp - Check whether there is a RAS connection (Internet via
    modem) on this machine, and if so whether it's up or not.

 Original code by "Elmue", reformatted by Warren Young.

 Build command: cl /GX rascheck.cpp
***********************************************************************/

#define WIN32_LEAN_AND_MEAN
#include <windows.h>

#include <iostream>

#include "rascheck.h"

using namespace std;


RasCheck::defConnStat RasCheck::pRasGetConnectStatus = 0;
RasCheck::defEnumConn RasCheck::pRasEnumConnections = 0;


// Loads RasAPI32.DLL and gets Entrypoints.
// creates public functions pRasGetConnectStatus and pRasEnumConnections

void
RasCheck::Init()
{
    HINSTANCE hRasLib = LoadLibrary("RASAPI32.DLL");
    if (hRasLib != 0) {
        pRasGetConnectStatus =
                (defConnStat)GetProcAddress(hRasLib,
                "RasGetConnectStatusA");
        pRasEnumConnections =
                (defEnumConn)GetProcAddress(hRasLib,
                "RasEnumConnectionsA");
    }
}


// Returns the state of the modem connection.  May return an error code if
// there is no RAS subsystem on this machine or something goes wrong
// while checking for the connection.

RasCheck::ConnectionState
RasCheck::Check()
{
    if ((pRasGetConnectStatus == 0) || (pRasEnumConnections == 0)) {
        return kNoRas;
    }

    DWORD nConnCount = 0;
    DWORD nBufSize = sizeof(RASCONN);
    LPRASCONN pRasBuf = (LPRASCONN)GlobalAlloc(GPTR, nBufSize);
    pRasBuf->dwSize = nBufSize;
    LPRASCONNSTATUS pStat = 0;

    ConnectionState eRetVal = kDisconnected;
    
    if (pRasEnumConnections(pRasBuf, &nBufSize, &nConnCount) == 0) {
        // Got the list of RAS links, which may be empty.  Try to find one
        // that's currently connected.
        pStat = (LPRASCONNSTATUS)GlobalAlloc(GPTR,
                sizeof(RASCONNSTATUS));

        for (DWORD i = 0; i < nConnCount; ++i, ++pRasBuf) {
            pStat->dwSize = sizeof(RASCONNSTATUS);
            if (pRasGetConnectStatus(pRasBuf->hrasconn, pStat) == 0) {
                if ((pStat->dwError == 0) && 
                        (pStat->rasconnstate == RASCS_Connected)) {
                    eRetVal = kConnected;
                    break;
                }
            }
        }
    }
    else {
        eRetVal = kError;
    }

    GlobalFree(pStat);
    GlobalFree(pRasBuf);
    return eRetVal;
}


#if 1       // change to 0 to compile as part of another program

int
main()
{
    RasCheck ras;
    ras.Init();

    switch (ras.Check()) {
        case RasCheck::kConnected:
            cout << "RAS connection is up." << endl;
            break;
            
        case RasCheck::kDisconnected:
            cout << "RAS connection is down." << endl;
            break;
            
        case RasCheck::kNoRas:
            cout << "No RAS present on this machine." << endl;
            break;
            
        case RasCheck::kError:
            cout << "Failure while checking RAS connections." << endl;
            break;
    }
    
    return 0;
}

#endif

