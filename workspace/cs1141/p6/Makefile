.SUFFIXES: .c .o
CC = gcc
EXEC = log
DATA =wtmp
CCFLAGS = -ansi -pedantic -g -Wall
OBJS = prog6.o tree.o

${EXEC}: ${OBJS}
	${CC} ${CCFLAGS} -lm -o ${EXEC} ${OBJS}

.c.o:
	${CC} ${CCFLAGS} -c $<

run: ${EXEC}
	./${EXEC} ${DATA}
    
clean:
	rm -f ${EXEC} ${OBJS}

prog6.o: prog6.c tree.c prog6.h tree.h
tree.o: tree.c tree.h prog6.h
