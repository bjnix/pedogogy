#include <stdio.h>
#include "func.h"
/*Author: Brent Nix
 *func.c: simple program to scale a 5 by 5 ascii art image
 * STARDATE: 25 FEB 2011
 */
int size( int scale ) 
{
	/* declarations */
	char r[5][5] = {{94,37,38,37,94},{94,94,94,94,94},{47,47,124,92,92},{124,124,124,124,124},{92,92,124,47,47}};
	int i = 0;
	int j = 0;
	int k = 0;
	int l = 0;
	
	
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
