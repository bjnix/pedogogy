/* Insertion Sort:
 * sorts 4 arrays with insertion sort
 */

void insSort(char uid[], char term[], char ip[], char evtime[], int length)
{
	int i,j,v;
	
	for( i = 0; i < length; i++ )
	{
		/*Insert */ 
		v = uid[i];

		for( j = i - 1; j >= 0; j-- )
		{
			if( uid[i] <= v ) break;
			uid[j + 1] = uid[j];
			term[j + 1] = term[j];
			ip[j + 1] = ip[j];
			evtime[j + 1] = evtime[j];
		}
		uid[j + 1] = v;
		term[j + 1] = v;
		ip[j + 1] = v;
		evtime[j + 1] = v;

	}
}

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void insSort();

	struct entry
	{
		char uid[14];
		char term[7];
		char ip[15];
		char evtime[42];
	};
	typedef struct entry Entry;

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


	fp = fopen(argv[1], "r");
	i = 0;
	if(fp)
	{
		while(!feof(fp))
		{
			fgets(buffer, 100, fp);
			
			sscanf(buffer, "%s %s %s %s %s %s %s %s %s %s",ent[i].uid, ent[i].term, ent[i].ip, tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7);
			
			sprintf(ent[i].evtime,"%s %s %s %s %s %s %s", tmp1,tmp2,tmp3,tmp4,tmp5,tmp6,tmp7);

			i++;
		}

		insSort( ent,i ); 
		temp = i;
		i = 0;

		while(i < temp-1)
		{
			printf("%16s %16s %16s %16s \n ", ent[i].uid, ent[i].term, ent[i].ip, ent[i].evtime );

			i++;
		}
	}

}/* end main */ 

/* Insertion Sort:
 * sorts 4 arrays with insertion sort
 */

void insSort( Entry  e[] , int length)
{
	int i,j;
	Entry temp;
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
}

