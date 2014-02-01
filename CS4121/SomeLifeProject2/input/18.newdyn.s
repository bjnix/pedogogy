	.data
.newline: .asciiz "\n"
	.text
	.globl main
main:	nop
	move	$fp,$sp
	add $s0, $gp, 16
	li $s1, 30
	sw $s1, 0($s0)
	add $s0, $gp, 12
	li $s1, 30
	sw $s1, 0($s0)
	add $s0, $gp, 20
	li $s1, 0
	sw $s1, 0($s0)
LOOP_0:
	add $s0, $gp, 20
	lw $s1, 0($s0)
	add $s0, $gp, 16
	lw $s2, 0($s0)
	slt $s0, $s1, $s2
	beq $zero,$s0,LOOP_0_END
	add $s1, $gp, 20
	lw $s2, 0($s1)
	add $s1, $gp, 16
	lw $s3, 0($s1)
	mul $s1, $s2, $s3
	add $s2, $gp, 48
	add $s1, $gp, 20
	lw $s3, 0($s1)
	sw $s3, 0($s2)
	add $s1, $gp, 20
	lw $s2, 0($s1)
	add $s1, $gp, 52
	add $s2, $gp, 20
	lw $s3, 0($s2)
	li $s2, 1
	add $s4, $s3, $s2
	sw $s4, 0($s1)
	add $s1, $gp, 20
	add $s2, $gp, 20
	lw $s3, 0($s2)
	li $s2, 1
	add $s4, $s3, $s2
	sw $s4, 0($s1)
	j,LOOP_0
LOOP_0_END:
	add $s1, $gp, 24
	li $s2, 0
	sw $s2, 0($s1)
LOOP_1:
	add $s1, $gp, 24
	lw $s2, 0($s1)
	add $s1, $gp, 12
	lw $s3, 0($s1)
	slt $s1, $s2, $s3
	beq $zero,$s1,LOOP_0_END
	add $s2, $gp, 24
	lw $s3, 0($s2)
	add $s2, $gp, 60
	add $s3, $gp, 24
	lw $s4, 0($s3)
	sw $s4, 0($s2)
	add $s2, $gp, 24
	lw $s3, 0($s2)
	add $s2, $gp, 56
	li $s3, 2
	add $s4, $gp, 24
	lw $s5, 0($s4)
	li $s4, 1
	add $s6, $s5, $s4
	mul $s4, $s3, $s6
	sw $s4, 0($s2)
	add $s2, $gp, 24
	add $s3, $gp, 24
	lw $s4, 0($s3)
	li $s3, 1
	add $s5, $s4, $s3
	sw $s5, 0($s2)
	j,LOOP_1
LOOP_1_END:
	add $s2, $gp, 20
	li $s3, 1
	sw $s3, 0($s2)
LOOP_2:
	add $s2, $gp, 20
	lw $s3, 0($s2)
	add $s2, $gp, 16
	lw $s4, 0($s2)
	slt $s2, $s3, $s4
	beq $zero,$s2,LOOP_0_END
	add $s3, $gp, 24
	li $s4, 1
	sw $s4, 0($s3)
LOOP_3:
	add $s3, $gp, 24
	lw $s4, 0($s3)
	add $s3, $gp, 12
	lw $s5, 0($s3)
	slt $s3, $s4, $s5
	beq $zero,$s3,LOOP_0_END
	add $s4, $gp, 20
	lw $s5, 0($s4)
	add $s4, $gp, 16
	lw $s6, 0($s4)
	mul $s4, $s5, $s6
	add $s5, $gp, 24
	lw $s6, 0($s5)
	add $s5, $s4, $s6
	add $s4, $gp, 76
	add $s5, $gp, 20
	lw $s6, 0($s5)
	li $s5, 1
	sub $s7, $s6, $s5
	add $s5, $gp, 16
	lw $s6, 0($s5)
	mul $s5, $s7, $s6
	add $s6, $gp, 24
	lw $s7, 0($s6)
	add $s6, $s5, $s7
	li $s5, 1
	sub $s7, $s6, $s5
	add $s5, $gp, 84
	lw $s6, 0($s5)
	sw $s6, 0($s4)
	add $s4, $gp, 20
	lw $s5, 0($s4)
	add $s4, $gp, 72
	lw $s5, 0($s4)
	add $s4, $gp, 24
	lw $s6, 0($s4)
	add $s4, $gp, 72
	lw $s6, 0($s4)
	sne $s4, $s5, $s6
	beq $zero,$s4,BR_0
	add $s5, $gp, 20
	lw $s6, 0($s5)
	add $s5, $gp, 16
	lw $s7, 0($s5)
	mul $s5, $s6, $s7
	add $s6, $gp, 24
	lw $s7, 0($s6)
	add $s6, $s5, $s7
	add $s5, $gp, 80
	add $s6, $gp, 20
	lw $s7, 0($s6)
	add $s6, $gp, 16
	lw $t0, 0($s6)
	mul $s6, $s7, $t0
	add $s7, $gp, 24
	lw $t0, 0($s7)
	add $s7, $s6, $t0
	add $s6, $gp, 84
	lw $s7, 0($s6)
	li $s6, 1
	add $t0, $s7, $s6
	sw $t0, 0($s5)
BR_0:
	add $s5, $gp, 20
	lw $s6, 0($s5)
	li $s5, 1
	sub $s7, $s6, $s5
	add $s5, $gp, 16
	lw $s6, 0($s5)
	mul $s5, $s7, $s6
	add $s6, $gp, 24
	lw $s7, 0($s6)
	add $s6, $s5, $s7
	add $s5, $gp, 80
	lw $s6, 0($s5)
	add $s5, $gp, 20
	lw $s7, 0($s5)
	add $s5, $gp, 16
	lw $t0, 0($s5)
	mul $s5, $s7, $t0
	add $s7, $gp, 24
	lw $t0, 0($s7)
	add $s7, $s5, $t0
	add $s5, $gp, 84
	lw $s7, 0($s5)
	slt $s5, $s6, $s7
	beq $zero,$s5,BR_1
	add $s6, $gp, 20
	lw $s7, 0($s6)
	add $s6, $gp, 16
	lw $t0, 0($s6)
	mul $s6, $s7, $t0
	add $s7, $gp, 24
	lw $t0, 0($s7)
	add $s7, $s6, $t0
	add $s6, $gp, 84
	add $s7, $gp, 20
	lw $t0, 0($s7)
	li $s7, 1
	sub $t1, $t0, $s7
	add $s7, $gp, 16
	lw $t0, 0($s7)
	mul $s7, $t1, $t0
	add $t0, $gp, 24
	lw $t1, 0($t0)
	add $t0, $s7, $t1
	add $s7, $gp, 88
	lw $t0, 0($s7)
	li $s7, 1
	add $t1, $t0, $s7
	sw $t1, 0($s6)
BR_1:
	add $s6, $gp, 20
	lw $s7, 0($s6)
	add $s6, $gp, 16
	lw $t0, 0($s6)
	mul $s6, $s7, $t0
	add $s7, $gp, 24
	lw $t0, 0($s7)
	add $s7, $s6, $t0
	li $s6, 1
	sub $t0, $s7, $s6
	add $s6, $gp, 88
	lw $s7, 0($s6)
	add $s6, $gp, 20
	lw $t0, 0($s6)
	add $s6, $gp, 16
	lw $t1, 0($s6)
	mul $s6, $t0, $t1
	add $t0, $gp, 24
	lw $t1, 0($t0)
	add $t0, $s6, $t1
	add $s6, $gp, 88
	lw $t0, 0($s6)
	slt $s6, $s7, $t0
	beq $zero,$s6,BR_2
	add $s7, $gp, 20
	lw $t0, 0($s7)
	add $s7, $gp, 16
	lw $t1, 0($s7)
	mul $s7, $t0, $t1
	add $t0, $gp, 24
	lw $t1, 0($t0)
	add $t0, $s7, $t1
	add $s7, $gp, 88
	add $t0, $gp, 20
	lw $t1, 0($t0)
	add $t0, $gp, 16
	lw $t2, 0($t0)
	mul $t0, $t1, $t2
	add $t1, $gp, 24
	lw $t2, 0($t1)
	add $t1, $t0, $t2
	li $t0, 1
	sub $t2, $t1, $t0
	add $t0, $gp, 96
	lw $t1, 0($t0)
	li $t0, 1
	add $t2, $t1, $t0
	sw $t2, 0($s7)
BR_2:
	add $s7, $gp, 24
	add $t0, $gp, 24
	lw $t1, 0($t0)
	li $t0, 1
	add $t2, $t1, $t0
	sw $t2, 0($s7)
	j,LOOP_3
LOOP_3_END:
	add $s7, $gp, 20
	add $t0, $gp, 20
	lw $t1, 0($t0)
	li $t0, 1
	add $t2, $t1, $t0
	sw $t2, 0($s7)
	j,LOOP_2
LOOP_2_END:
	add $s7, $gp, 16
	lw $t0, 0($s7)
	move $a0, $t0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s7, $gp, 12
	lw $t0, 0($s7)
	move $a0, $t0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $s7, 899
	add $t0, $gp, 84
	lw $s7, 0($t0)
	move $a0, $s7
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall
