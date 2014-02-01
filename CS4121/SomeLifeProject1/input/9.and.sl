PROGRAM exprAND;

VAR i,j,k,l : INTEGER;

BEGIN
	i := 1; j := 0; k := 0; l := 1;

	WRITE(i AND j);
	WRITE(i AND i);
	WRITE(k AND l);
        WRITE(k AND j);
	WRITE(i AND l AND k)
END.
