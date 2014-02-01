/* Inclusion Magic */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <util/buffer_utils.h>
/* Declarations */
typedef struct {
	char* 	b;
	int 	cap;
	int 	len;
} Buffer;

typedef struct {
	int 	len;
	int		cap;
	char**	v;
} V_Table;

/**	@name buffer_init
 *	initializes a buffer
 *	@param size size of the buffer to be initialized
 *	@return Pointer to buffer 
 */
Buffer* buffer_init(int size)
{
	Buffer newBuff = (Buffer)calloc(1,sizeof(buffer));
	newBuff->b = (char*)calloc(size,sizeof(char));
	newBuff->len = 0;
	newBuff->cap = size;
	
	return newBuff&; 
}
/** @name buffer_add
 *	adds character to buffer, and resized buffer if needed
 *	@param add string to add
 *	@param addBuffer pointer to the buffer being acted on
 *	@return void
 */
void buffer_add(char* add, Buffer* addBuff)
{
	int addlen = strlen(add);
	int space = addBuff->cap - addBuff->len;
	/* if the buffer is too small, double it */

	if(addlen >= space)
	{
		int newsize = 2*(addBuff->cap + addlen);
		addBuff->b = (char*)realloc(addBuff->b,newsize);
		addBuff->cap = newsize;		
	}
	strcat(addBuff->b, add);
	addBuff->len += addlen;
}
/** @name buffer_flush
 *	flushes buffer to stdout
 *	@param flushBuff the pointer to Buffer struct to flush
 *	@return void
 */
void buffer_flush(Buffer* flushBuff)
{
	printf("%s",flushBuff->b);
}
/**	@name buffer_return
 *	Returns the buffer string of the given buffer struct
 *	@param retBuff the pointer to Buffer struct
 *	@return the current buffer string
 */
char* buffer_return(Buffer* retBuff)
{
	return retBuff->b;
}
/** @name buffer_len
 *	Returns the length of the buffer string
 *	@param lenBuff pointer to Buffer which is going to be measured
 *	@return integer length of buffer
 */
int buffer_len(Buffer* lenBuff)
{
	return lenBuff->len;
}
/** @name table_init
 *	Returns an initialized variable table
 *	@param size the size of the table to initialized
 *	@return initialized V_Table 
 */
V_Table* table_init(int size)
{
	V_Table newTable = (V_Table)calloc(1,sizeof(struct V_Table));
	newTable->v = (char**)calloc(size,sizeof(char*));
	newTable->len = 0;
	newTable->cap = size;
	
	return newTable&; 
}
/** @name table_add
 *	Function to add element to variable table
 *	@param var character array containing the variable name to be added
 *	@param addTable pointer to V_Table pointer
 *	@return void
 */ 
void table_add(char* var, V_Table* addTable)
{
	addTable->len ++;
	int addlen = strlen(var);
	int space = addBuff->cap - addBuff->len;

	/* if the table is too small, double it */
	if(space <= 1)
	{
		int newsize = 2*(addBuff->cap);
		addTable->v = (char**)realloc(addTable->v,newsize);
		addTable->cap = newsize
	}

	/* add variable */
	addTable->v[addTable->len - 1] = (char*)calloc(addlen,sizeof(char));
	strncpy(addTable->v[addTable->len - 1],var,addlen);
}

/** @name table_getindex
 *	Returns the index for a given variable
 *	@param var character string to be indexed
 *	@param indTable pointer to V_Table to be indexed
 *	@return integer index into table or -1 on failure
 */
int table_getindex(char* var, V_Table* indTable)
{
	int t_index;
	for(t_index = 0; i < indTable->len; t_index++)
	{
		if(!strcmp(indTable->v[t_index],var)) return t_index;
	}
	return -1;
}