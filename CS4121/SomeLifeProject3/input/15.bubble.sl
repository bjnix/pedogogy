PROGRAM sortRecur;
VAR a : ARRAY [1..10] OF INTEGER;
    p, l, r : INTEGER;

FUNCTION sort : INTEGER;
VAR    i,j,t,least : INTEGER;
BEGIN	
    IF (l = r) THEN
       RETURN 1
    ELSE BEGIN
	i := l;
        least := i;
        j := l + 1;
	WHILE (j <= r ) DO BEGIN
           IF (a[j] < a[least]) THEN
             least := j;
	   j := j + 1
	END;

        t := a[i];
        a[i] := a[least];
        a[least] := t;
        
        l := l+1;
	RETURN sort()
    END
END;

FUNCTION output : INTEGER;
VAR i: INTEGER;
BEGIN
    i := 1;
    WHILE (i<=10) DO BEGIN
      WRITE(a[i]);
      i := i + 1      
    END;
    RETURN 1   
END;

FUNCTION input : INTEGER;
VAR i: INTEGER;
BEGIN
    WRITE('Enter a[1:10]:');
    i := 1;
    WHILE (i<=10) DO BEGIN
      READ(a[i]);
      i := i + 1      
    END;
    RETURN 1
END;

BEGIN
  p := input();
  p := output();
  l := 1;
  r := 10;
  p := sort();
  p := output()
END.
