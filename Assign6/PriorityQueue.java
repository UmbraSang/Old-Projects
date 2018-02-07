import java.util.NoSuchElementException;

/*
 Connor McLeod
 V00725080
 CSC115
 Assignment #6
 */

 //holds a list of patients at a hospital sorted by their urgency
public class PriorityQueue<E extends Comparable<E>> {
	
	private Heap<E> priQ;

	public PriorityQueue() {
		priQ = new Heap<E>();
	}

	//adds to the queue using the data type's method.
	public void enqueue(E item){
		priQ.insert(item);
	}
	
	//removes from the queue using the data type's method.
	public E dequeue(){
		return priQ.removeRootItem();
	}
	
	//returns but does not remove the first spot in the queue using the data type's method.
	public E peek(){
		return priQ.getRootItem();
	}
	
	//checks if the queue is empty
	public boolean isEmpty(){
		return priQ.isEmpty();
	}
	
	//returns a string representation of the queue
	public String toString() {
	 	String a = priQ.toString();
		return "["+a.substring(7);
		
 	}
	
	//tester
	public static void main(String[] args){
		PriorityQueue<ER_Patient> test= new PriorityQueue<ER_Patient>();
		System.out.println(test.isEmpty());
		test.enqueue(new ER_Patient("17:12:00", 3, "Life-threatening"));
		test.enqueue(new ER_Patient("13:12:00", 1, "Chronic"));
		test.enqueue(new ER_Patient("16:12:00", 2, "Major fracture"));
		test.enqueue(new ER_Patient("18:12:00", 4, "Life-threatening"));
		test.enqueue(new ER_Patient("15:12:00", 5, "Major fracture"));
		test.enqueue(new ER_Patient("17:17:00", 6, "Chronic"));
		test.enqueue(new ER_Patient("17:19:00", 7, "Life-threatening"));
		System.out.println(test);
	}
			
}
	
