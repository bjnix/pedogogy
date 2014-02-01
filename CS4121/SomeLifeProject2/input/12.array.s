	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	li $s0, 3
	add $s1, $gp, 16
	li $s0, 4
	sw $s0, 0($s1)
	li $s0, 3
	add $s1, $gp, 16
	lw $s0, 0($s1)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	li $s1, 3
	sw $s1, 0($s0)
	add $s0, $gp, 4
	lw $s1, 0($s0)
	add $s0, $gp, 20
	li $s1, 7
	sw $s1, 0($s0)
	add $s0, $gp, 4
	lw $s1, 0($s0)
	add $s0, $gp, 20
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	add $s1, $gp, 4
	lw $s2, 0($s1)
	li $s1, 2
	add $s3, $s2, $s1
	sw $s3, 0($s0)
	add $s0, $gp, 4
	lw $s1, 0($s0)
	add $s0, $gp, 20
	li $s1, 22
	sw $s1, 0($s0)
	li $s0, 5
	add $s1, $gp, 16
	lw $s0, 0($s1)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
