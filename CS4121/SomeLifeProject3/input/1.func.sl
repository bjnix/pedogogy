PROGRAM func;
VAR a : INTEGER;

FUNCTION t : INTEGER;
BEGIN
	WRITE('inside function');
	RETURN 10
END;

BEGIN
  a := t();
  WRITE(a)
END.
