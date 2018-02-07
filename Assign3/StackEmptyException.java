/**
 * CSC115 Assignment 3 : Calculator.
 * StackEmptyException.java.
 * Created for use by CSC115 Spring 2016
 */

/**
 * An exception thrown when an pop or peek action is determined to be invalid.
 */ 
public class StackEmptyException extends RuntimeException {

	/**
	 * Creates an exception.
	 * @param msg The message to the calling program.
	 */
	public StackEmptyException(String msg) {
		super(msg);
	}

	/**
	 * Creates an exception without a message.
	 */
	public StackEmptyException() {
		super();
	}
}
