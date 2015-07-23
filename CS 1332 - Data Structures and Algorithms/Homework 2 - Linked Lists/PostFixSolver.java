//HW 2 written by Jessie McGarry
//Collaboration Statement: This is solely my work.

/**
 * The purpose of this class is to solve arithmetic equations written
 * in postfix (also known as reverse polish notation).
 * 
 * @author Jessie McGarry
 * @version 1.0
 */
public class PostFixSolver {
	/**
	 * The linked list which will keep track of the numbers and
	 * operators in the equation.
	 */
	private IList<Integer> stack;
	
	/**
	 * Constructor creates a doubly linked list
	 */
	public PostFixSolver() {
		stack = new CircularDoublyLinkedList<Integer>();
	}
	
	/**
	 * This method solves the equation written in postfix form
	 * and returns the result. The String given MUST contain spaces
	 * in between each number and operators for this method to run
	 * properly.
	 * @param equation - String representation of equation to be solved
	 * 						(MUST contain spaces between each element)
	 * @return - the answer to the postfix problem as an int.
	 */
	public int solve (String equation) {
		int length = equation.length();
		int result = 0;
		for(int i = 0; i < length; i++) {
			try {
				int num = Integer.parseInt(equation.substring(
											i, equation.indexOf(' ', i)));
				if (num > 9)    //i increments again for every
					i++;		//extra digit in num
				if (num > 99)
					i++;
				stack.add(num);
			}
			
			catch (Exception e){
				char operator = equation.charAt(i);
				
				if(operator != ' ') {
					int operand1 = stack.removeAt(0);
					int operand2 = stack.removeAt(0);
					if (operator == '+')
						result = operand1 + operand2;
					else {
						if (operator == '-')
							result = operand2 - operand1;
						else
							result = operand1 * operand2;
					}
					stack.add(result);
				} //if
			} // catch
		} //for
		return result;
	}
	
	/**
	 * test main method
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		PostFixSolver solver = new PostFixSolver();
		System.out.println(solver.solve("10 5 3 + -"));
		System.out.println(solver.solve("5 1 2 + 4 * + 3 -"));
		System.out.println(solver.solve("1 2 3 4 5 * + 6 * * +"));
		System.out.println(solver.solve("7 16 * 5 + 16 * 3 + 16 * 1 +"));
	} // end test main
}
