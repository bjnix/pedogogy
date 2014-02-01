module part4( D,Clk,Qa,Qb,Qc );

	input D,Clk;
	output Qa,Qb,Qc;
	wire qa,qb,qc;
	 
	D_latch( D,Clk,qa );
	pos_D_FlipFlop( D,Clk,qb );
	neg_D_FlipFlop( D,Clk,qc );
	
	assign Qa = qa;
	assign Qb = qb;
	assign Qc = qc;
endmodule

module D_latch( D,Clk,Q );
	input D,Clk;
	output reg Q;
	
	always@( D, Clk )
	if( Clk )
		Q = D;
	
endmodule


module pos_D_FlipFlop( D,Clk,Q );
	input D,Clk;
	output reg Q;
	
	always@( posedge Clk )
		Q = D;
endmodule

module neg_D_FlipFlop( D,Clk,Q );
	input D,Clk;
	output reg Q;
	
	always@( negedge Clk )
		Q = D;
endmodule
/*/
module simple_register(in, out, clr_n, clk, aa);

  	// port declarations
	input clr_n;
	input clk;
	input [7:0]in;
	input aa;
	output [7:0]out;
	

  	// signal declarations
  	reg [7:0]out;
	wire aA;
	assign aA = aa;


  	// implement a register with asynchronous clear
	always @(posedge clk or negedge clr_n)
	begin
		if ( !clr_n )
		out <= 0;
		
		else
		out <= in;
	end

	// continuous assignment
	assign out[0] = aA ;
endmodule 
*/