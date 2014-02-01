/*
 * types.c
 *
 *  Created on: Jul 13, 2009
 *      Author: carr
 */

#include <string.h>
#include <codegen/types.h>
#include <util/symtab.h>
#include <codegen/symfields.h>

/**
 * Return true if a type is an array, false otherwise.
 *
 * @param symtab a symbol table
 * @param typeIndex a symbol table index for a type
 * @return see above
 */
bool isArrayType(SymTable symtab, int typeIndex) {
	char *typeString = SymGetFieldByIndex(symtab,typeIndex,SYM_NAME_FIELD);

	return (bool)(strchr(typeString,'[') != NULL);
}

/**
 * Return the base of an one-dimensional array.
 *
 * @param symtab a symbol table
 * @param typeIndex a symbol table index for a type
 * @return see above
 */
int getArrayBase(SymTable symtab, int typeIndex) {
        char *typeString = SymGetFieldByIndex(symtab,typeIndex,SYM_NAME_FIELD);
        char *start = ssave(strchr(typeString,'['));
        char *end = strchr(start, '.');
        *end = '\0';
        return(atoi(start+1));
}
