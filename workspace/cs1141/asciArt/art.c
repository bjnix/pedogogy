#include <stdio.h> 
#include <ctype.h>
#include "func.h"
/*Author: Brent Nix
 * art.c: simple program to scale a 5 by 5 ascii art image
 * STARDATE: 25 FEB 2011
 */
int main()
{
	int scale;

	do
	{	
		/* body */
		printf( "Hello and thank you for using the ASCII ART SCALER!\n" );
		printf( "Please enter the factor you wish to scale by or '0' to quit : " );
		
		/* read input */
		do { scanf( "%d", &scale ); } while (isspace(scale));
		
		if( scale )
		{ size( scale ); }
		
	} while( scale );
	
	printf( "Goodbye!\n" );	
	
	return(0);
}
