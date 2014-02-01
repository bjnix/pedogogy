/*
 * CS1141 PROGRAM_4
 * @author Brent Nix
 * stardate 21MAR2011
 *
 * this is a program that reads in a file called "userlog" containing a log of
 * when users have logged on to the system.
 * it then prints them out sorted.
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void insSort();

/* 
 * struct for an entry line from the logfile
 */
	struct entry
	{
		char uid[14];
		char term[7];
		char ip[15];
		char evtime[42];
	};
	typedef struct entry Entry; /*making my life easier*/

main(int argc, char * argv[])
{
	/*declarations*/
	FILE * fp;
	int i;
	int temp;
	char buffer[100];
	char tmp1[42];
	char tmp2[4];
	char tmp3[4];
	char tmp4[7];
	char tmp5[6];
	char tmp6[7];
	char tmp7[7];
	Entry ent[500];

	
	fp = fopen("userlog", "r");/*open file*/
	i = 0;
	if(fp)
	{
		while(!feof(fp))
		{
			fgets(buffer, 100, fp);/*pull entry into buffer*/
			
			sscanf(buffer, "%s %s %s %s %s %s %s %s %s %s",ent[i].uid, ent[i].term, ent[i].ip, tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7);/*push text from buffer to arrays*/
			
			sprintf(ent[i].evtime,"%s %s %s %s %s %s %s", tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7);/*concatenation!*/

			i++;
		}

		insSort( ent,i ); /*call sort*/
		temp = i;
		i = 0;

		while(i < temp-1)/*print sorted list*/
		{
			printf("%8s \t%8s \t%10s \t%16s \n ", ent[i].uid, ent[i].term, ent[i].ip, ent[i].evtime );

			i++;
		}
	}
	return(0);
}/* end main */ 

/* Insertion Sort:
 * sorts 4 arrays with insertion sort
 */

void insSort( Entry  e[] , int length)
{
	int i,j;
	Entry temp;
	/*sort by user id*/
	for( i = 1; i < length; i++ )
	{
		
		temp = e[i];	
		j = i;
	
	
		while(  strcmp((temp.uid), (e[j-1].uid)) < 0 && j > 0  )
		{
			e[j] = e[j - 1];
			j--;
		}
	
				
		e[j] = temp ;
	}	
	/*sort by terminal*/
	for( i = 1; i < length; i++ )
	{
		
		temp = e[i];	
		j = i;
	
		 while( strcmp((temp.uid), (e[j-1].uid)) == 0 && strcmp((temp.term), (e[j-1].term)) < 0 && j > 0 )
		{
			e[j] = e[j -1];
			j--;
		}
		e[j] = temp;
	}
}/* end insSort */

