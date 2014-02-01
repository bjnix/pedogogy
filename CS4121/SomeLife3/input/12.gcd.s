	.data
.newline: .asciiz "\n"
	.text
	j	main
#symtab start
gcd:
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
	add $s0, $gp, 8
	lw $s1, 0($s0)
	li $s0, 0
	seq $s2, $s1, $s0
	beq $s2, $zero, .L0
	add $s0, $gp, 4
	lw $s1, 0($s0)
	move $v0, $s1
	j .L1
.L0:	 nop
	add $s0, $fp, -4
	add $s1, $gp, 4
	lw $s2, 0($s1)
	sw $s2, 0($s0)
	add $s0, $gp, 4
	add $s1, $gp, 8
	lw $s2, 0($s1)
	sw $s2, 0($s0)
	add $s0, $gp, 8
	add $s1, $fp, -4
	lw $s2, 0($s1)
	add $s1, $gp, 8
	lw $s3, 0($s1)
	add $s1, $fp, -4
	lw $s4, 0($s1)
	add $s1, $gp, 8
	lw $s5, 0($s1)
	div $s1, $s4, $s5
	mul $s4, $s3, $s1
	sub $s1, $s2, $s4
	sw $s1, 0($s0)
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
	jal	gcd
	addi $sp, $sp,80
	move $s0, $v0
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
	move $v0, $s0
.L1:	 nop
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
	li $v0, 5
	syscall
	sw $v0, 0($s0)
	add $s0, $gp, 8
	li $v0, 5
	syscall
	sw $v0, 0($s0)
.L2:	nop
	add $s0, $gp, 4
	lw $s1, 0($s0)
	li $s0, 0
	sne $s2, $s1, $s0
	add $s0, $gp, 8
	lw $s1, 0($s0)
	li $s0, 0
	sne $s3, $s1, $s0
	or $s0, $s2, $s3
	beq $s0, $zero, .L3
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
	jal	gcd
	addi $sp, $sp,80
	move $s0, $v0
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
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	li $v0, 5
	syscall
	sw $v0, 0($s0)
	add $s0, $gp, 8
	li $v0, 5
	syscall
	sw $v0, 0($s0)
	j .L2
.L3:	 nop
	li $v0, 10
	syscall
