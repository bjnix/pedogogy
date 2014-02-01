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
#include <codegen/symfields.h>
#include "SomeLifeParserHeader.h"
#include "SomeLifeParser.h"

void* slParser;
SymTable symtab;
DList instList;
DList dataList;
int offsetDirection = 1;
int globalOffset = 0;
char *fileName;

void SomeLife_error(char *s)
{
  fprintf(stderr,"%s: line %d: %s\n",fileName,SomeLife_lineno,s);
}

int SomeLife_wrap() {
	return 1;
}

void initSymTable() {
	symtab = SymInit(SYMTABLE_SIZE);
        SymInitField(symtab,SYMTAB_TYPE_INDEX_FIELD,(Generic)-1,NULL);
        SymInitField(symtab,SYMTAB_SIZE_FIELD,(Generic)-1,NULL);
        SymInitField(symtab,SYMTAB_OFFSET_FIELD,(Generic)-1,NULL);
        SymInitField(symtab,SYMTAB_REGISTER_INDEX_FIELD,(Generic)-1,NULL);
}

void deleteSymTable() {
    SymKillField(symtab,SYMTAB_TYPE_INDEX_FIELD);
    SymKillField(symtab,SYMTAB_SIZE_FIELD);
    SymKillField(symtab,SYMTAB_OFFSET_FIELD);
    SymKillField(symtab,SYMTAB_REGISTER_INDEX_FIELD);
    SymKill(symtab);
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

        initRegisters();

        instList = dlinkListAlloc(NULL);
        dataList = dlinkListAlloc(NULL);

        slParser = SomeLife_ParseAlloc( malloc );

	initSymTable();
}

static void finalize() {
    deleteSymTable();
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
