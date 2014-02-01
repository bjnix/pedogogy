/*
 * CS1141 PROGRAM_1
 * @author Brent Nix
 * stardate 28JAN2011
 *  
 * spent immediately or saved for ten years
 */

#include <stdio.h>

int main ()
{
		int beg, end, i, fizz, buzz, FUZZ, BIZZ;


		

		printf( "             Welcome to Fizz-Buzz EXTREME \n" ); 
		printf( " Where All Your Numerical Dreams Can Come True!\n\n" );
	do{
		printf( "Please enter begining value (int): " );
		scanf( "%d", &beg );	
		if ( !beg )
		{
		break;
		}		
		printf( "\nPlease enter end value (int): ");
		scanf( "%d", &end );	
		if ( beg > end )
		{
		printf( "Please Enter Valid Starting And Ending Values\n" );
		continue;
		}	
		printf( "\nPlease Enter 'Fizz' Value: " );
		scanf( "%d", &FUZZ );
		printf( "\nPlease Enter 'Buzz Value: " );
		scanf( "%d", &BIZZ );
		printf( "\n\n-------------------------------------------------------------------------------\n" );

		
		for( i = beg; i <= end; i ++ )
		{
			fizz = i % FUZZ;
			buzz = i % BIZZ;	
			if ( i )
			{
				if( !fizz && !buzz )
				{
					printf( "FIZZ-BUZZ!\n" );
				}	
				else if ( !fizz )
				{
					printf( "FIZZ!\n" );
				}
				else if( !buzz )
				{
					printf( "BUZZ!\n" );
				}
				else 
				{
					printf( "%i\n", i );
				}
			}	
			else
			{
				printf( "%i\n", i );
			}
		}
	}while( beg );

	printf( "\nTHANK YOU FOR COMING!\n" );
	return(0);
}

