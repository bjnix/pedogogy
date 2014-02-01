#ifndef REG_H_
#define REG_H_

#define NUM_INTEGER_REGISTERS 18 /**< the number of Mips integer registers that may be allocated */

EXTERN(void, initRegisters, (void));
EXTERN(void, cleanupRegisters,(void));
EXTERN(int, allocateIntegerRegister,(void));
EXTERN(void, freeIntegerRegister,(int reg));
EXTERN(char*, getIntegerRegisterName,(int reg));
EXTERN(int, getFreeIntegerRegisterIndex, (SymTable symtab));
EXTERN(bool, isAllocatedIntegerRegister,(int reg));
#endif /*REG_H_*/
