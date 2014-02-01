PROGRAM exprOR;

VAR i,j,k,l : INTEGER;

BEGIN
	i := 1; j := 0; k := 0; l := 1;

	WRITE(i OR j);
	WRITE(i OR i);
	WRITE(k OR l);
        WRITE(k OR j);
	WRITE(i OR l OR k)
END.
