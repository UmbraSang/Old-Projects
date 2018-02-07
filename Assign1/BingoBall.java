/**
 * Template created for CSC115 Assignment 1: Bingo
 * Connor McLeod
 * V00725080
 */

public class BingoBall {

//Establishes data fields and holds our array of letters.

	private static char[] bingo = {'B','I','N','G','O'};
	private char bingoChar;
	private int bingoNum;


	public BingoBall(int value) {
		//tests to make sure that our letter is within acceptable bounds.
		if (value > 0 && value < 76) {
			 setValue(value);
		} else {
			throw new IllegalArgumentException("number must be between 1 and 75; it was "+value);
		}
	}

	private void setLetter() {
		//sets our Letter by using our number and array.
		this.bingoChar = bingo[this.bingoNum/15];
	}
		
	public int getValue() {
		//returns our number.
		return this.bingoNum;
	}

	public char getLetter() {
		//returns our letter.
		return this.bingoChar; 
	}

	public void setValue(int value) {
		//sets our number and initiates a change in letter.
		this.bingoNum = value;
		setLetter();
		
	}

	public boolean equals(BingoBall other) {
		//checks to see if two bingo balls are equivelant. 
		if (other.getValue() == this.bingoNum){
			return true;
		}else{
			return false;
		}
	}

	public String toString() {
		return bingoChar+""+bingoNum;
	}


	public static void main(String[] args) {
		//Tests our class.
		BingoBall b = new BingoBall(42);
		System.out.println("Created a BingoBall: "+b);
		System.out.println("The number is "+b.getValue());
		System.out.println("The letter is "+b.getLetter());
		BingoBall c = null;
		try {
			c = new BingoBall(76);
		} catch (Exception e) {
			System.out.println("Correctly caught the exception");
		}
		System.out.println("Created a second BingoBall: "+c);
		c = new BingoBall(14);
		if (!b.equals(c)) {
			System.out.println("The two balls are not equivalent");
		}
		c.setValue(42);
		System.out.println("The second ball has been changed to "+c);
		if (b.equals(c)) {
			System.out.println("They are now equivalent");
		}
		c.setValue(74);
		System.out.println("The second bingo ball has been changed to "+c);
	}
}
