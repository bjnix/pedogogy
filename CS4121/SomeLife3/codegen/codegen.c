#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <util/string_utils.h>
#include <util/symtab.h>
#include <util/dlink.h>
#include "reg.h"
#include "codegen.h"
#include "symfields.h"
#include "types.h"

extern int globalOffset;


EXTERN(void,SomeLife_error,(char*));

/**
 * Print a data declaration to stdout. This function is called by dlinkApply only.
 *
 * @param decl a DNode containing a data declaration
 */
static void printDataDeclaration(DNode decl) {
	fprintf(stdout,"%s\n",(char*)dlinkNodeAtom(decl));
}

/**
 * Print the assembly prologue that includes the data section, a declaration of main and an allocation of stack space
 * for the main routine.
 *
 * @param dataList a list of data declarations (strings and floats)
 * @param frameSize The number of bytes need for local variables declared in main
 */
void emitDataPrologue(DList dataList) {
	
	fprintf(stdout,"\t.data\n");
	fprintf(stdout,".newline: .asciiz \"\\n\"\n");
	dlinkApply(dataList,(DLinkApplyFunc)printDataDeclaration);
	fprintf(stdout,"\t.text\n");
}

/**
 * Emit the assembly prologue for a procedure
 *
 * @param frameSize The number of bytes need for local variables declared in main
 */
void emitProcedurePrologue(DList instList,SymTable symtab, int index, int frameSize) {

	char *name = (char*)SymGetFieldByIndex(symtab,index,SYM_NAME_FIELD); 

	char* inst = nssave(2,"\t.globl ",name);
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
	inst = nssave(2,name,":\tnop");
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	char frameStr[10];
	inst = ssave("\tmove $fp,$sp");
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	if (frameSize != 0) {
		sprintf(frameStr,"%d",frameSize);
		inst = nssave(2,"\tadd $sp, $sp, ",frameStr);
		dlinkAppend(instList,dlinkNodeAlloc(inst));
	}

	
	globalOffset = frameSize;
}
/**
 * emitPreCall
 *
 * allocate AR
 * store return address & fp
 * save caller-saved regs
 * jump to callee
 */
void emitPreCall(DList instList, SymTable symtab, int index){
	//get name for func
	char* funcName = (char*)SymGetFieldByIndex(symtab,index,SYM_NAME_FIELD);

	//save $ra
	char* inst = ssave("\tsw $ra,-4($sp)");
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	char regN[5];
	int si = 0;
	//save t0-t9 regs
	for( ; si<10; si++){
		sprintf(regN,"$t%d,%d($sp)",si,(-si*4)-8);
		inst = nssave(2,"\tsw ",regN);
		dlinkAppend(instList,dlinkNodeAlloc(inst));
	}
	//allocate AR
	inst = ssave("\taddi $sp,$sp,-80");
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	//save fp
	inst = ssave("\tsw $fp,0($sp)");
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	//jump to callee
	char * insta = nssave(2,"\tjal\t",funcName);
	dlinkAppend(instList,dlinkNodeAlloc(insta));
	
}
/**
 * emitPostCall
 *
 * 	Save callee-saved regs
 * 	extend AR for locals
 * 	find static data area
 * 	initialize locals
 * 	fall through to code
 *	@return index to label in symbol table for caller methods
 */
void emitPostCall(DList instList, SymtabStack symtabStack, int index, int frameSize){
	SymTable symtab = currentSymtab(symtabStack);
	SymTable symtab_0 = lastSymtab(symtabStack);
	
	char* name = (char*)SymGetFieldByIndex(symtab_0, index,SYM_NAME_FIELD);
	
	char* inst = nssave(2,name,":");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
	//save $ra
	inst = ssave("\tsw $ra,76($sp)");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
	char regN[5];
	char frameStr[10];
	int si = 0;
	//save s0-s7 regs
	for( ; si<8; si++){
		sprintf(regN,"$s%d,%d($sp)",si,(si*4)+4);
		inst = nssave(2,"\tsw ",regN);
		dlinkAppend(instList,dlinkNodeAlloc(inst));
	}
	
	inst = ssave("\tmove $fp,$sp");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	//allocate room for any local variables
	if (frameSize != 0) {
		sprintf(frameStr,"%d",frameSize);
		inst = nssave(2,"\taddi $sp,$sp,",frameStr);
		dlinkAppend(instList,dlinkNodeAlloc(inst));
	//store any local variables on the stack(kind of)
		int i = 0;
		int max = SymMaxIndex(symtab);
		int offset = 0;
		for(;i<=max;i++)
		{
			offset = -(int)SymGetFieldByIndex(symtab,i,SYMTAB_OFFSET_FIELD);
			SymPutFieldByIndex(symtab,i,SYMTAB_OFFSET_FIELD,(Generic)offset);
		}
	}
	
	
}

/**
 * emitPreReturn
 * restore callee-saved regs
 * discard local data
 * restore callers fp
 * jump to return address
 */
void emitPreReturn(DList instList){
	
	//discard local data
	char* inst = ssave("\tmove $sp,$fp"); 
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	int si = 0;
	char regN[5];

	//restore callee-saved regs ($s0-$s7)
	for( ; si<8; si++)
	{
		sprintf(regN,"$s%d,%d($sp)",si,(si*4)+4);
		inst = nssave(2,"\tlw ",regN);
		dlinkAppend(instList,dlinkNodeAlloc(inst));
	}

	//restore callers fp
	inst = ssave("\tlw $fp,($fp)");		
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
	//load return address into $ra
	inst = ssave("\tlw $ra,76($sp)");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
/*
	//save return value from $v0 into stack where ra was before
	inst = ssave("\tsw $v0 76($sp)");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
*/
	//jump to return address
	inst = ssave("\tjr\t$ra");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
}
/**
 * emitReturn
 * save return value to $v0
 */
void emitReturn(DList instList,SymTable symtab,int index){
	
	char* inst = nssave(2,"\tmove $v0, ",
		      (char*)SymGetFieldByIndex(symtab,index,SYM_NAME_FIELD));
	
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	freeIntegerRegister((int)SymGetFieldByIndex(symtab,index,SYMTAB_REGISTER_INDEX_FIELD));
}

/**
 * emitPostReturn
 * dallocate basic AR
 * restore caller-saved regs
 * restore reference parameters
 */
int emitPostReturn(DList instList,SymTable symtab){
	//get new reg	
	int regIndex = getFreeIntegerRegisterIndex(symtab);
	
	char* regName = (char*)SymGetFieldByIndex(symtab,regIndex,SYM_NAME_FIELD);
	//deallocate basic AR
	char* inst = ssave("\taddi $sp, $sp,80");
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	//return	
	inst = nssave(3,"\tmove ",regName,", $v0");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
	//restore t0-t9 regs
	char regN[5];
	int si = 0;
	for( ; si<10; si++){
		sprintf(regN,"$t%d,%d($sp)",si,(-si*4)-8);
		inst = nssave(2,"\tlw ",regN);
		dlinkAppend(instList,dlinkNodeAlloc(inst));
	}



	return regIndex;
	
}
void emitExit(DList instList) {

	char *inst = nssave(2,"\tli $v0, ",SYSCALL_EXIT);
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	inst = ssave("\tsyscall");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
}

/**
 * Print an assembly instruction to stdout. This function is only called by dlinkApply.
 *
 * @param inst a DNode containing an assembly instruction.
 */
static void printInstruction(DNode inst) {
	fprintf(stdout,"%s\n",(char*)dlinkNodeAtom(inst));
}

/**
 * Print all of the assembly instructions for the main routine to stdout.
 *
 * @param instList a DList of assembly instructions.
 */
void emitInstructions(DList instList) {
	
	//jump to main
	fprintf(stdout,"\tj\tmain\n");
	dlinkApply(instList,(DLinkApplyFunc)printInstruction);
}

/**
 * Add an instruction that performance an assignment.
 *
 * @param instList a DList of assembly instructions
 * @param symtab a symbol table
 * @param lhsRegIndex the symbol table index of the register for the l-value address
 * @param rhsRegIndex the symbol table index of the register for the r-value
 */
void emitAssignment(DList instList,SymTable symtab,int lhsRegIndex, int rhsRegIndex) {
        char *inst = nssave(5,"\tsw ",(char*)SymGetFieldByIndex(symtab,rhsRegIndex,SYM_NAME_FIELD),", 0(",
				(char*)SymGetFieldByIndex(symtab,lhsRegIndex,SYM_NAME_FIELD),")");
		
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	freeIntegerRegister((int)SymGetFieldByIndex(symtab,rhsRegIndex,SYMTAB_REGISTER_INDEX_FIELD));
	freeIntegerRegister((int)SymGetFieldByIndex(symtab,lhsRegIndex,SYMTAB_REGISTER_INDEX_FIELD));
}

/**
 * Add the instructions needed to read a variable using the read system call.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param addrIndex the symbol table index of the register holding the address that is to be read into
 */
void emitReadVariable(DList instList, SymTable symtab, int addrIndex) {
	char* inst;

	inst = nssave(2,"\tli $v0, ", SYSCALL_READ_INTEGER);
	
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	inst = ssave("\tsyscall");
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	inst = nssave(3,"\tsw $v0, 0(",
		      (char*)SymGetFieldByIndex(symtab,addrIndex,SYM_NAME_FIELD),")");

	dlinkAppend(instList,dlinkNodeAlloc(inst));
	freeIntegerRegister((int)SymGetFieldByIndex(symtab,addrIndex,SYMTAB_REGISTER_INDEX_FIELD));
}

/**
 * Add the instructions needed to write a value using the print system call.
 *
 * @param instList a Dlist of instructions
 * @param symtab a symbol table
 * @param regIndex the symbol table index of the register to be printed (must be addres if string)
 * @param syscallService the system call print service to use
 */
void emitWriteExpression(DList instList,SymTable symtab, int regIndex, char* syscallService) {
	char *inst;

	inst = nssave(2,"\tmove $a0, ",
		      (char*)SymGetFieldByIndex(symtab,regIndex,SYM_NAME_FIELD));
	
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	freeIntegerRegister((int)SymGetFieldByIndex(symtab,regIndex,SYMTAB_REGISTER_INDEX_FIELD));
	
	inst = nssave(2,"\tli $v0, ",syscallService);
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
	inst = ssave("\tsyscall");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
	inst = nssave(2,"\tli $v0, ",SYSCALL_PRINT_STRING);
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
	inst = ssave("\tla, $a0, .newline");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
	inst = ssave("\tsyscall");
	dlinkAppend(instList,dlinkNodeAlloc(inst));
	
}

/**
 * Create a unique label
 * @param label a character array of size 20 in which the label will be stored
 */
static void makeLabel(char label[20]) {
	static int labelCount = 0;

	snprintf(label,19,".L%d",labelCount++);
}

/**
 * Insert instructions to test whether the expression of a if-statement is false, if false, branch around the then-part
 * of the if-statement.
 *
 * @param instList a list of instructions
 * @param symtab a symbol table
 * @param regIndex the symbol table index of the register holding the rest of the test expression of an if-statement
 * @return the symbol table index of the label that must follow the then-part of an if-statement
 */
int emitIfTest(DList instList, SymTable symtab, int regIndex) {
	char label[20];
	makeLabel(label);

	char* inst = nssave(4,"\tbeq ",SymGetFieldByIndex(symtab,regIndex,SYM_NAME_FIELD),", $zero, ",label);

	dlinkAppend(instList,dlinkNodeAlloc(inst));

	freeIntegerRegister((int)SymGetFieldByIndex(symtab,regIndex,SYMTAB_REGISTER_INDEX_FIELD));

	return SymIndex(symtab,label);
}
/**
 * Insert a nop as a branch target in the list of instructions.
 *
 * @param instList a list of instructions
 * @param symtab a symbol table
 * @param endLabelIndex the symbol table index of the label for the nop
 */
void emitEndBranchTarget(DList instList, SymTable symtab, int endLabelIndex) {
	char* inst = nssave(2,SymGetFieldByIndex(symtab,endLabelIndex,SYM_NAME_FIELD),":\t nop");

	dlinkAppend(instList, dlinkNodeAlloc(inst));
}

/**
 * Insert a branch to an ending label after the else-part of an if-statement.
 *
 * @param instList a list of instructions
 * @param symtab a symbol table
 * @param elseLabelIndex the symbol table index of the label for the beginning of the else-part of an if-statement
 * @return a symbol table index for the end label of an if-statement
 */
int emitThenBranch(DList instList, SymTable symtab, int elseLabelIndex) {
	char label[20];
	makeLabel(label);
	
	char* inst = nssave(2,"\tj ",label);

	dlinkAppend(instList, dlinkNodeAlloc(inst));

	emitEndBranchTarget(instList,symtab,elseLabelIndex);

	return SymIndex(symtab,label);
}
/**
 * Insert a nop to serve as a target of the backwards branch of a while-statement
 *
 * @param instList a list of instructions
 * @param symtab a symbol table
 * @return the label for the backwards branch target
 */
int emitWhileLoopLandingPad(DList instList,SymTable symtab) {
	char label[20];
	makeLabel(label);

	char *inst = nssave(2,label,":\tnop");

	dlinkAppend(instList,dlinkNodeAlloc(inst));

	return SymIndex(symtab,label);
}

/**
 * Insert a test to enter a while loop. If the test is false, branch to a label after the loop.
 *
 * @param instList a list of instructions
 * @param symtab a symbol table
 * @param regIndex a symbol table index for the register holding the result of the test expression of a while-statement
 * @return a symbol table index for the label at the end of the while-loop
 */
int emitWhileLoopTest(DList instList, SymTable symtab, int regIndex) {
	char label[20];
	makeLabel(label);
	char* inst = nssave(4,"\tbeq ",(char*)SymGetFieldByIndex(symtab,regIndex,SYM_NAME_FIELD),", $zero, ",label);

	dlinkAppend(instList,dlinkNodeAlloc(inst));

	freeIntegerRegister((int)SymGetFieldByIndex(symtab,regIndex,SYMTAB_REGISTER_INDEX_FIELD));

	return SymIndex(symtab,label);
}

/**
 * Insert a branch back to the the landing pad of a while loop, followed by a branch target for loop exit.
 *
 * @param instList a list of instructions
 * @param symtab a symbol table
 * @param beginLabelIndex a symbol table index of the label for the while loop landing pad
 * @param endLabelIndex a symbol table index of the lable for the exit of the while loop
 */
void emitWhileLoopBackBranch(DList instList, SymTable symtab, int beginLabelIndex, int endLabelIndex) {
	char *inst = nssave(2,"\tj ",(char*)SymGetFieldByIndex(symtab,beginLabelIndex,SYM_NAME_FIELD));

	dlinkAppend(instList,dlinkNodeAlloc(inst));

	inst = nssave(2,(char*)SymGetFieldByIndex(symtab,endLabelIndex,SYM_NAME_FIELD),":\t nop");

	dlinkAppend(instList,dlinkNodeAlloc(inst));
}

/**
 * Add an instruction that performs a binary computation.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param typeTable the type table for the expression being emitted
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @param opcode the opcode of the mips assembly instruction
 * @return
 */
static int emitBinaryExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand, char* opcode) {
	int regIndex;

	regIndex = getFreeIntegerRegisterIndex(symtab);
	char* regName = (char*)SymGetFieldByIndex(symtab,regIndex,SYM_NAME_FIELD);
	char* leftName = (char*)SymGetFieldByIndex(symtab,leftOperand,SYM_NAME_FIELD);
	char* rightName = (char*)SymGetFieldByIndex(symtab,rightOperand,SYM_NAME_FIELD);

	char* inst = nssave(8,"\t",opcode," ",regName,", ",leftName,", ",rightName);
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	freeIntegerRegister((int)SymGetFieldByIndex(symtab,leftOperand,SYMTAB_REGISTER_INDEX_FIELD));
	freeIntegerRegister((int)SymGetFieldByIndex(symtab,rightOperand,SYMTAB_REGISTER_INDEX_FIELD));

	return regIndex;
}

/**
 * Add an or instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitOrExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
	return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"or");
}

/**
 * Add an and instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitAndExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
	return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"and");
}

/**
 * Add a not instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param operand the symbol table index of the register holding the operand
 * @return the symbol table index for the result register
 */
int emitNotExpression(DList instList, SymTable symtab, int operand) {
	int regIndex = getFreeIntegerRegisterIndex(symtab);
	char* regName = (char*)SymGetFieldByIndex(symtab,regIndex,SYM_NAME_FIELD);
	char* opName = (char*)SymGetFieldByIndex(symtab,operand,SYM_NAME_FIELD);

	char* inst = nssave(5,"\txori ",regName,", ",opName,", 1");
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	freeIntegerRegister((int)SymGetFieldByIndex(symtab,operand,SYMTAB_REGISTER_INDEX_FIELD));

	return regIndex;
}

/**
 * Add an equal instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitEqualExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
         return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"seq");
}
/**
 * Add a not-equal instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */

int emitNotEqualExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
        return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"sne");
}

/**
 * Add an less-or-equal instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitLessEqualExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
         return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"sle");
}

/**
 * Add a less-than instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitLessThanExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
          return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"slt");
}

/**
 * Add a greater-equal instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitGreaterEqualExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
 	   return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"sge");
}

/**
 * Add a greater-than instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitGreaterThanExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
	   return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"sgt");
}

/**
 * Add an add instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitAddExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
          return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"add");
}

/**
 * Add a subtract instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitSubtractExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
        return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"sub");
}

/**
 * Add a multiply instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitMultiplyExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
	return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"mul");
}

/**
 * Add a divide instruction.
 *
 * @param instList a DList of instructions
 * @param symtab a symbol table
 * @param leftOperand the symbol table index of the register holding the left operand
 * @param rightOperand the symbol table index of the register holding the right operand
 * @return the symbol table index for the result register
 */
int emitDivideExpression(DList instList, SymTable symtab, int leftOperand, int rightOperand) {
	return emitBinaryExpression(instList,symtab,leftOperand,rightOperand,"div");
}

/**
 * Add an instruction to compute the address of a variable.
 *
 * @param instList a Dlist of instructions
 * @param varSymtab a symbol table for the variable
 * @param regSymtab a symbol table for the registers
 * @param varIndex the symbol table index for a variable
 * @return the symbol table index of the result register
 */
int emitComputeVariableAddress(DList instList, SymTable varSymtab, SymTable regSymtab, int varIndex) {
	

	int regIndex = getFreeIntegerRegisterIndex(regSymtab);
	
	int varTypeIndex = (int)SymGetFieldByIndex(varSymtab,varIndex,SYMTAB_TYPE_INDEX_FIELD);
	
	if (!isArrayType(regSymtab,varTypeIndex)) {
		char* regName = (char*)SymGetFieldByIndex(regSymtab,regIndex,SYM_NAME_FIELD);

		int offset = (int)SymGetFieldByIndex(varSymtab,varIndex,SYMTAB_OFFSET_FIELD);
		char offsetStr[10];

		snprintf(offsetStr,9,"%d",offset);

		char *inst;
		if (offset < 0)
			inst = nssave(4,"\tadd ",regName,", $fp, ",offsetStr);
		else
			inst = nssave(4,"\tadd ",regName,", $gp, ",offsetStr);
		dlinkAppend(instList,dlinkNodeAlloc(inst));
	}
	else {
		char msg[80];

		snprintf(msg,80,"Array variable %s used as a scalar",
				(char*)SymGetFieldByIndex(varSymtab,varIndex,SYM_NAME_FIELD));
		SomeLife_error(msg);
	}

	return regIndex;

}

/**
 * Compute the address of an array element and store it in a register.
 *
 * @param instList a list of instructions
 * @param varSymtab a symbol table for the variable
 * @param regSymtab a symbol table for the registers
 * @param varIndex the symbol table index of the array variable
 * @param subIndex the symbol table index of the register holding the subscript value
 * @return the symbol table index of the register holding the address of the
 * 		   array element.
 */
int emitComputeArrayAddress(DList instList, SymTable varSymtab, int varIndex, SymTable regSymtab, int subIndex) {
	
	int regIndex = getFreeIntegerRegisterIndex(regSymtab);
	int varTypeIndex = (int)SymGetFieldByIndex(varSymtab,varIndex,SYMTAB_TYPE_INDEX_FIELD);
	
	if (isArrayType(regSymtab,varTypeIndex)) {
		char* regName = (char*)SymGetFieldByIndex(regSymtab,regIndex,SYM_NAME_FIELD);
	
		int offset = (int)SymGetFieldByIndex(varSymtab,varIndex,SYMTAB_OFFSET_FIELD);
	
		char offsetStr[10];
	
		snprintf(offsetStr,9,"%d",offset);
	
		/* compute base address of array */
		char *inst;

		inst = nssave(4,"\tadd ",regName,", $gp, ",offsetStr);
		dlinkAppend(instList,dlinkNodeAlloc(inst));

		/* compute offset base on subscript */
		char* subRegName = (char*)SymGetFieldByIndex(regSymtab,subIndex,SYM_NAME_FIELD);
		int arrayBase = getArrayBase(regSymtab, varTypeIndex);
		
		snprintf(offsetStr,9,"%d",arrayBase);
		inst = nssave(6,"\tsub ", subRegName, ", ", subRegName, ", ", offsetStr);
		dlinkAppend(instList,dlinkNodeAlloc(inst));

		inst = nssave(5,"\tsll ", subRegName, ", ", subRegName, ", 2");
		dlinkAppend(instList,dlinkNodeAlloc(inst));

		/* compute element address */
		inst = nssave(6,"\tadd ", regName, ", ", regName, ", ", subRegName);
		dlinkAppend(instList,dlinkNodeAlloc(inst));
	}
	else {
		char msg[80];

		snprintf(msg,80,"Scalar variable %s used as an array",
				(char*)SymGetFieldByIndex(varSymtab,varIndex,SYM_NAME_FIELD));
		SomeLife_error(msg);
	}
	
	freeIntegerRegister((int)SymGetFieldByIndex(regSymtab,subIndex,SYMTAB_REGISTER_INDEX_FIELD));

	return regIndex;

}

/**
 * Add an instruction to load a variable from memory.
 *
 * @param instList a Dlist of instructions
 * @param symtab a symbol table
 * @param regIndex the symbol table index for the address of a variable
 * @return the symbol table index of the result register
 */
int emitLoadVariable(DList instList, SymTable symtab, int regIndex) {

	int newRegIndex = getFreeIntegerRegisterIndex(symtab);

	char* newRegName = (char*)SymGetFieldByIndex(symtab,newRegIndex,SYM_NAME_FIELD);

	char* regName = (char*)SymGetFieldByIndex(symtab,regIndex,SYM_NAME_FIELD);

	char *inst;
	
	inst = nssave(5,"\tlw ",newRegName,", 0(",regName,")");
	
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	freeIntegerRegister((int)SymGetFieldByIndex(symtab,regIndex,SYMTAB_REGISTER_INDEX_FIELD));

	return newRegIndex;

}

/**
 * Add an instruction to load an integer constant
 *
 * @param instList a Dlist of instructions
 * @param symtab a symbol table
 * @param intIndex the symbol table index for an integer constant
 * @return the symbol table index of the result register
 */
int emitLoadIntegerConstant(DList instList, SymTable symtab, int intIndex) {
	int regIndex = getFreeIntegerRegisterIndex(symtab);
	char* regName = (char*)SymGetFieldByIndex(symtab,regIndex,SYM_NAME_FIELD);
	
	char *intName = SymGetFieldByIndex(symtab,intIndex,SYM_NAME_FIELD);
	
	char *inst = nssave(4,"\tli ",regName, ", ", intName);
	
	DNode node = dlinkNodeAlloc(inst);
	
	dlinkAppend(instList,node);
	
	return regIndex;
			                
}

/**
 * Add a .asciiz declaration for a string constant.
 *
 * @param dataList a DList of data declarations
 * @param symtab a symbol table
 * @param stringIndex the symbol table index of a string constant
 * @return
 */
static char* makeDataDeclaration(DList dataList, SymTable symtab, int stringIndex) {
	static int stringNum = 0;
	char* string = (char*)SymGetFieldByIndex(symtab,stringIndex,SYM_NAME_FIELD);
	char* strLabel = (char*)malloc(sizeof(char)*15);

	snprintf(strLabel,14,".string%d",stringNum++);

	char* strChars = substr(string,1,strlen(string)-2); /**< the string constant w/o quotes */
	char* decl = nssave(4,strLabel,": .asciiz \"",strChars,"\"");
	dlinkAppend(dataList,dlinkNodeAlloc(decl));

	free(strChars);

	return strLabel;
}


/**
 * Add an instruction to load the address of a string constant
 *
 * @param instList a Dlist of instructions
 * @param dataList a Dlist of data declarations
 * @param symtab a symbol table
 * @param stringIndex the symbol table index for a string constant
 * @return the symbol table index of the result register
 */
int emitLoadStringConstantAddress(DList instList, DList dataList, SymTable symtab, int stringIndex) {
	char *strLabel = makeDataDeclaration(dataList,symtab,stringIndex);

	int regIndex = getFreeIntegerRegisterIndex(symtab);
	char* regName = (char*)SymGetFieldByIndex(symtab,regIndex,SYM_NAME_FIELD);

	char* inst = nssave(4,"\tla ",regName, ", ",strLabel);
	dlinkAppend(instList,dlinkNodeAlloc(inst));

	free(strLabel);

	return regIndex;
}


/**
 * Add an identifier to the symbol table and store its offset in the activation record.
 * This function is called by dlinkApply1.
 *
 * @param node a node on a linked list containing the symbol table index of a variable
 * 		  delcared in a program
 * @param data a structure containing the symbol table index of the type of the variable,
 * 		  the symbol table, and the current offset in the activation record.
 */
void addIdToSymtab(DNode node, AddIdStructPtr data) {
        int symIndex = (int)dlinkNodeAtom(node);
        int typeIndex = (int)SymGetFieldByIndex(data->idSymtab,symIndex,SYMTAB_TYPE_INDEX_FIELD);

        if (typeIndex == -1) {
                SymPutFieldByIndex(data->idSymtab,symIndex,SYMTAB_TYPE_INDEX_FIELD,(Generic)data->typeIndex);
                typeIndex = data->typeIndex;
        }

        int size = (int)SymGetFieldByIndex(data->typeSymtab,typeIndex,SYMTAB_SIZE_FIELD);

        SymPutFieldByIndex(data->idSymtab,symIndex,SYMTAB_OFFSET_FIELD,(Generic)(data->offset));
        data->offset += size;      
}

