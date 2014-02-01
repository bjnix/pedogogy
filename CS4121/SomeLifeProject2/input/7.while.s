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
	add $s0, $gp, 0
	li $v0, 5
	syscall
	sw $v0, 0($s0)
	add $s0, $gp, 8
	li $s1, 0
	sw $s1, 0($s0)
	add $s0, $gp, 4
	li $s1, 0
	sw $s1, 0($s0)
LOOP_0:
	add $s0, $gp, 4
	lw $s1, 0($s0)
	add $s0, $gp, 0
	lw $s2, 0($s0)
	sle $s0, $s1, $s2
	add $s1, $gp, 4
	lw $s2, 0($s1)
	li $s1, 100
	sle $s3, $s2, $s1
	and $s1, $s0, $s3
	beq $zero,$s1,LOOP_0_END
	add $s0, $gp, 8
	add $s2, $gp, 8
	lw $s3, 0($s2)
	add $s2, $gp, 4
	lw $s4, 0($s2)
	add $s2, $s3, $s4
	sw $s2, 0($s0)
	add $s0, $gp, 4
	add $s2, $gp, 4
	lw $s3, 0($s2)
	li $s2, 1
	add $s4, $s3, $s2
	sw $s4, 0($s0)
	j,LOOP_0
LOOP_0_END:
	add $s0, $gp, 8
	lw $s2, 0($s0)
	move $a0, $s2
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
