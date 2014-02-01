PROGRAM func;
VAR i : INTEGER;
    a : ARRAY [1..10] OF INTEGER;
   
FUNCTION t : INTEGER;
VAR i, s : INTEGER; 
    b : ARRAY [2..11] OF INTEGER;
BEGIN
	i := 2;
	WHILE (i <=11) DO
        BEGIN
          b[i] := a[i-1];
          i := i + 1
        END;

	RETURN b[5]
END;

BEGIN
  i := 1;
  WHILE (i <= 10) DO
  BEGIN
    a[i] := i;
    i := i + 1
  END;

  i := t();
  WRITE(i)
END.
