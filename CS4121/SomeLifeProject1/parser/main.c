#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <strings.h>
#include <string.h>
#include <util/general.h>
#include <util/symtab.h>
#include <util/symtab_stack.h>
#include <util/dlink.h>
#include <util/string_utils.h>
#include "SomeLifeParserHeader.h"
#include "SomeLifeParser.h"

void* slParser;
char *fileName;

void SomeLife_error(char *s)
{
  fprintf(stderr,"%s: line %d: %s\n",fileName,SomeLife_lineno,s);
}

int SomeLife_wrap() {
	return 1;
}

static void initialize(char* inputFileName) {

	stdin = freopen(inputFileName,"r",stdin);
        if (stdin == NULL) {
          fprintf(stderr,"Error: Could not open file %s\n",inputFileName);
          exit(-1);
        }

	char* dotChar = rindex(inputFileName,'.');
	int endIndex = strlen(inputFileName) - strlen(dotChar);
	char *outputFileName = nssave(2,substr(inputFileName,0,endIndex),".s");
	stdout = freopen(outputFileName,"w",stdout);
        if (stdout == NULL) {
          fprintf(stderr,"Error: Could not open file %s\n",outputFileName);
          exit(-1);
        }

        slParser = SomeLife_ParseAlloc( malloc );
	
}

static void finalize() {

    fclose(stdin);
    fclose(stdout);
    
 
}

int main(int argc, char** argv)

{
    int id;

    fileName = argv[1];
    initialize(fileName);

    while ((id=SomeLife_lex()) != 0)
    {
    	SomeLife_Parse(slParser, id, tokenAttr );
    }

    SomeLife_Parse(slParser, 0, (TokenAttributePtr)NULL);

    finalize();

    return 0;
}
/******************END OF C ROUTINES**********************/
