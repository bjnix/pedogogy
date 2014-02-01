PROGRAM subprog;
 VAR a,b,c,d:INTEGER;


FUNCTION b4  : INTEGER;
BEGIN
	WRITE(4);
	WRITE(a+1);
	RETURN a+1
END;

FUNCTION d2 : INTEGER;
VAR tmp:INTEGER;
BEGIN
	WRITE(b);
        WRITE(a);
	tmp:=a;
	a:=b;
	b:=tmp;

	RETURN 1
END;

FUNCTION d3:INTEGER;
BEGIN
	a:=a+1;
	b:=b+2;
	c:=c+3;
	d:=d;

	RETURN c
END;

FUNCTION d1:INTEGER;
VAR b:INTEGER;

BEGIN
	b:=a*200;
	WRITE(a); WRITE(b); WRITE(c); WRITE(d);
	WRITE(d2());
	WRITE(a); WRITE(b); WRITE(c); WRITE(d);
	WRITE(d3());
	WRITE(a); WRITE(b); WRITE(c); WRITE(d);

	RETURN b
END;

BEGIN
	a:=1; b:=2; c:=3; d:=4;


	a := 2;
	WRITE(b4());
	WRITE(d1());
	a := 1;
	WRITE(b4())


END.
