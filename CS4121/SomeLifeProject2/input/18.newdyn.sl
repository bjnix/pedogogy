PROGRAM newdyn;
	
 VAR source,target : ARRAY [0..29] OF INTEGER;
     cost : ARRAY [0..899] OF INTEGER;
     n,m,i,j : INTEGER;
             
 BEGIN
              m := 30;
              n := 30;
              
              i := 0;
	      
              WHILE (i < m) DO BEGIN
                cost[i*m] := i;
                target[i] := i+1;
                i := i + 1
			  END;
              
              j := 0;
	      WHILE (j < n) DO BEGIN
                cost[j] := j;
                target[j] := 2 * (j+1);
                j := j + 1
		  END;
              
          i := 1;
	      WHILE (i < m) DO BEGIN	

            j := 1;
			WHILE (j < n) DO BEGIN	
              
                cost[i*m+j] := cost[(i - 1)*m+j - 1];
		  		IF (target[i] <> source[j]) THEN
		    		cost[i*m+j] := cost[i*m+j] + 1;

              	IF (cost[(i - 1)*m+j] < cost[i*m+j]) THEN
		    		cost[i*m+j] := cost[(i-1)*m+j] + 1;

                IF (cost[i*m+j - 1] < cost[i*m+j]) THEN
                    cost[i*m+j] := cost[i*m+j - 1] + 1;
              
                j := j + 1
	        END;
              
            i := i + 1

	      END;
              
          WRITE(m);
          WRITE(n);
          WRITE(cost[899])
END.
