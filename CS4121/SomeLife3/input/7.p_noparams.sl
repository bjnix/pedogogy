PROGRAM subprog;
 VAR i,j,k,l:INTEGER;


FUNCTION a1 : INTEGER;
BEGIN
	WRITE(1);
	RETURN 0
END;

FUNCTION a2 : INTEGER;
BEGIN
	j := a1(); 
	WRITE(2);
	RETURN 0
END;

FUNCTION a3 : INTEGER;
BEGIN
	i := a1();
	j := a2();
	WRITE(3);
	RETURN 0
END;

FUNCTION a4 : INTEGER;
BEGIN
	i := a1();
	j := a2();
	k := a3();
	WRITE(4);
	RETURN 0
END;


BEGIN


	i := a1(); j := a2(); k := a3(); l := a4() 



END.
