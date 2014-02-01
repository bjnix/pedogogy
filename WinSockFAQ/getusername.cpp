// Borland C++ 5.0: bcc32.cpp getusername.cpp
// Visual C++ 5.0: cl getusername.cpp advapi32.lib 
//
// This sample program is hereby placed in the public domain.

#include <iostream.h>
#include <windows.h>

int main()
{
    char acUserName[100];
    DWORD nUserName = sizeof(acUserName);
    if (GetUserName(acUserName, &nUserName)) {
        cout << "User name is " << acUserName << "." << endl;
    }
    else {
        cerr << "Failed to lookup user name, error code " <<
                GetLastError() << "." << endl;
    }

    return 0;
}
