#include <stdio.h>
#include <stdlib.h>


int main(){

	char *buf;
	int cnt, i, bufsize;

	bufsize=10;
	buf=malloc(bufsize);
	cnt=0;
	do {
		printf("Enter a character: ");
		scanf("%c",&buf[cnt]);cnt++;	
		if (cnt==bufsize){
			buf=malloc(bufsize+10);
			bufsize+=10;
		}
		printf("\n");
	} while (buf[cnt-1]!='q');
	return(0);
}

