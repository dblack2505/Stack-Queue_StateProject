import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Class Stack
 * 
 * @author Dalton Black
 * @version 2/16/2021
 */
public class Stack {

	private static NumberFormat formatter = new DecimalFormat("#0.0000");
	private static State[] stateStack;
	private static int max;
	private static int top;
	private static int nElems;

	/**
	 * Constructor to set maxSize, top, and stateStack. 
	 * @param maxSize -  int max size of Stack array.
	 */
	public Stack(int maxSize) {
		this.max = maxSize;
		top = -1;
		stateStack = new State[max];
	}

	/**
	 * Method to push State objects onto the Stack from the 4 PQs that removed the States.
	 * @param state - State object
	 */
	public static void push(State state) {
		if (isFull()) 
			System.out.println("The Stack is full.");
		else {
			stateStack[++top] = state;
			nElems++;
		}
	}

	/**
	 * Method is used to pop the State objects from stateStack and pass them to main where they are displayed.  
	 * @return State object from Stack to main.
	 */
	public static State pop() {
		if (isEmpty()) 
			System.out.println("The Stack is empty.");
		return stateStack[top--];
	}

	/**
	 * Method to test if the stack is empty.
	 * @return boolean
	 */
	public static boolean isEmpty() {
		return (top == -1);
	}

	/**
	 * Method to test if stack is full. 
	 * @return boolean
	 */
	public static boolean isFull() {
		return (top == max - 1);
	}

	/**
	 * Method to display the stack. 
	 */
	public static void printStateStack() {
		System.out.println("Stack Priority Queue Contents: IN METHOD");
		System.out.println("Name\t\t\t MHI\t VCR\t CFR\t\t Case Rate\t Death Rate");
		System.out.println("---------------------------------------------------------------------------------------");
		for (int i = 0; i < nElems; i++) {
			System.out.printf("%-8s" + "\t\t" + stateStack[i].getHouseIncome() + "\t" + stateStack[i].getViolentCrime()
					+ "\t " + formatter.format(stateStack[i].getCFR()) + "\t  " + formatter.format(stateStack[i].getCaseRate()) + "\t " + 
					formatter.format(stateStack[i].getDeathRate()) + "\n", stateStack[i].getState());
		}
		System.out.println();
	} 
	
}
