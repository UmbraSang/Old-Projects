import java.util.Iterator;

/*
 Connor McLeod
 V00725080
 CSC115
 Assignment #5
 */

/**
 * BinarySearchTree is an ordered binary tree, where the element in each node
 * comes after all the elements in the left subtree rooted at that node
 * and before all the elements in the right subtree rooted at that node.
 * For this assignment, we can assume that there are no elements that are
 * identical in this tree. 
 * A small modification will allow duplicates.
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Creates a BinarySearchTree with a single item.
	 * @param item The single item in the tree.
	 */
	public BinarySearchTree(E item) {
		super(item);
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		throw new UnsupportedOperationException();
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachRightSubtree(BinaryTree<E> right) {
		throw new UnsupportedOperationException();
	}

	/**
	Using Encapsulation,
	the public method calls the private.
	*/
	public void insert(E item) {
		if(isEmpty()){
			root = new TreeNode<E>(item);
		}else{
			insert(root, item);
		}
	}
	
	/**
	Adds to the tree while maintaining it's sorted status.
	*/
	private void insert(TreeNode<E> curr, E item){
		TreeNode<E> temp = new TreeNode<E>(item);
		int dir = curr.item.compareTo(temp.item);
		if(item==curr.item){
			return;
		}else if(dir<0&&curr.right!=null){
			insert(curr.right, item);
		}else if(dir>0&&curr.left!=null){
			insert(curr.left, item);
		}else if(dir<0){
			curr.right=new TreeNode<E>(item);
			curr.right.parent=curr;
		}else if(dir>0){
			curr.left = new TreeNode<E>(item);
			curr.left.parent=curr;
		}
	}
	
	/**
	Using Encapsulation,
	the public method calls the private.
	*/
	public E retrieve(E item) {
		E temp = retrieve(root, item);
		return temp;
	}
	
	/**
	Searches the tree recursively for the Item in question,
	and returns it if it is found in the tree. Otherwise,
	returns null.
	*/
	private E retrieve(TreeNode<E> curr, E item){
		TreeNode<E> temp = new TreeNode<E>(item);
		int dir = curr.item.compareTo(temp.item);
		if(dir==0){
			return item;
		}else if(dir<0&&curr.right!=null){
			return retrieve(curr.right, item);
		}else if(dir>0&&curr.left!=null){
			return retrieve(curr.left, item);
		}else{
			return null;
		}
	}
	
	/**
	Using Encapsulation,
	the public method calls the private.
	*/
	public E delete(E item) {
		E temp = delete(root, item);
		return null;
	}
	
	/**
	Removes a node from the tree and
	replaces it with it's closest predecessor.
	*/
	private E delete(TreeNode<E> curr, E item){
		if(item==curr.item){
			//deleting root
			if(curr==root){
				if(curr.right!=null){
					while(curr.right!=null){
						curr.item=curr.right.item;
						curr=curr.right;
					}
					curr.parent.right=null;
				}else if(curr.left!=null){
					while(curr.left!=null){
						curr.item=curr.left.item;
						curr=curr.left;
					}
					curr.parent.left=null;
				}
			//deleting leaf
			}else if(curr.left==null&&curr.right==null){
				if(curr.equals(curr.parent.left)){
					curr.parent.left=null;
				}else{
					curr.parent.right=null;
				}
			//deleting left node
			}else if(curr.equals(curr.parent.left)){
				if(curr.right==null){
					curr.left.parent = curr.parent;
					curr.parent.left = curr.left;
				}else{
					while(curr.right!=null){
						curr.item=curr.right.item;
						curr=curr.right;
					}
					curr.parent.right=null;
				}
			//deleting right node
			}else{
				if(curr.left==null){
					curr.right.parent = curr.parent;
					curr.parent.right = curr.right;
				}else{
					while(curr.left!=null){
						curr.item=curr.left.item;
						curr=curr.left;
					}
					curr.parent.left=null;
				}
			}
		}
		TreeNode<E> temp = new TreeNode<E>(item);
		int dir = curr.item.compareTo(temp.item);
		if(dir<0&&curr.right!=null){
			delete(curr.right, item);
		}else if(dir>0&&curr.left!=null){
			delete(curr.left, item);
		}else{
			return null;
		}
		return null;
	}

	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		// NOTE TO STUDENT: something to get yo`u started.
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		String s1 = new String("optimal");
		String s2 = new String("needs");
		String s3 = new String("programmers");
		String s4 = new String("CSC115");
		tree.insert(s1);
		tree.insert(s2);
		tree.insert(s3);
		tree.insert(s4);
		String test = tree.retrieve("needs");
		if (test != null && !test.equals("")) {
			System.out.println("retrieving the node that contains "+s2);
			if (test.equals(s2)) {
				System.out.println("Confirmed");
			} else {
				System.out.println("retrieve returns the wrong item");
			}
		} else {
			System.out.println("retrieve returns nothing when it should not");
		}
		Iterator<String> it = tree.inorderIterator();
		System.out.println("printing out the contents of the tree in sorted order:");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		System.out.println();
		DrawableBTree<String> dbt = new DrawableBTree<String>(tree);
		dbt.showFrame();
		
		tree.delete(s4);
		it = tree.inorderIterator();
		System.out.println("printing out the new contents of the tree in sorted order:");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		} 
		System.out.println();
		DrawableBTree<String> dbt2 = new DrawableBTree<String>(tree);
		dbt2.showFrame();
	}
}

	

	
