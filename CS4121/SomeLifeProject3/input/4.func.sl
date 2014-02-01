PROGRAM func;
VAR a, b : INTEGER;

FUNCTION t : INTEGER;
VAR a : INTEGER; 
BEGIN
	b := 5;
	a := 2;
	WRITE(a);
	RETURN a+b
END;

BEGIN
  a := 1;
  b := t();
  WRITE(a+b)
END.
