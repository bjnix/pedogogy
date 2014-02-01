/*
 * main.c
 *
 *  Created on: Jan. 22, 2013
 *      Author: zlwang
 */

#include <stdio.h>
#include <malloc.h>
void* cParser;
int calclval;

void initialize();

int main(int argc, char** argv)
{
	int id;

	if (argc < 2) {
		printf("no input file\n");
		return -1;
	}

    initialize(argv[1]);

    cParser = CalcParseAlloc( malloc );

    while ((id=yylex()) != 0)
    {
    	CalcParse(cParser, id, calclval);
    }


    CalcParse(cParser, 0, (int)NULL);

    CalcParseFree(cParser,free);
    fclose(stdin);

    return 0;
}

void initialize(char* inputFileName) {

	stdin = fopen(inputFileName,"r");

	if (stdin == NULL) {
		printf("Fail to open %s\n", inputFileName);
		exit(-1);
	}
}

static void finalize() {

    fclose(stdin);
}

int yywrap()
{
  return 1;
}
