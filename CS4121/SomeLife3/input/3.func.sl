PROGRAM func;
VAR a, b : INTEGER;

FUNCTION t : INTEGER;
VAR a : INTEGER; 
BEGIN
	a := 2;
	WRITE(a);
	RETURN a+1
END;

BEGIN
  a := 1;
  b := t();
  WRITE(a+b)
END.
