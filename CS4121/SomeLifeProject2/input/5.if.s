	.data
.newline: .asciiz "\n"
.string0: .asciiz "enter a:"
.string1: .asciiz "enter b:"
.string2: .asciiz "a = "
.string3: .asciiz "b = "
.string4: .asciiz "a = "
.string5: .asciiz "b = "
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
	la $s0, .string1
	move $a0, $s0
	li $v0, 4
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	li $v0, 5
	syscall
	sw $v0, 0($s0)
	add $s0, $gp, 4
	lw $s1, 0($s0)
	add $s0, $gp, 0
	lw $s2, 0($s0)
	sgt $s0, $s1, $s2
	beq $zero,$s0,BR_0
	add $s1, $gp, 0
	lw $s2, 0($s1)
	li $s1, 0
	sgt $s3, $s2, $s1
	beq $zero,$s3,BR_1
	la $s1, .string2
	move $a0, $s1
	li $v0, 4
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s1, $gp, 0
	lw $s2, 0($s1)
	move $a0, $s2
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	beq $zero,$zero, BR_1_END
BR_1:
	la $s1, .string3
	move $a0, $s1
	li $v0, 4
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s1, $gp, 4
	lw $s2, 0($s1)
	move $a0, $s2
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
BR_1_END:
	beq $zero,$zero, BR_0_END
BR_0:
	add $s1, $gp, 0
	lw $s2, 0($s1)
	li $s1, 0
	slt $s4, $s2, $s1
	beq $zero,$s4,BR_2
	la $s1, .string4
	move $a0, $s1
	li $v0, 4
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s1, $gp, 0
	lw $s2, 0($s1)
	move $a0, $s2
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	beq $zero,$zero, BR_2_END
BR_2:
	la $s1, .string5
	move $a0, $s1
	li $v0, 4
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s1, $gp, 4
	lw $s2, 0($s1)
	move $a0, $s2
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
BR_2_END:
BR_0_END:
	li $v0, 10
	syscall
