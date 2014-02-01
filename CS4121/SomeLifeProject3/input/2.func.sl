PROGRAM func;
VAR a : INTEGER;

FUNCTION t : INTEGER;
VAR b : INTEGER; 
BEGIN
	WRITE('inside function');
	b := 10;
	RETURN b+5
END;

BEGIN
  a := t();
  WRITE(a)
END.
