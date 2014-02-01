module part4(SW,LEDR,HEX0,HEX1);
	
	input [17:0] SW;		//toggle switches
	output [6:0] HEX0;		//7-seg display 0 
	output [6:0] HEX1;		//"			  " 1
	output [17:0] LEDR;		//red leds
	

	assign LEDR = SW;			//assign the red leds to the switches
	
	wire [15:0] U;
//inputs
	char_7seg H3 (SW[3:0],HEX0,U);
	char_7seg H4 (SW[7:4],HEX1,U);
	char_7seg H5 (SW[11:8],HEX2,U);
	char_7seg H6 (SW[15:12],HEX3,U);
	
	adder (U[3:0],U[4:7],1'b0,G,M,Cout);
	
	
//outputs
	char_7seg H0 (M[3:0],HEX0);
	char_7seg H1 (M[3:0],HEX1);
	char_7seg H2 (M[3:0],HEX2);
	
	
	

endmodule

module adder(A,B,C,G,M,Cout)
	output [3:0] M;
	output Cout;
	wire [3:0] A;
	wire [7:4] B;
	wire C;			
	
	assign A = SW [3:0];
	assign B = SW [7:4];
	assign C = SW [8];
	
	//check for overflow
	reg gOUT;
	always@(A || B)
		case(A || B)
			4'b1010 : gOUT = 1'b0;	//10
			4'b1011 : gOUT = 1'b0;	//11
			4'b1100 : gOUT = 1'b0;	//12
			4'b1101 : gOUT = 1'b0;	//13
			4'b1110 : gOUT = 1'b0;	//14
			4'b1111 : gOUT = 1'b0;	//15
			default : gOUT = 1'b1;  //off
		endcase
	assign LEDG[7] = gOUT;
		
	reg [4:0] OO;
	always@(A || B || C)
	OO = A + B + C; 
	
	assign {Cout,M} = OO;
	

endmodule

//implements a 7-segment decoder for binary to decimal
module char_7seg (C,Display);
	input[3:0] C;		//input code
	output[6:0]Display; //output 7-seg code
//code for 7-segment decoder

	wire[6:0] b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,BLANK;			
	reg[13:0]OUT;
					//   6    5    4    3    2    1    0
	assign b0[6:0] = {1'b1,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0};
	assign b1[6:0] = {1'b1,1'b1,1'b1,1'b1,1'b0,1'b0,1'b1};
	assign b2[6:0] = {1'b0,1'b1,1'b0,1'b0,1'b1,1'b0,1'b0};
	assign b3[6:0] = {1'b0,1'b1,1'b1,1'b0,1'b0,1'b0,1'b0};
	assign b4[6:0] = {1'b0,1'b0,1'b1,1'b1,1'b0,1'b0,1'b1};
	assign b5[6:0] = {1'b0,1'b0,1'b1,1'b0,1'b0,1'b1,1'b0};
	assign b6[6:0] = {1'b0,1'b0,1'b0,1'b0,1'b0,1'b1,1'b0};
	assign b7[6:0] = {1'b1,1'b1,1'b1,1'b1,1'b0,1'b0,1'b0};
	assign b8[6:0] = {1'b0,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0};
	assign b9[6:0] = {1'b0,1'b0,1'b1,1'b1,1'b0,1'b0,1'b0};
	assign BLANK[6:0] = {1'b1,1'b1,1'b1,1'b1,1'b1,1'b1,1'b1};
	
	always@(C)
		case(C)
							
			4'b0000 : OUT = { BLANK , b0 };
			4'b0001 : OUT = { BLANK , b1 };
			4'b0010 : OUT = { BLANK , b2 };
			4'b0011 : OUT = { BLANK , b3 };
			4'b0100 : OUT = { BLANK , b4 };
			4'b0101 : OUT = { BLANK , b5 };
			4'b0110 : OUT = { BLANK , b6 };
			4'b0111 : OUT = { BLANK , b7 };
			4'b1000 : OUT = { BLANK , b8 };
			4'b1001 : OUT = { BLANK , b9 };
			
			default : OUT = BLANK;
	endcase	
	assign Display = OUT[6:0];
	
	
	
endmodule