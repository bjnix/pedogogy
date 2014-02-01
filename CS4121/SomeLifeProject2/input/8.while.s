	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	add $s0, $gp, 4
	li $s1, 1
	sw $s1, 0($s0)
LOOP_0:
	add $s0, $gp, 4
	lw $s1, 0($s0)
	li $s0, 9
	sle $s2, $s1, $s0
	beq $zero,$s2,LOOP_0_END
	add $s0, $gp, 0
	li $s1, 1
	sw $s1, 0($s0)
LOOP_1:
	add $s0, $gp, 0
	lw $s1, 0($s0)
	li $s0, 9
	sle $s3, $s1, $s0
	beq $zero,$s3,LOOP_0_END
	add $s0, $gp, 0
	lw $s1, 0($s0)
	add $s0, $gp, 4
	lw $s4, 0($s0)
	mul $s0, $s1, $s4
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 0
	add $s1, $gp, 0
	lw $s4, 0($s1)
	li $s1, 1
	add $s5, $s4, $s1
	sw $s5, 0($s0)
	j,LOOP_1
LOOP_1_END:
	add $s0, $gp, 4
	add $s1, $gp, 4
	lw $s4, 0($s1)
	li $s1, 1
	add $s5, $s4, $s1
	sw $s5, 0($s0)
	j,LOOP_0
LOOP_0_END:
	li $v0, 10
	syscall
