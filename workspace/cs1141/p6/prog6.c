/*
 * CS1141 PROGRAM_5
 * @author Brent Nix
 * stardate 4APRIL2011
 *
 * this is a program that reads in the file "wtmp" containing a log of
 * when users have logged on to the system.
 * it then prints them out sorted by name,terminal,and date.
 */

/* 

#include <stdio.h>
#include <string.h>
#include <utmp.h>
#include <stdlib.h>
#include <time.h>
#include <arpa/inet.h>
typedef struct utmp Entry;



void insSort( Entry  e[] , int length);
*/
#include "tree.h"
#include "prog6.h"
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
	SearchTree T;
	Position P;
	char username[32];

	/*open filestream*/
	elements = 0;
	i = 0;
	if((fp = fopen(argv[1], "rb")))			
	{
		 T = MakeEmpty( NULL );
		/*read in entries*/	
		while(!feof(fp) && i<500)
		{
			fseek(fp,sizeof(Entry)*i,SEEK_SET);
			fread(&buffer,sizeof(Entry),1,fp);
			
			if (buffer.ut_type == 7 )			/*ignore improper entries*/
			{ 
				ent[elements] = buffer;
				address = (struct in_addr *)&(ent[elements].ut_addr_v6);
				T = Insert(ent[elements].ut_user,inet_ntoa(*address),T);
				
				elements++;
			
			}
			
			i++;
		}

		/*close filestream*/
		temp = fclose(fp);					
		if(temp == EOF)
		{
		memError(T);/*free the memories!*/	
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
		while(1)
		{
			printf("\nIf you would like to quit, enter 'q' or 'Q'\n\n"); 
			printf("Please enter the user name that you would like to query : ");
			scanf("%s",&username);

			if((username[1]==0)&&(username[0] == 'q' || username[0] == 'Q')){ break; }	

			P = Find(username,  T);



		}
		printf("thankyou for using this amazing program. \n bye bye now!\n");
		MakeEmpty( T );	
	}
	else{
		fprintf(stderr,"I cannot open file stream, sorry...");
		exit(1);
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

