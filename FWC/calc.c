/*
 * SimpleCalc
 *
 * This program gives a further example of terminal i/o, and
 * demonstrates some of the C looping structures. It works by
 * implementing a very simple calculator.
 *
 * to compile:
 *  gcc -ansi -pedantic -lm simplecalc.c
 * (The -lm flag specifies that the linker needs the math library)
 *
 *
 * to run:
 *  ./a.out
 *
 * Written by Paul Bonamy - 16 Sept 2010
 * Updated to include pow() function - 26 Jan 2011
 */

#include <stdio.h> /* scanf and printf live here */

/*
 * We'll see more about the string library later. For the moment,
 * we really only care about the isspace() function.
 */
#include <string.h>

/*
 * Many things live in math.h. For the moment, we're interested
 * in the pow() function, which computes x^y
 */
#include <math.h>

int main() {
    float total = 1.0;  /* stores running total of calculated value */
    float value;        /* value received from user */
    char op;            /* operator telling us what to do */
    
    /* a little bit of instruction for the user */
    printf("Running total is now %f.\n", total);
    printf("Enter an operation followed by a value.\n");
    printf("Valid operations are +, -, ^, p to print total, q to quit\n");
    
    /*
     * Normally, scanf will eat whitespace. The exception to this
     * is in the case of reading single characters, where it will give
     * you back whatever the next character is, whitespace or no.
     *
     * the initial scanf populates the op variable. We then use
     * isspace() - which returns true if the character is a whitespace,
     * and false otherwise - in a while loop to keep reading until we
     * come up with something that isn't whitespace
     */
    scanf("%c", &op);
    while(isspace(op))
        { scanf("%c", &op); }
    
    /* continue looping until the user says quit */
    while( op != 'q') {
        /*
         * we don't read in the value here to avoid trouble
         * with operations that don't need a value, like print
         */
        
        switch (op) {
            /*
             * Switch takes in an integer expression to evaluate
             * and matches it to (not more than) one of its cases.
             * Here, we use it to figure out what we should be
             * doing and do it.
             */
            case '+': /* Addition - get a value and add it to total */
                scanf("%f", &value);
                total += value;
                break;
            
            case '-': /* Subtraction - get a value and sub. from total */
                scanf("%f", &value);
                total -= value;
                break;
            
            case '^': /* Compute total ^ value */
                scanf("%f", &value);
                
                /*
                 * pow(x, y) computes x^y. Note that, technically,
                 * it expects and returns doubles, but there's no
                 * problem using it with normal floats instead.
                 *
                 * The float-specific form (powf) may or may not work
                 * with your compiler of choice in ANSI C mode, so it's
                 * a hair safer to just use pow()
                 */
                total = pow(total, value);
                break;
            
            case 'p': /* print current total */
                printf("Current total is: %f\n", total);
                break;
            
            default:
                /*
                 * if we didn't eat whitespace, we'd see this
                 * getting triggered for newlines and spaces, too
                 */
                printf("I don't understand operator '%c'\n", op);
        }
        
        /*
         * This loop does the same thing as the one up top:
         * it eats whitespace for us. In this case, we'll take
         * advantage of do loops always running at least once
         * to aleaviate the need for the initial call to scanf
         */
        do
            { scanf("%c", &op); }
        while(isspace(op));
    
    }
    
    return 0;
}