	.data
.newline: .asciiz "\n"
	.text
	j	main
#symtab start
b4:
	sw $ra,76($sp)
	sw $s0,4($sp)
	sw $s1,8($sp)
	sw $s2,12($sp)
	sw $s3,16($sp)
	sw $s4,20($sp)
	sw $s5,24($sp)
	sw $s6,28($sp)
	sw $s7,32($sp)
	move $fp,$sp
	addi $sp,$sp,4
	li $s0, 4
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	lw $s1, 0($s0)
	li $s0, 1
	add $s2, $s1, $s0
	move $a0, $s2
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	lw $s1, 0($s0)
	li $s0, 1
	add $s2, $s1, $s0
	move $v0, $s2
	move $sp,$fp
	lw $s0,4($sp)
	lw $s1,8($sp)
	lw $s2,12($sp)
	lw $s3,16($sp)
	lw $s4,20($sp)
	lw $s5,24($sp)
	lw $s6,28($sp)
	lw $s7,32($sp)
	lw $fp,($fp)
	lw $ra,76($sp)
	jr	$ra
#symtab end
#symtab start
d2:
	sw $ra,76($sp)
	sw $s0,4($sp)
	sw $s1,8($sp)
	sw $s2,12($sp)
	sw $s3,16($sp)
	sw $s4,20($sp)
	sw $s5,24($sp)
	sw $s6,28($sp)
	sw $s7,32($sp)
	move $fp,$sp
	addi $sp,$sp,8
	add $s0, $gp, 8
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $fp, -4
	add $s1, $gp, 4
	lw $s2, 0($s1)
	sw $s2, 0($s0)
	add $s0, $gp, 4
	add $s1, $gp, 8
	lw $s2, 0($s1)
	sw $s2, 0($s0)
	add $s0, $gp, 8
	add $s1, $fp, -4
	lw $s2, 0($s1)
	sw $s2, 0($s0)
	li $s0, 1
	move $v0, $s0
	move $sp,$fp
	lw $s0,4($sp)
	lw $s1,8($sp)
	lw $s2,12($sp)
	lw $s3,16($sp)
	lw $s4,20($sp)
	lw $s5,24($sp)
	lw $s6,28($sp)
	lw $s7,32($sp)
	lw $fp,($fp)
	lw $ra,76($sp)
	jr	$ra
#symtab end
#symtab start
d3:
	sw $ra,76($sp)
	sw $s0,4($sp)
	sw $s1,8($sp)
	sw $s2,12($sp)
	sw $s3,16($sp)
	sw $s4,20($sp)
	sw $s5,24($sp)
	sw $s6,28($sp)
	sw $s7,32($sp)
	move $fp,$sp
	addi $sp,$sp,4
	add $s0, $gp, 4
	add $s1, $gp, 4
	lw $s2, 0($s1)
	li $s1, 1
	add $s3, $s2, $s1
	sw $s3, 0($s0)
	add $s0, $gp, 8
	add $s1, $gp, 8
	lw $s2, 0($s1)
	li $s1, 2
	add $s3, $s2, $s1
	sw $s3, 0($s0)
	add $s0, $gp, 12
	add $s1, $gp, 12
	lw $s2, 0($s1)
	li $s1, 3
	add $s3, $s2, $s1
	sw $s3, 0($s0)
	add $s0, $gp, 16
	add $s1, $gp, 16
	lw $s2, 0($s1)
	sw $s2, 0($s0)
	add $s0, $gp, 12
	lw $s1, 0($s0)
	move $v0, $s1
	move $sp,$fp
	lw $s0,4($sp)
	lw $s1,8($sp)
	lw $s2,12($sp)
	lw $s3,16($sp)
	lw $s4,20($sp)
	lw $s5,24($sp)
	lw $s6,28($sp)
	lw $s7,32($sp)
	lw $fp,($fp)
	lw $ra,76($sp)
	jr	$ra
#symtab end
#symtab start
d1:
	sw $ra,76($sp)
	sw $s0,4($sp)
	sw $s1,8($sp)
	sw $s2,12($sp)
	sw $s3,16($sp)
	sw $s4,20($sp)
	sw $s5,24($sp)
	sw $s6,28($sp)
	sw $s7,32($sp)
	move $fp,$sp
	addi $sp,$sp,8
	add $s0, $fp, -4
	add $s1, $gp, 4
	lw $s2, 0($s1)
	li $s1, 200
	mul $s3, $s2, $s1
	sw $s3, 0($s0)
	add $s0, $gp, 4
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $fp, -4
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 12
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 16
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	sw $ra,-4($sp)
	sw $t0,-8($sp)
	sw $t1,-12($sp)
	sw $t2,-16($sp)
	sw $t3,-20($sp)
	sw $t4,-24($sp)
	sw $t5,-28($sp)
	sw $t6,-32($sp)
	sw $t7,-36($sp)
	sw $t8,-40($sp)
	sw $t9,-44($sp)
	addi $sp,$sp,-80
	sw $fp,0($sp)
	jal	d2
	addi $sp, $sp,80
	move $s0, $v0
	lw $t0,-8($sp)
	lw $t1,-12($sp)
	lw $t2,-16($sp)
	lw $t3,-20($sp)
	lw $t4,-24($sp)
	lw $t5,-28($sp)
	lw $t6,-32($sp)
	lw $t7,-36($sp)
	lw $t8,-40($sp)
	lw $t9,-44($sp)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $fp, -4
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 12
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 16
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	sw $ra,-4($sp)
	sw $t0,-8($sp)
	sw $t1,-12($sp)
	sw $t2,-16($sp)
	sw $t3,-20($sp)
	sw $t4,-24($sp)
	sw $t5,-28($sp)
	sw $t6,-32($sp)
	sw $t7,-36($sp)
	sw $t8,-40($sp)
	sw $t9,-44($sp)
	addi $sp,$sp,-80
	sw $fp,0($sp)
	jal	d3
	addi $sp, $sp,80
	move $s0, $v0
	lw $t0,-8($sp)
	lw $t1,-12($sp)
	lw $t2,-16($sp)
	lw $t3,-20($sp)
	lw $t4,-24($sp)
	lw $t5,-28($sp)
	lw $t6,-32($sp)
	lw $t7,-36($sp)
	lw $t8,-40($sp)
	lw $t9,-44($sp)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $fp, -4
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 12
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 16
	lw $s1, 0($s0)
	move $a0, $s1
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $fp, -4
	lw $s1, 0($s0)
	move $v0, $s1
	move $sp,$fp
	lw $s0,4($sp)
	lw $s1,8($sp)
	lw $s2,12($sp)
	lw $s3,16($sp)
	lw $s4,20($sp)
	lw $s5,24($sp)
	lw $s6,28($sp)
	lw $s7,32($sp)
	lw $fp,($fp)
	lw $ra,76($sp)
	jr	$ra
#symtab end
	.globl main
main:	nop
	move $fp,$sp
	add $s0, $gp, 4
	li $s1, 1
	sw $s1, 0($s0)
	add $s0, $gp, 8
	li $s1, 2
	sw $s1, 0($s0)
	add $s0, $gp, 12
	li $s1, 3
	sw $s1, 0($s0)
	add $s0, $gp, 16
	li $s1, 4
	sw $s1, 0($s0)
	add $s0, $gp, 4
	li $s1, 2
	sw $s1, 0($s0)
	sw $ra,-4($sp)
	sw $t0,-8($sp)
	sw $t1,-12($sp)
	sw $t2,-16($sp)
	sw $t3,-20($sp)
	sw $t4,-24($sp)
	sw $t5,-28($sp)
	sw $t6,-32($sp)
	sw $t7,-36($sp)
	sw $t8,-40($sp)
	sw $t9,-44($sp)
	addi $sp,$sp,-80
	sw $fp,0($sp)
	jal	b4
	addi $sp, $sp,80
	move $s0, $v0
	lw $t0,-8($sp)
	lw $t1,-12($sp)
	lw $t2,-16($sp)
	lw $t3,-20($sp)
	lw $t4,-24($sp)
	lw $t5,-28($sp)
	lw $t6,-32($sp)
	lw $t7,-36($sp)
	lw $t8,-40($sp)
	lw $t9,-44($sp)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	sw $ra,-4($sp)
	sw $t0,-8($sp)
	sw $t1,-12($sp)
	sw $t2,-16($sp)
	sw $t3,-20($sp)
	sw $t4,-24($sp)
	sw $t5,-28($sp)
	sw $t6,-32($sp)
	sw $t7,-36($sp)
	sw $t8,-40($sp)
	sw $t9,-44($sp)
	addi $sp,$sp,-80
	sw $fp,0($sp)
	jal	d1
	addi $sp, $sp,80
	move $s0, $v0
	lw $t0,-8($sp)
	lw $t1,-12($sp)
	lw $t2,-16($sp)
	lw $t3,-20($sp)
	lw $t4,-24($sp)
	lw $t5,-28($sp)
	lw $t6,-32($sp)
	lw $t7,-36($sp)
	lw $t8,-40($sp)
	lw $t9,-44($sp)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	add $s0, $gp, 4
	li $s1, 1
	sw $s1, 0($s0)
	sw $ra,-4($sp)
	sw $t0,-8($sp)
	sw $t1,-12($sp)
	sw $t2,-16($sp)
	sw $t3,-20($sp)
	sw $t4,-24($sp)
	sw $t5,-28($sp)
	sw $t6,-32($sp)
	sw $t7,-36($sp)
	sw $t8,-40($sp)
	sw $t9,-44($sp)
	addi $sp,$sp,-80
	sw $fp,0($sp)
	jal	b4
	addi $sp, $sp,80
	move $s0, $v0
	lw $t0,-8($sp)
	lw $t1,-12($sp)
	lw $t2,-16($sp)
	lw $t3,-20($sp)
	lw $t4,-24($sp)
	lw $t5,-28($sp)
	lw $t6,-32($sp)
	lw $t7,-36($sp)
	lw $t8,-40($sp)
	lw $t9,-44($sp)
	move $a0, $s0
	li $v0, 1
	syscall
	li $v0, 4
	la, $a0, .newline
	syscall
	li $v0, 10
	syscall