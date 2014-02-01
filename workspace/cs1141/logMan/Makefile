.SUFFIXES: .c .o
CC = gcc
EXEC = log
CCFLAGS = -ansi -pedantic -g -Wall
OBJS = prog4.o

${EXEC}: ${OBJS}
	${CC} ${CCFLAGS} -lm -o ${EXEC} ${OBJS}

.c.o:
	${CC} ${CCFLAGS} -c $<

run: ${EXEC}
	./${EXEC}
    
clean:
	rm -f ${EXEC} ${OBJS}

prog4.o: prog4.c
