#include <stdio.h>
#inlcude <string.h>

#define NUM_OF(x)(sizeof(x)/sizeof*(x))

int main()
{	
	/* 1 
	int a[] = { 5,15,34,54,14,2,52,72 };
	int *p = &a[1], *q=&a[5];
	int i = 0;
	/* 2 
	int b[] = { 1,2,3,4,5,6,7,8,9,10 };
	int *r = &b[0], *s = &b[9], temp;
	/* 3 
	char str[100];

	/* (1) 
	printf( "(1).\n" ); 
	printf( "\ta. value of *( p + 3 ) : %d\n", *( p + 3 ) );
	printf( "\tb. value of *( q - 3 ) : %d\n", *( q - 3 ) );
	printf( "\tc. value of q - p : %d\n", q - p );
	printf( "\td. p < q : %c\n", ( p < q ) ? 'T' : 'F' );
	printf( "\te. *p < *q : %c\n", ( *p < *q ) ? 'T' : 'F' );

	/* (2) 
	while ( r < s )
	{
		temp = *r;
		*r++ = *s;
		*s-- = temp;
	}
	printf( "(2).\n" );
	printf( "\tThe  contents of the array will be { " );
	for( i = 0; i < NUM_OF(b) ; i ++ )
	{
		printf( "%d, ", b[i] );
	}
	printf( "}\n" );

	/* (3)
	printf( "\tup.c:\n" 
	
	fgets( str,100,stdin );
	for( i = 0, i < strlen( str ); i++
	{
		if( ( str[i] > 40 ) && ( str[i] < 91 ) )
		{ 
			str[i] = str[i] + 32;
		}
	}
	*/

	
	
return 0;
}
 
