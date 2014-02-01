module part2(KEY,LEDR,LEDG,SW,HEX0,HEX1,HEX2,HEX3,HEX4,HEX5,HEX6,HEX7);
	input [17:0] SW;		//toggle switches
	input [1:0] KEY;
	output [6:0] HEX0;		//7-seg display 0 
	output [6:0] HEX1;		//"			  " 1
	output [6:0] HEX2; 		//"           " 2
	output [6:0] HEX3;		//"           " 3
	output [6:0] HEX4;		//"           " 4
	output [6:0] HEX5;		//"           " 5
	output [6:0] HEX6;		//"           " 6
	output [6:0] HEX7;		//"           " 7
	output [7:0] LEDR;		//red leds
	output [1:0]LEDG;
	
	wire [7:0] A,B,S;
	wire [7:0] M;
	wire overflow;
	wire [7:0] C;
	reg [7:0] temp;
	assign LEDG[1] = SW[16];
//------------------------- save to registers
	always@(SW[16])	begin
		if( SW[16] )
			temp = (~SW[15:8])+SW[16];
		else
			temp = SW[15:8]+SW[16]; 
	end
	regi regiA (temp,KEY[1],KEY[0],A);
	regi regiB (SW[7:0],KEY[1],KEY[0],B);
	
	//set input displays
	char_7seg h0(0,HEX2);
	char_7seg h1(0,HEX3);
	char_7seg h2(B[3:0],HEX4);
	char_7seg h3(B[7:4],HEX5);
	char_7seg h4(A[3:0],HEX6);
	char_7seg h5(A[7:4],HEX7);
	
	
	
//------------------------- start add/subtract'n!
	//adder	
	full_adder fa0(A[0],B[0],0,M[0],C[0]);
	full_adder fa1(A[1],B[1],C[0],M[1],C[1]);
	full_adder fa2(A[2],B[2],C[1],M[2],C[2]);
	full_adder fa3(A[3],B[3],C[2],M[3],C[3]);
	full_adder fa4(A[4],B[4],C[3],M[4],C[4]);
	full_adder fa5(A[5],B[5],C[4],M[5],C[5]);
	full_adder fa6(A[6],B[6],C[5],M[6],C[6]);
	full_adder fa7(A[7],B[7],C[6],M[7],C[7]);
	
	//overflow register
	Oflow o1 (C[7],KEY[1],KEY[0],overflow);
	
	//output register
	regi regiS(M,KEY[1],KEY[0],S);
	//output LEDs
	assign LEDR[7:0] = S;
	assign LEDG[0] = overflow; 
	// set output displays
	char_7seg h6(S[3:0],HEX0);
	char_7seg h7(S[7:4],HEX1);

	
endmodule 
module Oflow( D,Clk,Clr_n,Q );
	input Clk,Clr_n;
	input D;
	output reg Q;
	
	always@( negedge Clk, negedge Clr_n )
		begin
			if( !Clr_n )
				Q = 0;
			else
				Q = D;
		end
endmodule
module regi( D,Clk,Clr_n,Q );
	input Clk,Clr_n;
	input [7:0] D;
	output reg [7:0] Q;
	
	always@( negedge Clk, negedge Clr_n )
		begin
			if( !Clr_n )
				Q = 0;
			else
				Q = D;
		end
endmodule
module full_adder ( A, B, C , M , Cout);
//A = input A
//B = input B
//C = input C
//M = result
//Cout = overflow bit 
	input A,B,C;
	output M,Cout;
	assign {Cout,M} = A + B + C;
		
endmodule
module char_7seg( C,Display );
	input[3:0]C;            //input code
	output reg[6:0]Display; //output 7-seg code
	
//assigns numbers to displays based on 4 bit input
	always@(C)
		case(C)
			4'b0000 : Display=7'b1000000; //0
			4'b0001 : Display=7'b1111001; //1
			4'b0010 : Display=7'b0100100; //2
			4'b0011 : Display=7'b0110000; //3
			4'b0100 : Display=7'b0011001; //4
			4'b0101 : Display=7'b0010010; //5
			4'b0110 : Display=7'b0000010; //6
			4'b0111 : Display=7'b1111000; //7
			4'b1000 : Display=7'b0000000; //8
			4'b1001 : Display=7'b0010000; //9
			4'b1010 : Display=7'b0001000; //A
			4'b1011 : Display=7'b0000011; //b
			4'b1100 : Display=7'b1000110; //c
			4'b1101 : Display=7'b0100001; //d
			4'b1110 : Display=7'b0000110; //E
			4'b1111 : Display=7'b0001110; //F
		endcase
	
endmodule
	
