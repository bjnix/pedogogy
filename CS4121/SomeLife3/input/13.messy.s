	.data
.newline: .asciiz "\n"
	.text
	j	main
#symtab start
f1:
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
	addi $sp,$sp,8
	add $s0, $fp, -4
	li $s1, 10
	sw $s1, 0($s0)
.L0:	nop
	add $s0, $fp, -4
	lw $s1, 0($s0)
	li $s0, 0
	sgt $s2, $s1, $s0
	beq $s2, $zero, .L1
	add $s0, $fp, -4
	lw $s1, 0($s0)
	add $s0, $gp, 8
	sub 10, 10, 1
	sll 10, 10, 2
	add $s0, $s0, 10
	add $s2, $fp, -4
	lw $s3, 0($s2)
	add $s2, $gp, 8
	sub $s2, $s2, 1
	sll $s2, $s2, 2
	add $s2, $s2, $s2
	lw $s2, 0($s3)
	li $s3, 1
	sub $s4, $s2, $s3
	sw $s4, 0(.L1)
	add $s2, $fp, -4
	add $s3, $fp, -4
	lw $s4, 0($s3)
	li $s3, 1
	sub $s5, $s4, $s3
	sw $s5, 0($s2)
	j .L0
.L1:	 nop
	li $s2, 5
	add $s3, $gp, 8
	sub f1, f1, 1
	sll f1, f1, 2
	add $s3, $s3, f1
	lw $s4, 0(1)
	move $v0, $s4
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
#symtab start
f2:
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
	addi $sp,$sp,48
	add $s4, $fp, -4
	li $s5, 2
	sw $s5, 0($s4)
.L2:	nop
	add $s4, $fp, -4
	lw $s5, 0($s4)
	li $s4, 11
	sle $s6, $s5, $s4
	beq $s6, $zero, .L3
	add $s4, $fp, -4
	lw $s5, 0($s4)
	li $s4, 5
	slt $s6, $s5, $s4
	beq $s6, $zero, .L4
	add $s4, $fp, -4
	lw $s5, 0($s4)
	add $s4, $gp, -8
	sub $s5, $s5, 2
	sll $s5, $s5, 2
	add $s4, $s4, $s5
	li $s5, 0
	sw $s5, 0($s4)
	j .L5
.L4:	 nop
	add $s4, $fp, -4
	lw $s5, 0($s4)
	add $s4, $gp, -8
	sub $s5, $s5, 2
	sll $s5, $s5, 2
	add $s4, $s4, $s5
	li $s5, 1
	sw $s5, 0($s4)
.L5:	 nop
	add $s4, $fp, -4
	add $s5, $fp, -4
	lw $s6, 0($s5)
	li $s5, 1
	add $s7, $s6, $s5
	sw $s7, 0($s4)
	j .L2
.L3:	 nop
	li $s4, 10
	add $s5, $gp, -8
	sub $s4, $s4, 2
	sll $s4, $s4, 2
	add $s5, $s5, $s4
	lw $s4, 0($s5)
	move $v0, $s4
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
	add $s4, $gp, 4
	li $s5, 1
	sw $s5, 0($s4)
.L6:	nop
	add $s4, $gp, 4
	lw $s5, 0($s4)
	li $s4, 10
	sle $s6, $s5, $s4
	beq $s6, $zero, .L7
	add $s4, $gp, 4
	lw $s5, 0($s4)
	add $s4, $gp, 8
	sub $s5, $s5, 1
	sll $s5, $s5, 2
	add $s4, $s4, $s5
	add $s5, $gp, 4
	lw $s6, 0($s5)
	add $s5, $gp, 4
	lw $s7, 0($s5)
	mul $s5, $s6, $s7
	sw $s5, 0($s4)
	add $s4, $gp, 4
	add $s5, $gp, 4
	lw $s6, 0($s5)
	li $s5, 1
	add $s7, $s6, $s5
	sw $s7, 0($s4)
	j .L6
.L7:	 nop
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
	jal	f1
	addi $sp, $sp,80
	move $s4, $v0
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
	move $a0, $s4
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s4, $gp, 4
	lw $s5, 0($s4)
	move $a0, $s5
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
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
	jal	f2
	addi $sp, $sp,80
	move $s4, $v0
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
	move $a0, $s4
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s4, $gp, 4
	lw $s5, 0($s4)
	move $a0, $s5
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $s4, 10
	add $s5, $gp, 8
	sub $s4, $s4, 1
	sll $s4, $s4, 2
	add $s5, $s5, $s4
	lw $s4, 0($s5)
	move $a0, $s4
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
