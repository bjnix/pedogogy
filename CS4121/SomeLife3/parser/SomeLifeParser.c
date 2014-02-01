/* Driver template for the LEMON parser generator.
** The author disclaims copyright to this source code.
*/
/* First off, code is included that follows the "include" declaration
** in the input grammar file. */
#include <stdio.h>
#line 7 "SomeLifeParser.y"

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

#line 29 "SomeLifeParser.c"
/* Next is all token values, in a form suitable for use by makeheaders.
** This section will be null unless lemon is run with the -m switch.
*/
/* 
** These constants (all generated automatically by the parser generator)
** specify the various kinds of tokens (terminals) that the parser
** understands. 
**
** Each symbol here is a terminal symbol in the grammar.
*/
/* Make sure the INTERFACE macro is defined.
*/
#ifndef INTERFACE
# define INTERFACE 1
#endif
/* The next thing included is series of defines which control
** various aspects of the generated parser.
**    YYCODETYPE         is the data type used for storing terminal
**                       and nonterminal numbers.  "unsigned char" is
**                       used if there are fewer than 250 terminals
**                       and nonterminals.  "int" is used otherwise.
**    YYNOCODE           is a number of type YYCODETYPE which corresponds
**                       to no legal terminal or nonterminal number.  This
**                       number is used to fill in empty slots of the hash 
**                       table.
**    YYFALLBACK         If defined, this indicates that one or more tokens
**                       have fall-back values which should be used if the
**                       original value of the token will not parse.
**    YYACTIONTYPE       is the data type used for storing terminal
**                       and nonterminal numbers.  "unsigned char" is
**                       used if there are fewer than 250 rules and
**                       states combined.  "int" is used otherwise.
**    SomeLife_ParseTOKENTYPE     is the data type used for minor tokens given 
**                       directly to the parser from the tokenizer.
**    YYMINORTYPE        is the data type used for all minor tokens.
**                       This is typically a union of many types, one of
**                       which is SomeLife_ParseTOKENTYPE.  The entry in the union
**                       for base tokens is called "yy0".
**    YYSTACKDEPTH       is the maximum depth of the parser's stack.  If
**                       zero the stack is dynamically sized using realloc()
**    SomeLife_ParseARG_SDECL     A static variable declaration for the %extra_argument
**    SomeLife_ParseARG_PDECL     A parameter declaration for the %extra_argument
**    SomeLife_ParseARG_STORE     Code to store %extra_argument into yypParser
**    SomeLife_ParseARG_FETCH     Code to extract %extra_argument from yypParser
**    YYNSTATE           the combined number of states.
**    YYNRULE            the number of rules in the grammar
**    YYERRORSYMBOL      is the code number of the error symbol.  If not
**                       defined, then do no error processing.
*/
#define YYCODETYPE unsigned char
#define YYNOCODE 85
#define YYACTIONTYPE unsigned char
#define SomeLife_ParseTOKENTYPE TokenAttributePtr
typedef union {
  int yyinit;
  SomeLife_ParseTOKENTYPE yy0;
} YYMINORTYPE;
#ifndef YYSTACKDEPTH
#define YYSTACKDEPTH 100
#endif
#define SomeLife_ParseARG_SDECL
#define SomeLife_ParseARG_PDECL
#define SomeLife_ParseARG_FETCH
#define SomeLife_ParseARG_STORE
#define YYNSTATE 140
#define YYNRULE 72
#define YY_NO_ACTION      (YYNSTATE+YYNRULE+2)
#define YY_ACCEPT_ACTION  (YYNSTATE+YYNRULE+1)
#define YY_ERROR_ACTION   (YYNSTATE+YYNRULE)

/* The yyzerominor constant is used to initialize instances of
** YYMINORTYPE objects to zero. */
static const YYMINORTYPE yyzerominor = { 0 };

/* Define the yytestcase() macro to be a no-op if is not already defined
** otherwise.
**
** Applications can choose to define yytestcase() in the %include section
** to a macro that can assist in verifying code coverage.  For production
** code the yytestcase() macro should be turned off.  But it is useful
** for testing.
*/
#ifndef yytestcase
# define yytestcase(X)
#endif


/* Next are the tables used to determine what action to take based on the
** current state and lookahead token.  These tables are used to implement
** functions that take a state number and lookahead value and return an
** action integer.  
**
** Suppose the action integer is N.  Then the action is determined as
** follows
**
**   0 <= N < YYNSTATE                  Shift N.  That is, push the lookahead
**                                      token onto the stack and goto state N.
**
**   YYNSTATE <= N < YYNSTATE+YYNRULE   Reduce by rule N-YYNSTATE.
**
**   N == YYNSTATE+YYNRULE              A syntax error has occurred.
**
**   N == YYNSTATE+YYNRULE+1            The parser accepts its input.
**
**   N == YYNSTATE+YYNRULE+2            No such action.  Denotes unused
**                                      slots in the yy_action[] table.
**
** The action table is constructed as a single large table named yy_action[].
** Given state S and lookahead X, the action is computed as
**
**      yy_action[ yy_shift_ofst[S] + X ]
**
** If the index value yy_shift_ofst[S]+X is out of range or if the value
** yy_lookahead[yy_shift_ofst[S]+X] is not equal to X or if yy_shift_ofst[S]
** is equal to YY_SHIFT_USE_DFLT, it means that the action is not in the table
** and that yy_default[S] should be used instead.  
**
** The formula above is for computing the action when the lookahead is
** a terminal symbol.  If the lookahead is a non-terminal (as occurs after
** a reduce action) then the yy_reduce_ofst[] array is used in place of
** the yy_shift_ofst[] array and YY_REDUCE_USE_DFLT is used in place of
** YY_SHIFT_USE_DFLT.
**
** The following are the tables generated in this section:
**
**  yy_action[]        A single table containing all actions.
**  yy_lookahead[]     A table containing the lookahead for each entry in
**                     yy_action.  Used to detect hash collisions.
**  yy_shift_ofst[]    For each state, the offset into yy_action for
**                     shifting terminals.
**  yy_reduce_ofst[]   For each state, the offset into yy_action for
**                     shifting non-terminals after a reduce.
**  yy_default[]       Default action for each state.
*/
#define YY_ACTTAB_COUNT (296)
static const YYACTIONTYPE yy_action[] = {
 /*     0 */   132,   18,   19,   16,   17,   20,   21,  107,   83,  140,
 /*    10 */    24,   37,   36,  108,  139,  138,  137,  136,  135,  134,
 /*    20 */   133,   89,  132,    6,   12,  119,    8,  116,   85,   49,
 /*    30 */    90,   82,    9,    1,  128,  111,   79,  138,  137,  136,
 /*    40 */   135,  134,  133,   89,  132,   15,   14,  119,    8,  106,
 /*    50 */    15,   14,   24,   37,   36,  130,    5,  109,  139,  138,
 /*    60 */   137,  136,  135,  134,  133,   89,  132,  131,  129,  119,
 /*    70 */     8,   77,    4,  103,  102,  122,   38,  130,    2,  132,
 /*    80 */   121,  120,  137,  136,  135,  134,  133,   89,   62,  126,
 /*    90 */   129,  119,    8,  118,  117,  137,  136,  135,  134,  133,
 /*   100 */    89,   23,   22,  110,  119,    8,  130,   50,   87,   80,
 /*   110 */   101,  100,   76,  130,   44,   42,   53,   57,  126,  129,
 /*   120 */    81,   27,   42,   53,   57,  126,  129,  115,  130,   51,
 /*   130 */   130,   30,   71,   11,   86,  130,   64,   42,   53,   57,
 /*   140 */   126,  129,  127,  129,   42,   53,   57,  126,  129,    7,
 /*   150 */   130,   45,   26,   15,   14,  130,   46,   29,   71,   42,
 /*   160 */    53,   57,  126,  129,   42,   53,   57,  126,  129,  130,
 /*   170 */    52,   70,  113,  103,  102,  130,    3,   74,   42,   53,
 /*   180 */    57,  126,  129,   13,   43,   53,   57,  126,  129,  130,
 /*   190 */   123,  173,  213,   39,  130,   25,   61,   34,   41,   53,
 /*   200 */    57,  126,  129,   40,   53,   57,  126,  129,  124,  130,
 /*   210 */    35,   67,  105,  103,  102,   47,  130,   10,  104,   63,
 /*   220 */    57,  126,  129,   32,  112,  125,   60,   57,  126,  129,
 /*   230 */   130,  101,  100,   72,  130,   98,   15,   14,  130,   97,
 /*   240 */    58,   57,  126,  129,   56,   57,  126,  129,   55,   57,
 /*   250 */   126,  129,  130,   13,   95,   61,   68,  130,   28,   93,
 /*   260 */    65,   91,   54,   57,  126,  129,   61,  124,   59,  126,
 /*   270 */   129,   11,   33,  114,    1,   84,   10,   83,  124,   78,
 /*   280 */    94,   31,   48,   88,  125,   96,   75,   10,   73,   69,
 /*   290 */    99,   66,  214,   92,  214,  125,
};
static const YYCODETYPE yy_lookahead[] = {
 /*     0 */    49,    4,    5,    6,    7,    8,    9,   51,   16,    0,
 /*    10 */    54,   55,   56,   62,   63,   64,   65,   66,   67,   68,
 /*    20 */    69,   70,   49,   31,   30,   74,   75,   35,   36,   78,
 /*    30 */    14,   39,   40,   41,   38,   62,   63,   64,   65,   66,
 /*    40 */    67,   68,   69,   70,   49,    1,    2,   74,   75,   51,
 /*    50 */     1,    2,   54,   55,   56,   70,   32,   62,   63,   64,
 /*    60 */    65,   66,   67,   68,   69,   70,   49,   82,   83,   74,
 /*    70 */    75,   57,   34,   59,   60,   26,   37,   70,   17,   49,
 /*    80 */    63,   64,   65,   66,   67,   68,   69,   70,   81,   82,
 /*    90 */    83,   74,   75,   63,   64,   65,   66,   67,   68,   69,
 /*   100 */    70,   10,   11,   42,   74,   75,   70,   71,   72,   73,
 /*   110 */    22,   23,   24,   70,   71,   79,   80,   81,   82,   83,
 /*   120 */    77,   12,   79,   80,   81,   82,   83,   38,   70,   71,
 /*   130 */    70,   20,   21,   25,   76,   70,   71,   79,   80,   81,
 /*   140 */    82,   83,   82,   83,   79,   80,   81,   82,   83,   37,
 /*   150 */    70,   71,   43,    1,    2,   70,   71,   20,   21,   79,
 /*   160 */    80,   81,   82,   83,   79,   80,   81,   82,   83,   70,
 /*   170 */    71,   57,   38,   59,   60,   70,   33,   26,   79,   80,
 /*   180 */    81,   82,   83,    3,   79,   80,   81,   82,   83,   70,
 /*   190 */    38,   32,   47,   48,   70,   50,   16,   25,   79,   80,
 /*   200 */    81,   82,   83,   79,   80,   81,   82,   83,   28,   70,
 /*   210 */    53,   57,   17,   59,   60,   58,   70,   37,   17,   80,
 /*   220 */    81,   82,   83,   27,   44,   45,   80,   81,   82,   83,
 /*   230 */    70,   22,   23,   29,   70,   28,    1,    2,   70,   16,
 /*   240 */    80,   81,   82,   83,   80,   81,   82,   83,   80,   81,
 /*   250 */    82,   83,   70,    3,   17,   16,   16,   70,   20,   17,
 /*   260 */    16,   49,   80,   81,   82,   83,   16,   28,   81,   82,
 /*   270 */    83,   25,   17,   38,   41,   70,   37,   16,   28,   49,
 /*   280 */    52,   18,   58,   37,   45,   16,   61,   37,   28,   19,
 /*   290 */    59,   15,   84,   52,   84,   45,
};
#define YY_SHIFT_USE_DFLT (-9)
#define YY_SHIFT_COUNT (91)
#define YY_SHIFT_MIN   (-8)
#define YY_SHIFT_MAX   (276)
static const short yy_shift_ofst[] = {
 /*     0 */   276,   -8,   -8,   -8,   -8,   -8,  250,  180,  250,  250,
 /*    10 */   250,  250,  250,  239,  239,  239,  239,  239,  239,  239,
 /*    20 */   239,  239,  239,  239,  270,  270,  239,  239,   88,   88,
 /*    30 */    88,  269,  209,  263,  260,  269,  263,  233,  261,  233,
 /*    40 */    -3,   -3,   -3,   -3,  235,   49,  152,  137,  111,   61,
 /*    50 */    44,   44,   44,   91,   91,   91,   91,  109,   91,  109,
 /*    60 */    91,  246,  109,   91,   44,  255,  244,  242,  238,  240,
 /*    70 */   237,  223,  207,  204,  196,  151,  172,  201,  195,  159,
 /*    80 */   143,  134,  112,  108,   89,   39,   38,   24,   -4,   -6,
 /*    90 */     9,   16,
};
#define YY_REDUCE_USE_DFLT (-50)
#define YY_REDUCE_COUNT (39)
#define YY_REDUCE_MIN   (-49)
#define YY_REDUCE_MAX   (241)
static const short yy_reduce_ofst[] = {
 /*     0 */   145,  -49,   -5,  -27,   30,   17,   36,   43,   58,   99,
 /*    10 */    85,   80,   65,  124,  119,  105,  182,  168,  164,  160,
 /*    20 */   146,  139,  187,    7,   -2,  -44,   60,  -15,  154,  114,
 /*    30 */    14,  157,  231,  241,  225,  224,  228,  230,  205,  212,
};
static const YYACTIONTYPE yy_default[] = {
 /*     0 */   212,  212,  212,  212,  212,  212,  212,  212,  212,  212,
 /*    10 */   212,  212,  212,  212,  212,  212,  212,  212,  212,  212,
 /*    20 */   212,  212,  212,  212,  146,  146,  212,  212,  212,  212,
 /*    30 */   212,  212,  212,  144,  212,  143,  144,  212,  212,  212,
 /*    40 */   189,  188,  186,  187,  212,  212,  212,  212,  212,  212,
 /*    50 */   174,  177,  182,  190,  196,  195,  194,  197,  193,  199,
 /*    60 */   192,  207,  198,  191,  168,  212,  212,  212,  212,  212,
 /*    70 */   212,  212,  212,  212,  212,  212,  212,  212,  212,  160,
 /*    80 */   212,  212,  212,  207,  212,  212,  212,  212,  212,  212,
 /*    90 */   212,  212,  142,  149,  148,  150,  152,  153,  159,  158,
 /*   100 */   157,  156,  155,  154,  151,  147,  145,  141,  184,  185,
 /*   110 */   183,  170,  209,  181,  180,  179,  178,  176,  175,  172,
 /*   120 */   171,  169,  208,  206,  211,  210,  200,  202,  205,  204,
 /*   130 */   203,  201,  167,  166,  165,  164,  163,  162,  161,  160,
};

/* The next table maps tokens into fallback tokens.  If a construct
** like the following:
** 
**      %fallback ID X Y Z.
**
** appears in the grammar, then ID becomes a fallback token for X, Y,
** and Z.  Whenever one of the tokens X, Y, or Z is input to the parser
** but it does not parse, the type of the token is changed to ID and
** the parse is retried before an error is thrown.
*/
#ifdef YYFALLBACK
static const YYCODETYPE yyFallback[] = {
};
#endif /* YYFALLBACK */

/* The following structure represents a single element of the
** parser's stack.  Information stored includes:
**
**   +  The state number for the parser at this level of the stack.
**
**   +  The value of the token stored at this level of the stack.
**      (In other words, the "major" token.)
**
**   +  The semantic value stored at this level of the stack.  This is
**      the information used by the action routines in the grammar.
**      It is sometimes called the "minor" token.
*/
struct yyStackEntry {
  YYACTIONTYPE stateno;  /* The state-number */
  YYCODETYPE major;      /* The major token value.  This is the code
                         ** number for the token at this stack level */
  YYMINORTYPE minor;     /* The user-supplied minor token value.  This
                         ** is the value of the token  */
};
typedef struct yyStackEntry yyStackEntry;

/* The state of the parser is completely contained in an instance of
** the following structure */
struct yyParser {
  int yyidx;                    /* Index of top element in stack */
#ifdef YYTRACKMAXSTACKDEPTH
  int yyidxMax;                 /* Maximum value of yyidx */
#endif
  int yyerrcnt;                 /* Shifts left before out of the error */
  SomeLife_ParseARG_SDECL                /* A place to hold %extra_argument */
#if YYSTACKDEPTH<=0
  int yystksz;                  /* Current side of the stack */
  yyStackEntry *yystack;        /* The parser's stack */
#else
  yyStackEntry yystack[YYSTACKDEPTH];  /* The parser's stack */
#endif
};
typedef struct yyParser yyParser;

#ifndef NDEBUG
#include <stdio.h>
static FILE *yyTraceFILE = 0;
static char *yyTracePrompt = 0;
#endif /* NDEBUG */

#ifndef NDEBUG
/* 
** Turn parser tracing on by giving a stream to which to write the trace
** and a prompt to preface each trace message.  Tracing is turned off
** by making either argument NULL 
**
** Inputs:
** <ul>
** <li> A FILE* to which trace output should be written.
**      If NULL, then tracing is turned off.
** <li> A prefix string written at the beginning of every
**      line of trace output.  If NULL, then tracing is
**      turned off.
** </ul>
**
** Outputs:
** None.
*/
void SomeLife_ParseTrace(FILE *TraceFILE, char *zTracePrompt){
  yyTraceFILE = TraceFILE;
  yyTracePrompt = zTracePrompt;
  if( yyTraceFILE==0 ) yyTracePrompt = 0;
  else if( yyTracePrompt==0 ) yyTraceFILE = 0;
}
#endif /* NDEBUG */

#ifndef NDEBUG
/* For tracing shifts, the names of all terminals and nonterminals
** are required.  The following table supplies these names */
static const char *const yyTokenName[] = { 
  "$",             "OR",            "AND",           "NOT",         
  "LT",            "LE",            "GT",            "GE",          
  "NE",            "EQ",            "PLUS",          "MINUS",       
  "TIMES",         "DIVDE",         "DOT",           "PROGRAM",     
  "IDENTIFIER",    "SEMICOLON",     "VAR",           "FUNCTION",    
  "COLON",         "COMMA",         "INTEGER",       "FLOAT",       
  "ARRAY",         "LBRACKET",      "RBRACKET",      "OF",          
  "INTNUM",        "DOTDOT",        "ASSIGN",        "IF",          
  "ELSE",          "THEN",          "DO",            "WHILE",       
  "READ",          "LPAREN",        "RPAREN",        "WRITE",       
  "RETURN",        "T_BEGIN",       "END",           "DIVIDE",      
  "STRING",        "FLOATCON",      "error",         "program",     
  "programHeadAndProcedures",  "compoundStatement",  "programHead",   "procedures",  
  "decls",         "declList",      "procedureDecl",  "procedureHead",
  "functionDecl",  "type",          "identifierList",  "standardType",
  "arrayType",     "dim",           "statement",     "matched_stmt",
  "open_stmt",     "matched_if",    "assignment",    "matchedWhileStatement",
  "ioStatement",   "returnStatement",  "variable",      "expr",        
  "testAndThen",   "test",          "openWhileStatement",  "whileToken",  
  "whileExpr",     "stringConstant",  "statementList",  "simpleExpr",  
  "addExpr",       "mulExpr",       "factor",        "constant",    
};
#endif /* NDEBUG */

#ifndef NDEBUG
/* For tracing reduce actions, the names of all rules are required.
*/
static const char *const yyRuleName[] = {
 /*   0 */ "program ::= programHeadAndProcedures compoundStatement DOT",
 /*   1 */ "programHeadAndProcedures ::= programHead procedures",
 /*   2 */ "programHead ::= PROGRAM IDENTIFIER SEMICOLON decls",
 /*   3 */ "decls ::= VAR declList",
 /*   4 */ "decls ::=",
 /*   5 */ "procedures ::= procedureDecl procedures",
 /*   6 */ "procedures ::=",
 /*   7 */ "procedureDecl ::= procedureHead compoundStatement SEMICOLON",
 /*   8 */ "procedureHead ::= functionDecl decls",
 /*   9 */ "functionDecl ::= FUNCTION IDENTIFIER COLON type SEMICOLON",
 /*  10 */ "declList ::= identifierList COLON type SEMICOLON",
 /*  11 */ "declList ::= declList identifierList COLON type SEMICOLON",
 /*  12 */ "identifierList ::= IDENTIFIER",
 /*  13 */ "identifierList ::= identifierList COMMA IDENTIFIER",
 /*  14 */ "type ::= standardType",
 /*  15 */ "type ::= arrayType",
 /*  16 */ "standardType ::= INTEGER",
 /*  17 */ "standardType ::= FLOAT",
 /*  18 */ "arrayType ::= ARRAY LBRACKET dim RBRACKET OF standardType",
 /*  19 */ "dim ::= INTNUM DOTDOT INTNUM",
 /*  20 */ "statement ::= matched_stmt",
 /*  21 */ "statement ::= open_stmt",
 /*  22 */ "matched_stmt ::= matched_if",
 /*  23 */ "matched_stmt ::= assignment",
 /*  24 */ "matched_stmt ::= matchedWhileStatement",
 /*  25 */ "matched_stmt ::= ioStatement",
 /*  26 */ "matched_stmt ::= returnStatement",
 /*  27 */ "matched_stmt ::= compoundStatement",
 /*  28 */ "assignment ::= variable ASSIGN expr",
 /*  29 */ "matched_if ::= IF testAndThen ELSE matched_stmt",
 /*  30 */ "open_stmt ::= IF test THEN statement",
 /*  31 */ "open_stmt ::= IF testAndThen ELSE open_stmt",
 /*  32 */ "open_stmt ::= openWhileStatement",
 /*  33 */ "testAndThen ::= test THEN matched_stmt",
 /*  34 */ "test ::= expr",
 /*  35 */ "matchedWhileStatement ::= whileToken whileExpr DO matched_stmt",
 /*  36 */ "openWhileStatement ::= whileToken whileExpr DO open_stmt",
 /*  37 */ "whileExpr ::= expr",
 /*  38 */ "whileToken ::= WHILE",
 /*  39 */ "ioStatement ::= READ LPAREN variable RPAREN",
 /*  40 */ "ioStatement ::= WRITE LPAREN expr RPAREN",
 /*  41 */ "ioStatement ::= WRITE LPAREN stringConstant RPAREN",
 /*  42 */ "returnStatement ::= RETURN expr",
 /*  43 */ "compoundStatement ::= T_BEGIN statementList END",
 /*  44 */ "statementList ::= statement",
 /*  45 */ "statementList ::= statementList SEMICOLON statement",
 /*  46 */ "expr ::= simpleExpr",
 /*  47 */ "expr ::= expr OR simpleExpr",
 /*  48 */ "expr ::= expr AND simpleExpr",
 /*  49 */ "expr ::= NOT simpleExpr",
 /*  50 */ "simpleExpr ::= addExpr",
 /*  51 */ "simpleExpr ::= simpleExpr EQ addExpr",
 /*  52 */ "simpleExpr ::= simpleExpr NE addExpr",
 /*  53 */ "simpleExpr ::= simpleExpr LE addExpr",
 /*  54 */ "simpleExpr ::= simpleExpr LT addExpr",
 /*  55 */ "simpleExpr ::= simpleExpr GE addExpr",
 /*  56 */ "simpleExpr ::= simpleExpr GT addExpr",
 /*  57 */ "addExpr ::= mulExpr",
 /*  58 */ "addExpr ::= addExpr PLUS mulExpr",
 /*  59 */ "addExpr ::= addExpr MINUS mulExpr",
 /*  60 */ "mulExpr ::= factor",
 /*  61 */ "mulExpr ::= mulExpr TIMES factor",
 /*  62 */ "mulExpr ::= mulExpr DIVIDE factor",
 /*  63 */ "factor ::= variable",
 /*  64 */ "factor ::= constant",
 /*  65 */ "factor ::= IDENTIFIER LPAREN RPAREN",
 /*  66 */ "factor ::= LPAREN expr RPAREN",
 /*  67 */ "variable ::= IDENTIFIER",
 /*  68 */ "variable ::= IDENTIFIER LBRACKET expr RBRACKET",
 /*  69 */ "stringConstant ::= STRING",
 /*  70 */ "constant ::= FLOATCON",
 /*  71 */ "constant ::= INTNUM",
};
#endif /* NDEBUG */


#if YYSTACKDEPTH<=0
/*
** Try to increase the size of the parser stack.
*/
static void yyGrowStack(yyParser *p){
  int newSize;
  yyStackEntry *pNew;

  newSize = p->yystksz*2 + 100;
  pNew = realloc(p->yystack, newSize*sizeof(pNew[0]));
  if( pNew ){
    p->yystack = pNew;
    p->yystksz = newSize;
#ifndef NDEBUG
    if( yyTraceFILE ){
      fprintf(yyTraceFILE,"%sStack grows to %d entries!\n",
              yyTracePrompt, p->yystksz);
    }
#endif
  }
}
#endif

/* 
** This function allocates a new parser.
** The only argument is a pointer to a function which works like
** malloc.
**
** Inputs:
** A pointer to the function used to allocate memory.
**
** Outputs:
** A pointer to a parser.  This pointer is used in subsequent calls
** to SomeLife_Parse and SomeLife_ParseFree.
*/
void *SomeLife_ParseAlloc(void *(*mallocProc)(size_t)){
  yyParser *pParser;
  pParser = (yyParser*)(*mallocProc)( (size_t)sizeof(yyParser) );
  if( pParser ){
    pParser->yyidx = -1;
#ifdef YYTRACKMAXSTACKDEPTH
    pParser->yyidxMax = 0;
#endif
#if YYSTACKDEPTH<=0
    pParser->yystack = NULL;
    pParser->yystksz = 0;
    yyGrowStack(pParser);
#endif
  }
  return pParser;
}

/* The following function deletes the value associated with a
** symbol.  The symbol can be either a terminal or nonterminal.
** "yymajor" is the symbol code, and "yypminor" is a pointer to
** the value.
*/
static void yy_destructor(
  yyParser *yypParser,    /* The parser */
  YYCODETYPE yymajor,     /* Type code for object to destroy */
  YYMINORTYPE *yypminor   /* The object to be destroyed */
){
  SomeLife_ParseARG_FETCH;
  switch( yymajor ){
    /* Here is inserted the actions which take place when a
    ** terminal or non-terminal is destroyed.  This can happen
    ** when the symbol is popped from the stack during a
    ** reduce or during error processing or when a parser is 
    ** being destroyed before it is finished parsing.
    **
    ** Note: during a reduce, the only symbols destroyed are those
    ** which appear on the RHS of the rule, but which are not used
    ** inside the C code.
    */
    default:  break;   /* If no destructor action specified: do nothing */
  }
}

/*
** Pop the parser's stack once.
**
** If there is a destructor routine associated with the token which
** is popped from the stack, then call it.
**
** Return the major token number for the symbol popped.
*/
static int yy_pop_parser_stack(yyParser *pParser){
  YYCODETYPE yymajor;
  yyStackEntry *yytos = &pParser->yystack[pParser->yyidx];

  if( pParser->yyidx<0 ) return 0;
#ifndef NDEBUG
  if( yyTraceFILE && pParser->yyidx>=0 ){
    fprintf(yyTraceFILE,"%sPopping %s\n",
      yyTracePrompt,
      yyTokenName[yytos->major]);
  }
#endif
  yymajor = yytos->major;
  yy_destructor(pParser, yymajor, &yytos->minor);
  pParser->yyidx--;
  return yymajor;
}

/* 
** Deallocate and destroy a parser.  Destructors are all called for
** all stack elements before shutting the parser down.
**
** Inputs:
** <ul>
** <li>  A pointer to the parser.  This should be a pointer
**       obtained from SomeLife_ParseAlloc.
** <li>  A pointer to a function used to reclaim memory obtained
**       from malloc.
** </ul>
*/
void SomeLife_ParseFree(
  void *p,                    /* The parser to be deleted */
  void (*freeProc)(void*)     /* Function used to reclaim memory */
){
  yyParser *pParser = (yyParser*)p;
  if( pParser==0 ) return;
  while( pParser->yyidx>=0 ) yy_pop_parser_stack(pParser);
#if YYSTACKDEPTH<=0
  free(pParser->yystack);
#endif
  (*freeProc)((void*)pParser);
}

/*
** Return the peak depth of the stack for a parser.
*/
#ifdef YYTRACKMAXSTACKDEPTH
int SomeLife_ParseStackPeak(void *p){
  yyParser *pParser = (yyParser*)p;
  return pParser->yyidxMax;
}
#endif

/*
** Find the appropriate action for a parser given the terminal
** look-ahead token iLookAhead.
**
** If the look-ahead token is YYNOCODE, then check to see if the action is
** independent of the look-ahead.  If it is, return the action, otherwise
** return YY_NO_ACTION.
*/
static int yy_find_shift_action(
  yyParser *pParser,        /* The parser */
  YYCODETYPE iLookAhead     /* The look-ahead token */
){
  int i;
  int stateno = pParser->yystack[pParser->yyidx].stateno;
 
  if( stateno>YY_SHIFT_COUNT
   || (i = yy_shift_ofst[stateno])==YY_SHIFT_USE_DFLT ){
    return yy_default[stateno];
  }
  assert( iLookAhead!=YYNOCODE );
  i += iLookAhead;
  if( i<0 || i>=YY_ACTTAB_COUNT || yy_lookahead[i]!=iLookAhead ){
    if( iLookAhead>0 ){
#ifdef YYFALLBACK
      YYCODETYPE iFallback;            /* Fallback token */
      if( iLookAhead<sizeof(yyFallback)/sizeof(yyFallback[0])
             && (iFallback = yyFallback[iLookAhead])!=0 ){
#ifndef NDEBUG
        if( yyTraceFILE ){
          fprintf(yyTraceFILE, "%sFALLBACK %s => %s\n",
             yyTracePrompt, yyTokenName[iLookAhead], yyTokenName[iFallback]);
        }
#endif
        return yy_find_shift_action(pParser, iFallback);
      }
#endif
#ifdef YYWILDCARD
      {
        int j = i - iLookAhead + YYWILDCARD;
        if( 
#if YY_SHIFT_MIN+YYWILDCARD<0
          j>=0 &&
#endif
#if YY_SHIFT_MAX+YYWILDCARD>=YY_ACTTAB_COUNT
          j<YY_ACTTAB_COUNT &&
#endif
          yy_lookahead[j]==YYWILDCARD
        ){
#ifndef NDEBUG
          if( yyTraceFILE ){
            fprintf(yyTraceFILE, "%sWILDCARD %s => %s\n",
               yyTracePrompt, yyTokenName[iLookAhead], yyTokenName[YYWILDCARD]);
          }
#endif /* NDEBUG */
          return yy_action[j];
        }
      }
#endif /* YYWILDCARD */
    }
    return yy_default[stateno];
  }else{
    return yy_action[i];
  }
}

/*
** Find the appropriate action for a parser given the non-terminal
** look-ahead token iLookAhead.
**
** If the look-ahead token is YYNOCODE, then check to see if the action is
** independent of the look-ahead.  If it is, return the action, otherwise
** return YY_NO_ACTION.
*/
static int yy_find_reduce_action(
  int stateno,              /* Current state number */
  YYCODETYPE iLookAhead     /* The look-ahead token */
){
  int i;
#ifdef YYERRORSYMBOL
  if( stateno>YY_REDUCE_COUNT ){
    return yy_default[stateno];
  }
#else
  assert( stateno<=YY_REDUCE_COUNT );
#endif
  i = yy_reduce_ofst[stateno];
  assert( i!=YY_REDUCE_USE_DFLT );
  assert( iLookAhead!=YYNOCODE );
  i += iLookAhead;
#ifdef YYERRORSYMBOL
  if( i<0 || i>=YY_ACTTAB_COUNT || yy_lookahead[i]!=iLookAhead ){
    return yy_default[stateno];
  }
#else
  assert( i>=0 && i<YY_ACTTAB_COUNT );
  assert( yy_lookahead[i]==iLookAhead );
#endif
  return yy_action[i];
}

/*
** The following routine is called if the stack overflows.
*/
static void yyStackOverflow(yyParser *yypParser, YYMINORTYPE *yypMinor){
   SomeLife_ParseARG_FETCH;
   yypParser->yyidx--;
#ifndef NDEBUG
   if( yyTraceFILE ){
     fprintf(yyTraceFILE,"%sStack Overflow!\n",yyTracePrompt);
   }
#endif
   while( yypParser->yyidx>=0 ) yy_pop_parser_stack(yypParser);
   /* Here code is inserted which will execute if the parser
   ** stack every overflows */
   SomeLife_ParseARG_STORE; /* Suppress warning about unused %extra_argument var */
}

/*
** Perform a shift action.
*/
static void yy_shift(
  yyParser *yypParser,          /* The parser to be shifted */
  int yyNewState,               /* The new state to shift in */
  int yyMajor,                  /* The major token to shift in */
  YYMINORTYPE *yypMinor         /* Pointer to the minor token to shift in */
){
  yyStackEntry *yytos;
  yypParser->yyidx++;
#ifdef YYTRACKMAXSTACKDEPTH
  if( yypParser->yyidx>yypParser->yyidxMax ){
    yypParser->yyidxMax = yypParser->yyidx;
  }
#endif
#if YYSTACKDEPTH>0 
  if( yypParser->yyidx>=YYSTACKDEPTH ){
    yyStackOverflow(yypParser, yypMinor);
    return;
  }
#else
  if( yypParser->yyidx>=yypParser->yystksz ){
    yyGrowStack(yypParser);
    if( yypParser->yyidx>=yypParser->yystksz ){
      yyStackOverflow(yypParser, yypMinor);
      return;
    }
  }
#endif
  yytos = &yypParser->yystack[yypParser->yyidx];
  yytos->stateno = (YYACTIONTYPE)yyNewState;
  yytos->major = (YYCODETYPE)yyMajor;
  yytos->minor = *yypMinor;
#ifndef NDEBUG
  if( yyTraceFILE && yypParser->yyidx>0 ){
    int i;
    fprintf(yyTraceFILE,"%sShift %d\n",yyTracePrompt,yyNewState);
    fprintf(yyTraceFILE,"%sStack:",yyTracePrompt);
    for(i=1; i<=yypParser->yyidx; i++)
      fprintf(yyTraceFILE," %s",yyTokenName[yypParser->yystack[i].major]);
    fprintf(yyTraceFILE,"\n");
  }
#endif
}

/* The following table contains information about every rule that
** is used during the reduce.
*/
static const struct {
  YYCODETYPE lhs;         /* Symbol on the left-hand side of the rule */
  unsigned char nrhs;     /* Number of right-hand side symbols in the rule */
} yyRuleInfo[] = {
  { 47, 3 },
  { 48, 2 },
  { 50, 4 },
  { 52, 2 },
  { 52, 0 },
  { 51, 2 },
  { 51, 0 },
  { 54, 3 },
  { 55, 2 },
  { 56, 5 },
  { 53, 4 },
  { 53, 5 },
  { 58, 1 },
  { 58, 3 },
  { 57, 1 },
  { 57, 1 },
  { 59, 1 },
  { 59, 1 },
  { 60, 6 },
  { 61, 3 },
  { 62, 1 },
  { 62, 1 },
  { 63, 1 },
  { 63, 1 },
  { 63, 1 },
  { 63, 1 },
  { 63, 1 },
  { 63, 1 },
  { 66, 3 },
  { 65, 4 },
  { 64, 4 },
  { 64, 4 },
  { 64, 1 },
  { 72, 3 },
  { 73, 1 },
  { 67, 4 },
  { 74, 4 },
  { 76, 1 },
  { 75, 1 },
  { 68, 4 },
  { 68, 4 },
  { 68, 4 },
  { 69, 2 },
  { 49, 3 },
  { 78, 1 },
  { 78, 3 },
  { 71, 1 },
  { 71, 3 },
  { 71, 3 },
  { 71, 2 },
  { 79, 1 },
  { 79, 3 },
  { 79, 3 },
  { 79, 3 },
  { 79, 3 },
  { 79, 3 },
  { 79, 3 },
  { 80, 1 },
  { 80, 3 },
  { 80, 3 },
  { 81, 1 },
  { 81, 3 },
  { 81, 3 },
  { 82, 1 },
  { 82, 1 },
  { 82, 3 },
  { 82, 3 },
  { 70, 1 },
  { 70, 4 },
  { 77, 1 },
  { 83, 1 },
  { 83, 1 },
};

static void yy_accept(yyParser*);  /* Forward Declaration */

/*
** Perform a reduce action and the shift that must immediately
** follow the reduce.
*/
static void yy_reduce(
  yyParser *yypParser,         /* The parser */
  int yyruleno                 /* Number of the rule by which to reduce */
){
  int yygoto;                     /* The next state */
  int yyact;                      /* The next action */
  YYMINORTYPE yygotominor;        /* The LHS of the rule reduced */
  yyStackEntry *yymsp;            /* The top of the parser's stack */
  int yysize;                     /* Amount to pop the stack */
  SomeLife_ParseARG_FETCH;
  yymsp = &yypParser->yystack[yypParser->yyidx];
#ifndef NDEBUG
  if( yyTraceFILE && yyruleno>=0 
        && yyruleno<(int)(sizeof(yyRuleName)/sizeof(yyRuleName[0])) ){
    fprintf(yyTraceFILE, "%sReduce [%s].\n", yyTracePrompt,
      yyRuleName[yyruleno]);
  }
#endif /* NDEBUG */

  /* Silence complaints from purify about yygotominor being uninitialized
  ** in some cases when it is copied into the stack after the following
  ** switch.  yygotominor is uninitialized when a rule reduces that does
  ** not set the value of its left-hand side nonterminal.  Leaving the
  ** value of the nonterminal uninitialized is utterly harmless as long
  ** as the value is never used.  So really the only thing this code
  ** accomplishes is to quieten purify.  
  **
  ** 2007-01-16:  The wireshark project (www.wireshark.org) reports that
  ** without this code, their parser segfaults.  I'm not sure what there
  ** parser is doing to make this happen.  This is the second bug report
  ** from wireshark this week.  Clearly they are stressing Lemon in ways
  ** that it has not been previously stressed...  (SQLite ticket #2172)
  */
  /*memset(&yygotominor, 0, sizeof(yygotominor));*/
  yygotominor = yyzerominor;


  switch( yyruleno ){
  /* Beginning here are the reduction cases.  A typical example
  ** follows:
  **   case 0:
  **  #line <lineno> <grammarfile>
  **     { ... }           // User supplied code
  **  #line <lineno> <thisfile>
  **     break;
  */
      case 0: /* program ::= programHeadAndProcedures compoundStatement DOT */
#line 45 "SomeLifeParser.y"
{
		    //end of program
		    emitExit(instList);
		    emitDataPrologue(dataList);
		    emitInstructions(instList);
	    }
#line 909 "SomeLifeParser.c"
        break;
      case 1: /* programHeadAndProcedures ::= programHead procedures */
#line 53 "SomeLifeParser.y"
{
		     //all function and procedure declaration finished
		     emitProcedurePrologue(instList,symtab,yymsp[-1].minor.yy0->symIndex,0);
		     free(yymsp[-1].minor.yy0);
	     }
#line 918 "SomeLifeParser.c"
        break;
      case 2: /* programHead ::= PROGRAM IDENTIFIER SEMICOLON decls */
#line 60 "SomeLifeParser.y"
{
		     //program declared
		     int symIndex = SymIndex(symtab,"main");
		     int intIndex = SymQueryIndex(symtab,SYMTAB_INTEGER_TYPE_STRING);
		     SymPutFieldByIndex(symtab,symIndex,SYMTAB_TYPE_INDEX_FIELD,(Generic)intIndex);
		     yygotominor.yy0 = malloc(sizeof(TokenAttribute));
		     yygotominor.yy0->symIndex = symIndex;
		     globalOffset = yymsp[0].minor.yy0->offset;
		     free(yymsp[0].minor.yy0);
	    }
#line 932 "SomeLifeParser.c"
        break;
      case 3: /* decls ::= VAR declList */
#line 73 "SomeLifeParser.y"
{
		     //variable declaration
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->offset = yymsp[0].minor.yy0->offset;
			free(yymsp[0].minor.yy0);
		}
#line 942 "SomeLifeParser.c"
        break;
      case 4: /* decls ::= */
#line 81 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->offset = 4;
		}
#line 950 "SomeLifeParser.c"
        break;
      case 5: /* procedures ::= procedureDecl procedures */
#line 87 "SomeLifeParser.y"
{
		     
	        }
#line 957 "SomeLifeParser.c"
        break;
      case 6: /* procedures ::= */
#line 91 "SomeLifeParser.y"
{
		     //all procedures have been declared
	        }
#line 964 "SomeLifeParser.c"
        break;
      case 7: /* procedureDecl ::= procedureHead compoundStatement SEMICOLON */
#line 96 "SomeLifeParser.y"
{
			//procedure has been declared, pop scope
			//TODO:pre-return
			emitPreReturn(instList);
			endScope(symtabStack);
			symtab = lastSymtab(symtabStack);
			char* inst = ssave("#symtab end");  
			dlinkAppend(instList,dlinkNodeAlloc(inst));
		}
#line 977 "SomeLifeParser.c"
        break;
      case 8: /* procedureHead ::= functionDecl decls */
#line 107 "SomeLifeParser.y"
{
			    //TODO:post-call
			    //variables declared
			emitPostCall(instList,symtabStack,yymsp[-1].minor.yy0->symIndex,yymsp[0].minor.yy0->offset);
			free(yymsp[0].minor.yy0);
	        }
#line 987 "SomeLifeParser.c"
        break;
      case 9: /* functionDecl ::= FUNCTION IDENTIFIER COLON type SEMICOLON */
#line 115 "SomeLifeParser.y"
{
			int symIndex = SymIndex(symtab,yymsp[-3].minor.yy0->name);
			int intIndex = SymQueryIndex(symtab,SYMTAB_INTEGER_TYPE_STRING);
		     	SymPutFieldByIndex(symtab,symIndex,SYMTAB_TYPE_INDEX_FIELD,(Generic)intIndex);
			
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = symIndex;
			//defined in main
			initSymTable();
			char* inst = ssave("#symtab start");  
			dlinkAppend(instList,dlinkNodeAlloc(inst));
			free(yymsp[-3].minor.yy0);
			free(yymsp[-1].minor.yy0);
		}
#line 1005 "SomeLifeParser.c"
        break;
      case 10: /* declList ::= identifierList COLON type SEMICOLON */
#line 131 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));

			AddIdStructPtr data = (AddIdStructPtr)malloc(sizeof(AddIdStruct));
			
			data->idSymtab = symtab;
			data->typeSymtab = symtab;
			data->typeIndex = yymsp[-1].minor.yy0->symIndex;
			data->offset = 4;
			
			dlinkApply1(yymsp[-3].minor.yy0->idList,(DLinkApply1Func)addIdToSymtab,(Generic)data);
			yygotominor.yy0->offset = data->offset;
			dlinkFreeNodes(yymsp[-3].minor.yy0->idList);
			
			free(data);
			free(yymsp[-1].minor.yy0);
			free(yymsp[-3].minor.yy0);
		}
#line 1027 "SomeLifeParser.c"
        break;
      case 11: /* declList ::= declList identifierList COLON type SEMICOLON */
#line 151 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			AddIdStructPtr data = (AddIdStructPtr)malloc(sizeof(AddIdStruct));
			
			data->idSymtab = symtab;
			data->typeSymtab = symtab;
			data->typeIndex = yymsp[-1].minor.yy0->symIndex;
			data->offset = yymsp[-4].minor.yy0->offset;
			
			dlinkApply1(yymsp[-3].minor.yy0->idList,(DLinkApply1Func)addIdToSymtab,(Generic)data);
			yygotominor.yy0->offset = data->offset;
			dlinkFreeNodes(yymsp[-3].minor.yy0->idList);
			
			free(data);
			free(yymsp[-4].minor.yy0);
			free(yymsp[-1].minor.yy0);
			free(yymsp[-3].minor.yy0);
	 	}
#line 1049 "SomeLifeParser.c"
        break;
      case 12: /* identifierList ::= IDENTIFIER */
#line 172 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->idList = dlinkListAlloc(NULL);
			int symtabIndex = SymIndex(symtab,yymsp[0].minor.yy0->name);
			dlinkAppend(yygotominor.yy0->idList,dlinkNodeAlloc((Generic)symtabIndex));
			free(yymsp[0].minor.yy0);
		}
#line 1060 "SomeLifeParser.c"
        break;
      case 13: /* identifierList ::= identifierList COMMA IDENTIFIER */
#line 181 "SomeLifeParser.y"
{
			int symtabIndex = SymIndex(symtab,yymsp[0].minor.yy0->name);
			dlinkAppend(yymsp[-2].minor.yy0->idList,dlinkNodeAlloc((Generic)symtabIndex));
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->idList = yymsp[-2].minor.yy0->idList;
			free(yymsp[0].minor.yy0);
			free(yymsp[-2].minor.yy0);
		}
#line 1072 "SomeLifeParser.c"
        break;
      case 14: /* type ::= standardType */
      case 15: /* type ::= arrayType */ yytestcase(yyruleno==15);
#line 191 "SomeLifeParser.y"
{ 
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = yymsp[0].minor.yy0->symIndex;
		}
#line 1081 "SomeLifeParser.c"
        break;
      case 16: /* standardType ::= INTEGER */
#line 203 "SomeLifeParser.y"
{ 
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = SymQueryIndex(symtab,SYMTAB_INTEGER_TYPE_STRING);
		}
#line 1089 "SomeLifeParser.c"
        break;
      case 18: /* arrayType ::= ARRAY LBRACKET dim RBRACKET OF standardType */
#line 211 "SomeLifeParser.y"
{
    			char* lowerBound = (char*)SymGetFieldByIndex(symtab,yymsp[-3].minor.yy0->bounds.lowerBoundIndex,SYM_NAME_FIELD);
    			char* upperBound = (char*)SymGetFieldByIndex(symtab,yymsp[-3].minor.yy0->bounds.upperBoundIndex,SYM_NAME_FIELD);
    			char* dimString = nssave(3,lowerBound,"..",upperBound);
    				   
    			char* baseTypeString = (char*)SymGetFieldByIndex(symtab,yymsp[0].minor.yy0->symIndex,SYM_NAME_FIELD);
    		
    			char* typeString = nssave(4,baseTypeString,"[",dimString,"]");
    			
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
    			yygotominor.yy0->symIndex = SymIndex(symtab,typeString);
    		
    			int numElements = atoi(upperBound) - atoi(lowerBound) + 1;
    			SymPutFieldByIndex(symtab,yygotominor.yy0->symIndex,SYMTAB_SIZE_FIELD,
    					   (Generic)(((int)SymGetField(symtab,baseTypeString,SYMTAB_SIZE_FIELD))*numElements));
		}
#line 1109 "SomeLifeParser.c"
        break;
      case 19: /* dim ::= INTNUM DOTDOT INTNUM */
#line 229 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			int symIndex = SymIndex(symtab,yymsp[-2].minor.yy0->name);
			yygotominor.yy0->bounds.lowerBoundIndex = symIndex;
			symIndex = SymIndex(symtab,yymsp[0].minor.yy0->name);
			yygotominor.yy0->bounds.upperBoundIndex = symIndex;
		}
#line 1120 "SomeLifeParser.c"
        break;
      case 28: /* assignment ::= variable ASSIGN expr */
#line 248 "SomeLifeParser.y"
{
			
			emitAssignment(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1130 "SomeLifeParser.c"
        break;
      case 29: /* matched_if ::= IF testAndThen ELSE matched_stmt */
      case 30: /* open_stmt ::= IF test THEN statement */ yytestcase(yyruleno==30);
      case 31: /* open_stmt ::= IF testAndThen ELSE open_stmt */ yytestcase(yyruleno==31);
#line 256 "SomeLifeParser.y"
{
			emitEndBranchTarget(instList,symtab,yymsp[-2].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
		}
#line 1140 "SomeLifeParser.c"
        break;
      case 33: /* testAndThen ::= test THEN matched_stmt */
#line 276 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
		   	yygotominor.yy0->symIndex = emitThenBranch(instList,symtab,yymsp[-2].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
		}
#line 1149 "SomeLifeParser.c"
        break;
      case 34: /* test ::= expr */
#line 283 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex= emitIfTest(instList,symtab,yymsp[0].minor.yy0->symIndex);
			free(yymsp[0].minor.yy0);
		}
#line 1158 "SomeLifeParser.c"
        break;
      case 35: /* matchedWhileStatement ::= whileToken whileExpr DO matched_stmt */
      case 36: /* openWhileStatement ::= whileToken whileExpr DO open_stmt */ yytestcase(yyruleno==36);
#line 290 "SomeLifeParser.y"
{
			emitWhileLoopBackBranch(instList,symtab,yymsp[-3].minor.yy0->symIndex,yymsp[-2].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[-3].minor.yy0);
		}
#line 1168 "SomeLifeParser.c"
        break;
      case 37: /* whileExpr ::= expr */
#line 304 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitWhileLoopTest(instList,symtab,yymsp[0].minor.yy0->symIndex);
			free(yymsp[0].minor.yy0);
		}
#line 1177 "SomeLifeParser.c"
        break;
      case 38: /* whileToken ::= WHILE */
#line 311 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitWhileLoopLandingPad(instList,symtab);
		}
#line 1185 "SomeLifeParser.c"
        break;
      case 39: /* ioStatement ::= READ LPAREN variable RPAREN */
#line 317 "SomeLifeParser.y"
{
			emitReadVariable(instList,symtab,yymsp[-1].minor.yy0->symIndex);
			free(yymsp[-1].minor.yy0);
		}
#line 1193 "SomeLifeParser.c"
        break;
      case 40: /* ioStatement ::= WRITE LPAREN expr RPAREN */
#line 323 "SomeLifeParser.y"
{
		        emitWriteExpression(instList,symtab,yymsp[-1].minor.yy0->symIndex,SYSCALL_PRINT_INTEGER);
			free(yymsp[-1].minor.yy0);
		}
#line 1201 "SomeLifeParser.c"
        break;
      case 41: /* ioStatement ::= WRITE LPAREN stringConstant RPAREN */
#line 329 "SomeLifeParser.y"
{
			emitWriteExpression(instList,symtab,yymsp[-1].minor.yy0->symIndex,SYSCALL_PRINT_STRING);
			free(yymsp[-1].minor.yy0);
		}
#line 1209 "SomeLifeParser.c"
        break;
      case 42: /* returnStatement ::= RETURN expr */
#line 335 "SomeLifeParser.y"
{
			//TODO:save value of expr to $v0
			
			emitReturn(instList,symtab,yymsp[0].minor.yy0->symIndex);
			free(yymsp[0].minor.yy0);
		}
#line 1219 "SomeLifeParser.c"
        break;
      case 43: /* compoundStatement ::= T_BEGIN statementList END */
#line 343 "SomeLifeParser.y"
{

	        }
#line 1226 "SomeLifeParser.c"
        break;
      case 46: /* expr ::= simpleExpr */
      case 50: /* simpleExpr ::= addExpr */ yytestcase(yyruleno==50);
      case 57: /* addExpr ::= mulExpr */ yytestcase(yyruleno==57);
      case 60: /* mulExpr ::= factor */ yytestcase(yyruleno==60);
#line 352 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = yymsp[0].minor.yy0->symIndex; 
			free(yymsp[0].minor.yy0);
		}
#line 1238 "SomeLifeParser.c"
        break;
      case 47: /* expr ::= expr OR simpleExpr */
#line 359 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitOrExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1248 "SomeLifeParser.c"
        break;
      case 48: /* expr ::= expr AND simpleExpr */
#line 367 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitAndExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1258 "SomeLifeParser.c"
        break;
      case 49: /* expr ::= NOT simpleExpr */
#line 375 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitNotExpression(instList,symtab,yymsp[0].minor.yy0->symIndex);
			free(yymsp[0].minor.yy0);
		}
#line 1267 "SomeLifeParser.c"
        break;
      case 51: /* simpleExpr ::= simpleExpr EQ addExpr */
#line 389 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitEqualExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1277 "SomeLifeParser.c"
        break;
      case 52: /* simpleExpr ::= simpleExpr NE addExpr */
#line 397 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitNotEqualExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1287 "SomeLifeParser.c"
        break;
      case 53: /* simpleExpr ::= simpleExpr LE addExpr */
#line 405 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitLessEqualExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1297 "SomeLifeParser.c"
        break;
      case 54: /* simpleExpr ::= simpleExpr LT addExpr */
#line 413 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitLessThanExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1307 "SomeLifeParser.c"
        break;
      case 55: /* simpleExpr ::= simpleExpr GE addExpr */
#line 421 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitGreaterEqualExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1317 "SomeLifeParser.c"
        break;
      case 56: /* simpleExpr ::= simpleExpr GT addExpr */
#line 429 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitGreaterThanExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1327 "SomeLifeParser.c"
        break;
      case 58: /* addExpr ::= addExpr PLUS mulExpr */
#line 444 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitAddExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1337 "SomeLifeParser.c"
        break;
      case 59: /* addExpr ::= addExpr MINUS mulExpr */
#line 452 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitSubtractExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1347 "SomeLifeParser.c"
        break;
      case 61: /* mulExpr ::= mulExpr TIMES factor */
#line 467 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitMultiplyExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1357 "SomeLifeParser.c"
        break;
      case 62: /* mulExpr ::= mulExpr DIVIDE factor */
#line 475 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitDivideExpression(instList,symtab,yymsp[-2].minor.yy0->symIndex,yymsp[0].minor.yy0->symIndex);
			free(yymsp[-2].minor.yy0);
			free(yymsp[0].minor.yy0);
		}
#line 1367 "SomeLifeParser.c"
        break;
      case 63: /* factor ::= variable */
#line 483 "SomeLifeParser.y"
{ 
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitLoadVariable(instList,symtab,yymsp[0].minor.yy0->symIndex);
			free(yymsp[0].minor.yy0);
		}
#line 1376 "SomeLifeParser.c"
        break;
      case 64: /* factor ::= constant */
#line 490 "SomeLifeParser.y"
{ 
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = yymsp[0].minor.yy0->symIndex;
			free(yymsp[0].minor.yy0);
		}
#line 1385 "SomeLifeParser.c"
        break;
      case 65: /* factor ::= IDENTIFIER LPAREN RPAREN */
#line 497 "SomeLifeParser.y"
{
			//TODO:caller pre-call
			SymTable symTab = findSymtab(symtabStack,yymsp[-2].minor.yy0->name);
			int symIndex = SymQueryIndex(symTab,yymsp[-2].minor.yy0->name);
			emitPreCall(instList,symTab,symIndex);
			//TODO:caller post-return
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = emitPostReturn(instList,symtab);
			free(yymsp[-2].minor.yy0);
			
		}
#line 1400 "SomeLifeParser.c"
        break;
      case 66: /* factor ::= LPAREN expr RPAREN */
#line 510 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			yygotominor.yy0->symIndex = yymsp[-1].minor.yy0->symIndex;
			free(yymsp[-1].minor.yy0);
		}
#line 1409 "SomeLifeParser.c"
        break;
      case 67: /* variable ::= IDENTIFIER */
#line 517 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute)); 
			SymTable symTab = findSymtab(symtabStack,yymsp[0].minor.yy0->name);
			int symIndex = SymQueryIndex(symTab,yymsp[0].minor.yy0->name);
			yygotominor.yy0->symIndex =emitComputeVariableAddress(instList,symTab,symTab,symIndex);
			free(yymsp[0].minor.yy0);
		}
#line 1420 "SomeLifeParser.c"
        break;
      case 68: /* variable ::= IDENTIFIER LBRACKET expr RBRACKET */
#line 526 "SomeLifeParser.y"
{
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			SymTable symTab = findSymtab(symtabStack,yymsp[-3].minor.yy0->name);
			int symIndex = SymQueryIndex(symTab,yymsp[-3].minor.yy0->name);
			yygotominor.yy0->symIndex =
			emitComputeArrayAddress(instList,symTab,symIndex,symTab,yymsp[-1].minor.yy0->symIndex);
			free(yymsp[-3].minor.yy0);
			free(yymsp[-1].minor.yy0);
               	}
#line 1433 "SomeLifeParser.c"
        break;
      case 69: /* stringConstant ::= STRING */
#line 537 "SomeLifeParser.y"
{ 
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			int symIndex = SymIndex(symtab,yymsp[0].minor.yy0->name);
			yygotominor.yy0->symIndex = emitLoadStringConstantAddress(instList,dataList,symtab,symIndex); 
			free(yymsp[0].minor.yy0);
		}
#line 1443 "SomeLifeParser.c"
        break;
      case 71: /* constant ::= INTNUM */
#line 547 "SomeLifeParser.y"
{ 
			yygotominor.yy0 = malloc(sizeof(TokenAttribute));
			int symIndex = SymIndex(symtab,yymsp[0].minor.yy0->name);
			yygotominor.yy0->symIndex = emitLoadIntegerConstant(instList,symtab,symIndex); 
			free(yymsp[0].minor.yy0);
		}
#line 1453 "SomeLifeParser.c"
        break;
      default:
      /* (17) standardType ::= FLOAT */ yytestcase(yyruleno==17);
      /* (20) statement ::= matched_stmt */ yytestcase(yyruleno==20);
      /* (21) statement ::= open_stmt */ yytestcase(yyruleno==21);
      /* (22) matched_stmt ::= matched_if */ yytestcase(yyruleno==22);
      /* (23) matched_stmt ::= assignment */ yytestcase(yyruleno==23);
      /* (24) matched_stmt ::= matchedWhileStatement */ yytestcase(yyruleno==24);
      /* (25) matched_stmt ::= ioStatement */ yytestcase(yyruleno==25);
      /* (26) matched_stmt ::= returnStatement */ yytestcase(yyruleno==26);
      /* (27) matched_stmt ::= compoundStatement */ yytestcase(yyruleno==27);
      /* (32) open_stmt ::= openWhileStatement */ yytestcase(yyruleno==32);
      /* (44) statementList ::= statement */ yytestcase(yyruleno==44);
      /* (45) statementList ::= statementList SEMICOLON statement */ yytestcase(yyruleno==45);
      /* (70) constant ::= FLOATCON */ yytestcase(yyruleno==70);
        break;
  };
  yygoto = yyRuleInfo[yyruleno].lhs;
  yysize = yyRuleInfo[yyruleno].nrhs;
  yypParser->yyidx -= yysize;
  yyact = yy_find_reduce_action(yymsp[-yysize].stateno,(YYCODETYPE)yygoto);
  if( yyact < YYNSTATE ){
#ifdef NDEBUG
    /* If we are not debugging and the reduce action popped at least
    ** one element off the stack, then we can push the new element back
    ** onto the stack here, and skip the stack overflow test in yy_shift().
    ** That gives a significant speed improvement. */
    if( yysize ){
      yypParser->yyidx++;
      yymsp -= yysize-1;
      yymsp->stateno = (YYACTIONTYPE)yyact;
      yymsp->major = (YYCODETYPE)yygoto;
      yymsp->minor = yygotominor;
    }else
#endif
    {
      yy_shift(yypParser,yyact,yygoto,&yygotominor);
    }
  }else{
    assert( yyact == YYNSTATE + YYNRULE + 1 );
    yy_accept(yypParser);
  }
}

/*
** The following code executes when the parse fails
*/
#ifndef YYNOERRORRECOVERY
static void yy_parse_failed(
  yyParser *yypParser           /* The parser */
){
  SomeLife_ParseARG_FETCH;
#ifndef NDEBUG
  if( yyTraceFILE ){
    fprintf(yyTraceFILE,"%sFail!\n",yyTracePrompt);
  }
#endif
  while( yypParser->yyidx>=0 ) yy_pop_parser_stack(yypParser);
  /* Here code is inserted which will be executed whenever the
  ** parser fails */
  SomeLife_ParseARG_STORE; /* Suppress warning about unused %extra_argument variable */
}
#endif /* YYNOERRORRECOVERY */

/*
** The following code executes when a syntax error first occurs.
*/
static void yy_syntax_error(
  yyParser *yypParser,           /* The parser */
  int yymajor,                   /* The major type of the error token */
  YYMINORTYPE yyminor            /* The minor type of the error token */
){
  SomeLife_ParseARG_FETCH;
#define TOKEN (yyminor.yy0)
#line 32 "SomeLifeParser.y"

  SomeLife_error("Syntax Error");
#line 1531 "SomeLifeParser.c"
  SomeLife_ParseARG_STORE; /* Suppress warning about unused %extra_argument variable */
}

/*
** The following is executed when the parser accepts
*/
static void yy_accept(
  yyParser *yypParser           /* The parser */
){
  SomeLife_ParseARG_FETCH;
#ifndef NDEBUG
  if( yyTraceFILE ){
    fprintf(yyTraceFILE,"%sAccept!\n",yyTracePrompt);
  }
#endif
  while( yypParser->yyidx>=0 ) yy_pop_parser_stack(yypParser);
  /* Here code is inserted which will be executed whenever the
  ** parser accepts */
  SomeLife_ParseARG_STORE; /* Suppress warning about unused %extra_argument variable */
}

/* The main parser program.
** The first argument is a pointer to a structure obtained from
** "SomeLife_ParseAlloc" which describes the current state of the parser.
** The second argument is the major token number.  The third is
** the minor token.  The fourth optional argument is whatever the
** user wants (and specified in the grammar) and is available for
** use by the action routines.
**
** Inputs:
** <ul>
** <li> A pointer to the parser (an opaque structure.)
** <li> The major token number.
** <li> The minor token number.
** <li> An option argument of a grammar-specified type.
** </ul>
**
** Outputs:
** None.
*/
void SomeLife_Parse(
  void *yyp,                   /* The parser */
  int yymajor,                 /* The major token code number */
  SomeLife_ParseTOKENTYPE yyminor       /* The value for the token */
  SomeLife_ParseARG_PDECL               /* Optional %extra_argument parameter */
){
  YYMINORTYPE yyminorunion;
  int yyact;            /* The parser action. */
  int yyendofinput;     /* True if we are at the end of input */
#ifdef YYERRORSYMBOL
  int yyerrorhit = 0;   /* True if yymajor has invoked an error */
#endif
  yyParser *yypParser;  /* The parser */

  /* (re)initialize the parser, if necessary */
  yypParser = (yyParser*)yyp;
  if( yypParser->yyidx<0 ){
#if YYSTACKDEPTH<=0
    if( yypParser->yystksz <=0 ){
      /*memset(&yyminorunion, 0, sizeof(yyminorunion));*/
      yyminorunion = yyzerominor;
      yyStackOverflow(yypParser, &yyminorunion);
      return;
    }
#endif
    yypParser->yyidx = 0;
    yypParser->yyerrcnt = -1;
    yypParser->yystack[0].stateno = 0;
    yypParser->yystack[0].major = 0;
  }
  yyminorunion.yy0 = yyminor;
  yyendofinput = (yymajor==0);
  SomeLife_ParseARG_STORE;

#ifndef NDEBUG
  if( yyTraceFILE ){
    fprintf(yyTraceFILE,"%sInput %s\n",yyTracePrompt,yyTokenName[yymajor]);
  }
#endif

  do{
    yyact = yy_find_shift_action(yypParser,(YYCODETYPE)yymajor);
    if( yyact<YYNSTATE ){
      assert( !yyendofinput );  /* Impossible to shift the $ token */
      yy_shift(yypParser,yyact,yymajor,&yyminorunion);
      yypParser->yyerrcnt--;
      yymajor = YYNOCODE;
    }else if( yyact < YYNSTATE + YYNRULE ){
      yy_reduce(yypParser,yyact-YYNSTATE);
    }else{
      assert( yyact == YY_ERROR_ACTION );
#ifdef YYERRORSYMBOL
      int yymx;
#endif
#ifndef NDEBUG
      if( yyTraceFILE ){
        fprintf(yyTraceFILE,"%sSyntax Error!\n",yyTracePrompt);
      }
#endif
#ifdef YYERRORSYMBOL
      /* A syntax error has occurred.
      ** The response to an error depends upon whether or not the
      ** grammar defines an error token "ERROR".  
      **
      ** This is what we do if the grammar does define ERROR:
      **
      **  * Call the %syntax_error function.
      **
      **  * Begin popping the stack until we enter a state where
      **    it is legal to shift the error symbol, then shift
      **    the error symbol.
      **
      **  * Set the error count to three.
      **
      **  * Begin accepting and shifting new tokens.  No new error
      **    processing will occur until three tokens have been
      **    shifted successfully.
      **
      */
      if( yypParser->yyerrcnt<0 ){
        yy_syntax_error(yypParser,yymajor,yyminorunion);
      }
      yymx = yypParser->yystack[yypParser->yyidx].major;
      if( yymx==YYERRORSYMBOL || yyerrorhit ){
#ifndef NDEBUG
        if( yyTraceFILE ){
          fprintf(yyTraceFILE,"%sDiscard input token %s\n",
             yyTracePrompt,yyTokenName[yymajor]);
        }
#endif
        yy_destructor(yypParser, (YYCODETYPE)yymajor,&yyminorunion);
        yymajor = YYNOCODE;
      }else{
         while(
          yypParser->yyidx >= 0 &&
          yymx != YYERRORSYMBOL &&
          (yyact = yy_find_reduce_action(
                        yypParser->yystack[yypParser->yyidx].stateno,
                        YYERRORSYMBOL)) >= YYNSTATE
        ){
          yy_pop_parser_stack(yypParser);
        }
        if( yypParser->yyidx < 0 || yymajor==0 ){
          yy_destructor(yypParser,(YYCODETYPE)yymajor,&yyminorunion);
          yy_parse_failed(yypParser);
          yymajor = YYNOCODE;
        }else if( yymx!=YYERRORSYMBOL ){
          YYMINORTYPE u2;
          u2.YYERRSYMDT = 0;
          yy_shift(yypParser,yyact,YYERRORSYMBOL,&u2);
        }
      }
      yypParser->yyerrcnt = 3;
      yyerrorhit = 1;
#elif defined(YYNOERRORRECOVERY)
      /* If the YYNOERRORRECOVERY macro is defined, then do not attempt to
      ** do any kind of error recovery.  Instead, simply invoke the syntax
      ** error routine and continue going as if nothing had happened.
      **
      ** Applications can set this macro (for example inside %include) if
      ** they intend to abandon the parse upon the first syntax error seen.
      */
      yy_syntax_error(yypParser,yymajor,yyminorunion);
      yy_destructor(yypParser,(YYCODETYPE)yymajor,&yyminorunion);
      yymajor = YYNOCODE;
      
#else  /* YYERRORSYMBOL is not defined */
      /* This is what we do if the grammar does not define ERROR:
      **
      **  * Report an error message, and throw away the input token.
      **
      **  * If the input token is $, then fail the parse.
      **
      ** As before, subsequent error messages are suppressed until
      ** three input tokens have been successfully shifted.
      */
      if( yypParser->yyerrcnt<=0 ){
        yy_syntax_error(yypParser,yymajor,yyminorunion);
      }
      yypParser->yyerrcnt = 3;
      yy_destructor(yypParser,(YYCODETYPE)yymajor,&yyminorunion);
      if( yyendofinput ){
        yy_parse_failed(yypParser);
      }
      yymajor = YYNOCODE;
#endif
    }
  }while( yymajor!=YYNOCODE && yypParser->yyidx>=0 );
  return;
}
