/*
 * Header File for prog6
 */
#ifndef _PROG6_H
#define _PROG6_H
#include <stdio.h>
#include <string.h>
#include <utmp.h>
#include <stdlib.h>
#include <time.h>
#include <arpa/inet.h>
#include "tree.h"

typedef struct utmp Entry;/* making my life easier*/
void insSort( Entry  e[] , int length);

#endif
