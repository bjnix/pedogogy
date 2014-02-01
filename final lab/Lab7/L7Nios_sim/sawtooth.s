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
	br	main

	#amplitude = 4.085
	#ori 	r4,zero,0.5
	ori 	r5,zero,4.085
	ori		r6,zero,0.00002
LOOP:
	div 	r7,r6/r5
	addi	r4,r7,0.5
	sub		r4,r7,r4
	sli		r4,r4,0x1
	
	
main:
	




.data
PIO_ADDR:	
	.word	0x11030
	
.end