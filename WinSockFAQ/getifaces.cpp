// Borland C++ 5.0: bcc32.cpp getifaces.cpp ws2_32.lib
// Visual C++ 5.0: cl getifaces.cpp ws2_32.lib 
//
// This sample program is hereby placed in the public domain.

#include <iostream.h>
#include <winsock2.h>
#include <ws2tcpip.h>

int doit()
{
    SOCKET sd = WSASocket(AF_INET, SOCK_DGRAM, 0, 0, 0, 0);
    if (sd == SOCKET_ERROR) {
        cerr << "Failed to get a socket. Error " << WSAGetLastError() <<
            endl; return 1;
    }

    INTERFACE_INFO InterfaceList[20];
    unsigned long nBytesReturned;
    if (WSAIoctl(sd, SIO_GET_INTERFACE_LIST, 0, 0, &InterfaceList,
			sizeof(InterfaceList), &nBytesReturned, 0, 0) == SOCKET_ERROR) {
        cerr << "Failed calling WSAIoctl: error " << WSAGetLastError() <<
				endl;
		return 1;
    }

    int nNumInterfaces = nBytesReturned / sizeof(INTERFACE_INFO);
    cout << "There are " << nNumInterfaces << " interfaces:" << endl;
    for (int i = 0; i < nNumInterfaces; ++i) {
        cout << endl;

        sockaddr_in *pAddress;
        pAddress = (sockaddr_in *) & (InterfaceList[i].iiAddress);
        cout << " " << inet_ntoa(pAddress->sin_addr);

        pAddress = (sockaddr_in *) & (InterfaceList[i].iiBroadcastAddress);
        cout << " has bcast " << inet_ntoa(pAddress->sin_addr);

        pAddress = (sockaddr_in *) & (InterfaceList[i].iiNetmask);
        cout << " and netmask " << inet_ntoa(pAddress->sin_addr) << endl;

        cout << " Iface is ";
        u_long nFlags = InterfaceList[i].iiFlags;
        if (nFlags & IFF_UP) cout << "up";
        else                 cout << "down";
        if (nFlags & IFF_POINTTOPOINT) cout << ", is point-to-point";
        if (nFlags & IFF_LOOPBACK)     cout << ", is a loopback iface";
        cout << ", and can do: ";
        if (nFlags & IFF_BROADCAST) cout << "bcast ";
        if (nFlags & IFF_MULTICAST) cout << "multicast ";
        cout << endl;
    }

    return 0;
}

int main()
{
    WSADATA WinsockData;
    if (WSAStartup(MAKEWORD(2, 2), &WinsockData) != 0) {
        cerr << "Failed to find Winsock 2.2!" << endl;
        return 2;
    }

    int nRetVal = doit();

    WSACleanup();

    return nRetVal;
}

