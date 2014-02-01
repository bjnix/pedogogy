.include "nios_macros.s"

/*---------------------------------------------------------
 * Define Macros for PUSH and POP for stack interactions
 *---------------------------------------------------------
 */
 #4294967295 = clock cycles per interrupt
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
	
	br MAIN
	
	
	.org 0x20									#for case where the int. vector = 0x20
	#insert ISR code here
	subi	sp,sp,128
	stw		et,96(sp)
	rdctl	et,ctl4			#read from ctl4 to check for extrn intrpt
	beq		et,r1,INTERN_INT
	subi	ea,ea,4
	
MAIN:
	ori		r1,zero,0x2			#interrupt signal
	addi 	r7,zero,0x1FFE		#count stopping condition (amplitude)
	
	ori		sp,zero,0xfffc		#assign stack pointer
	
	ori		r3,zero,0x1000
	orhi	r3,r3,0x1			#Our SPI address
	
	ori		r9,zero,0x40
	stwio	r9,12(r3)			#Set IRRDY to 1 to enable interrupts for the transmitter ready condition
	
	addi	r5, r0, 0x1
	wrctl	ctl0, r5			#enable external interrupts on cpu
	
	addi	r5, r0, 0x2
	wrctl	ctl3, r5			#enable IRQ1 only
	
	ori 	r4,zero,0x1020		#our timer address
	orhi	r4,r4,0x1
	
	#set the timeout value
	ori		r9, zero, 0x07A8		#timeout value
	stwio	r9, 8(r4)			#write to periodl
	
	#set the control register: stop bit=0, start bit=1, cont bit=1, interrupt bit=1
	#i.e. start timer, when it reaches 0, send interrupt, and restart timer (looping timer)
	ori		r9, zero, 0x7		#value to be written to timer's control register (b'0111)
	stwio	r9,	4(r4)			#write control register
	
	br		CODE


	
	
/*---------------------------------------------------------*/
INTERN_INT:
	stw	r1,		4(sp)
	stw	r2,		8(sp)
	stw	r3,		12(sp)
	#stw	r4,		16(sp)
	#stw	r5,		20(sp)
	stw	r6,		24(sp)
	stw	r7,		28(sp)
	stw	r8,		32(sp)
	stw	r9,		36(sp)
	stw	r10,	40(sp)
	stw	r11,	44(sp)
	stw	r12,	48(sp)
	stw	r13,	52(sp)
	stw	r14,	56(sp)
	stw	r15,	60(sp)
	stw	r16,	64(sp)
	stw	r17,	68(sp)
	stw	r18,	72(sp)
	stw	r19,	76(sp)
	stw	r20,	80(sp)
	stw	r21,	84(sp)
	stw	r22,	88(sp)
	stw	r23,	92(sp)
	#skip r24 ("et") because it is already saved above
	stw	r25,	100(sp)	# bt
	stw r26,	104(sp)	# gp
	#skip r27 because its the s-pointer and theres no point in saving dead man
	stw	r28,	112(sp) # fp
	stw r29,	116(sp)	# ea
	stw r30,	120(sp)	# ba
	stw r31,	124(sp)	# ra
	addi 	fp,sp,128
	
	call 	ISH
	
	ldw	r1,		4(sp)
	ldw	r2,		8(sp)
	ldw	r3,		12(sp)
	ldw	r4,		16(sp)
	#ldw	r5,		20(sp)
	ldw	r6,		24(sp)
	ldw	r7,		28(sp)
	ldw	r8,		32(sp)
	ldw	r9,		36(sp)
	ldw	r10,	40(sp)
	ldw	r11,	44(sp)
	ldw	r12,	48(sp)
	ldw	r13,	52(sp)
	ldw	r14,	56(sp)
	ldw	r15,	60(sp)
	ldw	r16,	64(sp)
	ldw	r17,	68(sp)
	ldw	r18,	72(sp)
	ldw	r19,	76(sp)
	ldw	r20,	80(sp)
	ldw	r21,	84(sp)
	ldw	r22,	88(sp)
	ldw	r23,	92(sp)
	ldw	r24,	96(sp)
	ldw	r25,	100(sp)	# bt
	ldw r26,	104(sp)	# gp
	#skip r27 because its the s-pointer and theres no point in saving dead man
	ldw	r28,	112(sp) # fp
	ldw r29,	116(sp)	# ea
	ldw r30,	120(sp)	# ba
	ldw r31,	124(sp)	# ra
	addi 		sp,sp,128
	eret
	#rdctl		r12,	estatus
	#wrctl		status, r12
	#br CODE
/*---------------------------------------------------------*/
	
	ISH:
	PUSH	ra
	
	addi 	r5,r5,0x01
	ori		r5,r5,0x1000
	stwio	r5,4(r3)
	
	POP		ra
	ret
	
/*---------------------------------------------------------*/
	
CODE:	
	nop		#spacer for breakpoints
	bgt		r5,r7,RESET
	nop		#spacer for breakpoints
	br 		CODE
RESET:		
	add		r5,zero,zero
	ori		r5,r5,0x1000
	br		CODE
	
	




.data
	
.end
