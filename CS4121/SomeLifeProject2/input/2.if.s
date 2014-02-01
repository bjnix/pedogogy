	.data
.newline: .asciiz "\n"
.string0: .asciiz "Enter a:"
.string1: .asciiz "Complete!"
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
	li $s0, 0
	sgt $s2, $s1, $s0
	beq $zero,$s2,BR_0
	li $s0, 1
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	beq $zero,$zero, BR_0_END
BR_0:
	li $s0, 0
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
BR_0_END:
	la $s0, .string1
	move $a0, $s0
	li $v0, 4
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
