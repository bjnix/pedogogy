import java.util.Arrays;


public class Lab1 {
	private static char B_0 = '0';
	private static char B_1 = '1';
	private static int SIZE;

	public static char [] sum(char [] a, char [] b) {

		char aSign = a[ SIZE ];
		char bSign = b[ SIZE ];
		char sign;//sign bit
		boolean bB = true;//b is the number with the greater magnitude
		boolean aA = false;//a is the number with the greater magnitude
		char [] r = null;//array to return
		SIZE = a.length - 1;//size of arrays

		if( aSign == B_0 && bSign == B_0)
			return add(a,b);
		else if ( aSign == B_1 && bSign == B_1)
		{
			r = add(a,b);
			r[ SIZE ] = B_1;
			return r;
		}


		for ( int i = SIZE - 1 ; i > -1 ; i -- )
		{
			if ( a[i] == b[i] )
			{
				if  (i == 0 )
				{
					//if both numbers have been completely read, they are the same magnitude

					if ( ( aSign == B_0 && bSign == B_1  ) ^
							( aSign == B_1 && bSign == B_0 ) ) 
					{
						//if both numbers are the same magnitude but are
						//opposites, the answer must be zero.
						for( int j = 0 ; j < SIZE ; j ++ )
							r[j] = B_0;

						return r;
					}

				}

				continue;
			}//end a==b

			if ( a[i] == B_0 && b[i] == B_1 )
			{
				r = sub(b,a);// b is larger

			}
			else if (a[i] == B_1 && b[i] == B_0 )
				r = sub(a,b);// a is larger

		}
		return r;


	} // end of sum method
	
	private static char[] add( char[] a, char[] b )
	{
		boolean carry = false;
		char[] r = new char[ SIZE+1 ]; //array to return
		for ( int i = 0 ; i < SIZE - 1 ; i ++)
		{
			r[i] = ( ( a[i] == B_0 && b[i] == B_1 ) || ( a[i] == B_1 && b[i] == B_0 ) ) ? B_1 : B_0;
			if ( carry )
			{
				if ( r[i] == B_0 )
					carry = false;
				r[i] = (r[i] == B_0) ? B_1 : B_0;

			}
			else if (a[i] == B_1 && b[i] == B_1) 
				carry = true;

		}
		return r;
	}
	private static char[] sub( char[] big, char[] small )
	{
		char[] r = new char[SIZE+1];
		for ( int i = 0 ; i < SIZE ; i ++)
		{
			if ( big[i] == B_1 && small[i] == B_0 )
				r[i] = 1;
			else if (big[i] == B_1 && small[i] == B_0 )
				r[i] = 0;
			else if (big[i] == B_0 && small[i] == B_1)
				r[i] = 1;
			for(int j = i+1 ; j < SIZE-1 ; j++)
				if ( big[j] == B_1)
				{
					big[j] = B_0;
					while(j >= i)
					{
						big[j] = B_1;
						j --;
					}
				}				
		}
		r[ SIZE ] = big[ SIZE ];
		return r;

	}


	public static void main(String [] args) {

		char [] x = {'1','1','0','1'};  // 1011 = -3
		char [] y = {'1','0','0','0'};  // 0001 = 1
		char [] result = sum(x,y);
		System.out.println(Arrays.toString(result));
		// should print ['0','1','0','1'] (i.e., 1010 = -2)

	} // end of main method

} // end of Lab1 class