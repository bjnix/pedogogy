PROGRAM example;
   VAR x, y : INTEGER;
   FUNCTION gcd :INTEGER;
      VAR t : INTEGER;
      BEGIN
         IF y=0
            THEN RETURN x
            ELSE BEGIN
	      t := x;
	      x := y;
	      y := t - y * (t DIV y);
	      RETURN gcd()
	    END
       END;
      BEGIN
         READ (x);
         READ (y);
         WHILE (x <> 0) OR (y <> 0) DO
            BEGIN
	       WRITE (gcd());
               READ (x);
               READ (y)
             END
      END.
