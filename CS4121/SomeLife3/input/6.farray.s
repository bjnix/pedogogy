	.data
.newline: .asciiz "\n"
	.text
	j	main
#symtab start
t:
	sw $ra,76($sp)
	sw $s0,4($sp)
	sw $s1,8($sp)
	sw $s2,12($sp)
	sw $s3,16($sp)
	sw $s4,20($sp)
	sw $s5,24($sp)
	sw $s6,28($sp)
	sw $s7,32($sp)
	move $fp,$sp
	addi $sp,$sp,52
	add $s0, $fp, -4
	li $s1, 2
	sw $s1, 0($s0)
.L0:	nop
	add $s0, $fp, -4
	lw $s1, 0($s0)
	li $s0, 11
	sle $s2, $s1, $s0
	beq $s2, $zero, .L1
	add $s0, $fp, -4
	lw $s1, 0($s0)
	add $s0, $gp, -12
	sub $s1, $s1, 2
	sll $s1, $s1, 2
	add $s0, $s0, $s1
	add $s1, $fp, -4
	lw $s2, 0($s1)
	li $s1, 1
	sub $s3, $s2, $s1
	lw $s2, 0($s1)
	sw $s2, 0($s0)
	add $s0, $fp, -4
	add $s1, $fp, -4
	lw $s2, 0($s1)
	li $s1, 1
	add $s3, $s2, $s1
	sw $s3, 0($s0)
	j .L0
.L1:	 nop
	li $s0, 5
	add $s1, $gp, -12
	sub $s0, $s0, 2
	sll $s0, $s0, 2
	add $s1, $s1, $s0
	lw $s0, 0($s1)
	move $v0, $s0
	move $sp,$fp
	lw $s0,4($sp)
	lw $s1,8($sp)
	lw $s2,12($sp)
	lw $s3,16($sp)
	lw $s4,20($sp)
	lw $s5,24($sp)
	lw $s6,28($sp)
	lw $s7,32($sp)
	lw $fp,($fp)
	lw $ra,76($sp)
	jr	$ra
#symtab end
	.globl main
main:	nop
	move $fp,$sp
	add $s0, $gp, 4
	li $s1, 1
	sw $s1, 0($s0)
.L2:	nop
	add $s0, $gp, 4
	lw $s1, 0($s0)
	li $s0, 10
	sle $s2, $s1, $s0
	beq $s2, $zero, .L3
	add $s0, $gp, 4
	lw $s1, 0($s0)
	add $s0, $gp, 8
	sub $s1, $s1, 1
	sll $s1, $s1, 2
	add $s0, $s0, $s1
	add $s1, $gp, 4
	lw $s2, 0($s1)
	sw $s2, 0($s0)
	add $s0, $gp, 4
	add $s1, $gp, 4
	lw $s2, 0($s1)
	li $s1, 1
	add $s3, $s2, $s1
	sw $s3, 0($s0)
	j .L2
.L3:	 nop
	add $s0, $gp, 4
	sw $ra,-4($sp)
	sw $t0,-8($sp)
	sw $t1,-12($sp)
	sw $t2,-16($sp)
	sw $t3,-20($sp)
	sw $t4,-24($sp)
	sw $t5,-28($sp)
	sw $t6,-32($sp)
	sw $t7,-36($sp)
	sw $t8,-40($sp)
	sw $t9,-44($sp)
	addi $sp,$sp,-80
	sw $fp,0($sp)
	jal	t
	addi $sp, $sp,80
	move $s1, $v0
	lw $t0,-8($sp)
	lw $t1,-12($sp)
	lw $t2,-16($sp)
	lw $t3,-20($sp)
	lw $t4,-24($sp)
	lw $t5,-28($sp)
	lw $t6,-32($sp)
	lw $t7,-36($sp)
	lw $t8,-40($sp)
	lw $t9,-44($sp)
	sw $s1, 0($s0)
	add $s0, $gp, 4
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
