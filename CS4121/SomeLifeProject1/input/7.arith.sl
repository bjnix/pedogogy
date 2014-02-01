PROGRAM arithExpr;

VAR i,j,k,l : INTEGER;

BEGIN
	i := 1; j := 2; k := 3; l := 4;
	WRITE(i+j+k*l);
	WRITE(i+(l-i) DIV k+j)
END.
