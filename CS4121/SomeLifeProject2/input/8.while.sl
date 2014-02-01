PROGRAM elemMul;
VAR a, b : INTEGER;
BEGIN
	b := 1;
	WHILE (b <= 9) DO
	  BEGIN
	     a := 1;

	     WHILE (a <= 9) DO
	       BEGIN
	         WRITE(a*b);
                 a := a + 1
 	       END;

            b := b+1
	  END   
END.
