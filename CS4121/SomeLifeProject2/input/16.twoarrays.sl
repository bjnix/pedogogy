PROGRAM whilearray;
 VAR  a : ARRAY [1..50] OF INTEGER;
      i, j, s: INTEGER;
      b: ARRAY [51..100] OF INTEGER;
      
BEGIN
	i := 1;
	WHILE (i <= 50) DO BEGIN
	  a[i] := i;
	  i := i+1
	END;

  	j := 51;
	WHILE (j <= 100) DO BEGIN
	  b[j] := j;
	  j := j+1
	END;

	i := 1;
	s := 0;
	WHILE (i <= 100) DO BEGIN
	  IF (i <= 50) THEN
	    s := s + a[i]
          ELSE
            s := s + b[i]; 
	  i := i+1
	END;

	WRITE(s);
	WRITE(a[5]);
	WRITE(b[60])
END.
