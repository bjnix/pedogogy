/*******************************************************/
/*                     SomeLife Parser                   */
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
			printf("program -> programHeadAndProcedures compoundStatement .\n");
        	}
		
programHeadAndProcedures ::= programHead procedures.
		{
			printf("programHeadAndProcedures -> programHead procedures \n");
		}

programHead ::= PROGRAM IDENTIFIER(idAttr) SEMICOLON decls.
		{
			printf("programHead -> PROGRAM %s ; decls\n",idAttr->name);
		}

       
decls	::=  VAR declList.
		{
			printf("decls -> VAR declList\n");
		}

decls	::=  .
      		{
			printf("decls -> epsilon\n");
		}
	
procedures 	::= procedureDecl procedures.
		{
			printf("procedures -> procedures procedureDecl\n");
		}

procedures	::= .
		{
			printf("procedures -> epsilon\n");
		}

procedureDecl 	::= procedureHead compoundStatement SEMICOLON.
		{
			printf("procedureDecl -> procedureHead compoundStatement ;\n");
		}

procedureHead ::= functionDecl decls.
		{
			printf("procedureHead -> functionDecl decls\n");
		}

functionDecl ::=  FUNCTION IDENTIFIER(idAttr) COLON type SEMICOLON. 
		{
			printf("functionDecl -> FUNCTION %s : standardType ;\n",idAttr->name);
		}


declList ::= identifierList COLON type SEMICOLON.
		{
			printf("declList -> identifierList : type ;\n");
		}		

declList ::= declList identifierList COLON type SEMICOLON.
	 	{
	  		printf("declList -> declList identifierList : type ;\n");
	 	}


identifierList 	::= IDENTIFIER(idAttr).
		{
			printf("identifierList -> %s\n",idAttr->name);
		}
						
identifierList ::= identifierList COMMA IDENTIFIER(idAttr).
		{
			printf("identifierList -> identifierList , %s\n",idAttr->name);
		}

type	::= standardType.
		{ 
			printf("type -> standardType\n");
		}

type	::= arrayType.
		{ 
			printf("type -> arrayType\n");
		}

standardType  ::= INTEGER.
		{ 
			printf("standardType -> INTEGER\n");
		}

standardType  ::= FLOAT.
		{ 
			printf("standardType -> FLOAT\n");
		}

arrayType ::= ARRAY LBRACKET dim RBRACKET OF standardType.
		{
			printf("arrayType -> ARRAY [ dim ] OF standardType\n");
		}

dim ::= INTNUM(int1Attr) DOTDOT INTNUM(int2Attr).
		{
			printf("dim -> %s .. %s\n",int1Attr->name,int2Attr->name);
		}

statement	::= matched_stmt.
		{
			printf("statement -> matched_stmt\n");
		}
statement	::= open_stmt.
		{
			printf("statement -> open_stmt\n");
		}

matched_stmt	::= matched_if.
		{
			printf("matched_stmt -> matched_if\n");
		}
matched_stmt 	::= assignment.
		{
			printf("matched_stmt  -> assignment\n");
		}
matched_stmt	::= matchedWhileStatement.
		{
			printf("matched_stmt -> matchedWhileStatement\n");
		}
matched_stmt	::= ioStatement .
		{
			printf("matched_stmt -> ioStatement \n");
		}
matched_stmt	::= returnStatement.
		{
			printf("matched_stmt -> returnStatement\n");
		}
matched_stmt	::= compoundStatement.
		{
			printf("matched_stmt -> compoundStatement\n");
		}

assignment      ::= variable ASSIGN expr.
		{
			printf("assignment -> variable ASSIGN expr\n");
		}
				
matched_if	::= IF testAndThen ELSE matched_stmt.
		{
			printf("matched_if -> IF testAndThen ELSE matched_stmt\n");
		}

open_stmt	::=  IF test THEN statement.
		{
			printf("open_stmt ->  IF test THEN statement\n");
		}

open_stmt	::= IF testAndThen ELSE open_stmt.
		{
			printf("open_stmt -> IF testAndThen ELSE open_stmt\n");
		}

open_stmt	::= openWhileStatement.
		{
			printf("open_stmt -> openWhileStatement\n");
		}

testAndThen	::= test THEN matched_stmt.
		{
			printf("testAndThen -> test THEN matched_stmt\n");
		}

test	::= expr.
		{
			printf("test -> expr\n");
		}
				
matchedWhileStatement  ::= whileToken whileExpr DO matched_stmt.
		{
			printf("matchedWhileStatement -> whileToken whileExpr DO matched_stmt\n");
		}

openWhileStatement  ::= whileToken whileExpr DO open_stmt.
		{
			printf("openWhileStatement -> whileToken whileExpr DO open_stmt\n");
		}
                
whileExpr ::= expr.
		{
			printf("whileExpr -> expr\n");
		}
				
whileToken ::= WHILE.
		{
			printf("whileToken -> WHILE\n");
		}

ioStatement     ::= READ LPAREN variable RPAREN.
		{
			printf("ioStatement -> READ LPAREN variable RPAREN\n");
		}

ioStatement	::= WRITE LPAREN expr RPAREN.
		{
			printf("ioStatement -> WRITE LPAREN expr RPAREN\n");
		}

ioStatement 	::= WRITE LPAREN stringConstant RPAREN.
		{
			printf("ioStatement -> WRITE LPAREN stringConstant RPAREN\n");
		}

returnStatement ::= RETURN expr.
		{
			printf("returnStatement -> RETURN expr\n");
		}

compoundStatement ::= T_BEGIN statementList END.
		{
			printf("compoundStatement -> T_BEGIN statementList END\n");
		}

statementList   ::= statement.
		{
			printf("statementList -> statement\n");
		}
		
statementList	::= statementList SEMICOLON statement.
		{
			printf("statementList -> statementList SEMICOLON statement\n");
		}

expr    ::= simpleExpr.
		{
			printf("expr -> simpleExpr\n");
		}

expr	::= expr OR simpleExpr.
		{
			printf("expr -> expr OR simpleExpr\n");
		}

expr	::= expr AND simpleExpr. 
		{
			printf("expr -> expr AND simpleExpr.\n");
		}

expr	::= NOT simpleExpr.
		{
			printf("expr -> NOT simpleExpr\n");
		}

simpleExpr ::= addExpr.
		{
			printf("simpleExpr -> addExpr\n");
		}

simpleExpr ::= simpleExpr EQ addExpr.
		{
			printf("simpleExpr -> simpleExpr EQ addExpr\n");
		}

simpleExpr ::= simpleExpr NE addExpr.
		{
			printf("simpleExpr -> simpleExpr NE addExpr\n");
		}

simpleExpr ::= simpleExpr LE addExpr.
		{
			printf("simpleExpr -> simpleExpr LE addExpr\n");
		}

simpleExpr ::= simpleExpr LT addExpr.
		{
			printf("simpleExpr -> simpleExpr LT addExpr\n");
		}

simpleExpr ::= simpleExpr GE addExpr.
		{
			printf("simpleExpr -> simpleExpr GE addExpr\n");
		}

simpleExpr ::= simpleExpr GT addExpr.
		{
			printf("simpleExpr -> simpleExpr GT addExpr\n");
		}

addExpr ::=  mulExpr.
		{
			printf("addExpr ->  mulExpr\n");
		}

addExpr ::=  addExpr PLUS mulExpr.
		{
			printf("addExpr ->  addExpr PLUS mulExpr\n");
		}

addExpr ::=  addExpr MINUS mulExpr.
		{
			printf("addExpr ->  addExpr MINUS mulExpr\n");
		}

mulExpr ::=  factor.
		{
			printf("mulExpr ->  factor\n");
		}

mulExpr ::=  mulExpr TIMES factor.
		{
			printf("mulExpr ->  mulExpr TIMES factor\n");
		}

mulExpr ::=  mulExpr DIVIDE factor.
		{
			printf("mulExpr ->  mulExpr DIVIDE factor\n");
		}		
				
factor	::= variable.
		{ 
			printf("factor	-> variable\n");
		}

factor ::= constant.
		{ 
			printf("factor -> constant\n");
		}

factor ::= IDENTIFIER(idAttr) LPAREN RPAREN.
       		{	
			printf("factor -> %s LPAREN RPAREN\n",idAttr->name);
		}

factor ::= LPAREN expr RPAREN.
		{
			printf("factor -> LPAREN expr RPAREN\n");
		}

variable ::= IDENTIFIER(idAttr).
		{
			printf("variable -> %s\n",idAttr->name);
		}

variable ::= IDENTIFIER(idAttr) LBRACKET expr RBRACKET.
               	{
			printf("variable -> %s LBRACKET expr RBRACKET\n",idAttr->name);
               	}

stringConstant ::= STRING.
		{ 
			printf("stringConstant -> STRING\n");
		}

constant ::= FLOATCON.
		{ 
			printf("constant -> FLOATCON\n");
		}

constant ::= INTNUM.
		{ 
			printf("constant -> INTNUM\n");
		}
