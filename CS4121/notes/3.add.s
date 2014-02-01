	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	li $s0, 10
	li $s1, 20
	add $s2, $s0, $s1
	move $a0, $s2
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 0
	li $s1, 1
	sw $s1, 0($s0)
	add $s0, $gp, 8
	li $s1, 3
	sw $s1, 0($s0)
	add $s0, $gp, 12
	li $s1, 4
	sw $s1, 0($s0)
	add $s0, $gp, 4
	add $s1, $gp, 0
	lw $s2, 0($s1)
	add $s1, $gp, 12
	lw $s3, 0($s1)
	add $s1, $s2, $s3
	add $s2, $gp, 8
	lw $s3, 0($s2)
	add $s2, $s1, $s3
	sw $s2, 0($s0)
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
