# Brent Nix
# 
# CS3421 Lab 3
# Spring 2011
#
# This program contains a function that merges two sorted lists into a single
# sorted list.

	.text
# test program starts here
	la 	$a0,list1       # load argument registers
	la	$a1,list1end
	la	$a2,list2
	la	$a3,list2end
	jal	intersect       # call the function
	lw  $s0,8($v0)	# get reference to node after header
loop:
	lw  $t0,8($s0)	# see if next pointer is null (trailer)
	li  $t1,-1
	beq $t0,$t1,done    # if so, done
	lw  $a0,0($s0)      # get data from node and print it
	li  $v0,1
	syscall
	la  $a0,space       # print a space
	li  $v0,4
	syscall
	lw  $s0,8($s0)      # get reference to next node
	b   loop            # try again
done:
	li  $v0,10          # exit
	syscall
# the lists
	.data
list1:
	.word 	0
	.word 	-1
	.word 	l1a
l1a:
	.word 	-1
	.word 	list1
	.word 	l1b
l1b:
	.word 	4
	.word 	l1a
	.word 	l1c
l1c: 
	.word	17
	.word	l1b
	.word	list1end
list1end:
	.word 	0
	.word 	l1b
	.word 	-1
list2:
	.word 	0
	.word 	-1
	.word 	l2a
l2a:
	.word 	4
	.word 	list2
	.word 	l2b
l2b:
	.word 	9
	.word 	l2a
	.word 	l2c
l2c: 
	.word	17
	.word	l2b
	.word	list2end
list2end:
	.word 	0
	.word 	l2b
	.word 	-1
space:
	.asciiz	" "
	.text

# intersect:
# a function that creates a list of two intersecting nodes
	
intersect:
	lw	$t2, 8($a0)		#list one pointer
	la	$t2, ($t2)		
	
	lw	$t3, 8($a2) 		#list two pointer
	la	$t3, ($t3)		
	
	
	 
	la	$t8, space +4  		# t8 == pointer for new list
	la	$t9, -1			# t9 == pointer for prev
		
	
	
	sw	$zero, ($t8)		#save header
	sw	$t9, 4($t8)  		#save previous
	la	$t8,12($t8)
	sw	$t8,-4($t8)		#save next
			
	la	$s0, ($ra)		# save return address
		
		
INTLOOP:	
	beq 	$t3, $a3, END		#if list is empty, no point in going any further
	beq	$t2, $a1, END		#if list is empty, no point in going any further
	
	lw	$t0,($t2)
	lw	$t1,($t3)
	
	bne	$t0,$t1,ELSE0		#if $t0 == $t1, save and increment both
	
	la	$t9,($t8)		#set the current node to the previous node
	, 
	sw 	$t0,($t8)		#store the value in the new list
	sw	$t9,4($t8)		#store prev
	la	$t8,12($t8)
	sw	$t8,-4($t8)
	
	lw	$t2,8($t2)		#increment pointer for list one
	la	$t2,($t2)
	lw 	$t3,8($t3)		#increment pointer for list two
	la	$t3,($t3)
	
	j 	INTLOOP
	
ELSE0:	blt 	$t0,$t1,ELSE1		#if $t0 is greater than $t1 so increment $t1
	lw 	$t3,8($t3)		#increment pointer for list two
	la 	$t3,($t3)
	
	j	INTLOOP
	
ELSE1: 	lw	$t2,8($t2)		#$t0 is less than $t1, so increment $t0	
	la	$t2,($t2)		#increment pointer for list one
	j	INTLOOP

END:
	li 	$t9, -1
	sw	$zero, ($t8)		#save header
	sw	$t8, 4($t8)  		#save previous
	sw	$t9, 8($t8)		#save next	
	
	la	$v0, space +4
	
	jr 	$s0
	add	
	
	
	
	
	
	
	
