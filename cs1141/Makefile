.SUFFIXES: .c .o
CC = gcc
EXEC = art
CCFLAGS = -ansi -pedantic -g -Wall
OBJS = func.c func.h

${EXEC}: ${OBJS}
	${CC} ${CCFLAGS} -o ${EXEC} ${OBJS}

.c.o:
	${CC} ${CCFLAGS} -c $<

run: ${EXEC}
	./${EXEC}
    
clean:
	rm -f ${EXEC} ${OBJS}

func.o: func.c func.h