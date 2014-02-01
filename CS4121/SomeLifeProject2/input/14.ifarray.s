	.data
.newline: .asciiz "\n"
.string0: .asciiz "Enter i:"
.string1: .asciiz "OUT OF BOUND"
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
	add $s0, $gp, 0
	li $v0, 5
	syscall
	sw $v0, 0($s0)
	add $s0, $gp, 0
	lw $s1, 0($s0)
	li $s0, 51
	slt $s2, $s1, $s0
	add $s0, $gp, 0
	lw $s1, 0($s0)
	li $s0, 100
	sgt $s3, $s1, $s0
	or $s0, $s2, $s3
	beq $zero,$s0,BR_0
	la $s1, .string1
	move $a0, $s1
	li $v0, 4
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	j, BR_0_END
BR_0:
	add $s1, $gp, 0
	lw $s2, 0($s1)
	add $s1, $fp, -172
	add $s2, $gp, 0
	lw $s3, 0($s2)
	li $s2, 2
	mul $s4, $s3, $s2
	sw $s4, 0($s1)
	li $s1, 51
	add $s2, $fp, -180
	li $s1, 1
	sw $s1, 0($s2)
	add $s1, $gp, 0
	lw $s2, 0($s1)
	add $s1, $fp, -172
	lw $s2, 0($s1)
	li $s1, 51
	add $s3, $fp, -180
	lw $s1, 0($s3)
	add $s3, $s2, $s1
	move $a0, $s3
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
BR_0_END:
	li $v0, 10
	syscall
