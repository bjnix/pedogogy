PROGRAM ifcompound;

VAR a,b : INTEGER;

BEGIN
	WRITE('Enter a:');
	READ(a);
	IF (a) THEN
	  BEGIN
	    b := 0 - a;
	    WRITE(b)
	  END
	ELSE
          BEGIN 
	    b := a;
	    WRITE(b)
	  END  
END.
