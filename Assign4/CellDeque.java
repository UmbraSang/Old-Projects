
public class CellDeque{
	
	CellNode first=null;
	CellNode last=null;
	
	public CellDeque(){
		
	}
	/*
	inserts a new Cell into
	the First spot in the deque.
	*/
	public void insertFirst(Cell p){
		if(isEmpty()){
			first = new CellNode(p);
			last = first;
		}else{
			first.prev = new CellNode(p,null,first);
			first = first.prev;
		}
	}
	/*
	inserts a new Cell into
	the Last spot in the deque.
	*/
	public void insertLast(Cell p){
		if(isEmpty()){
			last = new CellNode(p);
			first = last;
		}else{
			last.next = new CellNode(p,last,null);
			last = last.next;
		}
	}
	/*
	removes the first Cell from the deque.
	*/
	public Cell removeFirst(){
		if(isEmpty()){
			throw new DequeEmptyException();
		}else if(first.next==null){
			CellNode temp = first;
			first=null;
			return temp.item;
		}else{
			CellNode temp = first;
			first=first.next;
			first.prev=null;
			return temp.item;
		}
	}
	/*
	removes the last Cell from the deque.
	*/
	public Cell removeLast(){
		if(isEmpty()){
			throw new DequeEmptyException();
		}else{
			CellNode temp = last;
			last=last.prev;
			last.next=null;
			return temp.item;
		}
	}
	/*
	returns the First Cell in the deque.
	*/
	public Cell first(){
		return first.item;
	}
	/*
	returns Last Cell in the deque.
	*/
	public Cell last(){
		return last.item;
	}
	/*
	Tells us if the deque is empty.
	*/
	public boolean isEmpty(){
		return (first==null);
	}
	/*
	Empties the deque.
	*/
	public void makeEmpty(){
		first=null;
		last=null;
	}
	/*
	returns a string that
	represents the cells in
	the deque.
	*/
	public String toString(){
		CellNode curr = first;
		String sample ="";
		do{
			sample += curr.item+" ";
			curr=curr.next;
		}while(curr!=null);
		return sample;
	}
	/*
	tester
	*/
	public static void main(java.lang.String[] args){
		CellDeque test = new CellDeque();
		test.insertFirst(new Cell(1,5));
		test.insertLast(new Cell(2,6));
		test.insertFirst(new Cell(3,7));
		System.out.println(test);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
