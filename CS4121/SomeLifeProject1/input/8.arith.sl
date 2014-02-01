PROGRAM arithExpr;

VAR i,j,k,l,m,s0 : INTEGER;

BEGIN
	i := 1; j := 2; k := 3; l := 4;
	m := i + 7;
	WRITE(m);
	s0 := l - 5;
	WRITE(s0);
	WRITE(j*k*l);
	WRITE(j*k-l);
	WRITE(i+j+k*l);
	WRITE(k * l DIV j -i);		
	WRITE((i+j)*(k + (i*m)) DIV s0)
END.
