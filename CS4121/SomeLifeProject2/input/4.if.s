	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	add $s0, $gp, 0
	li $s1, 0
	sw $s1, 0($s0)
	add $s0, $gp, 4
	add $s1, $gp, 0
	lw $s2, 0($s1)
	li $s1, 2
	sub $s3, $s2, $s1
	sw $s3, 0($s0)
	add $s0, $gp, 0
	lw $s1, 0($s0)
	beq $zero,$s1,BR_0
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
	add $s0, $gp, 4
	lw $s2, 0($s0)
	beq $zero,$s2,BR_1
	add $s0, $gp, 0
	lw $s3, 0($s0)
	beq $zero,$s3,BR_2
	li $s0, 0
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	beq $zero,$zero, BR_2_END
BR_2:
	li $s0, 1
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
BR_2_END:
	beq $zero,$zero, BR_1_END
BR_1:
	li $s0, 0
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
BR_1_END:
	li $v0, 10
	syscall
