#include <stdio.h>
int main( int argc, char *argv[] )
{
	FILE* fp; char str[81]; char*ptr;
	fp = fopen(argv[1], "r");
	if(fp)
	{
		while( !feof(fp)) 
		{
			ptr = fgets(str, 81, fp);
			if(ptr)
				{ fputs(str, stdout); }
		}
	}
	fclose(fp);
	return 0;
}
