PROGRAM exprMul;

VAR i,j,k,l : INTEGER;

BEGIN
	WRITE(51 DIV 17);
	WRITE(52 DIV 17);
	i := 100; k := 2; l := 5;
	j := i DIV k DIV l;
	WRITE(j);
	WRITE('Enter integer l = ');
	READ(l);
	l := j + l DIV 4;
	WRITE(l)
END.
