	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	add $s0, $fp, -16
	li $s1, 5
	sw $s1, 0($s0)
LOOP_0:
	add $s0, $fp, -16
	lw $s1, 0($s0)
	li $s0, 15
	sle $s2, $s1, $s0
	beq $zero,$s2,LOOP_0_END
	add $s0, $fp, -16
	lw $s1, 0($s0)
	add $s0, $gp, 0
	add $s1, $fp, -16
	lw $s3, 0($s1)
	sw $s3, 0($s0)
	add $s0, $fp, -16
	add $s1, $fp, -16
	lw $s3, 0($s1)
	li $s1, 1
	add $s4, $s3, $s1
	sw $s4, 0($s0)
	j,LOOP_0
LOOP_0_END:
	add $s0, $fp, -16
	li $s1, 5
	sw $s1, 0($s0)
LOOP_1:
	add $s0, $fp, -16
	lw $s1, 0($s0)
	li $s0, 15
	sle $s3, $s1, $s0
	beq $zero,$s3,LOOP_0_END
	add $s0, $fp, -16
	lw $s1, 0($s0)
	li $s0, 10
	sgt $s4, $s1, $s0
	beq $zero,$s4,BR_0
	add $s0, $fp, -16
	lw $s1, 0($s0)
	add $s0, $gp, 0
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	j, BR_0_END
BR_0:
	li $s0, 0
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
BR_0_END:
	add $s0, $fp, -16
	add $s1, $fp, -16
	lw $s5, 0($s1)
	li $s1, 1
	add $s6, $s5, $s1
	sw $s6, 0($s0)
	j,LOOP_1
LOOP_1_END:
	li $v0, 10
	syscall
