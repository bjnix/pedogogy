PROGRAM ifinput;
	VAR a,b : INTEGER;
	
BEGIN
	WRITE('enter a:'); READ(a);
	WRITE('enter b:'); READ(b);
	
	IF (b > a) THEN 
		IF (a > 0) THEN
		  BEGIN
		    WRITE('a = ');
		    WRITE(a)
	          END	
		ELSE
		  BEGIN
		    WRITE('b = ');
		    WRITE(b)
	          END	
	ELSE
		IF (a < 0) THEN
		  BEGIN
		    WRITE('a = ');
		    WRITE(a)
	          END 
		ELSE
		  BEGIN
		    WRITE('b = ');
		    WRITE(b)
		  END
END.
