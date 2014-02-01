WS [ \t\n\r]

%{
#include <stdio.h>
#include "calc.h"
extern int calclval;
%}

%%
\(                      { return LPAREN; }

\)                      { return RPAREN; }

\+                      { return PLUS; }

\-                      { return MINUS; }

\*                      { return MUL; }

\/                      { return DIV;}

[0-9]+                  { calclval = atoi(yytext); return INT;}

\;                      {return SEMICOLON;}

{WS}                    {;}
%%
