/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
package hw7;


public class BST<T extends Comparable <? super T>> {
	
	private int numElem;
	private Node root;
	
	//Constructor to create an empty tree
	public BST(){
		root = null; 
		numElem = 0;
	}
	
	//Inserts a new node in BST
	public void insert(T val)
	{
		if (root == null)
		{
			root = new Node(val);
		}
		else
			inserthelp(root, val);
		numElem = numElem + 1;
	}

	//helper method. Must be recursive
	private Node inserthelp(Node rt, T val)
	{	
		int compare = val.compareTo(rt.getElement());
		if( compare < 0)
		{
			if (rt.getLeftChild() == null )
				rt.setLeftChild(new Node(val));
			else
				inserthelp(rt.getLeftChild(), val);
		}
		else
		{
			if (rt.getRightChild() == null)
				rt.setRightChild(new Node(val));
			else
				inserthelp(rt.getRightChild(), val);
		}	
		return rt;
	}

    //traverses BST in order
	public void printInOrder()
	{
		printInOrderHelp(root);
		
	}
	
	//helper method for printInOrder
	public void printInOrderHelp(Node curr) {
		if (curr == null)
			return;
		
		printInOrderHelp(curr.getLeftChild());
		System.out.print(curr.getElement() + " ");
		printInOrderHelp(curr.getRightChild());

	}
	
	
	//A class that implements nodes to be used 
	//in BST
	protected class Node{
		T data;
		Node rChild;
		Node lChild;
		

		//Constructor to create singleton Node
		public Node(T element){
		 data = element;
		 rChild = null;
		 lChild = null;
		}


		//Constructor to create singleton Node
		//that points to a given children
		public Node(T element, Node right, Node left){
			data = element;
			lChild = left;
			rChild = right;	
		}
		
		//Returns the data of a node
		public T getElement(){
			return data;
		}
		
		//Sets the data of a node
		public void setElement(T element){
			data = element;
		}
		
		//returns the left child pointer
		public Node getLeftChild(){
			return lChild;
		}
		
		//returns the right child pointer
		public Node getRightChild(){
			return rChild;
		}
		
		//sets the left child pointer
		public void setLeftChild(Node left){
			lChild = left;
		}
		
		//sets the right child pointer
		public void setRightChild(Node right){
			rChild = right;
		}
		
	}  //end of class node.
	
}

