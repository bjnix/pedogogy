#if !defined(RASCHECK_H)
#define RASCHECK_H

#include <ras.h>

#include <string>

class RasCheck 
{
public:
	enum ConnectionState {
		kConnected,
		kDisconnected,
		kNoRas,
		kError
	};
		
	static void Init();
	ConnectionState Check();

private:
	typedef DWORD(WINAPI * defConnStat) (HRASCONN, LPRASCONNSTATUS);
	typedef DWORD(WINAPI * defEnumConn) (LPRASCONN, LPDWORD, LPDWORD);

	static defConnStat pRasGetConnectStatus;
	static defEnumConn pRasEnumConnections;
};

#endif							// !defined(RASCHECK_H)
