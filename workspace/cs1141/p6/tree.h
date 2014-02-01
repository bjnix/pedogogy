/*
 * header file for tree
 */
        #ifndef Tree_H
        #define Tree_H
	struct address;
	struct TreeNode;

	typedef struct address *nodeAddr;
        typedef struct TreeNode *Position;
        typedef struct TreeNode *SearchTree;
	
	struct address
	{
	   	char addr[20];                  /*address*/
		int nLogins;                    /*number of logins*/
		nodeAddr next;
	};/*end address*/
	
	struct TreeNode
	{
		char name[32];
		nodeAddr A;                     /*address struct*/
		SearchTree  Left;               /*pointer to left node*/
		SearchTree  Right;              /*pointer to right node*/
	};/*end TreeNode*/
	
	
        SearchTree MakeEmpty( SearchTree T );
	nodeAddr EmptyList( nodeAddr X );
	Position Find(char * usr, SearchTree T );
        Position FindMin( SearchTree T );
        Position FindMax( SearchTree T );
        SearchTree Insert( char * usr, char * a, SearchTree T );
        SearchTree Delete( char * usr,  SearchTree T );
	void memError(SearchTree head);

        #endif

/* END */
