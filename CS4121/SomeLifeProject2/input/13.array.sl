PROGRAM array;
 VAR  a : ARRAY [2..9] OF INTEGER;
      i, j: INTEGER;

BEGIN
	i := 8;
	a[i] := 18;
	WRITE(a[i]);
	j := 9;
	a[j] := 19;
	WRITE(a[j]);
	WRITE(i);
	WRITE(j)
END.
