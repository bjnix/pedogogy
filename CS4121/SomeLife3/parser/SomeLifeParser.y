/*******************************************************/
/*                     SomeLife Parser                 */
/*                                                     */
/*******************************************************/

/*********************DEFINITIONS***********************/
%include{
#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <assert.h>
#include <util/general.h>
#include <util/symtab.h>
#include <util/symtab_stack.h>
#include <util/dlink.h>
#include <util/string_utils.h>
#include <codegen/symfields.h>
#include <codegen/types.h>
#include <codegen/codegen.h>
#include <codegen/reg.h>
#include "SomeLifeParserHeader.h"

#define SYMTABLE_SIZE 100

/*********************EXTERNAL DECLARATIONS***********************/

}

%token_type {TokenAttributePtr}
%name SomeLife_Parse

%syntax_error {
  SomeLife_error("Syntax Error");
}

%left OR.
%left AND.
%left NOT.
%left LT LE GT GE NE EQ.
%left PLUS MINUS.
%left TIMES DIVDE.

/***********************PRODUCTIONS****************************/
program		::= programHeadAndProcedures compoundStatement DOT.
	    {
		    //end of program
		    emitExit(instList);
		    emitDataPrologue(dataList);
		    emitInstructions(instList);
	    }
		
programHeadAndProcedures ::= programHead(phAttr) procedures.
	     {
		     //all function and procedure declaration finished
		     emitProcedurePrologue(instList,symtab,phAttr->symIndex,0);
		     free(phAttr);
	     }

programHead(phAttr) ::= PROGRAM IDENTIFIER SEMICOLON decls(dAttr).
	    {
		     //program declared
		     int symIndex = SymIndex(symtab,"main");
		     int intIndex = SymQueryIndex(symtab,SYMTAB_INTEGER_TYPE_STRING);
		     SymPutFieldByIndex(symtab,symIndex,SYMTAB_TYPE_INDEX_FIELD,(Generic)intIndex);
		     phAttr = malloc(sizeof(TokenAttribute));
		     phAttr->symIndex = symIndex;
		     globalOffset = dAttr->offset;
		     free(dAttr);
	    }

       
decls(dAttr)	::=  VAR declList(dlAttr).
		{
		     //variable declaration
			dAttr = malloc(sizeof(TokenAttribute));
			dAttr->offset = dlAttr->offset;
			free(dlAttr);
		}

decls(dAttr)	::=  .
		{
			dAttr = malloc(sizeof(TokenAttribute));
			dAttr->offset = 4;
		}
	
procedures 	::= procedureDecl procedures.
	        {
		     
	        }
procedures	::= .
	        {
		     //all procedures have been declared
	        }

procedureDecl 	::= procedureHead compoundStatement SEMICOLON.
		{
			//procedure has been declared, pop scope
			//TODO:pre-return
			emitPreReturn(instList);
			endScope(symtabStack);
			symtab = lastSymtab(symtabStack);
			char* inst = ssave("#symtab end");  
			dlinkAppend(instList,dlinkNodeAlloc(inst));
		}

procedureHead ::= functionDecl(funcDAttr) decls(dAttr).
	        {
			    //TODO:post-call
			    //variables declared
			emitPostCall(instList,symtabStack,funcDAttr->symIndex,dAttr->offset);
			free(dAttr);
	        }

functionDecl(funcDAttr) ::=  FUNCTION IDENTIFIER(idAttr) COLON type(typeAttr) SEMICOLON. 
		{
			int symIndex = SymIndex(symtab,idAttr->name);
			int intIndex = SymQueryIndex(symtab,SYMTAB_INTEGER_TYPE_STRING);
		     	SymPutFieldByIndex(symtab,symIndex,SYMTAB_TYPE_INDEX_FIELD,(Generic)intIndex);
			
			funcDAttr = malloc(sizeof(TokenAttribute));
			funcDAttr->symIndex = symIndex;
			//defined in main
			initSymTable();
			char* inst = ssave("#symtab start");  
			dlinkAppend(instList,dlinkNodeAlloc(inst));
			free(idAttr);
			free(typeAttr);
		}

declList(dlAttr) ::= identifierList(ilAttr) COLON type(typeAttr) SEMICOLON.
		{
			dlAttr = malloc(sizeof(TokenAttribute));

			AddIdStructPtr data = (AddIdStructPtr)malloc(sizeof(AddIdStruct));
			
			data->idSymtab = symtab;
			data->typeSymtab = symtab;
			data->typeIndex = typeAttr->symIndex;
			data->offset = 4;
			
			dlinkApply1(ilAttr->idList,(DLinkApply1Func)addIdToSymtab,(Generic)data);
			dlAttr->offset = data->offset;
			dlinkFreeNodes(ilAttr->idList);
			
			free(data);
			free(typeAttr);
			free(ilAttr);
		}		

declList(dlAttrLhs) ::= declList(dlAttrRhs) identifierList(ilAttr) COLON type(typeAttr) SEMICOLON.
	 	{
			dlAttrLhs = malloc(sizeof(TokenAttribute));
			AddIdStructPtr data = (AddIdStructPtr)malloc(sizeof(AddIdStruct));
			
			data->idSymtab = symtab;
			data->typeSymtab = symtab;
			data->typeIndex = typeAttr->symIndex;
			data->offset = dlAttrRhs->offset;
			
			dlinkApply1(ilAttr->idList,(DLinkApply1Func)addIdToSymtab,(Generic)data);
			dlAttrLhs->offset = data->offset;
			dlinkFreeNodes(ilAttr->idList);
			
			free(data);
			free(dlAttrRhs);
			free(typeAttr);
			free(ilAttr);
	 	}


identifierList(ilAttr) 	::= IDENTIFIER(idAttr).
		{
			ilAttr = malloc(sizeof(TokenAttribute));
			ilAttr->idList = dlinkListAlloc(NULL);
			int symtabIndex = SymIndex(symtab,idAttr->name);
			dlinkAppend(ilAttr->idList,dlinkNodeAlloc((Generic)symtabIndex));
			free(idAttr);
		}
						
identifierList(ilAttrLhs) ::= identifierList(ilAttrRhs) COMMA IDENTIFIER(idAttr).
		{
			int symtabIndex = SymIndex(symtab,idAttr->name);
			dlinkAppend(ilAttrRhs->idList,dlinkNodeAlloc((Generic)symtabIndex));
			ilAttrLhs = malloc(sizeof(TokenAttribute));
			ilAttrLhs->idList = ilAttrRhs->idList;
			free(idAttr);
			free(ilAttrRhs);
		}

type(tAttr)	::= standardType(sAttr).
		{ 
			tAttr = malloc(sizeof(TokenAttribute));
			tAttr->symIndex = sAttr->symIndex;
		}

type(tAttr)	::= arrayType(aAttr).
		{ 
			tAttr = malloc(sizeof(TokenAttribute));
			tAttr->symIndex = aAttr->symIndex;
		}

standardType(typeAttr)  ::= INTEGER.
		{ 
			typeAttr = malloc(sizeof(TokenAttribute));
			typeAttr->symIndex = SymQueryIndex(symtab,SYMTAB_INTEGER_TYPE_STRING);
		}

standardType ::= FLOAT.

arrayType(aAttr) ::= ARRAY LBRACKET dim(dAttr) RBRACKET OF standardType(sAttr).
		{
    			char* lowerBound = (char*)SymGetFieldByIndex(symtab,dAttr->bounds.lowerBoundIndex,SYM_NAME_FIELD);
    			char* upperBound = (char*)SymGetFieldByIndex(symtab,dAttr->bounds.upperBoundIndex,SYM_NAME_FIELD);
    			char* dimString = nssave(3,lowerBound,"..",upperBound);
    				   
    			char* baseTypeString = (char*)SymGetFieldByIndex(symtab,sAttr->symIndex,SYM_NAME_FIELD);
    		
    			char* typeString = nssave(4,baseTypeString,"[",dimString,"]");
    			
			aAttr = malloc(sizeof(TokenAttribute));
    			aAttr->symIndex = SymIndex(symtab,typeString);
    		
    			int numElements = atoi(upperBound) - atoi(lowerBound) + 1;
    			SymPutFieldByIndex(symtab,aAttr->symIndex,SYMTAB_SIZE_FIELD,
    					   (Generic)(((int)SymGetField(symtab,baseTypeString,SYMTAB_SIZE_FIELD))*numElements));
		}

dim(dAttr) ::= INTNUM(int1Attr) DOTDOT INTNUM(int2Attr).
		{
			dAttr = malloc(sizeof(TokenAttribute));
			int symIndex = SymIndex(symtab,int1Attr->name);
			dAttr->bounds.lowerBoundIndex = symIndex;
			symIndex = SymIndex(symtab,int2Attr->name);
			dAttr->bounds.upperBoundIndex = symIndex;
		}

statement	::= matched_stmt.
statement	::= open_stmt.

matched_stmt	::= matched_if.
matched_stmt 	::= assignment.
matched_stmt	::= matchedWhileStatement.
matched_stmt	::= ioStatement .
matched_stmt	::= returnStatement.
matched_stmt	::= compoundStatement.

assignment      ::= variable(varAttr) ASSIGN expr(exAttr).
		{
			
			emitAssignment(instList,symtab,varAttr->symIndex,exAttr->symIndex);
			free(varAttr);
			free(exAttr);
		}
				
matched_if	::= IF testAndThen(tAttr) ELSE matched_stmt.
		{
			emitEndBranchTarget(instList,symtab,tAttr->symIndex);
			free(tAttr);
		}

open_stmt	::=  IF test(exAttr) THEN statement.
		{
			emitEndBranchTarget(instList,symtab,exAttr->symIndex);
			free(exAttr);
		}

open_stmt	::= IF testAndThen(tAttr) ELSE open_stmt.
		{
			emitEndBranchTarget(instList,symtab,tAttr->symIndex);
			free(tAttr);
		}

open_stmt	::= openWhileStatement.

testAndThen(tAttr)	::= test(exAttr) THEN matched_stmt.
		{
			tAttr = malloc(sizeof(TokenAttribute));
		   	tAttr->symIndex = emitThenBranch(instList,symtab,exAttr->symIndex);
			free(exAttr);
		}

test(tAttr)	::= expr(exAttr).
		{
			tAttr = malloc(sizeof(TokenAttribute));
			tAttr->symIndex= emitIfTest(instList,symtab,exAttr->symIndex);
			free(exAttr);
		}
				
matchedWhileStatement  ::= whileToken(tokenAttr) whileExpr(exAttr) DO matched_stmt.
		{
			emitWhileLoopBackBranch(instList,symtab,tokenAttr->symIndex,exAttr->symIndex);
			free(exAttr);
			free(tokenAttr);
		}

openWhileStatement  ::= whileToken(tokenAttr) whileExpr(exAttr) DO open_stmt.
		{
			emitWhileLoopBackBranch(instList,symtab,tokenAttr->symIndex,exAttr->symIndex);
			free(exAttr);
			free(tokenAttr);
		}
                
whileExpr(whAttr) ::= expr(exAttr).
		{
			whAttr = malloc(sizeof(TokenAttribute));
			whAttr->symIndex = emitWhileLoopTest(instList,symtab,exAttr->symIndex);
			free(exAttr);
		}
				
whileToken(wtAttr) ::= WHILE.
		{
			wtAttr = malloc(sizeof(TokenAttribute));
			wtAttr->symIndex = emitWhileLoopLandingPad(instList,symtab);
		}

ioStatement     ::= READ LPAREN variable(varAttr) RPAREN.
		{
			emitReadVariable(instList,symtab,varAttr->symIndex);
			free(varAttr);
		}

ioStatement	::= WRITE LPAREN expr(exAttr) RPAREN.
		{
		        emitWriteExpression(instList,symtab,exAttr->symIndex,SYSCALL_PRINT_INTEGER);
			free(exAttr);
		}

ioStatement 	::= WRITE LPAREN stringConstant(strAttr) RPAREN.
		{
			emitWriteExpression(instList,symtab,strAttr->symIndex,SYSCALL_PRINT_STRING);
			free(strAttr);
		}

returnStatement ::= RETURN expr(exAttr).
		{
			//TODO:save value of expr to $v0
			
			emitReturn(instList,symtab,exAttr->symIndex);
			free(exAttr);
		}

compoundStatement ::= T_BEGIN statementList END.
                {

	        }

statementList   ::= statement.
		
statementList	::= statementList SEMICOLON statement.

expr(exAttr)    ::= simpleExpr(seAttr).
		{
			exAttr = malloc(sizeof(TokenAttribute));
			exAttr->symIndex = seAttr->symIndex; 
			free(seAttr);
		}

expr(exAttr)	::= expr(leftAttr) OR simpleExpr(rightAttr).
		{
			exAttr = malloc(sizeof(TokenAttribute));
			exAttr->symIndex = emitOrExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

expr(exAttr)	::= expr(leftAttr) AND simpleExpr(rightAttr). 
		{
			exAttr = malloc(sizeof(TokenAttribute));
			exAttr->symIndex = emitAndExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

expr(exAttr)	::= NOT simpleExpr(leftAttr).
		{
			exAttr = malloc(sizeof(TokenAttribute));
			exAttr->symIndex = emitNotExpression(instList,symtab,leftAttr->symIndex);
			free(leftAttr);
		}

simpleExpr(seAttr) ::= addExpr(rightAttr).
		{
			seAttr = malloc(sizeof(TokenAttribute));
			seAttr->symIndex = rightAttr->symIndex; 
			free(rightAttr);
		}

simpleExpr(seAttr) ::= simpleExpr(leftAttr) EQ addExpr(rightAttr).
		{
			seAttr = malloc(sizeof(TokenAttribute));
			seAttr->symIndex = emitEqualExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

simpleExpr(seAttr) ::= simpleExpr(leftAttr) NE addExpr(rightAttr).
		{
			seAttr = malloc(sizeof(TokenAttribute));
			seAttr->symIndex = emitNotEqualExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

simpleExpr(seAttr) ::= simpleExpr(leftAttr) LE addExpr(rightAttr).
		{
			seAttr = malloc(sizeof(TokenAttribute));
			seAttr->symIndex = emitLessEqualExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

simpleExpr(seAttr) ::= simpleExpr(leftAttr) LT addExpr(rightAttr).
		{
			seAttr = malloc(sizeof(TokenAttribute));
			seAttr->symIndex = emitLessThanExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

simpleExpr(seAttr) ::= simpleExpr(leftAttr) GE addExpr(rightAttr).
		{
			seAttr = malloc(sizeof(TokenAttribute));
			seAttr->symIndex = emitGreaterEqualExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

simpleExpr(seAttr) ::= simpleExpr(leftAttr) GT addExpr(rightAttr).
		{
			seAttr = malloc(sizeof(TokenAttribute));
			seAttr->symIndex = emitGreaterThanExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

addExpr(addAttr) ::=  mulExpr(rightAttr).
		{
			addAttr = malloc(sizeof(TokenAttribute));
			addAttr->symIndex = rightAttr->symIndex; 
			free(rightAttr);
		}

addExpr(addAttr) ::=  addExpr(leftAttr) PLUS mulExpr(rightAttr).
		{
			addAttr = malloc(sizeof(TokenAttribute));
			addAttr->symIndex = emitAddExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

addExpr(addAttr) ::=  addExpr(leftAttr) MINUS mulExpr(rightAttr).
		{
			addAttr = malloc(sizeof(TokenAttribute));
			addAttr->symIndex = emitSubtractExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

mulExpr(mulAttr) ::=  factor(rightAttr).
		{
			mulAttr = malloc(sizeof(TokenAttribute));
			mulAttr->symIndex = rightAttr->symIndex; 
			free(rightAttr);
		}

mulExpr(mulAttr) ::=  mulExpr(leftAttr) TIMES factor(rightAttr).
		{
			mulAttr = malloc(sizeof(TokenAttribute));
			mulAttr->symIndex = emitMultiplyExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}

mulExpr(mulAttr) ::=  mulExpr(leftAttr) DIVIDE factor(rightAttr).
		{
			mulAttr = malloc(sizeof(TokenAttribute));
			mulAttr->symIndex = emitDivideExpression(instList,symtab,leftAttr->symIndex,rightAttr->symIndex);
			free(leftAttr);
			free(rightAttr);
		}		
				
factor(facAttr)	::= variable(varAttr).
		{ 
			facAttr = malloc(sizeof(TokenAttribute));
			facAttr->symIndex = emitLoadVariable(instList,symtab,varAttr->symIndex);
			free(varAttr);
		}

factor(facAttr) ::= constant(conAttr).
		{ 
			facAttr = malloc(sizeof(TokenAttribute));
			facAttr->symIndex = conAttr->symIndex;
			free(conAttr);
		}

factor(facAttr) ::= IDENTIFIER(idAttr) LPAREN RPAREN.
       		{
			//TODO:caller pre-call
			SymTable symtab_current = findSymtab(symtabStack,idAttr->name);
			int symIndex = SymQueryIndex(symtab_current,idAttr->name);
			emitPreCall(instList,symtab_current,symIndex);
			//TODO:caller post-return
			facAttr = malloc(sizeof(TokenAttribute));
			facAttr->symIndex = emitPostReturn(instList,symtab);
			free(idAttr);
			
		}

factor(facAttr) ::= LPAREN expr(exAttr) RPAREN.
		{
			facAttr = malloc(sizeof(TokenAttribute));
			facAttr->symIndex = exAttr->symIndex;
			free(exAttr);
		}

variable(varAttr) ::= IDENTIFIER(idAttr).
		{
			varAttr = malloc(sizeof(TokenAttribute)); 
			SymTable symtab_current = findSymtab(symtabStack,idAttr->name);
			int symIndex = SymQueryIndex(symtab_current,idAttr->name);
			varAttr->symIndex =emitComputeVariableAddress(instList,symtab_current,symtab_current,symIndex);
			free(idAttr);
		}

variable(varAttr) ::= IDENTIFIER(idAttr) LBRACKET expr(exAttr) RBRACKET.
               	{
			varAttr = malloc(sizeof(TokenAttribute));
			SymTable symtab_current = findSymtab(symtabStack,idAttr->name);
			int symIndex = SymQueryIndex(symtab_current,idAttr->name);
			varAttr->symIndex =
			emitComputeArrayAddress(instList,symtab_current,symIndex,symtab_current,exAttr->symIndex);
			free(idAttr);
			free(exAttr);
               	}

stringConstant(scAttr) ::= STRING(strAttr).
		{ 
			scAttr = malloc(sizeof(TokenAttribute));
			int symIndex = SymIndex(symtab,strAttr->name);
			scAttr->symIndex = emitLoadStringConstantAddress(instList,dataList,symtab,symIndex); 
			free(strAttr);
		}

constant ::= FLOATCON.

constant(conAttr) ::= INTNUM(intAttr).
		{ 
			conAttr = malloc(sizeof(TokenAttribute));
			int symIndex = SymIndex(symtab,intAttr->name);
			conAttr->symIndex = emitLoadIntegerConstant(instList,symtab,symIndex); 
			free(intAttr);
		}
