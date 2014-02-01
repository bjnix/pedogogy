PROGRAM exprMix;

VAR i,j,k,l,m : INTEGER;

BEGIN
	i := 1; j := 2; k := 3; l := 4;

	m := k<j + l;
	WRITE(m);
	WRITE(i*l > j*k);
	WRITE(k*l >= j+k+l);
	WRITE(k* (l > j) + k +l)
END.
