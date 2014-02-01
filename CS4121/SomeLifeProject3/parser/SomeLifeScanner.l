WS	[ \t\n\r]

%{

/* This scanner approximates the Void specification.
 *
 * Major differences:
 *
 *  (1) the printed spec is wrong in about sixteen ways in its definition
 *	of numeric constants.  The scanner recognizes a fairly broad class
 * 	of things as constants - it allows both 0 and 1.0 as constants, where
 *	the document would not.
 *
 *      Feel free to accept the things that the scanner accepts as constants.
 *	The bug is in the original spec document.
 *
 *	Note: the scanner classifies all kinds of constants as "constant".
 *	It does not make a distinction between character, integer, and floating
 *	point constants.  I think of that distinction as something to look for
 *	in the type checker lab, not something that the grammar should enforce.
 *
 *
 * To hook this thing up to your parser, you will need to take the include
 * file of grammar terminals and relate them to the constants defined below.
 * You can either edit the flex spec or edit the constant defintions.  (Of
 * course, you should do that on your own copy!)  
 *
 */


#include <stdio.h>
#include <util/symtab.h>
#include <util/symtab_stack.h>
#include <util/string_utils.h>
#include <util/dlink.h>
#include "SomeLifeParserHeader.h"
#include "SomeLifeParser.h"

%}

%option prefix="SomeLife_"

%%

PROGRAM				{ return PROGRAM;}
FUNCTION			{ return FUNCTION;}
INTEGER				{ return INTEGER;}
WHILE				{ return WHILE; }
ELSE				{ return ELSE; }
THEN				{ return THEN; }
FLOAT				{ return FLOAT; }
IF				{ return IF; }
READ				{ return READ; }
WRITE				{ return WRITE; }
RETURN				{ return RETURN; }
BEGIN				{ return T_BEGIN; }
END				{ return END; }
DO				{ return DO; }
OF				{ return OF; }
VAR				{ return VAR; }
ARRAY				{ return ARRAY; }

NOT				{ return NOT;}
OR		    		{ return OR;}
AND				{ return AND;}
\<=				{ return LE; }
\<				{ return LT; }
\>=				{ return GE; }
\>				{ return GT; }
\=				{ return EQ; }
\<>				{ return NE; }
\:=				{ return ASSIGN; }
\;				{ return SEMICOLON; }
\:				{ return COLON; }
\[				{ return LBRACKET; }
\]				{ return RBRACKET; }
\(				{ return LPAREN; }
\)				{ return RPAREN; }
\+				{ return PLUS; }
\-          			{ return MINUS; }
\*				{ return TIMES; }
\DIV				{ return DIVIDE;}
","				{ return COMMA;}
"."				{ return DOT;}
".."				{ return DOTDOT;}

\'[^\']*\'			{ 
					tokenAttr = (TokenAttributePtr)malloc(sizeof(TokenAttribute));
					tokenAttr->name = ssave(SomeLife_text);
					return STRING; 
				}
	
[a-zA-Z]([a-zA-Z]|[0-9])*	{ 
					tokenAttr = (TokenAttributePtr)malloc(sizeof(TokenAttribute));
					tokenAttr->name = ssave(SomeLife_text);
					return IDENTIFIER; 
				}
	
[0-9]+				{ 
					tokenAttr = (TokenAttributePtr)malloc(sizeof(TokenAttribute));
					tokenAttr->name = ssave(SomeLife_text);
					return INTNUM; 
				}

{WS}				{ ; }

"\n"				{ SomeLife_lineno++;}

[0-9]*(\.)[0-9]+((e|E)(\+|\-)?[0-9]*)?/[^.$]   { 
							tokenAttr = (TokenAttributePtr)malloc(sizeof(TokenAttribute));
							tokenAttr->name = ssave(SomeLife_text);
							return FLOATCON; 
						}

[0-9]+((e|E)(\+|\-)?[0-9]*)/[^.$]  { 
					tokenAttr = (TokenAttributePtr)malloc(sizeof(TokenAttribute));
					tokenAttr->name = ssave(SomeLife_text);
					return FLOATCON; 
				   }


.				{
					fprintf(stderr, "Scanner: lexical error '%s'.\n", SomeLife_text); 
				}
%%
