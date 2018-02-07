/*
 * The shell of the class, to be completed as part of the CSC115 Assignment 3 : Calculator.
 */


public class StringStack {

	private Node head;

	//constructor
	public void StringStack(){
		head=null;
	}
	//tells us if the stack is empty.
	public boolean isEmpty() {
		return head==null;
	}
	//removes and returns the top of the stack.
	public String pop() /*throws StackEmptyException*/ {
		Node curr=head;
		if(isEmpty()){
			throw new StackEmptyException();
		}else{
			head=head.next;
			return curr.item;
		}
	}
	// returns but does NOT remove the top of the stack.
	public String peek() {
		if(isEmpty()){
			throw new StackEmptyException();
		}else{
			return head.item;
		}
	}
	//checks if stack is empty, then adds to stack.
	public void push(String item) {
		Node curr= new Node(item);
		curr.next=head;
		head=curr;
	}
	//removes everything from the stack.
	public void popAll() {
		head=null;
	}
	//toString for testing.
	public String toString(){
		String a = "|";
		if(!isEmpty()){
			Node curr=head;
			while(curr.next!=null){
				a+=curr.item+"|";
				curr=curr.next;
			}
		}
		return a;
	}
}
