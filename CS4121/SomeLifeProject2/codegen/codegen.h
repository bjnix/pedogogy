#ifndef CODEGEN_H_
#define CODEGEN_H_

#include <util/symtab.h>

#define SYSCALL_PRINT_INTEGER "1"	/**< The syscall code for printing an integer */
#define SYSCALL_PRINT_STRING "4"	/**< The syscall code for printing a string */
#define SYSCALL_READ_INTEGER "5"	/**< The syscall code for reading an integer */
#define SYSCALL_EXIT "10"			/**< The syscall code for exiting the interpreter */

/**
 * Data needed for adding variable to the symbol table.
 */
typedef struct AddIdType {
	SymTable idSymtab;		/**< the symbol table for the identifiers*/
	int offset;			/**< the current offset in the activation record */
} AddIdStruct, *AddIdStructPtr;

EXTERN(void, emitInstructions,(DList list));
EXTERN(void, emitProcedurePrologue,(DList instList,SymTable symtab, int index, int frameSize));
EXTERN(void, emitProcedureEpilogue,(DList instList,int offset));

EXTERN(void, emitAssignment, (DList instList,SymTable symtab,int lhsRegIndex, int rhsRegIndex));
EXTERN(void, emitReadVariable, (DList instList, SymTable symtab, int addrIndex));
EXTERN(void, emitWriteExpression,(DList instList,SymTable symtab, int index, char* syscallService));
EXTERN(void, emitExit,(DList instList));

EXTERN(void, emitWhileBegin, (DList instList, int brNum)); 
EXTERN(void, emitWhileEnd, (DList instList));
EXTERN(void, emitWhileBoolCheck, (DList instList, SymTable symtab, int boolIndex));

EXTERN(void, emitBranch, (DList instList, SymTable symtab, int brNum, int boolIndex));
EXTERN(void, emitIfThen, (Dlist instList, SymTable symtab, int brNum));
EXTERN(void, emitElseThen, (Dlist instList, SymTable symtab, int brNum));
EXTERN(void, emitElseThenEnd, (Dlist instList, int brNum));

EXTERN(int, emitOrExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitAndExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitNotExpression, (DList instList, SymTable symtab, int operand));
EXTERN(int, emitEqualExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitNotEqualExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitLessEqualExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitLessThanExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitGreaterEqualExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitGreaterThanExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitAddExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitSubtractExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitMultiplyExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));
EXTERN(int, emitDivideExpression, (DList instList, SymTable symtab, int leftOperand, int rightOperand));

EXTERN(int, emitComputeVariableAddress, (DList instList, SymTable varSymtab, SymTable regSymtab, int varIndex));
EXTERN(int, emitComputeArrayAddress, (DList instList, SymTable symtab, int varIndex, SymTable regSymtab, int subIndex));
EXTERN(int, emitLoadVariable, (DList instList, SymTable symtab, int varIndex));
EXTERN(int, emitLoadIntegerConstant, (DList instList, SymTable symtab, int intIndex));
EXTERN(int, emitLoadStringConstantAddress, (DList instList, DList dataList, SymTable symtab, int stringIndex));

EXTERN(void, addIdToSymtab, (DNode node, AddIdStructPtr data));


#endif /*CODEGEN_H_*/

