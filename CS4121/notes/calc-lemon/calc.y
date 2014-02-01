%include{
#include <stdio.h>
#include <assert.h>
extern void yyerror();
}

%name CalcParse

%token_type {int}

prog ::= calc.

calc ::= calc  SEMICOLON expr(e).
        {printf("%d\n", e);}

calc ::= expr(e).
        {printf("%d\n", e);} 
     

expr(el) ::= expr(er) PLUS term(t). 
        {el = er+t;}

expr(el) ::=  expr(er) MINUS term(t). 
        {el = er-t;}

expr(el) ::=  term(t).
       {el = t;}

term(tl) ::= term(tr) MUL factor(f). 
        {tl = tr*f;}
	
term(tl) ::= term(tr) DIV factor(f). 
        {tl = tr/f;}

term(tl) ::= factor(f).
        {tl = f;}

factor(f) ::= INT (i).
         { f = i;}

factor(f) ::= LPAREN expr(e) RPAREN.
         {f = e;} 

factor(f) ::= MINUS factor(fr).
         {f = -fr;} 
