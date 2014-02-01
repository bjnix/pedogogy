	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	li $s0, 3
	add $s1, $gp, 12
	li $s0, 4
	sw $s0, 0($s1)
	li $s0, 3
	add $s1, $gp, 12
	lw $s0, 0($s1)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
