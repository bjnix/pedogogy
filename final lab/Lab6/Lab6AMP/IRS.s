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
	br	main_init

.org 0x20									#for case where the int. vector = 0x20
	#insert ISR code here
	subi	sp,sp,128
	stw		et,96(sp)
	rdctl	et,ctl4			#read from ctl4 to check for extrn intrpt
	beq		et,r0,INTERN_INT
	subi	ea,ea,4

	
	
	/*---------------------------------------------------------
 */
INTERN_INT:
	stw	r1,		4(sp)
	stw	r2,		8(sp)
	stw	r3,		12(sp)
	#stw	r4,		16(sp)
	stw	r5,		20(sp)
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
	#ldw	r4,		16(sp)
	ldw	r5,		20(sp)
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
	addi 	sp,sp,128
	eret
	
ISH:
	PUSH	ra
	
	ldwio	r2, 12(r6)		#load edgecapture register
	andi	r3, r2, 1		#check bit0
	bne		r3, r0, CHECK2	#don't clear edgecapture
	#button 1 code
	addi	r4, r4, 1		#increase count
	addi	r1, r0, 1
	stwio	r1, 12(r6)		#store the data into edgecapture register
	b		SKIP
	
CHECK2:
	andi	r3, r2, 2		#check bit0
	beq		r3, r0, CHECK3	#don't clear edgecapture
	#button 2 code
	addi	r4, r4, 1		#increase count
	ldwio	r1, 12(r6)		#will clear all
	stwio	r1, 12(r6)		#store the data into edgecapture register
	b		SKIP
	
CHECK3:
	andi	r3, r2, 3		#check bit0
	beq		r3, r0, SKIP	#don't clear edgecapture
	#button 3 code	addi	
	r4, r4, 1		#increase count
	ldwio	r1, 12(r6)		#will clear all
	stwio	r1, 12(r6)		#store the data into edgecapture register
	b		SKIP

	
SKIP:
		
	POP		ra
	ret
main_init:
	#insert initialization code here
	addi	r5, r0, 0x1
	wrctl	ctl0, r5		#enable external inturupts on cpu
	addi	r5, r0, 0x2
	wrctl	ctl3, r5		#enable IRQ1 only
	movia	r6, PIO_ADDR	#point to addr
	ldw		r6,	0(r6)		#load actual word
	addi	r5, r0, 0x7
	stwio	r5, 8(r6)		#set inturrupt mask on PIO
	ldwio	r8, 8(r6)
	addi	r4, r0, 0
	
main_loop:
	#insert main loop code here
	nop
	nop
	nop
	br	main_loop
	
.data
PIO_ADDR:	
	.word	0x11030
	
.end