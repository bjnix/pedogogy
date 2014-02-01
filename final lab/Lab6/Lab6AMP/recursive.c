#include <alt_types.h>



void interrupt_handler(void);
alt_32 Fibs(alt_32 num);
void keys_isr(void);
void the_exception (void) __attribute__ ((section (".exceptions")));

alt_32* output;				//pointer for hex val
alt_32* input;	
alt_32* timer;
alt_32 fib_val;
alt_32 n;

/*
The assembly language code below handles CPU exception processing. This code
should not be modified; instead, the C language code in the function
interrupt_handler() can be modified as needed for a given application.
*/
void the_exception (void)
/***************************************************************************
Exceptions code. By giving the code a section attribute with the name
".exceptions" we allow the linker program to locate this code at the proper
exceptions vector address. This code calls the interrupt handler and later
returns from the exception.
****************************************************************************/
{
	asm ( ".set noat" ); // Magic, for the C compiler
	asm ( ".set nobreak" ); // Magic, for the C compiler
	asm ( "subi sp, sp, 128" );
	asm ( "stw et, 96(sp)" );
	asm ( "rdctl et, ctl4" );
	asm ( "beq et, r0, SKIP_EA_DEC" ); // Interrupt is not external
	asm ( "subi ea, ea, 4" ); /* Must decrement ea by one instruction
	for external interupts, so that the interrupted instruction will be run */
	asm ( "SKIP_EA_DEC:" );
	asm ( "stw r1, 4(sp)" ); // Save all registers
	asm ( "stw r2, 8(sp)" );
	asm ( "stw r3, 12(sp)" );
	asm ( "stw r4, 16(sp)" );
	asm ( "stw r5, 20(sp)" );
	asm ( "stw r6, 24(sp)" );
	asm ( "stw r7, 28(sp)" );
	asm ( "stw r8, 32(sp)" );
	asm ( "stw r9, 36(sp)" );
	asm ( "stw r10, 40(sp)" );
	asm ( "stw r11, 44(sp)" );
	asm ( "stw r12, 48(sp)" );
	asm ( "stw r13, 52(sp)" );
	asm ( "stw r14, 56(sp)" );
	asm ( "stw r15, 60(sp)" );
	asm ( "stw r16, 64(sp)" );
	asm ( "stw r17, 68(sp)" );
	asm ( "stw r18, 72(sp)" );
	asm ( "stw r19, 76(sp)" );
	asm ( "stw r20, 80(sp)" );
	asm ( "stw r21, 84(sp)" );
	asm ( "stw r22, 88(sp)" );
	asm ( "stw r23, 92(sp)" );
	asm ( "stw r25, 100(sp)" ); // r25 = bt (skip r24 = et, because it is saved above)
	asm ( "stw r26, 104(sp)" ); // r26 = gp
	// skip r27 because it is sp, and there is no point in saving this
	asm ( "stw r28, 112(sp)" ); // r28 = fp
	asm ( "stw r29, 116(sp)" ); // r29 = ea
	asm ( "stw r30, 120(sp)" ); // r30 = ba
	asm ( "stw r31, 124(sp)" ); // r31 = ra
	asm ( "addi fp, sp, 128" );
	asm ( "call interrupt_handler" ); // Call the C language interrupt handler
	asm ( "ldw r1, 4(sp)" ); // Restore all registers
	asm ( "ldw r2, 8(sp)" );
	asm ( "ldw r3, 12(sp)" );
	asm ( "ldw r4, 16(sp)" );
	asm ( "ldw r5, 20(sp)" );
	asm ( "ldw r6, 24(sp)" );
	asm ( "ldw r7, 28(sp)" );
	asm ( "ldw r8, 32(sp)" );
	asm ( "ldw r9, 36(sp)" );
	asm ( "ldw r10, 40(sp)" );
	asm ( "ldw r11, 44(sp)" );
	asm ( "ldw r12, 48(sp)" );
	asm ( "ldw r13, 52(sp)" );
	asm ( "ldw r14, 56(sp)" );
	asm ( "ldw r15, 60(sp)" );
	asm ( "ldw r16, 64(sp)" );
	asm ( "ldw r17, 68(sp)" );
	asm ( "ldw r18, 72(sp)" );
	asm ( "ldw r19, 76(sp)" );
	asm ( "ldw r20, 80(sp)" );
	asm ( "ldw r21, 84(sp)" );
	asm ( "ldw r22, 88(sp)" );
	asm ( "ldw r23, 92(sp)" );
	asm ( "ldw r24, 96(sp)" );
	asm ( "ldw r25, 100(sp)" ); // r25 = bt
	asm ( "ldw r26, 104(sp)" ); // r26 = gp
	// skip r27 because it is sp, and we did not save this on the stack
	asm ( "ldw r28, 112(sp)" ); // r28 = fp
	asm ( "ldw r29, 116(sp)" ); // r29 = ea
	asm ( "ldw r30, 120(sp)" ); // r30 = ba
	asm ( "ldw r31, 124(sp)" ); // r31 = ra
	asm ( "addi sp, sp, 128" );
	asm ( "eret" );
}



main()
{			

	alt_32 edge = 0;

	timer = 0x11000;

	input = 0x11030;			//set the addr of input keys
	output = 0x11020;			//set the addr of hex val to output addr
	*output = 0x0000000F;		//initialize to 0

	n = 2;

	//reset the counter to 0
	alt_u32 snap = 0x0000000;
	*(timer + 4) = snap;
	
	//start the timer
	alt_16 ctrl = *(timer + 1);	//read in info
	ctrl = ctrl | 0x4;			//turn on start bit
	*(timer + 1) = ctrl;		//write info
	
	fib_val = Fibs(n);
	
	//stop the timer
	ctrl = *(timer + 1);		//read in info
	ctrl = ctrl | 0x8;			//turn on stop bit
	*(timer + 1) = ctrl;		//write info
	
	//read timer value
	snap = *(timer + 4);
	
	*output = snap;

while(1){}



}


void keys_isr(void)
{
	int keys = *(input+3);
	int cont = 0;
	if(keys == 1)
	{
		*output = fib_val;
	}
	else if(keys == 2)
	{
		cont = 1;
	}
	else if(keys == 3)
	{				
		if( n == 47)
		{
			*output = 0xFFFFFFFF;
		}
		else
		{
			n++;
			cont = 1;
		}
	}
	if(cont == 1)
	{
		//reset the counter to 0
		alt_u32 snap = 0x0000000;
		*(timer + 4) = snap;
		
		//start the timer
		alt_16 ctrl = *(timer + 1);	//read in info
		ctrl = ctrl | 0x4;			//turn on start bit
		*(timer + 1) = ctrl;		//write info
		
		alt_32 fib_val = Fibs(n);
		
		//stop the timer
		ctrl = *(timer + 1);		//read in info
		ctrl = ctrl | 0x8;			//turn on stop bit
		*(timer + 1) = ctrl;		//write info
		
		//read timer value
		snap = *(timer + 4);
		
		*output = snap;
	}
	*(input+3) = keys; //Disable the interrupt by writing to edgecapture register of KEYs PIO
	return;
}

/****************************************************************************
Interrupt Service Routine
Determines what caused the interrupt and calls the appropriate subroutine.
ipending - Control register 4 which has the pending external interrupts
****************************************************************************/
void interrupt_handler(void)
{
	int ipending;
	ipending = __builtin_rdctl(4); //Read the ipending register
	if ((ipending & 0x02) > 0) //If irq0 is high, run keys_isr,
	{
		keys_isr();
	}
	return;
}

alt_32 Fibs(alt_32 num)
{
	if(num == 1 || num == 2) return 1;
	return (Fibs(num-1) + Fibs(num-2));
}

