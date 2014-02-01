// rebind.cpp - A simple program to test a Winsock stack's bind()
//      behavior.  With a little work it could also be a "get web page"
//      or "get email" app.
//
// Compiling: cl rebind.cpp wsock32.lib
//            Add -DDEBUG to get some useful debugging output.
//            Add -DSKIP_BIND to skip the bind() step.
//            Add -DTIMEOUT=n to redefine the timeout value (in seconds,
//                  defaults to 3).
//            Add -DPOP_MODE to use POP3 instead of HTTP.
//
// Copyright (c) 1998 Warren Young.  This program is freeware.  Use
// it freely, but don't even think about trying to sue me if it 
// doesn't work for you or causes problems.  NO WARRANTY, yadda,
// yadda, yadda.

#include <iostream.h>
#include <winsock.h>

//#define LOCAL_HOST "172.16.0.42"
#define LOCAL_HOST "204.134.118.6"

#if !defined(TIMEOUT)
    #define TIMEOUT 3   // seconds to wait for reply
#endif

#if defined(POP_MODE)
    // POP3 protocol
    #define REMOTE_HOST "204.134.75.6"      // mail.cyberport.com
    #define HOST_PORT 110
    char* apcCommands[] = {
        "HELO randomclient\r\n",
        0
    };
#else 
    // HTTP 1.1 protocol
    #define REMOTE_HOST "204.134.75.2"      // www.cyberport.com
    //#define REMOTE_HOST "172.16.0.18"     // intranet host
    #define HOST_PORT 80
    char* apcCommands[] = {
        "GET / HTTP/1.1\r\n",
        "Host: " REMOTE_HOST "\r\n",
        "\r\n",
        0
    };
#endif

int doit(int, char**)
{
    #if defined(DEBUG)
    cerr << "In debug mode." << endl;
    #endif

    SOCKET sd = socket(AF_INET, SOCK_STREAM, 0);

    BOOL bAllowRebind = TRUE;
    if (setsockopt(sd, SOL_SOCKET, SO_REUSEADDR, (char*)&bAllowRebind,
            sizeof(BOOL)) == SOCKET_ERROR) {
        cerr << "Failed to set rebind option: " << WSAGetLastError() <<
                endl;
        return 1;
    }
    
    #if defined(SKIP_BIND)
        #if defined(DEBUG)
        cerr << "Skipped bind step." << endl;
        #endif
    #else
    sockaddr_in sinLocal;
    sinLocal.sin_family = AF_INET;
    sinLocal.sin_addr.s_addr = inet_addr(LOCAL_HOST);
    sinLocal.sin_port = htons(HOST_PORT);
    if (bind(sd, (sockaddr*)&sinLocal, sizeof(sockaddr_in)) == SOCKET_ERROR) {
        cerr << "bind failed: " << WSAGetLastError() << endl;
        return 1;
    }
    #endif

    #if defined(DEBUG)
    cerr << "Connecting to " REMOTE_HOST " on port " << HOST_PORT << endl;
    #endif
    sockaddr_in sinRemote;
    sinRemote.sin_family = AF_INET;
    sinRemote.sin_addr.s_addr = inet_addr(REMOTE_HOST);
    sinRemote.sin_port = htons(HOST_PORT);
    if (connect(sd, (sockaddr*)&sinRemote, sizeof(sockaddr_in)) ==
            SOCKET_ERROR) {
        cerr << "connect failed: " << WSAGetLastError() << endl;
        return 1;
    }
    #if defined(DEBUG)
    cerr << "Connected to " REMOTE_HOST << endl;
    #endif

    for (int i = 0; apcCommands[i] != 0; ++i) {
        if (send(sd, apcCommands[i], strlen(apcCommands[i]), 0) ==
                SOCKET_ERROR) {
            cerr << "recv failed: " << WSAGetLastError() << endl;
            return 1;
        }
        #if defined(DEBUG)
        else {
            cerr << "Sent: " << apcCommands[i];
        }
        #endif
    }

    fd_set ReadFDs, TempReadFDs;
    FD_ZERO(&ReadFDs);
    FD_SET(sd, &ReadFDs);
    timeval Timeout = { TIMEOUT, 0 };
    
    char acBuffer[200];
    int nBytes;
    while (1) {
        memcpy(&TempReadFDs, &ReadFDs, sizeof(fd_set));
        if (select(sd, &TempReadFDs, 0, 0, &Timeout) == 0) {
            #if defined(DEBUG)
            cerr << "Timed out waiting for data." << endl;
            #endif
            break;
        }
    
        nBytes = recv(sd, acBuffer, 200, 0);
        if (nBytes == SOCKET_ERROR) {
            cerr << "recv failed: " << WSAGetLastError() << endl;
            return 1;
        }
        else if (nBytes == 0) {
            #if defined(DEBUG)
            cerr << "Connection closed by peer." << endl;
            #endif
            break;
        }
        acBuffer[min(nBytes, sizeof(acBuffer) - 1)] = 0;
        cerr << "==> Received " << nBytes << " bytes:" << endl;
        cout << acBuffer << flush;
    }

    // Sleep(5000);
    
    shutdown(sd, 1);
    if (closesocket(sd) == SOCKET_ERROR) {
        cerr << "closesocket failed: " << WSAGetLastError() << endl;
        return 1;
    }

    return 0;
}


int main(int argc, char* argv[])
{
    WSAData wsaData;
    if (WSAStartup(MAKEWORD(1, 1), &wsaData) != 0) {
        return 255;
    }

    int retval = doit(argc, argv);

    WSACleanup();
    return retval;
}

