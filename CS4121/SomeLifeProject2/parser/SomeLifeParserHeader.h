#ifndef _SOMELIFEPARSERHEDER_H_
#define _SOMELIFEPARSERHEDER_H_
#include <util/general.h>
#include <util/symtab.h>
#include <util/symtab_stack.h>
#include <util/dlink.h>
#include <codegen/codegen.h>

typedef union token_type {
	char*	name;
	int     symIndex;
	DList	idList;
	int 	offset;
} *TokenAttributePtr, TokenAttribute;

TokenAttributePtr tokenAttr;

extern void* slParser;
extern SymTable symtab;
extern DList instList;
extern DList dataList;
extern SymtabStack symtabStack;
extern int offsetDirection;
extern int globalOffset;
extern char* functionName;
extern int SomeLife_lineno;


EXTERN(void, SomeLife_Parse, (void *yyp, int yymajor, TokenAttributePtr yyminor));
EXTERN(void,*SomeLife_ParseAlloc, (void *(*mallocProc)(size_t)));
EXTERN(int, SomeLife_lex, (void));
EXTERN(void, SomeLife_ParseFree, (void *p, void (*freeProc)(void*)));
EXTERN(void,SomeLife_error,(char*));
EXTERN(SymTable, addSymTable,(void));
EXTERN(void, deleteSymTable,(void));


#endif
