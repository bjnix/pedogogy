.SUFFIXES: .c .o
CC = gcc
EXEC = art
CCFLAGS = -ansi -pedantic -g -Wall
OBJS = art.o func.o

${EXEC}: ${OBJS}
	${CC} ${CCFLAGS} -lm -o ${EXEC} ${OBJS}

.c.o:
	${CC} ${CCFLAGS} -c $<

run: ${EXEC}
	./${EXEC}
    
clean:
	rm -f ${EXEC} ${OBJS}

func.o: func.c 
