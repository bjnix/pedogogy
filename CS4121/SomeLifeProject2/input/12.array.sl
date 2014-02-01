PROGRAM array;
 VAR  a : ARRAY [0..9] OF INTEGER;
      i : INTEGER;

BEGIN
	a[3] := 4;
	WRITE(a[3]);
	i := 3;
	a[i] := 7;
	WRITE(a[i]);
	i := i + 2;
	a[i] := 22;
	WRITE(a[5])
END.
