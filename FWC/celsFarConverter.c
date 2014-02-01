#include <stdio.h>
#include <string.h>

/* Author: Brent Nix
 * simple program that converts from Farenheit to Celsius
 * STARDATE:16 FEB 2011
 */

float FtoC ( float tmp1 );
float CtoF ( float tmp2 );

int main()
{
	char command = 0;
	float temp = 0;
	int end = 0;
	int stage = 0;

	printf( " Welcome to The Temp. Converter! \n" );
	printf( "\nPlease enter 'c' for conversion from celcius," );
	printf( " 'f' for conversion from farenheit, or enter 'Q' to quit. \n" );
	printf( ": " );
	scanf( "%c", &command );
		
	while( isspace( command ) )
	{ scanf( "%c", &command ); }
	stage = 1;
	
	while( command != 'Q' )
	{
		if(stage)
		{
			switch( command )
			{	
				case 'f':	
					printf( "\n Please enter value for conversion: " );
					scanf( "%f", &temp );
					printf("\n %.2f C\n", FtoC( temp ) );
					stage = 0;
					break;
				case 'c':
					printf( "\n Please enter value for conversion: " );
					scanf( "%f", &temp);
					printf("\n %.2f F\n", CtoF( temp ) );
					stage = 0;
					break;
				case 0:
					stage = 0;
					break;
				default:
					printf( "\n Please enter a valid command ");
					stage = 0;
				break;
			}	
		}
		else if(!stage)	
		{
			command = 0;
			printf( "\nPlease enter 'c' for conversion from celcius," );
			printf( " 'f' for conversion from farenheit, or enter 'Q' to quit. \n" );
			printf( ": " );
			scanf( "%c", &command );
			
			while( isspace( command ) )
			{ scanf( "%c", &command ); }
			stage = 1;
		}
	}
	printf( "Thank you for coming!\nSee You soon!\n" );
}
/* FtoC:
 * converts from cels to far
 * takes and returns float
 */
float FtoC( float tmp1 )
{
	return( ( tmp1 - 32 ) * ( 5.0f / 9.0f ) );
}

/* CtoF:
 * converts from far to cels
 * takes and returns float
 */
float CtoF( float tmp2 )
{
	return( 32 + tmp2 * ( 9.0f / 5.0f ) );
}

