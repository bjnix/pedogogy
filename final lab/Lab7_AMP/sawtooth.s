.include "nios_macros.s"

/*---------------------------------------------------------
 * Define Macros for PUSH and POP for stack interactions
 *---------------------------------------------------------
 */
	.macro PUSH reg
		subi	sp,sp,0x4
		stw		\reg,0x0(sp)
	.endm
	.macro POP reg
		ldw		\reg,0x0(sp)
		addi	sp,sp,0x4
	.endm

/*---------------------------------------------------------*/

.text
.global _start
_start:

	#amplitude = 4.085
	#ori 	r4,zero,0.5
	
	#movia 	r5,408500
	#ori		r6,zero,2
	ori		r3,zero,0x1000
	orhi	r3,r3,0x1
	#ori 	r8,zero,0xc35
	ori		r9,zero,0x40
	stwio	r9,12(r3)
	
	addi	r6,r0,0x1fff
	addi	r4,r0,0
	
LOOP_OUT:

	addi	r5,r0,0			#set step to 0


LOOP:
	#div 	r7,r6,r5
	#addi	r4,r7,0.5
	#sub		r4,r7,r4
	#slli	r4,r4,0x1
	#div	r4,r4,r8
	#srli	r4,r4,0x5
	
	#div		r4,r5,r6
	
POLL:
	ldwio	r7, 8(r3)
	and		r7, r9, r7 
	beq 	r0, r7, POLL
	
	#andi	r5,r5,0x0fff
	ori		r5,r5,0x1000
	stwio	r5,4(r3)	
	
	or 	r2,zero,zero
	ori		r7, zero, 0x129
NOP_PARTY:
	addi	r2,r2,0x1
	nop
	bne 	r2,r7,NOP_PARTY
	
	addi	r5,r5,1				#increment step by 1
	
	beq 	r5,r6,LOOP_OUT
	
	
	
	br LOOP
	
	
	




.data
	
.end