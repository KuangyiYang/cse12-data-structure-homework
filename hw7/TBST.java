/* 
 * NAME: Kuangyi Yang
 * ID: A53083212
 * LOGIN: cs12fig
 */
package hw7;



public class TBST<T extends Comparable <? super T>> {
	
	private int numElem;
	private Node root;
	
	//Constructor to create an empty tree
	public TBST(){
		root = null; 
		numElem = 0;
	}
	
	//Inserts a new node in BST
	public void insert(T val)
	{
		if( root == null)
		{
			root = new Node(val);
		}
		else
			inserthelp(root, val);
		numElem = numElem + 1;
	}
	
	public Node inserthelp(Node rt, T val)
	{
		Node newNode = new Node(val);
		if (val.compareTo(rt.getElement()) < 0)
		{
			if (rt.getLeftThread() == true)
			{
				newNode.setLeftChild(rt.getLeftChild());
				newNode.setRightChild(rt);
				rt.setLeftChild(newNode);
				rt.setLeftThread(false);
				return newNode;
			}
			else
			{
				return inserthelp(rt.getLeftChild(), val);
			}
		}
		else
		{
			if(rt.getRightThread() == true)
			{
				newNode.setRightChild(rt.getRightChild());
				newNode.setLeftChild(rt);
				rt.setRightChild(newNode);
				rt.setRightThread(false);
				return newNode;
			}
			else
			{
				return inserthelp(rt.getRightChild(), val);
			}
		}
	}

	
	public void printInOrder()
	{
		Node p = root;
		while (p.getLeftChild() != null)
		{
			p = p.getLeftChild();
		}
		while (p != null)
		{
			System.out.print(p.getElement() + " ");
			if(p.getRightThread() == false)
			{
				p = p.getRightChild();
				while(p.getLeftThread() == false)
				{
					p = p.getLeftChild();
				}
			}
			else
			{
				p = p.getRightChild();
			}
		}
		
	}
	
	//A class that implements nodes to be used 
	//in BST
	protected class Node{
		T data;
		Node rChild;
		Node lChild;
		boolean lThread;
		boolean rThread;


		//Constructor to create singleton Node
		public Node(T element){
		 data = element;
		 rChild = null;
		 lChild = null;
		 lThread = true;
		 rThread = true;
		}


		//Constructor to create singleton Node
		//that points to a given children
		public Node(T element, Node right, Node left, boolean lT, boolean rT){
			data = element;
			lChild = left;
			rChild = right;	
			lThread = lT;
			rThread = rT;
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
		
		public boolean getLeftThread(){
			return lThread;
		}
		
		public boolean getRightThread(){
			return rThread;
		}
		
		public void setLeftThread(boolean n){
			lThread = n;
		}
		
		public void setRightThread(boolean n){
			rThread = n;
		}
		
	}  //end of class node.
	
}


