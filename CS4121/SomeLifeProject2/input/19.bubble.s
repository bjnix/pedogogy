	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	li $s0, 0
	add $s1, $gp, 28
	li $s0, 32
	sw $s0, 0($s1)
	li $s0, 1
	add $s1, $gp, 28
	li $s0, 11
	sw $s0, 0($s1)
	li $s0, 2
	add $s1, $gp, 28
	li $s0, 111
	sw $s0, 0($s1)
	li $s0, 3
	add $s1, $gp, 28
	li $s0, 88
	sw $s0, 0($s1)
	li $s0, 4
	add $s1, $gp, 28
	li $s0, 11
	sw $s0, 0($s1)
	li $s0, 5
	add $s1, $gp, 28
	li $s0, 44
	sw $s0, 0($s1)
	li $s0, 6
	add $s1, $gp, 28
	li $s0, 33
	sw $s0, 0($s1)
	li $s0, 7
	add $s1, $gp, 28
	li $s0, 33
	sw $s0, 0($s1)
	li $s0, 8
	add $s1, $gp, 28
	li $s0, 22
	sw $s0, 0($s1)
	li $s0, 9
	add $s1, $gp, 28
	li $s0, 77
	sw $s0, 0($s1)
	li $s0, 10
	add $s1, $gp, 28
	li $s0, 45
	sw $s0, 0($s1)
	li $s0, 11
	add $s1, $gp, 28
	li $s0, 65
	sw $s0, 0($s1)
	li $s0, 12
	add $s1, $gp, 28
	li $s0, 76
	sw $s0, 0($s1)
	li $s0, 13
	add $s1, $gp, 28
	li $s0, 87
	sw $s0, 0($s1)
	li $s0, 14
	add $s1, $gp, 28
	li $s0, 34
	sw $s0, 0($s1)
	add $s0, $gp, 4
	li $s1, 0
	sw $s1, 0($s0)
LOOP_0:
	add $s0, $gp, 4
	lw $s1, 0($s0)
	li $s0, 14
	slt $s2, $s1, $s0
	beq $zero,$s2,LOOP_0_END
	add $s0, $gp, 16
	add $s1, $gp, 4
	lw $s3, 0($s1)
	sw $s3, 0($s0)
	add $s0, $gp, 8
	add $s1, $gp, 4
	lw $s3, 0($s1)
	li $s1, 1
	add $s4, $s3, $s1
	sw $s4, 0($s0)
LOOP_1:
	add $s0, $gp, 8
	lw $s1, 0($s0)
	li $s0, 15
	slt $s3, $s1, $s0
	beq $zero,$s3,LOOP_0_END
	add $s0, $gp, 8
	lw $s1, 0($s0)
	add $s0, $gp, 32
	lw $s1, 0($s0)
	add $s0, $gp, 16
	lw $s4, 0($s0)
	add $s0, $gp, 148
	lw $s4, 0($s0)
	slt $s0, $s1, $s4
	beq $zero,$s0,BR_0
	add $s1, $gp, 16
	add $s4, $gp, 8
	lw $s5, 0($s4)
	sw $s5, 0($s1)
BR_0:
	add $s1, $gp, 8
	add $s4, $gp, 8
	lw $s5, 0($s4)
	li $s4, 1
	add $s6, $s5, $s4
	sw $s6, 0($s1)
	j,LOOP_1
LOOP_1_END:
	add $s1, $gp, 12
	add $s4, $gp, 4
	lw $s5, 0($s4)
	add $s4, $gp, 156
	lw $s5, 0($s4)
	sw $s5, 0($s1)
	add $s1, $gp, 4
	lw $s4, 0($s1)
	add $s1, $gp, 148
	add $s4, $gp, 16
	lw $s5, 0($s4)
	add $s4, $gp, 156
	lw $s5, 0($s4)
	sw $s5, 0($s1)
	add $s1, $gp, 16
	lw $s4, 0($s1)
	add $s1, $gp, 148
	add $s4, $gp, 12
	lw $s5, 0($s4)
	sw $s5, 0($s1)
	add $s1, $gp, 4
	add $s4, $gp, 4
	lw $s5, 0($s4)
	li $s4, 1
	add $s6, $s5, $s4
	sw $s6, 0($s1)
	j,LOOP_0
LOOP_0_END:
	add $s1, $gp, 4
	li $s4, 0
	sw $s4, 0($s1)
LOOP_2:
	add $s1, $gp, 4
	lw $s4, 0($s1)
	li $s1, 15
	slt $s5, $s4, $s1
	beq $zero,$s5,LOOP_0_END
	add $s1, $gp, 4
	lw $s4, 0($s1)
	add $s1, $gp, 148
	lw $s4, 0($s1)
	move $a0, $s4
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s1, $gp, 4
	add $s4, $gp, 4
	lw $s6, 0($s4)
	li $s4, 1
	add $s7, $s6, $s4
	sw $s7, 0($s1)
	j,LOOP_2
LOOP_2_END:
	li $v0, 10
	syscall
