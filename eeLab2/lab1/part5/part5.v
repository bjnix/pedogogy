module part5(SW,HEX0,HEX1,HEX2,HEX3,HEX4,LEDR);
	input [17:0] SW;		//toggle switches
	output [6:0] HEX0;		//7-seg display 0 
	output [6:0] HEX1;		//"			  " 1
	output [6:0] HEX2;		//"           " 2
	output [6:0] HEX3;		//"           " 3
	output [6:0] HEX4;		//"           " 4
	output [17:0] LEDR;
	assign LEDR = SW;			//assign the red leds to the switches
	wire [14:0]M;
	
	mux_3bit_5to1 M_3_5(SW[17:15],SW[14:12],SW[11:9],SW[8:6],SW[5:3],SW[2:0],M);
	
	char_7seg H0 (M[2:0],HEX0);
	char_7seg H1 (M[5:3],HEX1);
	char_7seg H2 (M[8:6],HEX2);
	char_7seg H3 (M[11:9],HEX3);
	char_7seg H4 (M[14:12],HEX4);
	/*
	assign HEX0 = H0;
	assign HEX1 = H1;
	assign HEX2 = H2;
	assign HEX3 = H3;
	assign HEX4 = H4;
	*/
endmodule

// implements a 3-bit wide  5-to-1 multiplexer
//S = 3'b input
//U = 'H'
//V = 'E'
//W = 'L'
//X = 'L'
//Y = 'O'
//M = output
module mux_3bit_5to1 (S,U,V,W,X,Y,M);
	input[2:0] S,U,V,W,X,Y;
	output[14:0] M;
	
//code for 3'b 5 to 1
	reg[14:0]OUT;

	
	always@(S,U,V,W,X,Y)
		case(S)			  //H,E,L,L,O
			3'b000 : OUT = {U,V,W,X,Y}; 
			3'b001 : OUT = {V,W,X,Y,U}; 
			3'b010 : OUT = {W,X,Y,U,V}; 
			3'b011 : OUT = {X,Y,U,V,W};
			3'b100 : OUT = {Y,U,V,W,X};
			
	endcase	
	
	assign M = OUT; 
 
endmodule

//implements a 7-segment decoder for H,E,L,O and 'blank'
module char_7seg (C,Display);
	input[2:0] C;		//input code
	output[6:0]Display; //output 7-seg code
	
//code for 7-segment decoder

	wire[6:0] H,E,L,O,BLANK;			
	reg[6:0]OUT;
		
	assign H[6:0] = {1'b0,1'b0,1'b0,1'b1,1'b0,1'b0,1'b1};
	assign E[6:0] = {1'b0,1'b0,1'b0,1'b0,1'b1,1'b1,1'b0};
	assign L[6:0] = {1'b1,1'b0,1'b0,1'b0,1'b1,1'b1,1'b1};
	assign O[6:0] = {1'b1,1'b0,1'b0,1'b0,1'b0,1'b0,1'b0};
	assign BLANK[6:0] = {1'b1,1'b1,1'b1,1'b1,1'b1,1'b1,1'b1};
	
	always@(C)
		case(C)
			3'b000 : OUT = H;
			3'b001 : OUT = E;
			3'b010 : OUT = L;
			3'b011 : OUT = O;
			default : OUT = BLANK;
	endcase	
	assign Display = OUT;
	
	
endmodule