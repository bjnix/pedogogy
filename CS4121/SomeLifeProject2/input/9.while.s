	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	add $s0, $gp, 0
	li $s1, 0
	sw $s1, 0($s0)
LOOP_0:
	add $s0, $gp, 0
	lw $s1, 0($s0)
	li $s0, 50
	slt $s2, $s1, $s0
	beq $zero,$s2,LOOP_0_END
	add $s0, $gp, 4
	li $s1, 0
	sw $s1, 0($s0)
LOOP_1:
	add $s0, $gp, 4
	lw $s1, 0($s0)
	li $s0, 10
	slt $s3, $s1, $s0
	beq $zero,$s3,LOOP_0_END
	add $s0, $gp, 0
	lw $s1, 0($s0)
	add $s0, $gp, 4
	lw $s4, 0($s0)
	add $s0, $s1, $s4
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	add $s1, $gp, 4
	lw $s4, 0($s1)
	li $s1, 2
	add $s5, $s4, $s1
	sw $s5, 0($s0)
	j,LOOP_1
LOOP_1_END:
	add $s0, $gp, 8
	li $s1, 0
	add $s4, $gp, 4
	lw $s5, 0($s4)
	sub $s4, $s1, $s5
	sw $s4, 0($s0)
LOOP_2:
	add $s0, $gp, 8
	lw $s1, 0($s0)
	add $s0, $gp, 4
	lw $s4, 0($s0)
	sne $s0, $s1, $s4
	beq $zero,$s0,LOOP_0_END
	add $s1, $gp, 8
	lw $s4, 0($s1)
	move $a0, $s4
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s1, $gp, 8
	add $s4, $gp, 8
	lw $s5, 0($s4)
	li $s4, 1
	add $s6, $s5, $s4
	sw $s6, 0($s1)
	j,LOOP_2
LOOP_2_END:
	add $s1, $gp, 0
	add $s4, $gp, 0
	lw $s5, 0($s4)
	li $s4, 10
	add $s6, $s5, $s4
	sw $s6, 0($s1)
	j,LOOP_0
LOOP_0_END:
	li $v0, 10
	syscall
