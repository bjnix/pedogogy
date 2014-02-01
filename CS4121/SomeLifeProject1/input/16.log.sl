PROGRAM exprLog;

VAR i,j,k,l,m : INTEGER;

BEGIN
	i := 1; j := 2; k := 3; l := 4;

	m := i<j;
	WRITE(NOT m);
	WRITE(i = j);
	WRITE(i = i);
	WRITE(l > k);
	WRITE(j >= j);
	WRITE(k <= i);
	WRITE(i <> j);
	WRITE(NOT (l>k));
	WRITE((i > j) OR (l > k));
	WRITE((j > i) AND (l > k));
    	WRITE(((i = j) OR (i <j)) AND (k <> l))

END.
