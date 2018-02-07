/**
 * CSC115 Assignment 3 : Calculator.
 * StackEmptyException.java.
 * Created for use by CSC115 Spring 2016
 */

public class Node{
	
	public String item = null;
	public Node next= null;
	
	//constructor
	public Node(String item){
		this.item = item;
	}
	//constructor
	public Node(String item, Node next){
		this.item = item;
		this.next = next;
	}
	
	
}