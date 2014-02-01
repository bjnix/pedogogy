	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	add $s0, $gp, 0
	li $s1, 1
	sw $s1, 0($s0)
LOOP_0:
	add $s0, $gp, 0
	lw $s1, 0($s0)
	li $s0, 50
	sle $s2, $s1, $s0
	beq $zero,$s2,LOOP_0_END
	add $s0, $gp, 0
	lw $s1, 0($s0)
	add $s0, $gp, 28
	add $s1, $gp, 0
	lw $s3, 0($s1)
	sw $s3, 0($s0)
	add $s0, $gp, 0
	add $s1, $gp, 0
	lw $s3, 0($s1)
	li $s1, 1
	add $s4, $s3, $s1
	sw $s4, 0($s0)
	j,LOOP_0
LOOP_0_END:
	add $s0, $gp, 4
	li $s1, 51
	sw $s1, 0($s0)
LOOP_1:
	add $s0, $gp, 4
	lw $s1, 0($s0)
	li $s0, 100
	sle $s3, $s1, $s0
	beq $zero,$s3,LOOP_0_END
	add $s0, $gp, 4
	lw $s1, 0($s0)
	add $s0, $fp, -160
	add $s1, $gp, 4
	lw $s4, 0($s1)
	sw $s4, 0($s0)
	add $s0, $gp, 4
	add $s1, $gp, 4
	lw $s4, 0($s1)
	li $s1, 1
	add $s5, $s4, $s1
	sw $s5, 0($s0)
	j,LOOP_1
LOOP_1_END:
	add $s0, $gp, 0
	li $s1, 1
	sw $s1, 0($s0)
	add $s0, $gp, 8
	li $s1, 0
	sw $s1, 0($s0)
LOOP_2:
	add $s0, $gp, 0
	lw $s1, 0($s0)
	li $s0, 100
	sle $s4, $s1, $s0
	beq $zero,$s4,LOOP_0_END
	add $s0, $gp, 0
	lw $s1, 0($s0)
	li $s0, 50
	sle $s5, $s1, $s0
	beq $zero,$s5,BR_0
	add $s0, $gp, 8
	add $s1, $gp, 8
	lw $s6, 0($s1)
	add $s1, $gp, 0
	lw $s7, 0($s1)
	add $s1, $gp, 68
	lw $s7, 0($s1)
	add $s1, $s6, $s7
	sw $s1, 0($s0)
	j, BR_0_END
BR_0:
	add $s0, $gp, 8
	add $s1, $gp, 8
	lw $s6, 0($s1)
	add $s1, $gp, 0
	lw $s7, 0($s1)
	add $s1, $fp, -120
	lw $s7, 0($s1)
	add $s1, $s6, $s7
	sw $s1, 0($s0)
BR_0_END:
	add $s0, $gp, 0
	add $s1, $gp, 0
	lw $s6, 0($s1)
	li $s1, 1
	add $s7, $s6, $s1
	sw $s7, 0($s0)
	j,LOOP_2
LOOP_2_END:
	add $s0, $gp, 8
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $s0, 5
	add $s1, $gp, 20
	lw $s0, 0($s1)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $s0, 60
	add $s1, $fp, -168
	lw $s0, 0($s1)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
