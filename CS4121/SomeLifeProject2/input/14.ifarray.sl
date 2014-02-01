PROGRAM ifarray;
 VAR  i : INTEGER;
      b : ARRAY [51..100] OF INTEGER;
      
BEGIN
	WRITE('Enter i:');
	READ(i);

	IF (i<51 OR i>100) THEN
	   WRITE('OUT OF BOUND')
        ELSE BEGIN 
          b[i] := i*2;
          b[51] := 1;
          WRITE(b[i]+b[51])
        END		
END.
