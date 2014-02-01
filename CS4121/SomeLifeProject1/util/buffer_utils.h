#ifndef BUFFER_UTILS
#define	BUFFER_UTILS
#include <util/general.h>

EXTERN(Buffer*, buffer_init, (int size));
EXTERN(void, buffer_add, (char* add, Buffer* addBuff));
EXTERN(void, buffer_flush, (Buffer* flushBuff));
EXTERN(char*, buffer_return, (Buffer* retBuff));
EXTERN(int, buffer_len, (Buffer* lenBuff));
EXTERN(V_Table*, table_init, (int size));
EXTERN(void, table_add, (char* var, V_Table* addTable));
EXTERN(int, table_getindex, (char* var, V_Table* indTable));

#endif BUFFER_UTILS
