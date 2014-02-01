module part1(SW,HEX0,HEX1,HEX2,HEX3,HEX4,HEX5,HEX6,HEX7,LEDR);
	input [17:0] SW;		//toggle switches
	output [6:0] HEX0;		//7-seg display 0 
	output [6:0] HEX1;		//"			  " 1
	output [6:0] HEX2;		//"           " 2
	output [6:0] HEX3;		//"           " 3
	output [6:0] HEX4;		//"           " 4
	output [6:0] HEX5;		//"           " 5
	output [6:0] HEX6;		//"           " 6
	output [6:0] HEX7;		//"           " 7
	output [17:0] LEDR;
	assign LEDR = SW;			//assign the red leds to the switches
	wire [15:0]M;
	
	
	
	
	char_7seg H0 (SW[3:0],HEX0);
	char_7seg H1 (SW[7:4],HEX1);
	char_7seg H2 (SW[11:8],HEX2);
	char_7seg H3 (SW[15:12],HEX3);
	assign HEX4 = 7'b1111111;
	assign HEX5 = 7'b1111111;
	assign HEX6 = 7'b1111111;
	assign HEX7 = 7'b1111111;

endmodule

//implements a 7-segment decoder for binary to decimal
module char_7seg (C,Display);
	input[3:0] C;		//input code
	output[6:0]Display; //output 7-seg code
	
//code for 7-segment decoder

	wire[6:0] b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,BLANK;			
	reg[6:0]OUT;
					//   6    5    4    3    2    1    0
	assign b0[6:0] = 7'b1000000;
	assign b1[6:0] = 7'b1111001;
	assign b2[6:0] = 7'b0100100;
	assign b3[6:0] = 7'b0110000;
	assign b4[6:0] = 7'b0011001;
	assign b5[6:0] = 7'b0010010;
	assign b6[6:0] = 7'b0000010;
	assign b7[6:0] = 7'b1111000;
	assign b8[6:0] = 7'b0000000;
	assign b9[6:0] = 7'b0010000;
	assign BLANK[6:0] = 7'b1111111;
	
	always@(C)
		case(C)
							
			4'b0000 : OUT = b0;
			4'b0001 : OUT = b1;
			4'b0010 : OUT = b2;
			4'b0011 : OUT = b3;
			4'b0100 : OUT = b4;
			4'b0101 : OUT = b5;
			4'b0110 : OUT = b6;
			4'b0111 : OUT = b7;
			4'b1000 : OUT = b8;
			4'b1001 : OUT = b9;
			//4'b1010 : OUT = ;
			//4'b1011 : OUT = ;
			//4'b1100 : OUT = ;
			//4'b1101 : OUT = ;
			//4'b1110 : OUT = ;
			//4'b1111 : OUT = ;
			default : OUT = BLANK;
	endcase	
	assign Display = OUT;
	
	
endmodule