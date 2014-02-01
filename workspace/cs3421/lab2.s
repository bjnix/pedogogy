# Brent Nix
#
# CS3421 Lab 2
# Spring 2011
#
# This program reads an integer and outputs it in 2's complement.

        .text
        
        #promt user for input
        li $v0, 5
	syscall
	
	#shift number to the left side of the word
	sll $t0, $v0,24 
	
	#initialize i at zero and set ending condition
	add $t1, $zero, $zero 
	addi $t2, $zero, 8
	
	#loop through and spit out the didgits
loop:		
		#isolate the most signifigant bit
		srl $a0, $t0, 31
		
		#print it
		li $v0,1
		syscall
		
		#get rid of it
		sll $t0, $t0, 1 
		
		#i++
		addi $t1, $t1, 1
	# loop for 7 more times
	blt $t1, $t2, loop 
	
	#end it like a champ
	li $v0, 10
	syscall

 


