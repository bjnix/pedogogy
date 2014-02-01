	.data
.newline: .asciiz "\n"
.string0: .asciiz "Enter a:"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	la $s0, .string0
	move $a0, $s0
	li $v0, 4
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 8
	li $v0, 5
	syscall
	sw $v0, 0($s0)
	add $s0, $gp, 8
	lw $s1, 0($s0)
	li $s0, 2
	div $s2, $s1, $s0
	li $s0, 2
	mul $s1, $s2, $s0
	add $s0, $gp, 8
	lw $s2, 0($s0)
	seq $s0, $s1, $s2
	beq $zero,$s0,BR_0
	add $s1, $gp, 4
	li $s2, 1
	sw $s2, 0($s1)
	add $s1, $gp, 0
	li $s2, 0
	sw $s2, 0($s1)
LOOP_0:
	add $s1, $gp, 4
	lw $s2, 0($s1)
	add $s1, $gp, 8
	lw $s3, 0($s1)
	sle $s1, $s2, $s3
	beq $zero,$s1,LOOP_0_END
	add $s2, $gp, 0
	add $s3, $gp, 0
	lw $s4, 0($s3)
	add $s3, $gp, 4
	lw $s5, 0($s3)
	add $s3, $s4, $s5
	sw $s3, 0($s2)
	add $s2, $gp, 4
	add $s3, $gp, 4
	lw $s4, 0($s3)
	li $s3, 1
	add $s5, $s4, $s3
	sw $s5, 0($s2)
	j,LOOP_0
LOOP_0_END:
	j, BR_0_END
BR_0:
	add $s2, $gp, 4
	li $s3, 1
	sw $s3, 0($s2)
	add $s2, $gp, 0
	li $s3, 1
	sw $s3, 0($s2)
LOOP_1:
	add $s2, $gp, 4
	lw $s3, 0($s2)
	add $s2, $gp, 8
	lw $s4, 0($s2)
	sle $s2, $s3, $s4
	beq $zero,$s2,LOOP_0_END
	add $s3, $gp, 0
	add $s4, $gp, 0
	lw $s5, 0($s4)
	add $s4, $gp, 4
	lw $s6, 0($s4)
	mul $s4, $s5, $s6
	sw $s4, 0($s3)
	add $s3, $gp, 4
	add $s4, $gp, 4
	lw $s5, 0($s4)
	li $s4, 1
	add $s6, $s5, $s4
	sw $s6, 0($s3)
	j,LOOP_1
LOOP_1_END:
BR_0_END:
	add $s3, $gp, 0
	lw $s4, 0($s3)
	move $a0, $s4
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
