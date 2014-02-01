	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	add $s0, $fp, -4
	li $s1, 8
	sw $s1, 0($s0)
	add $s0, $fp, -4
	lw $s1, 0($s0)
	add $s0, $gp, 16
	li $s1, 18
	sw $s1, 0($s0)
	add $s0, $fp, -4
	lw $s1, 0($s0)
	add $s0, $gp, 16
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 0
	li $s1, 9
	sw $s1, 0($s0)
	add $s0, $gp, 0
	lw $s1, 0($s0)
	add $s0, $gp, 16
	li $s1, 19
	sw $s1, 0($s0)
	add $s0, $gp, 0
	lw $s1, 0($s0)
	add $s0, $gp, 16
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $fp, -4
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 0
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
