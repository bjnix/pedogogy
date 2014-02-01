/*
 * CS1141 PROGRAM_5
 * @author Brent Nix
 * stardate 4APRIL2011
 *
 * this is a program that reads in the file "wtmp" containing a log of
 * when users have logged on to the system.
 * it then prints them out sorted by name,terminal,and date.
 */

/* include statements */

#include <stdio.h>
#include <string.h>
#include <utmp.h>
#include <stdlib.h>
#include <time.h>
#include <arpa/inet.h>
typedef struct utmp Entry;/* making my life easier*/


void insSort( Entry  e[] , int length);/*function declaration*/

int main(int argc, char * argv[])
{
	/*declarations*/
	FILE * fp;
	int i;
	int temp;
	int elements;
	Entry ent[500];
	struct utmp buffer;
	struct in_addr *address;


	/*open filestream*/
	fp = fopen(argv[1], "rb");			
	elements = 0;
	i = 0;
	if(fp)
	{
		/*read in entries*/	
		while(!feof(fp) && i<500)
		{
			fseek(fp,sizeof(Entry)*i,SEEK_SET);
			fread(&buffer,sizeof(Entry),1,fp);
			if (buffer.ut_type == 7 )			/*ignore improper entries*/
				{ ent[elements] = buffer;
			elements++;
			
			}
			
			i++;
		}

		/*close filestream*/
		temp = fclose(fp);					
		if(temp == EOF)
		{
		printf("could not close filestream. Exiting...\n");
			exit(1);
		}

		
		
		i = 0;
		/*sort list*/
		insSort(ent,elements);
		/*print sorted list*/
		while(i < elements)
		{
		address = (struct in_addr *)&(ent[i].ut_addr_v6);
				
			printf("\t%16s %16s %16s \t%16s  ", ent[i].ut_user,ent[i].ut_line,inet_ntoa(*address),ctime((time_t *)(&ent[i].ut_tv)));

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
	
	
		while(  strcmp((temp.ut_user), (e[j-1].ut_user)) < 0 && j > 0  )
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
	
		 while( strcmp((temp.ut_user), (e[j-1].ut_user)) == 0 && strcmp((temp.ut_line), (e[j-1].ut_line)) < 0 && j > 0 )
		{
			e[j] = e[j -1];
			j--;
		}
		e[j] = temp;
	}
}/* end insSort */

