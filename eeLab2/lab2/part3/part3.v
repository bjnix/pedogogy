module part3(SW,LEDG,LEDR);
	input [17:0] SW;		//toggle switches
	output [6:0] LEDG;		//green leds
	output [17:0] LEDR;		//red leds
	
	assign LEDR = SW;			//assign the red leds to the switches
	
	wire [3:0] A;
	assign A = SW [3:0];
	
	wire [7:4] B;
	assign B = SW [7:4];
	
	wire C;
	assign C = SW [8];
	
	reg [4:0] OO;
	always@(A || B || C)
	OO = A + B + C; 
	
	assign LEDG = OO;

	
endmodule