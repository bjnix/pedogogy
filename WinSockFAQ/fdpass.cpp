// Borland C++ 5.0: bcc32 fdpass.cpp
// Visual C++ 5.0:  cl fdpass.cpp
//
// This sample program is hereby placed in the public domain.

#include <stdlib.h>
#include <stdio.h>
#include <io.h>
#include <fcntl.h>
#include <process.h>
#include <math.h>

enum PIPES {
    READ = 0,
    WRITE = 1
};

#define NUMPROBLEM 8

#ifdef _MSC_VER
    #define CWAIT _cwait
#else
    #define CWAIT cwait
#endif

int main(int argc, char *argv[])
{
    int hpipe[2];
    char hstr[20];
    int pid, problem, c;
    int termstat;

    if (argc == 1) {
        //// No arguments, so this must be the parent process
        // Open a set of pipes
        if (_pipe(hpipe, 256, O_BINARY) == -1) {
            perror("pipe failed");
            exit(1);
        }

        // Convert read side of pipe to string and pass as an argument 
        // to the child process.
        itoa(hpipe[READ], hstr, 10);
        if ((pid = spawnl(P_NOWAIT, argv[0], argv[0], hstr, NULL)) == -1) {
            perror("Spawn failed");
        }

        // Put problem in write pipe; it will appear in child's read pipe.
        for (problem = 1000; problem <= NUMPROBLEM * 1000; problem += 1000) {
            if (write(hpipe[WRITE], (char *) &problem, sizeof(int)) == -1) {
                perror("parent write failed");
            }
            else {
                printf("Son, what is the square root of %d?\n", problem);
            }
        }

        // Wait until spawned program is done processing.
        CWAIT(&termstat, pid, WAIT_CHILD);
        if (termstat & 0x0) {
            perror("Child failed");
        }

        close(hpipe[READ]);
        close(hpipe[WRITE]);
    }
    else {
        //// There is a command line argument, so we must be a child process
        // Convert argument to integer handle value.
        hpipe[READ] = atoi(argv[1]);

        // Read problem from pipe and calculate solution.
        for (c = 0; c < NUMPROBLEM; c++) {
            if (read(hpipe[READ], (char *) &problem, sizeof(int)) == -1) {
                perror("child read failed");
            }
            else {
                printf("Dad, the square root of %d is %3.2f.\n", problem,
                       sqrt((double) problem));
            }
        }
    }

    return 0;
}
