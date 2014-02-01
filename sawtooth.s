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

	#load 11000 to r3 (address of spi)
	ori	r3,zero,0x1000
	orhi	r3,r3,0x1
	
	#Set IRRDY to 1 to enable interrupts for the transmitter ready condition
	ori	r9,zero,0x40
	stwio	r9,12(r3)
	
	#load r6 with inner loop stopping condition
	addi	r6,r0,0x1fff
	addi	r4,r0,0
	
LOOP_OUT:

	addi	r5,r0,0			#set step to 0


LOOP:
	
POLL:
	#Read RRDY bit to see if the reciever is ready
	ldwio	r7, 8(r3)
	# check it against 0x40 
	and	r7, r9, r7 
	# if not keep polling
	beq 	r0, r7, POLL
	# add header to ouput
	ori	r5,r5,0x1000
	# write output to spi
	stwio	r5,4(r3)	
	
	#zero nop counter
	or 	r2,zero,zero
	#set nop loop stopping condition
	ori	r7, zero, 0x129
NOP_PARTY:
	#increment NOP loop counter
	addi	r2,r2,0x1
	nop
	bne 	r2,r7,NOP_PARTY
	#once past NOP loop, increment main loop counter
	addi	r5,r5,1				#increment step by 1
	
	#if the peak of the wave is reached, start outer loop again
	beq 	r5,r6,LOOP_OUT
	#otherwise continue in the inner loop	
	br LOOP
	

.data
	
.end
