PROGRAM dynamic;

VAR	source : ARRAY [0..18] OF INTEGER;
	target : ARRAY [0..15] OF INTEGER;
	cost : ARRAY [0..399] OF INTEGER;
	n,m,i,j,size : INTEGER;
	
BEGIN

	  source[0] := 0;        
	  source[1] := 1;        
	  source[2] := 2;        
	  source[3] := 3;        
	  source[4] := 4;        
	  source[5] := 5;        
	  source[6] := 6;        
	  source[7] := 7;        
	  source[8] := 8;        
	  source[9] := 9;        
	  source[10] := 8;        
	  source[11] := 7;        
	  source[12] := 6;        
	  source[13] := 5;        
	  source[14] := 4;        
	  source[15] := 3;        
	  source[16] := 2;        
	  source[17] := 1;        
	  source[18] := 0;        
	
	  target[0] := 0;
	  target[1] := 1;
	  target[2] := 3;
	  target[3] := 5;
	  target[4] := 7;
	  target[5] := 9;
	  target[6] := 11;
	  target[7] := 4;
	  target[8] := 3;
	  target[9] := 2;
	  target[10] := 1;
	  target[11] := 9;
	  target[12] := 8;
	  target[13] := 7;
	  target[14] := 6;
  	  target[15] := 0;
        
	  size := 20;
      n := 0;

	  WHILE (source[n+1] <> 0) DO BEGIN
          cost[n] := n;
          n := n + 1
      END;

      m := 0;
	  WHILE (target[m+1] <> 0) DO BEGIN
          cost[m*size] := m;
          m := m + 1
      END;
        
	  i := 1;
	  WHILE (i <= m) DO BEGIN
	    j := 1;
	    WHILE (j <= n) DO BEGIN
            cost[i*size+j] := cost[(i - 1)*size+j - 1];
	    
	    	IF (target[i] <> source[j]) THEN
	       		cost[i*size+j] := cost[i*size+j] + 1;
	    
	    	IF (cost[(i-1)*size+j] < cost[i*size+j]) THEN
                cost[i*size+j] := cost[(i - 1)*size+j] + 1;

	    	IF (cost[i*size+j - 1] < cost[i*size+j]) THEN
                cost[i*size+j] := cost[i*size+j - 1] + 1;

	    	j := j + 1

	  	END;
	
	  	i := i + 1

	  END;
    
    WRITE(m);
    WRITE(n);    
	WRITE(cost[m*size+n])
      
END.
