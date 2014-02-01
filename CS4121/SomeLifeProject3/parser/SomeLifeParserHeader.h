#ifndef _SOMELIFEPARSERHEDER_H_
#define _SOMELIFEPARSERHEDER_H_
#include <util/general.h>
#include <util/symtab.h>
#include <util/symtab_stack.h>
#include <util/dlink.h>
#include <codegen/codegen.h>
#include <codegen/types.h>

typedef struct dim_struct {
        int lowerBoundIndex;    /**< the symbol table index of the lower bound of an array dimension */
        int upperBoundIndex;    /**< the symbol table index of the upper bound of an array dimension */
} ArrayBounds;

typedef union token_type {
	char*	name;
	int     symIndex;
	DList	idList;
	int 	offset;
	ArrayBounds bounds;
} *TokenAttributePtr, TokenAttribute;

TokenAttributePtr tokenAttr;

extern void* slParser;
extern SymTable symtab;
extern DList instList;
extern DList dataList;
extern SymtabStack symtabStack;
extern int globalOffset;
extern int SomeLife_lineno;


EXTERN(void, SomeLife_Parse, (void *yyp, int yymajor, TokenAttributePtr yyminor));
EXTERN(void,*SomeLife_ParseAlloc, (void *(*mallocProc)(size_t)));
EXTERN(int, SomeLife_lex, (void));
EXTERN(void, SomeLife_ParseFree, (void *p, void (*freeProc)(void*)));
EXTERN(void,SomeLife_error,(char*));
EXTERN(SymTable, addSymTable,(void));
EXTERN(void, deleteSymTable,(void));


#endif
