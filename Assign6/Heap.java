import java.util.NoSuchElementException;
import java.util.Vector;


/*
 Connor McLeod
 V00725080
 CSC115
 Assignment #5
 */
 
//holds a min heap of objects.
public class Heap<E extends Comparable<E>> {

	private Vector<E> heapArray;;

	public Heap() {
		this.heapArray = new Vector<E>(10,5);
	}

	//checks if vector is empty
	public boolean isEmpty(){
		return (heapArray.isEmpty());
	}
	//checks number of objects in vector
	public int size(){
		return heapArray.size();
	}
	//places null as fist spot in vector,
	//then appends and bubbles the new item into position
	public void insert(E item){
		if(isEmpty()==true){
			heapArray.add(null);
			heapArray.add(item);
		}else{
			heapArray.add(item);
			bubbleup(size()-1);
		}
	}
	//bubbles from a passed index to the correct spot for a min heap
	private void bubbleup(int i){
		if(i<=1){
			return;
		}else if(heapArray.get(i/2).compareTo(heapArray.get(i))<0){
			return;
		}else{
			E temp = heapArray.get(i/2);
			heapArray.set(i/2,heapArray.get(i));
			heapArray.set(i,temp);
			bubbleup(i/2);
		}
	}
	//removes the root of the heap. AKA spot 1 of the vector.
	//places the last spot in it's place, and bubbles down to the correct index.
	public E removeRootItem(){
		int size = size()-1;
		E temp = heapArray.get(1);
		heapArray.set(1,heapArray.remove(size));
		bubbledown(1);
		return temp;
	}
	//bubbles from a passed index to the correct spot for a min heap
	private void bubbledown(int i){
		if(2*i>heapArray.size()-1){
			return;
		}else{
			if(heapArray.get(i).compareTo(heapArray.get(i*2))>0){
				E temp = heapArray.get(i);
				heapArray.set(i,heapArray.get(i*2));
				heapArray.set(i*2,temp);
				bubbledown(i*2);
			}
			if(((2*i)+1)<size()){
				if(heapArray.get(i).compareTo(heapArray.get((i*2)+1))>0){
					E temp = heapArray.get(i);
					heapArray.set(i,heapArray.get((i*2)+1));
					heapArray.set((i*2)+1,temp);
					bubbledown((i*2)+1);
				}
			}
		}
	}
	//returns but does not remove the item in vector spot 1
	public E getRootItem(){
		return heapArray.get(1);
	}
	//returns a string representation of the vector
 	public String toString() {
	 	return heapArray.toString();	
 	}
	//tester
	public static void main(String[] args) {
		Heap<Integer> test = new Heap<Integer>();
		System.out.println(test.size());
		test.insert(11);
		test.insert(5);
		test.insert(8);
		test.insert(3);
		test.insert(4);
		test.insert(15);
		System.out.println(test);
		
		Heap<Integer> testHeap = new Heap<Integer>();
		testHeap.insert(4);
		testHeap.insert(6);
		testHeap.insert(2);
		testHeap.insert(8);
		testHeap.insert(5);
		testHeap.insert(9);
		testHeap.insert(1);
		System.out.println(testHeap);
		testHeap.removeRootItem();
		testHeap.removeRootItem();
		System.out.println(testHeap);
	}
}