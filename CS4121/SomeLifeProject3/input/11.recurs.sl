
PROGRAM foo;
  VAR a:INTEGER;

FUNCTION decls : INTEGER;
BEGIN
  WRITE(a);
  IF (a > 0) THEN
  BEGIN
    a := a - 1;
    RETURN decls()
  END
  ELSE BEGIN
    a := 0;
    RETURN a
  END
END;

BEGIN
  WRITE('Enter a:'); 
  READ(a);
  WRITE(decls())
END.
