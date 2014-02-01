module part5( SW,HEX0,HEX1,HEX2,HEX3,HEX4,HEX5,HEX6,HEX7,LEDR,KEY );
	
	input [17:0] SW;
	input [1:0] KEY;
	output [6:0] HEX0,HEX1,HEX2,HEX3,HEX4,HEX5,HEX6,HEX7;
	output [17:0] LEDR;
	wire [15:0] Qa,Qb;
	assign LEDR = SW;
	
	posEdge m0( SW,KEY[1],KEY[0],Qb );
	posEdge m1( Qb,KEY[1],KEY[0],Qa );
	
	char_7seg h0( Qa[3:0],HEX4 );
	char_7seg h1( Qa[7:4],HEX5 );
	char_7seg h2( Qa[11:8],HEX6 );
	char_7seg h3( Qa[15:12],HEX7 );
	
	char_7seg h4( Qb[3:0],HEX0 );
	char_7seg h5( Qb[7:4],HEX1 );
	char_7seg h6( Qb[11:8],HEX2 );
	char_7seg h7( Qb[15:12],HEX3 );

	
endmodule

module posEdge( D,Clk,Clr_n,Q );
	input Clk,Clr_n;
	input [15:0] D;
	output reg [15:0] Q;
	
	always@( negedge Clk, negedge Clr_n )
		begin
			if( !Clr_n )
				Q = 0;
			else
				Q = D;
		end
endmodule

module char_7seg( C,Display );
	input[3:0] C;		//input code
	output[6:0]Display; //output 7-seg code
	
//code for 7-segment decoder

	wire[6:0] b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,BLANK;			
	reg[13:0]OUT;
					//  6543210
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
			default : OUT = BLANK;
	endcase	
	assign Display = OUT;
	
	
	
endmodule
	
	