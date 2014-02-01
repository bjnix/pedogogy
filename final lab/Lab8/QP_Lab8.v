module QP_Lab8 (CLOCK_50, KEY, GPIO_0);
input CLOCK_50;
input [0:0] KEY;
output [6:2] GPIO_0;


Lab8Nios n (CLOCK_50,KEY[0], null, GPIO_0[2], GPIO_0[4], GPIO_0[6]);

endmodule 