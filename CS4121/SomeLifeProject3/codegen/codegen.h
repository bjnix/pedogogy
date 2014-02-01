#ifndef CODEGEN_H_
#define CODEGEN_H_

#include <util/symtab.h>
#include <util/symtab_stack.h>
#include <codegen/types.h>

#define SYSCALL_PRINT_INTEGER "1"	/**< The syscall code for printing an integer */
#define SYSCALL_PRINT_FLOAT "2"	/**< The syscall code for printing a float */
#define SYSCALL_PRINT_STRING "4"	/**< The syscall code for printing a string */
#define SYSCALL_READ_INTEGER "5"	/**< The syscall code for reading an integer */
#define SYSCALL_READ_FLOAT "6"	/**< The syscall code for reading a float */
#define SYSCALL_EXIT "10"			/**< The syscall code for exiting the interpreter */

/**
 * Data needed for adding variable to the symbol table.
 */
typedef struct AddIdType {
	SymTable idSymtab;		/**< the symbol table for the identifiers*/
	SymTable typeSymtab;		/**< the symbol table for the type */
	int typeIndex;			/**< the symbol table index of the type of a list of variables */
	int offset;			/**< the current offset in the activation record */
} AddIdStruct, *AddIdStructPtr;

EXTERN(void, emitInstructions,(DList list));
EXTERN(void, emitProcedurePrologue,(DList instList,SymTable symtab, int index, int frameSize));

EXTERN(void, emitPreCall,(DList instList,SymTable symtab, int index));
EXTERN(void, emitPostCall,(DList instList,SymtabStack symtabStack, int index, int framesize));
EXTERN(void, emitPreReturn,(DList instList));
EXTERN(void, emitReturn,(DList instList,SymTable symtab, int index));
EXTERN(int, emitPostReturn,(DList instList,SymTable symtab));

EXTERN(void, emitAssignment, (DList instList,SymTable symtab,int lhsRegIndex, int rhsRegIndex));
EXTERN(void, emitReadVariable, (DList instList, SymTable symtab, int addrIndex));
EXTERN(void, emitWriteExpression,(DList instList,SymTable symtab, int index, char* syscallService));
EXTERN(int, emitIfTest, (DList instList, SymTable symtab, int regIndex));
EXTERN(void, emitEndBranchTarget, (DList instList, SymTable symtab, int endLabelIndex));
EXTERN(int, emitThenBranch, (DList instList, SymTable symtab, int elseLabelIndex));
EXTERN(int, emitWhileLoopLandingPad, (DList instList,SymTable symtab));
EXTERN(int, emitWhileLoopTest, (DList instList, SymTable symtab, int regIndex));
EXTERN(void, emitWhileLoopBackBranch,(DList instList, SymTable symtab, int beginLabelIndex, int endLabelIndex));
EXTERN(void, emitExit,(DList instList));

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

EXTERN(int, emitComputeVariableAddress,(DList instList, SymTable varSymtab, SymTable regSymtab, int varIndex));
EXTERN(int, emitComputeArrayAddress, (DList instList, SymTable symtab, int varIndex, SymTable regSymtab, int subIndex));
EXTERN(int, emitLoadVariable,(DList instList, SymTable symtab, int varIndex));
EXTERN(int, emitLoadIntegerConstant,(DList instList, SymTable symtab, int intIndex));
EXTERN(int, emitLoadStringConstantAddress, (DList instList, DList dataList, SymTable symtab, int stringIndex));

EXTERN(void, addIdToSymtab,(DNode node,AddIdStructPtr data));
#endif /*CODEGEN_H_*/

