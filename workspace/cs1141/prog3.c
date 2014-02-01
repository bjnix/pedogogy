#include <stdio.h> 
/*Author: Brent Nix
 * PROG3: simple program to scale a 5 by 5 ascii art image
 * STARDATE: 25 FEB 2011
int main()
{
	/* declarations */
	char r[5][5] = {{94,37,38,37,94},{94,94,94,94,94},{47,47,124,92,92},{124,124,124,124,124},{92,92,124,47,47}};
	int scale = 1;
	int i;
	int j;
	int k;
	int l;
	
	/* body */
	printf( "Hello and thank you for using the ASCII ART SCALER!\n" );
	printf( "Please enter the factor you wish to scale by : " );
	
	/* read input */
	do { scanf( "%d", &scale ); } while (isspace(scale));
	
	/* quadruple nested for loops? really? this is disgusting to look at
	 */
	for( i = 0; i < 5  ; i ++ )
	{
		for( j = 0; j < scale; j ++)
		{
			for( k = 0; k < 5; k++ )
			{
				for(l = 0; l < scale; l++)
				{
					printf( "%c", r[i][k] );
				}
			}	
			printf( "\n" );
		}	
	}
	
	return(0);	
}
