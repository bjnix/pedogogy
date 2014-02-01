module part4 (SW,LEDR,HEX0);
	input[17:0]SW;			//toggle switches
	output[17:0]LEDR;		//red LED's
	output[6:0]HEX0;
	wire[6:0] H,E,L,O,BLANK;			//green LED's
	reg[6:0]OUT;
	
	assign LEDR = SW;
	
	assign H[6:0] = {1'b0,1'b0,1'b0,1'b1,1'b0,1'b0,1'b1};
	assign E[6:0] = {1'b0,1'b0,1'b0,1'b0,1'b1,1'b1,1'b0};
	assign L[6:0] = {1'b1,1'b0,1'b0,1'b0,1'b1,1'b1,1'b1};
	assign O[6:0] = {1'b1,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0};
	assign BLANK[6:0] = {1'b1,1'b1,1'b1,1'b1,1'b1,1'b1,1'b1};
	
	always@(SW)
		case(SW[17:15])
			3'b000 : OUT = H;
			3'b001 : OUT = E;
			3'b010 : OUT = L;
			3'b011 : OUT = O;
			default : OUT = BLANK;
	endcase	
	assign HEX0 = OUT;
	
	
	endmodule
	