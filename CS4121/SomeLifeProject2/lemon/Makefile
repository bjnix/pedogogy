SRCS = lemon.c
TARGET = lemon
LEX_SRCS =
YACC_SRCS =
CC = gcc

OBJS = $(addsuffix .o,$(basename $(SRCS)))

ENV = -g

INCLUDES= -I. \
	  -I..

CFLAGS	= $(INCLUDES) -DYYERROR_VERBOSE $(ENV) 
LEX	= flex
LFLAGS = 
YACC	= lemon
YFLAGS	= 
ARFLAGS = ru

RM = /bin/rm -f

.SILENT:



.SUFFIXES: .c .y .l 

$(TARGET): $(OBJS)
	echo "Generating" $(TARGET)
	$(CC) -o $@ $(OBJS)

.PHONY: clean

clean:
	$(RM) $(TARGET) $(OBJS)

.c.o:
	echo "Compiling" $<
	$(CC) -c $(CFLAGS) $<

.y.c:
	echo "Making $@..."
	$(YACC) $(YFLAGS) $<

.l.c:
	echo  "Making $@..."
	$(LEX) -o $@ $<

#
# default rule to put all .o files in the archive and remove them
#

(%.o) : %.o
	$(AR) $(ARFLAGS) $@ $<
	$(RM) $<

#
# The following two rules make the dependence file for the C source
# files. The C files depend upon the corresponding dependence file. The
# dependence file depends upon the source file's actual dependences. This way
# both the dependence file and the source file are updated on any change.
# The depend.sed sed command file sets up the dependence file appropriately.
#

.d_%.l: %.l
	echo "$(basename $<).c: $<" > $@

.d_%.y: %.y
	echo "$(basename $<).c: $<" > $@

.d_%: %.c 
	echo  "Updating dependences for" $< "..."
	$(CPP) -MM -MT '$(ARCHIVE)($(basename $<).o)' $(INCLUDES) -MF $@ $<
	 

#
# This includes all of the dependence files. If the file does not exist,
# GNU Make will use one of the above rules to create it.
#

include $(DEPENDS)
	 
# DO NOT DELETE THIS LINE -- make depend depends on it.
