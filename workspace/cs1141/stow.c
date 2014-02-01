/*
 * CS1141 PROGRAM_1
 * @author Brent Nix
 * stardate 28JAN2011
 * a simple used to compare a user's bank account balance if the a windfall is 
 * spent immediately or saved for ten years
 */

#include <stdio.h>
#include <math.h>

float tax( float base, float rate ), interest( float principle, float rate, float years );

int main ()
{
	float w;
	float sales;
	float save; 

	printf( "             Welcome to the *Financial Brain* program!\n" ); 
	printf( " We use complex algorithyms to calulate your best investment decisions.\n\n" );
	printf( "Please enter windfall amount (USD): ");
	scanf( "%f", &w );	
	printf( "\nPlease enter current sales tax rate (0 to 100): ");
	scanf( "%f", &sales );	
	printf( "\nPlease enter rate of savings (0 to 100): ");
	scanf( "%f", &save);	
	printf( "\n\n-------------------------------------------------------------------------------" );
	printf( "\nRESULTS:" );
	printf( "\nTax paid if spent: %f", tax( w, sales ) );
	printf( "\nAmount available if windfall saved for ten years: %f \n", interest( w, save, 10.0 ) ); 


	return(0);

}

/* function to calculate tax */
float tax( float base, float rate )
{
	
	float taxF = ( ( rate / 100 ) * base );

	return ( taxF );
}

/* function to determine the amount of interest */
float interest( float principle, float rate, float years )
{

	float intr = principle * pow( ( 1 + ( rate / 100 )), years );


	return( intr );
}
