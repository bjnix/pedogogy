module QP_Lab7 (CLOCK_50, KEY,SW,LEDR ,GPIO_0);
input CLOCK_50;
input [0:0] KEY;
output [6:2] GPIO_0;
output [17:0] LEDR;
input [17:0] SW;

L7Nios n (CLOCK_50,KEY[0], null, GPIO_0[2], GPIO_0[4], GPIO_0[6]);
assign LEDR = SW;
endmodule 