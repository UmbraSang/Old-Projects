/*
 * The shell of the class, to be completed as part of CSC115 Assignment 3 : Calculator.
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class ArithExpression {

	private TokenList postfixTokens;
	private TokenList infixTokens;

	/**
	 * Sets up a legal standard Arithmetic expression.
	 * The only parentheses accepted are "(" and ")".
	 * @param word An arithmetic expression in standard infix order.
	 * 	An invalid expression is not expressly checked for, but will not be
	 * 	successfully evaluated, when the <b>evaluate</b> method is called.
	 * @throws InvalidExpressionException if the expression cannot be properly parsed,
	 *  	or converted to a postfix expression.
	 */
	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixTokens list, where each token is 
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 || 	
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}

	/**
	 * Determines whether a single character string is an operator.
	 * The allowable operators are {+,-,*,/,^}.
	 * @param op The string in question.
	 * @return True if it is recognized as a an operator.
	 */
	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}
		

	 /**
	 * A private method that initializes the postfixTokens data field.
	 * It takes the information from the infixTokens data field.
	 * If, during the process, it is determined that the expression is invalid,
	 * an InvalidExpressionException is thrown.
 	 * Note that since the only method that calls this method is the constructor,
	 * the Exception is propogated through the constructor.
	 */
	private void infixToPostfix() {
		postfixTokens = new TokenList(infixTokens.size());
		StringStack stack = new StringStack();
		for(int i = 0; i<infixTokens.size();i++){
			if(infixTokens.get(i).equals("(")){
				stack.push(infixTokens.get(i));
			}else if(infixTokens.get(i).equals(")")){
				while(!stack.peek().equals("(")){
					postfixTokens.append(stack.pop());
				}
				stack.pop();
			}else if(!isOperator(infixTokens.get(i))){
				postfixTokens.append(infixTokens.get(i));
			}else if(isOperator(infixTokens.get(i))){
				while(!stack.isEmpty() && !stack.peek().equals("(") && precedence(infixTokens.get(i))<=precedence(stack.peek())){
					postfixTokens.append(stack.pop());
				}
				stack.push(infixTokens.get(i));
			}
			//System.out.println(stack.toString());
		}
		while(!stack.isEmpty()){
			postfixTokens.append(stack.pop());
		}
	}
	//establishes the precedence of our operators.
	private int precedence(String a){
		if(a.equals("+") || a.equals("-")){
			return 1;
		}else if(a.equals("*") || a.equals("/")){
			return 2;
		}else if(a.equals("^")){
			return 3;
		}else{
			return -1;
		}
	}
	//returns a nicely spaced version of our infix list.
	public String getInfixExpression() {
		return infixTokens.toString();
	
	}
	//returns a nicely spaced version of our postfix list.
	public String getPostfixExpression() {
		return postfixTokens.toString();
	
	}
	//uses our postfix version to actually do the math of our expression.
	public double evaluate() {
		StringStack stack = new StringStack();
		for(int i = 0; i<postfixTokens.size();i++){
			if(!isOperator(postfixTokens.get(i))){
				stack.push(postfixTokens.get(i));
			}else if(isOperator(postfixTokens.get(i))){
				try{
					double c = Double.parseDouble(stack.pop());
					double d = Double.parseDouble(stack.pop());
					if(postfixTokens.get(i).equals("+")){
						stack.push(""+(d+c));
					}else if(postfixTokens.get(i).equals("-")){
						stack.push(""+(d-c));
					}else if(postfixTokens.get(i).equals("*")){
						stack.push(""+(d*c));
					}else if(postfixTokens.get(i).equals("/")){
						stack.push(""+(d/c));
					}else if(postfixTokens.get(i).equals("^")){
						stack.push(""+Math.pow(d,c));
					}
				}catch(Exception e){
					System.out.println("Exception thrown in evaluate"+e);
				}
			}	
		}
		return Double.parseDouble(stack.pop());
	}
	//tester!!
	public static void main(String[] args) {
		ArithExpression test = new ArithExpression("5+27*3-(16/4)/2");
		System.out.println(test.getInfixExpression());
		System.out.println(test.getPostfixExpression());
	}
			
}
