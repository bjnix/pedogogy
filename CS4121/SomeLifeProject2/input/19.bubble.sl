PROGRAM bubble;

VAR a : ARRAY [0..14] OF INTEGER;
    i,j,t,least : INTEGER;

BEGIN
	
	a[0] := 32;
	a[1] := 11;
	a[2] := 111;
	a[3] := 88;
	a[4] := 11;
	a[5] := 44;
	a[6] := 33;
	a[7] := 33;
	a[8] := 22;
	a[9] := 77;
	a[10] := 45;
	a[11] := 65;
	a[12] := 76;
	a[13] := 87;
	a[14] := 34;

	i := 0;
	
	WHILE i<14 DO BEGIN
    	least := i;
	  	j := i + 1;
	  	WHILE (j<15) DO BEGIN
            IF (a[j] < a[least]) THEN
              least := j;
       
	    	j := j + 1
	  	END;
        t := a[i];
        a[i] := a[least];
        a[least] := t;
        i := i + 1
	END;

	i := 0;
	WHILE (i<15) DO BEGIN
    	WRITE(a[i]);
	  	i := i + 1
    END
      
END.
