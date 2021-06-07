import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Class PriorityQ
 * 
 * @author Dalton Black
 * @version 2/16/2021
 */
public class PriorityQ {

	private static NumberFormat formatter = new DecimalFormat("#0.0000");
	private static int maxVGOOD;
	private static int maxGOOD;
	private static int maxFAIR;
	private static int maxPOOR;
	private static int nElemsVGOOD = 0;
	private static int nElemsGOOD = 0;
	private static int nElemsFAIR = 0;
	private static int nElemsPOOR = 0;
	public static State[] arrVGOOD;
	public static State[] arrGOOD;
	public static State[] arrFAIR;
	public static State[] arrPOOR;
	private static int frontVGOOD;
	private static int rearVGOOD;
	private static int frontGOOD;
	private static int rearGOOD;
	private static int frontFAIR;
	private static int rearFAIR;
	private static int frontPOOR;
	private static int rearPOOR;

	/**
	 * Constructor to set the max sizes of each Priority Queue array. I also initialize the 4 PQs and front/rear 
	 * variables below. 
	 * @param maxSizePOOR - int max size of POOR array.
	 * @param maxSizeFAIR - int max size of FAIR array.
	 * @param maxSizeGOOD - int max size of GOOD array.
	 * @param maxSizeVGOOD - int max size of VGOOD array.
	 */
	public PriorityQ(int maxSizeVGOOD, int maxSizeGOOD, int maxSizeFAIR, int maxSizePOOR) {
		this.maxVGOOD = maxSizeVGOOD;
		this.maxGOOD = maxSizeGOOD;
		this.maxFAIR = maxSizeFAIR;
		this.maxPOOR = maxSizePOOR;
		this.arrVGOOD = new State[maxVGOOD];
		this.arrGOOD = new State[maxGOOD];
		this.arrFAIR = new State[maxFAIR];
		this.arrPOOR = new State[maxPOOR];
		frontVGOOD = 0; rearVGOOD = -1;
		frontGOOD = 0; rearGOOD = -1;
		frontFAIR = 0; rearFAIR = -1;
		frontPOOR = 0; rearPOOR = -1;
	}

	/**
	 * Method to insert the appropriate State object into the VGOOD PQ.
	 * @param state - State object (DR < 50)
	 */
	public static void insertVGOOD(State state) {
		if (isFullVGOOD()) {
			System.out.println("The queue is full.");
		}
		else if (rearVGOOD == maxVGOOD - 1) rearVGOOD = -1;
		arrVGOOD[++rearVGOOD] = state;
		nElemsVGOOD++;
	}

	/**
	 * Method to insert the appropriate State object into the GOOD PQ.
	 * @param state - State object (DR >= 50 && DR < 100)
	 */
	public static void insertGOOD(State state) {
		if (isFullGOOD()) {
			System.out.println("The queue is full.");
		}
		else if (rearGOOD == maxGOOD - 1) rearGOOD = -1;
		arrGOOD[++rearGOOD] = state;
		nElemsGOOD++;
	}

	/**
	 * Method to insert the appropriate State object into the FAIR PQ.
	 * @param state - State object (DR >= 100 && DR  < 150)
	 */
	public static void insertFAIR(State state) {
		if (isFullFAIR()) {
			System.out.println("The queue is full.");
		}
		else if (rearFAIR == maxFAIR - 1) rearFAIR = -1;
		arrFAIR[++rearFAIR] = state;
		nElemsFAIR++;
	}

	/**
	 * Method to insert the appropriate State object into the POOR PQ.
	 * @param state - State object (DR >= 150)
	 */
	public static void insertPOOR(State state) {
		if (isFullPOOR()) {
			System.out.println("The queue is full.");
		}
		else if (rearPOOR == maxPOOR - 1) rearPOOR = -1;
		arrPOOR[++rearPOOR] = state;
		nElemsPOOR++;
	}

	/**
	 * Method to remove POOR object from PQ.
	 * @return - State object to main.
	 */
	public static State removePOOR() {
		if (isEmptyPOOR()) {
			System.out.println("The queue is empty.");
		} 
		else {
			int inner;
			int outer = 1;
				State temp = arrPOOR[outer];
				inner = outer - 1;
				while (inner >= 0 && arrPOOR[inner].getDeathRate() < temp.getDeathRate()) {
					arrPOOR[inner + 1] = arrPOOR[inner];
					inner--;
				}
				arrPOOR[inner + 1] = temp;
				outer++;
		}
		return arrPOOR[--nElemsPOOR];
	}

	/**
	 * Method to remove FAIR object from PQ.
	 * @return - State object to main.
	 */
	public static State removeFAIR() {
		if (isEmptyFAIR()) {
			System.out.println("The queue is empty.");
		} 
		else {
			int inner;
			int outer = 1;
				State temp = arrFAIR[outer];
				inner = outer - 1;
				while (inner >= 0 && arrFAIR[inner].getDeathRate() < temp.getDeathRate()) {
					arrFAIR[inner + 1] = arrFAIR[inner];
					inner--;
				}
				arrFAIR[inner + 1] = temp;
				outer++;
		}
		return arrFAIR[--nElemsFAIR];
	}

	/**
	 * Method to remove GOOD object from PQ.
	 * @return - State object to main.
	 */
	public static State removeGOOD() {
		if (isEmptyGOOD()) {
			System.out.println("The queue is empty.");
		} 
		else {
			int inner;
			int outer = 1;
				State temp = arrGOOD[outer];
				inner = outer - 1;
				while (inner >= 0 && arrGOOD[inner].getDeathRate() < temp.getDeathRate()) {
					arrGOOD[inner + 1] = arrGOOD[inner];
					inner--;
				}
				arrGOOD[inner + 1] = temp;
				outer++;
		}
		return arrGOOD[--nElemsGOOD];
	}

	/**
	 * Method to remove VGOOD object from PQ.
	 * @return -  State object to main.
	 */
	public static State removeVGOOD() {
		if (isEmptyVGOOD()) {
			System.out.println("The queue is empty.");
		} 
		else {
			int inner;
			int outer = 1;
				State temp = arrVGOOD[outer];
				inner = outer - 1;
				while (inner >= 0 && arrVGOOD[inner].getDeathRate() < temp.getDeathRate()) {
					arrVGOOD[inner + 1] = arrVGOOD[inner];
					inner--;
				}
				arrVGOOD[inner + 1] = temp;
				outer++;
		}
		return arrVGOOD[--nElemsVGOOD];
	}

	/**
	 * Below are the multiple isEmpty() and isFull() methods to check if the PQs are either empty or full. 
	 * @return boolean
	 */
	public static boolean isEmptyVGOOD() {
		return (nElemsVGOOD == 0);
	}
	public static boolean isFullVGOOD() {
		return (nElemsVGOOD == maxVGOOD);
	}
	public static boolean isEmptyGOOD() {
		return (nElemsGOOD == 0);
	}
	public static boolean isFullGOOD() {
		return (nElemsGOOD == maxGOOD);
	}
	public static boolean isEmptyFAIR() {
		return (nElemsFAIR == 0);
	}
	public static boolean isFullFAIR() {
		return (nElemsFAIR == maxFAIR);
	}
	public static boolean isEmptyPOOR() {
		return (nElemsPOOR == 0);
	}
	public static boolean isFullPOOR() {
		return (nElemsPOOR == maxPOOR);
	}

	/**
	 * Method to print POOR PQ. 
	 */
	public static void printPOOR() {
		System.out.println("POOR Priority Queue Contents: ");
		System.out.println("Name\t\t\t MHI\t VCR\t CFR\t\t Case Rate\t Death Rate");
		System.out.println("---------------------------------------------------------------------------------------");
		for (int i = 0; i < nElemsPOOR; i++) {
			System.out.printf("%-8s" + "\t\t" + arrPOOR[i].getHouseIncome() + "\t" + arrPOOR[i].getViolentCrime()
					+ "\t " + formatter.format(arrPOOR[i].getCFR()) + "\t  " + formatter.format(arrPOOR[i].getCaseRate()) + "\t " + 
					formatter.format(arrPOOR[i].getDeathRate()) + "\n", arrPOOR[i].getState());
		}		
		System.out.println();
	}

	/**
	 * Method to print FAIR PQ. 
	 */
	public static void printFAIR() {
		System.out.println("FAIR Priority Queue Contents: ");
		System.out.println("Name\t\t\t MHI\t VCR\t CFR\t\t Case Rate\t Death Rate");
		System.out.println("---------------------------------------------------------------------------------------");
		for (int i = 0; i < nElemsFAIR; i++) {
			System.out.printf("%-8s" + "\t\t" + arrFAIR[i].getHouseIncome() + "\t" + arrFAIR[i].getViolentCrime()
					+ "\t " + formatter.format(arrFAIR[i].getCFR()) + "\t  " + formatter.format(arrFAIR[i].getCaseRate()) + "\t " + 
					formatter.format(arrFAIR[i].getDeathRate()) + "\n", arrFAIR[i].getState());
		}		
		System.out.println();
	}

	/**
	 * Method to print GOOD PQ. 
	 */
	public static void printGOOD() {
		System.out.println("GOOD Priority Queue Contents: ");
		System.out.println("Name\t\t\t MHI\t VCR\t CFR\t\t Case Rate\t Death Rate");
		System.out.println("---------------------------------------------------------------------------------------");
		for (int i = 0; i < nElemsGOOD; i++) {
			System.out.printf("%-8s" + "\t\t" + arrGOOD[i].getHouseIncome() + "\t" + arrGOOD[i].getViolentCrime()
					+ "\t " + formatter.format(arrGOOD[i].getCFR()) + "\t  " + formatter.format(arrGOOD[i].getCaseRate()) + "\t " + 
					formatter.format(arrGOOD[i].getDeathRate()) + "\n", arrGOOD[i].getState());
		}		
		System.out.println();
	}

	/**
	 * Method to print VGOOD PQ. 
	 */
	public static void printVGOOD() {
		System.out.println("VGOOD Priority Queue Contents: ");
		System.out.println("Name\t\t\t MHI\t VCR\t CFR\t\t Case Rate\t Death Rate");
		System.out.println("---------------------------------------------------------------------------------------");
		for (int i = 0; i < nElemsVGOOD; i++) {
			System.out.printf("%-8s" + "\t\t" + arrVGOOD[i].getHouseIncome() + "\t" + arrVGOOD[i].getViolentCrime()
					+ "\t " + formatter.format(arrVGOOD[i].getCFR()) + "\t  " + formatter.format(arrVGOOD[i].getCaseRate()) + "\t " + 
					formatter.format(arrVGOOD[i].getDeathRate()) + "\n", arrVGOOD[i].getState());
		}
		System.out.println();
	}


}
