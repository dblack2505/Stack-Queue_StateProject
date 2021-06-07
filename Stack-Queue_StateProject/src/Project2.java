import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * COP 3530: Project 2 - Stacks and Queues
 * <p>
 * This program reads in data from an excel sheet and implements 4 priority queues to assign the state objects into
 * VGOOD, GOOD, FAIR, or POOR orders based off Death Rate. It then removes the items from each queue starting with 
 * POOR and pushes them onto a stack which pops the objects back to main which displays them.
 * <p>
 * 
 * @author Dalton Black
 * @version 2/16/2021
 *
 */
public class Project2 {
	private static NumberFormat formatter = new DecimalFormat("#0.0000");

	/**
	 * Below is the main function.
	 * <p>
	 * It takes user input for an excel file and reads the data into an array. Then, a for loop with if statements
	 * is ran to determine, which maxSize variables should be increased based on death rate. The variables are then 
	 * passed to the PriorityQ class which uses the ints as the size of the 4 arrays. Returning to main, another for loop
	 * is ran to determine which States objects should be inserted to which PQs. The PQs are then printed, removed, 
	 * and pushed onto the Stack which then pops the objects to be printed in main.
	 * @param args
	 */
	public static void main(String[] args) {
		int maxSizeVGOOD = 0;
		int maxSizeGOOD = 0;
		int maxSizeFAIR = 0;
		int maxSizePOOR = 0;
		int maxSize = 50;
		State state = new State(maxSize);
		Stack stack = new Stack(maxSize);
		State[] displayStack = new State[maxSize];
		State[] arr;
		arr = new State[maxSize];

		System.out.println("COP3530 Project 2");
		System.out.println("Instructor: Xudong Liu\n");
		System.out.println("Stacks and Priority Queues");

		int nElem = 0;
		String[] data; //excel data
		Scanner input = new Scanner(System.in);
		String line = "";
		String path = null;

		System.out.print("Enter the file name: ");
		path = input.nextLine();
		System.out.println("");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {	
			br.readLine();
			while((line = br.readLine()) != null) {
				data = line.split(",");
				int seats = Integer.parseInt(data[3]);
				int popu = Integer.parseInt(data[4]);
				int covidC = Integer.parseInt(data[5]);
				int covidD = Integer.parseInt(data[6]);
				int houseIncome = Integer.parseInt(data[7]);
				double violentC = Double.parseDouble(data[8]);
				arr[nElem] = new State(data[0],data[1],data[2],seats,popu,covidC,covidD,houseIncome,violentC);
				nElem++;
			}
			System.out.println("There were " + nElem + " records read.\n");

			for (int i = 0; i < nElem; i++) {
				if (arr[i].getDeathRate() < 50)
					maxSizeVGOOD++;
				else if (arr[i].getDeathRate() >= 50 && arr[i].getDeathRate() < 100)
					maxSizeGOOD++;
				else if (arr[i].getDeathRate() >= 100 && arr[i].getDeathRate() < 150)
					maxSizeFAIR++;
				else if (arr[i].getDeathRate() >= 150)
					maxSizePOOR++;
			}

			PriorityQ PQ = new PriorityQ(maxSizeVGOOD, maxSizeGOOD, maxSizeFAIR, maxSizePOOR);

			for (int j = 0; j < nElem; j++) {
				if (arr[j].getDeathRate() < 50)
					PriorityQ.insertVGOOD(arr[j]);
				else if (arr[j].getDeathRate() >= 50 && arr[j].getDeathRate() < 100)
					PriorityQ.insertGOOD(arr[j]);
				else if (arr[j].getDeathRate() >= 100 && arr[j].getDeathRate() < 150)
					PriorityQ.insertFAIR(arr[j]);
				else if (arr[j].getDeathRate() >= 150)
					PriorityQ.insertPOOR(arr[j]);
			}

			PriorityQ.printPOOR();
			PriorityQ.printFAIR();
			PriorityQ.printGOOD();
			PriorityQ.printVGOOD();

			for (int k = 0; k < maxSizePOOR; k++) {
				Stack.push(PriorityQ.removePOOR());
			}

			for (int k = 0; k < maxSizeFAIR; k++) {
				Stack.push(PriorityQ.removeFAIR());
			}

			for (int k = 0; k < maxSizeGOOD; k++) {
				Stack.push(PriorityQ.removeGOOD());
			}

			for (int k = 0; k < maxSizeVGOOD; k++) {
				Stack.push(PriorityQ.removeVGOOD());
			}

			System.out.println("Stack Priority Queue Contents:");
			System.out.println("Name\t\t\t MHI\t VCR\t CFR\t\t Case Rate\t Death Rate");
			System.out.println("---------------------------------------------------------------------------------------");
			for (int m = 0; m < displayStack.length; m++) {
				displayStack[m] = Stack.pop();
				System.out.printf("%-8s" + "\t\t" + displayStack[m].getHouseIncome() + "\t" + displayStack[m].getViolentCrime()
						+ "\t " + formatter.format(displayStack[m].getCFR()) + "\t  " + formatter.format(displayStack[m].getCaseRate()) + "\t " + 
						formatter.format(displayStack[m].getDeathRate()) + "\n", displayStack[m].getState());
			}
			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
