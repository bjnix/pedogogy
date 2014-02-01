PROGRAM exprMix;

VAR i,j,k,l,m,n : INTEGER;

BEGIN
	WRITE('i= ');
	READ(i);
	WRITE('j= ');
	READ(j);
	WRITE('k= ');
	READ(k);
	WRITE('l= ');
	READ(l);
	WRITE('m= ');
	READ(m);
	n := k < j + l AND (NOT (i <> k));
	WRITE(n);
	WRITE(i*l > j*k OR 1 <=m);
	WRITE(k*l > j+k+l AND i <> j OR l >n);
	WRITE(l DIV j - i > m AND k + l * n <> l + k * n)
END.
