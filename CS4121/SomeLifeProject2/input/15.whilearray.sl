PROGRAM whilearray;
 VAR  c : ARRAY [5..15] OF INTEGER;
      a : INTEGER;
      
BEGIN
	a := 5;
	WHILE (a <= 15) DO BEGIN
	  c[a] := a;
	  a := a+1
	END;

  	a := 5;
	WHILE (a <= 15) DO BEGIN
	  IF (a > 10) THEN
	    WRITE(c[a])
          ELSE
            WRITE(0);
	  a := a+1
	END
END.
