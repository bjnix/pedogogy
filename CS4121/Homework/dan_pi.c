/*C PROGRAM TO CALCULATE THE VALUE OF PI
 *
 * 	input number of iterations to calculate pi 
 *
 * Calculate the value of pi using 
 *
 * Nilakantha's series:
 * pi=3+4/(2*3*4)-4/(4*5*6)+4/(6*7*8)-...
 *
 * */
//INCLUDE header files
#include <stdio.h>
#include <math.h>

//START MAIN PROGRAM
void main(void){
//declare variables for pi
	double pi;
//declare variables for loops and such
	int i,j,n;
//input the number of iterations 
	printf("How many iterations?");
//set number = n
	scanf("%d",&n);
//initialize pi values
	pi=3;
//initialize j (used in nil series)
	j=2;
//loop through g-l and nil series to get n terms
	for(i=0;i<n;i++){
		if(i!=0){
			pi = pi+pow(-1,i-1)*4 /(j*(j+1)*(j+2));
//redefine j (used as denominator)
			j=j+2;
		}
	}
//print values for pi to 20 decimals
	printf("pi from nil: %.20lf\n",pi);
//END PROGRAM
}

