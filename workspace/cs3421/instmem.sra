# test SRA instruction

00 8c150000     lw	$21,0		# load 0x80000000
00 00151943	sra	$3,$21,5	# $3 should become 0xfc000000
01 ac030004	sw	$3,4
02 fc000000	halt
