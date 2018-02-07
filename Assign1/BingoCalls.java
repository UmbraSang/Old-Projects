/**
 * Template created for CSC115 Assignment 1: Bingo
 * Connor McLeod
 * V00725080
 */

public class BingoCalls{
	
	private static BingoBall[] calls = new BingoBall[5];
	private int numCalled = 0;
	
	public BingoCalls(){
		//creates an array to hold bingo ball objects.
	}
	
	public void insert(BingoBall ball){
		//calls the checker, doubles the size of the array if necessary.
		boolean test = wasCalled(ball);
		BingoBall[] temp;
		if (test == false){
			if(calls.length==numCalled){
				temp = new BingoBall[calls.length];
				for(int i = 0; i<calls.length; i++){
					temp[i] = calls[i];
				}
				this.calls = new BingoBall[temp.length*2];
				for(int j = 0; j<temp.length; j++){
					calls[j] = temp[j];
				}
			}
			calls[numCalled]=ball;
			numCalled +=1;
		}
		
	}
	
	public int numBallsCalled(){
		//returns the number of balls in the array.
		return this.numCalled;
	}
	
	public void remove(BingoBall ball){
		//finds the ball in the array and changes it to null.
		for(int i=0; i<calls.length; i++){
			if(calls[i]==ball){
				calls[i] = null;
			}
		}			
		numCalled -=1;
	}
	
	public boolean wasCalled(BingoBall ball){
		//checks the array to see if the ball already exists in it.
		for(int i=0; i<calls.length; i++){
			if(calls[i] != null){
				if(calls[i].equals(ball)==true){
					return true;
				}
			}			
		}
		return false;
	}
	
	public void makeEmpty(){
		//changes the entire string to null.
		for(int i=0; i<calls.length; i++){
			calls[i]=null;
		}
	}
	
	public java.lang.String toString(){
		//puts the entire array into a single string and returns it.
		int i=0;
		String list = new String("{");
		if(calls[i] != null){
			list+=calls[i].toString();
		}
		for(i=1; i<calls.length; i++){
			if(calls[i] != null){
				list+=", "+calls[i].toString();
			}
		}
		list+="}";
		return list;
	}
	
	public static void main(java.lang.String[] args){
		//tests the class by trying the methods.
		BingoCalls test = new BingoCalls();
		BingoBall a = new BingoBall(42);
		BingoBall b = new BingoBall(20);
		BingoBall c = new BingoBall(15);
		BingoBall d = new BingoBall(57);
		BingoBall e = new BingoBall(1);
		test.insert(a);
		test.insert(b);
		test.insert(c);
		test.insert(d);
		test.insert(e);
		System.out.println(test);
		test.insert(c);
		System.out.println(test.numBallsCalled());
		BingoBall f = new BingoBall(6);
		test.insert(f);
		System.out.println(test);
		test.remove(d);
		System.out.println(test);
		test.makeEmpty();
		System.out.println(test);
		numBallsCalled();
	}
	
}


