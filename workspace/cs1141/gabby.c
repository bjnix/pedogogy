#include <string.h>
#include <stdio.h>
#include <stdlib.h>
	/* tstCopy.c
	 */
int *create_array(int n, int iValue);
		
int main(int argc, char * argv[])
{
	int j;
	int * gummy;

	gummy = create_array(5,6);
	for(j = 0; j < 5; j++)
		printf("%i\n",*(gummy+j));
	return(0);
}
int *create_array(int n, int iValue)
{
	int i;
	int * ip;
	ip = (int *)malloc(n); 
	for(i = 0; i < n; i++)
		*(ip+i) = iValue;
	
	return ip;
}

	
