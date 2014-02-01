module part4(SW,LEDR,LEDG,HEX2,HEX3,HEX4,HEX5,HEX6,HEX7,HEX0,HEX1);
	
	input [17:0] SW;		//toggle switches
	output [6:0] HEX0;		//7-seg display 0 
	output [6:0] HEX1;		//"			  " 1
	output [6:0] HEX2; 		//"           " 2
	output [6:0] HEX3;		//"           " 3
	output [6:0] HEX4;		//"           " 4
	output [6:0] HEX5;		//"           " 5
	output [6:0] HEX6;		//"           " 6
	output [6:0] HEX7;		//"           " 7
	output [17:0] LEDR;		//red leds
	output [7:0] LEDG;
	


	assign LEDR = SW;			//assign the red leds to the switches
	
	wire [4:0] M;
	wire Co;
	wire C1;
	wire C2;
	
	
	wire [3:0] A;
	assign A = SW [3:0];
	
	wire [7:4] B;
	assign B = SW [7:4];
	
	
		reg gOUT;
	always@(A | B)
		case(A | B)
			4'b1010 : gOUT = 1'b1;	//10
			4'b1011 : gOUT = 1'b1;	//11
			4'b1100 : gOUT = 1'b1;	//12
			4'b1101 : gOUT = 1'b1;	//13
			4'b1110 : gOUT = 1'b1;	//14
			4'b1111 : gOUT = 1'b1;	//15
			default : gOUT = 1'b0;  //off
		endcase
	assign LEDG[7] = gOUT;
	
	
	
	
	full_adder (SW[0],SW[4],SW[8],M[0],Co);
	full_adder (SW[1],SW[5],Co,M[1],C1);
	full_adder (SW[2],SW[6],C1,M[2],C2);
	full_adder (SW[3],SW[7],C2,M[3],M[4]);
	
	char_7seg H0 (M,HEX0,HEX1);
	assign HEX2 = 7'b1111111;
	assign HEX3 = 7'b1111111;
	assign HEX4 = 7'b1111111;
	assign HEX5 = 7'b1111111;
	assign HEX6 = 7'b1111111;
	assign HEX7 = 7'b1111111;
	

endmodule
//full adder
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
	

//implements a 7-segment decoder for binary to decimal
module char_7seg ( C, Display0, Display1 );
	input[4:0] C;		//input code
	output[6:0]Display0,Display1; //output 7-seg code
	
//code for 7-segment decoder

	wire[6:0] b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,BLANK;			
	reg[13:0]OUT;
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
							
			6'b000000 : OUT = { BLANK , b0 };
			6'b000001 : OUT = { BLANK , b1 };
			6'b000010 : OUT = { BLANK , b2 };
			6'b000011 : OUT = { BLANK , b3 };
			6'b000100 : OUT = { BLANK , b4 };
			6'b000101 : OUT = { BLANK , b5 };
			6'b000110 : OUT = { BLANK , b6 };
			6'b000111 : OUT = { BLANK , b7 };
			6'b001000 : OUT = { BLANK , b8 };
			6'b001001 : OUT = { BLANK , b9 };
			6'b001010 : OUT = { b1 , b0 };	//10
			6'b001011 : OUT = { b1 , b1 };	//11
			6'b001100 : OUT = { b1 , b2 };	//12
			6'b001101 : OUT = { b1 , b3 };	//13
			6'b001110 : OUT = { b1 , b4 };	//14
			6'b001111 : OUT = { b1 , b5 };	//15
			6'b010000 : OUT = { b1 , b6 };	//16
			6'b010001 : OUT = { b1 , b7 };	//17
			6'b010010 : OUT = { b1 , b8 };	//18
			6'b010011 : OUT = { b1 , b9 };	//19
			6'b010100 : OUT = { b2 , b0 };	//20
			6'b010101 : OUT = { b2 , b1 };	//21
			6'b010110 : OUT = { b2 , b2 };	//22
			6'b010111 : OUT = { b2 , b3 };	//23
			6'b011000 : OUT = { b2 , b4 };	//24
			6'b011001 : OUT = { b2 , b5 };	//25
			6'b011010 : OUT = { b2 , b6 };	//26
			6'b011011 : OUT = { b2 , b7 };	//27
			6'b011100 : OUT = { b2 , b8 };	//28
			6'b011101 : OUT = { b2 , b9 };	//29
			6'b011110 : OUT = { b3 , b0 };	//30
			6'b011111 : OUT = { b3 , b1 };	//31
			6'b100000 : OUT = { b3 , b2 };	//32
			6'b100001 : OUT = { b3 , b3 };	//33
			6'b100010 : OUT = { b3 , b4 };	//34
			6'b100011 : OUT = { b3 , b5 };	//35
			6'b100100 : OUT = { b3 , b6 };	//36
			6'b100101 : OUT = { b3 , b7 };	//37
			6'b100110 : OUT = { b3 , b8 };	//38
			6'b100111 : OUT = { b3 , b9 };	//39
			6'b101000 : OUT = { b4 , b0 };	//40
			6'b101001 : OUT = { b4 , b1 };	//41
			6'b101010 : OUT = { b4 , b2 };	//42
			6'b101011 : OUT = { b4 , b3 };	//43
			6'b101100 : OUT = { b4 , b4 };	//44
			6'b101101 : OUT = { b4 , b5 };	//45
			6'b101110 : OUT = { b4 , b6 };	//46
			6'b101111 : OUT = { b4 , b7 };	//47
			6'b110000 : OUT = { b4 , b8 };	//48
			6'b110001 : OUT = { b4 , b9 };	//49
			6'b110010 : OUT = { b5 , b0 };	//50
			6'b110011 : OUT = { b5 , b1 };	//51
			6'b110100 : OUT = { b5 , b2 };	//52
			6'b110101 : OUT = { b5 , b3 };	//53
			6'b110110 : OUT = { b5 , b4 };	//54
			6'b110111 : OUT = { b5 , b5 };	//55
			6'b111000 : OUT = { b5 , b6 };	//56
			6'b111001 : OUT = { b5 , b7 };	//57
			6'b111010 : OUT = { b5 , b8 };	//58
			6'b111011 : OUT = { b5 , b9 };	//59
			6'b111100 : OUT = { b6 , b0 };	//60 
			6'b111101 : OUT = { b6 , b1 };	//61
			6'b111111 : OUT = { b6 , b2 };	//62 
			 
			
			
			
			
			//default : OUT = BLANK;
	endcase	
	assign Display0 = OUT[6:0];
	assign Display1 = OUT[13:7];
	
	
endmodule