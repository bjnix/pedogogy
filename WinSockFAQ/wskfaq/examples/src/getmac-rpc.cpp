// Visual C++ 5.0:  cl -GX getmac-rpc.cpp rpcrt4.lib
// Borland C++ 5.0: bcc32 getmac-rpc.cpp
//
// This sample program is hereby placed in the public domain.

#include <rpc.h>
#include <iostream>

#ifdef _MSC_VER
using namespace std;
#endif

int main()
{
    cout << "MAC address is: ";

    // Ask RPC to create a UUID for us.  If this machine has an Ethernet
    // adapter, the last six bytes of the UUID (bytes 2-7 inclusive in
    // the Data4 element) should be the MAC address of the local
    // Ethernet adapter.
    UUID uuid;
    UuidCreate(&uuid);

    // Spit the address out
    for (int i = 2; i < 8; ++i) {
        cout << hex;
        cout.fill('0');
        cout.width(2);
        cout << int (uuid.Data4[i]);
        if (i < 7) {
            cout << ":";
        }
    }
    cout << endl;

    return 0;
}
