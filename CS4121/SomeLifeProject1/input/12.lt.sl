PROGRAM exprLT;

VAR i,j,k,l : INTEGER;

BEGIN
	i := 1; j := 2; k := 3; l := 4;

	WRITE(i < j);
	WRITE(i <= i);
	WRITE(l < k);
        WRITE(k <= j);
	WRITE(i <= j < 0)
END.
