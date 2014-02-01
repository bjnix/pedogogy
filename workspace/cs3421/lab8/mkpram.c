/* mkpram.c:
 * this is a program that makes a bunch of cache.param files
 * author: Brent Nix 
 * stardate: 21April2011
 */
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>

void memError()
{
	fprintf(stderr,"I can't allocate that much memory Dave");
}
int main(int argc, char * argv[])
{
	static int NUMFILES = 10;
	/*inital values*/
	/*----------------------------------------------------------*/	
	int blkSz = 4; /*block size x>=4*/
	int assoc = 1; /*associativity 1<=x<=16*/
	int totSz = 1024;/*total size x>=4*/
	char strtup = 'c';/*startup policy c=cold, w=warm*/ 
	char replPol = 'l';/*replace policy r=random, f=fifo, l=lru*/  
	char wPol = 'b';/*write policy t=through, b=back*/
	char wHit = 'u';/*write-hit action i=invalidate, u=update*/
	char wMiss = 'a';/*write-miss action n=nothing a=allocate*/
	/*-----------------------------------------------------------*/
	int i = 0;
	FILE *fp;
	char * ugly = "ugly";
	char filename[20];
	
	for(i = 0; i<NUMFILES; i++)
	{
		blkSz=blkSz*2;
		
/*		if(a){assoc=1+i;}
*//*		if(assoc>16){break;}
*/		totSz = pow(2,10+i);
		printf("-----------------------------------%i\n",totSz);
		if(totSz>pow(2,21)){break;}

/*		if(s){strtup = 'w';}
*//*		if(r){repPol = 'f';}
*/		
		
		
		sprintf(filename,"%s%i.goo",ugly,i);
		
		if(!(fp = fopen(filename, "w")))
		{
			fprintf(stderr,"Sorry Dave, I can't open that file. Goodbye.\n");
			exit(1);
		}
		else
		{
			fprintf(stderr,"%s opened for writing\n",filename);
		
			fprintf(fp,"%i \n",blkSz);
			fprintf(fp,"%i \n",assoc);
			fprintf(fp,"%i \n",totSz);
			fprintf(fp,"%c \n",strtup);
			fprintf(fp,"%c \n",replPol);
			fprintf(fp,"%c \n",wPol);
			fprintf(fp,"%c \n",wHit);
			fprintf(fp,"%c \n",wMiss);
		}
		if(EOF == (fclose(fp)))
		{
			fprintf(stderr,"Sorry Dave, I can't close that file. Goodbye.\n");
			exit(1);
		}
	}



	return 0;
}
