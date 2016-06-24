/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define BUFSIZE 100
#define TRUE 1

//struct tree_node can be reference simply as Node
typedef struct tree_node Node;

//struct definition of Node
struct tree_node {
    char * elem;
    struct tree_node *leftChild;
    struct tree_node *rightChild;
};

// declared before use these function
Node *root;
void printNode(Node * node);
int compare(char * a, char *b);
void insert(char* input);
Node * insertHelp(Node* root, char* input);
void find(char* input);
void traverse();
void traverseHelp(Node *curr);
Node *createNode(void *val);
void insertPrompt();
void lookupPrompt();
int main();


/*-----------------------------------------------------------------------------
 * Function name:   print
 * Input:           pointer to a struct of type Node 
 * Output:          none
 * Result:          the string value of node is printed
 * Notes:           DO NOT EDIT THIS FUNCTION
 ----------------------------------------------------------------------------*/
void printNode(Node * node) {
    printf("%s\n", node->elem);
}

/*-----------------------------------------------------------------------------
 * Function name:   compare
 * Input:           two char pointers 
 * Output:          0  if both strings are equal
 *                  <0 the first character that does not match has a 
                        lower value in ptr1 than in ptr2
                    >0 the first character that does not match has a 
                        greater value in ptr1 than in ptr2
 * Notes:           USE THIS TO COMPARE STRINGS WHEN INSERTING/FINDING
 ----------------------------------------------------------------------------*/
int compare(char * a, char *b) {
    int i = 0;
    // find the first character that does not match
    while(*(a+i) != '\0' && *(b+i) != '\0')
    {
        if (*(a+i) != *(b+i))
            break;
        else
            i = i + 1;
    }

    // compare the first character that does not match
    if (*(a+i) < *(b+i))
        return -1;
    else if (*(a+i) > *(b+i))
        return 1;
    else
        return 0;
}

/*-----------------------------------------------------------------------------
 * Function name:   insert
 * Input:           char pointer
 * Output:          none
 * Result:          a node having input as value is inserted into it's
                    appropriate location in the tree
 ----------------------------------------------------------------------------*/
void insert(char* input) {
    if(root == NULL)
    {
        root = createNode(input);
    }
    else
    {
        insertHelp(root, input);
    }
}

/*-----------------------------------------------------------------------------
 * Function name:   insertHelp
 * Input:           pointer to a struct of type Node, char pointer 
 * Output:          pointer to a struct of type Node
 * Result:          helper function for insert
 ----------------------------------------------------------------------------*/
Node * insertHelp(Node* root, char* input){
    // compare the insert node and value of root
    int comp = compare(input, (*root).elem);

    // if the insert node is smaller than root, goes to left
    if (comp < 0)
    {
        if((*root).leftChild == NULL)
            (*root).leftChild = createNode(input);
        else
            insertHelp((*root).leftChild, input);
    }

    // if the insert node is larger than root, goes to right
    else if(comp > 0)
    {
        if((*root).rightChild == NULL)
            (*root).rightChild = createNode(input);
        else insertHelp((*root).rightChild, input);
    }
    return root;
}

/*-----------------------------------------------------------------------------
 * Function name:   find
 * Input:           char pointer
 * Output:          none
 * Result:          see write-up
 ----------------------------------------------------------------------------*/
void find(char* input) {
    // if tree is empty
    if(root == NULL)
    {
        printf("NOT found: %s", input);
        printf("\n");
        return;
    }

    // search all nodes in the tree
    Node* check = root;
    int comp = compare(input, (*root).elem);
    while(check!= NULL)
    {
        if (comp == 0)
        {
            printf("Found: ");
            printNode(check);
            return;
        }

        // if input is smaller than node, keep check left
        else if(comp < 0)
        {
            check = (*check).leftChild;
            if(check == NULL)
            {
               break; 
            }   
        }

        // if input is larger than node, keep check right
        else
        {
            check = (*check).rightChild;
            if(check == NULL)
            {
               break; 
            }
        }

        comp = compare(input, (*check).elem);
    }

    // if nothing found, return
    if(check == NULL)
    {
        printf("NOT found: %s", input);
        printf("\n");
        return;
    }
}

/*-----------------------------------------------------------------------------
 * Function name:   traverse
 * Input:           none
 * Output:          none
 * Result:          the nodes in the tree are printed in order of increasing
 *                  value
 ----------------------------------------------------------------------------*/
void traverse() {
    traverseHelp(root);
}

/*-----------------------------------------------------------------------------
 * Function name:   traverseHelp
 * Input:           pointer to a struct of type Node
 * Output:          none
 * Result:          helper function for traverse
 ----------------------------------------------------------------------------*/
void traverseHelp(Node *curr){
    // traverse until nothing left
    if(curr == NULL)
        return;
    // traverse left leaf
    traverseHelp((*curr).leftChild);
    printf("%s", (*curr).elem);
    printf("\n");
    // traverse right leaf
    traverseHelp((*curr).rightChild);
}

/*-----------------------------------------------------------------------------
 * Function name:   createNode
 * Input:           pointer to a value
 * Output:          pointer to a node
 * Result:          create a node
 ----------------------------------------------------------------------------*/
Node *createNode(void *val)
{
    Node *node;
    node = (Node *) malloc(sizeof(Node));
    node -> elem = val;
    node -> rightChild = (Node *) NULL;
    node -> leftChild = (Node *) NULL;
    return node;
}

//DO NOT EDIT
void insertPrompt() {
    char * input = malloc(sizeof(char)*(BUFSIZE+1));
    printf("Enter a string to insert (max length 100): ");
    scanf("%s", input);
    insert(input);
}

//DO NOT EDIT
void lookupPrompt() {
    char * input = malloc(sizeof(char)*(BUFSIZE+1));
    printf("Enter a string to look up: ");
    scanf("%s", input);
    find(input);
}

//MUST MATCH STARTER CODE ON TURNIN 
int main() {

    char input[BUFSIZE];

    printf("Binary Search Tree!\n");

    while (!feof(stdin)) {

        printf("Select an Operation: (i)nsert, (l)ookup, (p)rint in order, (q)uit: ");
        scanf("%s", input);

        if (feof(stdin)) {
            break;
        }
        if (input[0]=='i') {
            insertPrompt();
        }
        else if (input[0]=='l') {   
            lookupPrompt();
        }
        else if (input[0]=='p') {
            traverse();
        }
        else if (input[0] == 'q') {
            break;
        }
        else {
            printf("Invalid option selected, try again\n"); 
        }
    }

    return 0;
}
