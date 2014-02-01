.SUFFIXES: .c .o
CC = gcc
EXEC = log
DATA = /var/log/wtmp
CCFLAGS = -ansi -pedantic -g -Wall
OBJS = prog5.o

${EXEC}: ${OBJS}
	${CC} ${CCFLAGS} -lm -o ${EXEC} ${OBJS}

.c.o:
	${CC} ${CCFLAGS} -c $<

run: ${EXEC}
	./${EXEC} ${DATA}
    
clean:
	rm -f ${EXEC} ${OBJS}

prog4.o: prog5.c
