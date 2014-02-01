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
#include <string.h>

//START MAIN PROGRAM
int main(){
  int i, M;

  printf("\nHow many iterationss do you want for your calculation of Pi? ");
  scanf("%d",&M);
  
  //declare variables for pi, power
  double pi,x,k,horse;
  pi = 3;
  k=2;
  for (i=0;i<M;i++){
    x=pow(-1,i-1);	
    if (i>0){
      pi = (4*(x)/(k*(k+1)*(k+2))) + pi;
      k=k+2;}
  }
  //  pi = pi + 3;
  printf("The calculated value of pi using the Nilakantha series to %d iterations is given as %.20lf\n", M, pi);

/*Indicates the return*/
	return 0;}
/*Main program ends*/

