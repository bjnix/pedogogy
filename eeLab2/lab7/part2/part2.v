//EE2304 Lab7 - State Machines - Part 2
module part2 (SW,KEY,LEDR,LEDG,HEX0,HEX1,HEX2,HEX3,HEX4,HEX5,HEX6,HEX7);
  input  [17:0] SW;
  input  [3:0]  KEY;
  output [17:0] LEDR;
  output [8:0]  LEDG;
  output [0:6]  HEX0,HEX1,HEX2,HEX3;
  output [0:6]  HEX4,HEX5,HEX6,HEX7;
  wire   [3:0]  y_Q;
  reg    [3:0]  Y_D;  //Y_D represents next state
  reg    z;
  wire   w, Clock, reset;
  
  assign w = SW[1];
  assign reset = SW[0];
  assign Clock = KEY[0];
  parameter A = 4'b0000, 
			B = 4'b0001, 
			C = 4'b0010, 
			D = 4'b0011, 
			E = 4'b0100, 
			F = 4'b0101, 
			G = 4'b0110, 
			H = 4'b0111, 
			I = 4'b1000;
			
  parameter Z = 4'b1111;
  
  initial Y_D = 4'b0000;
  State(Y_D,y_Q,Clock);  
  assign LEDG[0] = z;
  assign LEDR = SW;
  
  //Next state machine
  always @(w,y_Q,reset) begin
  if( ~reset )
    case (y_Q)
	A: begin
		if (~w) Y_D = B;
			else Y_D = F;
	end
	B: begin
		if (~w) Y_D = C;
			else Y_D = F;
	end
	C: begin
		if (~w) Y_D = D;
			else Y_D = F;
	end
	D: begin 
		if (~w) Y_D = E;
			else Y_D = F;
	end
	E: begin
		if (~w) Y_D = E;
			else Y_D = F;
	end
	F: begin
		if (w) Y_D = G;
			else Y_D = B;
	end
	G: begin
		if (w) Y_D = H;
			else Y_D = B;
	end
	H: begin
		if (w) Y_D = I;
			else Y_D = B;
	end
	I: begin
		if (w) Y_D = I;
			else Y_D = B;
	end
	default: Y_D = A;
    
    endcase      
    else
		Y_D = A;
  end

  //Output State Machine for Z
  always @(*)
    begin
        if((y_Q==E) || (y_Q==I))
			z = 1;
		else
			z = 0;
    end
  
  char_7seg(Y_D,HEX0);
  char_7seg(y_Q,HEX1);
  char_7seg(Z,HEX2);
  char_7seg(Z,HEX3);
  char_7seg(Z,HEX4);
  char_7seg(Z,HEX5);
  char_7seg(Z,HEX6);
  char_7seg(Z,HEX7);
endmodule

//7 segment decoders  
module char_7seg (C, Display);
  input 	 [3:0] C; // input code
  output reg [0:6] Display; // output 7-seg code
  
  always @ (*) begin
	case (C)

			
	  4'b0000: Display = 7'b0001000;//A
	  4'b0001: Display = 7'b1100000;//b
	  4'b0010: Display = 7'b0110001;//C
	  4'b0011: Display = 7'b1000010;//D
	  4'b0100: Display = 7'b0110000;//E
	  4'b0101: Display = 7'b0111000;//F
	  4'b0110: Display = 7'b0100001;//G
	  4'b0111: Display = 7'b1001000;//H
	  4'b1000: Display = 7'b1111001;//I
	  default: Display = 7'b1111111;//Blank
	endcase
  end
endmodule

//State machine - stores the current state
module State(D,Q,Clock);
  input      Clock;
  input      [3:0] D;//new state
  output reg [3:0] Q;//output
  initial Q = 4'b0000;
  
  //fill in this
  always @(negedge Clock) begin
	Q = D;
  end
endmodule
