PROGRAM func;
VAR a, b : INTEGER;
   
FUNCTION t : INTEGER;
VAR a : INTEGER; 
    b : ARRAY [0..9] OF INTEGER;
BEGIN
	b[3] := 5;
	a := 2;
	WRITE(a);
	RETURN a+b[3]
END;

BEGIN
  a := 1;
  b := t();
  WRITE(a+b)
END.
