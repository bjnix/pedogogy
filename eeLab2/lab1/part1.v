module part1 (SW,LEDR);
	input[17:0]SW;			//toggle switches
	output[17:0]LEDR;		//red LED's
	
	assign LEDR=SW;
endmodule