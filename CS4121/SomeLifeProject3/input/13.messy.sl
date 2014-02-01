PROGRAM messy;
VAR a : INTEGER;
    b : ARRAY [1..10] OF INTEGER;

FUNCTION f1 : INTEGER;
VAR a : INTEGER; 
BEGIN
	a := 10;
	WHILE (a > 0) DO
        BEGIN
          b[a] := b[a]-1;
          a := a - 1
        END;

	RETURN b[5]
END;
   
FUNCTION f2 : INTEGER;
VAR b : INTEGER; 
    a : ARRAY [2..11] OF INTEGER;
BEGIN
	b := 2;
	WHILE (b <= 11) DO
        BEGIN
          IF (b < 5) THEN
             a[b] := 0
          ELSE
             a[b] := 1;

          b := b + 1
        END;

	RETURN a[10]
END;

BEGIN
  a := 1;
  WHILE (a <= 10) DO
  BEGIN
    b[a] := a*a;
    a := a + 1
  END;
  
  WRITE(f1());
  WRITE(a);
  WRITE(f2());
  WRITE(a);
  WRITE(b[10])
END.
