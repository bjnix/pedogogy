	
	#include "prog6.h"
	#include "tree.h"
	

        /* MakeEmpty:
	 * Returns an empty tree
	 */
	SearchTree MakeEmpty ( SearchTree T )
        {
		if( T )
		{
                	MakeEmpty( T->Left );
                	MakeEmpty( T->Right );
                	EmptyList( T->A );
			free( T );
            	}
            	return 0;
        }/*end MakeEmpty*/
	nodeAddr EmptyList ( nodeAddr X )
	{
		if( X )
		{	
			EmptyList( X->next );
			free( X );
		}
		return 0;
	}

        /* Find:
	 * Searches for a specific node and
	 * returns it
	 */
	Position Find( char * X, SearchTree T )
        {
            	nodeAddr Y;
/*		fprintf(stderr,"searching...\n");
*/
		if( !T)
		{
                	fprintf(stderr,"Could not find user\n");
			return 0;
		}
            	else if( strncmp(X, T->name, 32) < 0 )
		{
/*			fprintf(stderr,"going left\n");
*/                	return Find( X, T->Left );
		}
            	else if( strncmp(X, T->name, 32) > 0 )
                {	
/*			fprintf(stderr,"going right");
*/			return Find( X, T->Right );
		}
            	else if( strncmp(X,T->name,32) == 0)
	    	{
	    		Y = T->A;
/*			fprintf(stderr,"-found-\n");
*/			printf("User Name: %s\n",T->name);
			do{
				printf("IP Address: %s\n",Y->addr);
            			printf("# Of Logins: %d\n----------------\n",Y->nLogins);
				Y = Y->next;
			}while(Y);
	    		return T;
        	}
		else{
			fprintf(stderr,"ERROR SOMETHING WENT WRONG IN FIND!!!\n");
			return 0;
		}

	}/*end Find*/

	/* FindMin:
	 * Searches for node with lowest
	 * key value and returns it
	 */
        Position FindMin( SearchTree T )
        {
            if( !T )
                return 0;
            else
            if(!T->Left )
                return T;
            else
                return FindMin( T->Left );
        }/*end FindMin*/

	/* FindMax:
	 * Searches for node with greatest
	 * key value and returns it
	 */
        Position FindMax( SearchTree T )
        {
            if( T )
                while( T->Right )
                    T = T->Right;

            return T;
        }/*end FindMax*/
	
	/* Insert:
	 * Takes username( %s ) and an address ( %s )
	 * and inserts them into the given SearchTree
	 */
        SearchTree Insert( char* usr, char* a, SearchTree T )
        {
		int found = 0;
		
		nodeAddr X;
		nodeAddr Y;
		
		
/**		fprintf(stderr,"inserting... usr: %s, address: %s\n",usr,a);
**/		if( !T )
		{
			/* Create and return a one-node tree */
		  T = malloc( sizeof(* T));
		  T->A = malloc( sizeof(* T->A)); 
			if((!T)||(!T->A))
				{ memError(T); }
			else
			{	

/*				fprintf(stderr,"creating new treenode\n");
*/				strcpy(T->A->addr, a);
				T->A->nLogins = 1;
				T->A->next = 0;
				strcpy(T->name, usr);
				T->Left = T->Right = 0;
/*				fprintf(stderr,"inserted\n");
*/			}
		}
		else if(strcmp(usr, T->name) < 0 )
		{
/*			fprintf(stderr,"going left\n");
*/			T->Left = Insert( usr, a, T->Left );/*its not here, take a left*/
		}
		else if(strcmp(usr, T->name) > 0 )
		{
/*			fprintf(stderr,"going right\n");
*/			T->Right = Insert( usr, a, T->Right );/*nope not here either, take a right*/
		}		
		else if(strcmp(usr,T->name) == 0)/*found it!  Update count for logins or create new address node*/
		{	
/*			fprintf(stderr,"found matching treenode\n");
*/			X = T->A;
			do{
				if( strncmp(a, X->addr,32) == 0)
				{
					found = 1;
					 X->nLogins ++;
/*					fprintf(stderr,"found matching address %s, %d logins\n", X->addr, X->nLogins);
*/					}
				X =  X->next;
			}while((X)&&(!found));
			if(!found)
			{
				X=T->A;
/*				 fprintf(stderr,"no matching address found, creating new %a ...");
*/				if(!(Y=malloc( sizeof(struct address))))
					{ memError(T); }
				else
				{
				printf("%p\n",X->addr);
					strcpy(Y->addr, a );
					Y->nLogins = 1;
					Y->next = X->next;
					 X->next = Y;
/*					fprintf(stderr,"created and inserted\n");
*/				}
			}
		}
		else
		{
			fprintf(stderr,"ERROR INSERT FAIL");
			return 0;
		}
		return T;  
        }/*end Insert*/
       #define USE  0
       #if USE 
	SearchTree Delete( char * usr, SearchTree T )
        {
            Position TmpCell;

            if( T == 0 )
                Error( "Element null found" );
            else
            if( strncmp(usr, T->name,32 ) < 0 )  /* Go left */
                T->Left = Delete( X, T->Left );
            else
            if( strncmp(usr,T->name) > 0 )  /* Go right */
                T->Right = Delete( X, T->Right );
            else  /* Found element to be deleted */
            if( T->Left && T->Right )  /* Two children */
            {
                /* Replace with smallest in right subtree */
                TmpCell = FindMin( T->Right );
                strncpy(TmpCell->name ,T->name, 32);
                strncpy(TmpCell->A->addr,T->A->addr,32);

		T->Right = Delete( T->name, T->Right );
            }
            else  /* One or zero children */
            {
                TmpCell = T;
                if( T->Left == 0 ) /* Also handles 0 children */
                    T = T->Right;
                else if( T->Right == 0 )
                    T = T->Left;
                free( TmpCell );
            }

            return T;
        }/*end Delete*/
	#endif
        /* memError:
	 * Function that safely exits
	 * program
	 */
	void memError(SearchTree head)
	{
		fprintf(stderr,"I can't allocate that ... much... mem-ahhhh...");
		MakeEmpty(head);
		exit(1);
	}/*end memError*/





