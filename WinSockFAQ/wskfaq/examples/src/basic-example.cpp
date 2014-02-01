#include <iostream.h>
#include <winsock.h>

int doit(int, char**)
{
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

