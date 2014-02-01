PROGRAM exprSub;

VAR i,j,k,l : INTEGER;

BEGIN
	WRITE(10-20);
	i := 77; k := 3; l := 4;
	j := i - k - l;
	WRITE(j);
	l := j + l - 100;
	WRITE(l)
END.
