PROGRAM sum;
VAR a, s : INTEGER;
BEGIN
	a := 1;
	s := 0;
	WHILE (a <= 10) DO
	  BEGIN
	     s := s + a;
             a := a + 1
	  END;
	WRITE(s)
END.
