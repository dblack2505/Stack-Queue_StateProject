import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Class State
 * 
 * @author Dalton Black
 * @version 2/16/2021
 */
public class State {
	private static NumberFormat formatter = new DecimalFormat("#0.0000");
	private static int nElems;
	private static int max;

	private String state;
	private String capital;
	private String region;
	private int houseSeats;
	private int population;
	private int covidCase;
	private int covidDeath;
	private int houseIncome;
	private double violentCrime;

	/**
	 * Constructor to assign max with maxSize.
	 * @param maxSize - int max size of array
	 */
	public State(int maxSize) {
		this.max = maxSize;
	}

	/**
	 * Constructor to set excel object values. 
	 * @param dataState - String state name
	 * @param dataCapital - String state capital
	 * @param dataRegion - String region
	 * @param dataSeats - int house seats
	 * @param dataPop - int population of state
	 * @param dataCovidC - int covid cases
	 * @param dataCovidD - int covid deaths
	 * @param dataIncome - int median household income
	 * @param dataCrime - double violent crime rate
	 */
	public State(String dataState, String dataCapital, String dataRegion, int dataSeats, int dataPop, int dataCovidC,
			int dataCovidD, int dataIncome, double dataCrime) {
		this.state = dataState;
		this.capital = dataCapital;
		this.region = dataRegion;
		this.houseSeats = dataSeats;
		this.population = dataPop;
		this.covidCase = dataCovidC;
		this.covidDeath = dataCovidD;
		this.houseIncome = dataIncome;
		this.violentCrime = dataCrime;
	}

	/**
	 * Below are the multiple getter functions used to get objects from the excel data and to do calculations. 
	 * @return State
	 */
	public String getState() {
		return this.state; 
	}
	public String getCapital() {
		return this.capital; 
	}
	public String getRegion() {
		return this.region; 
	}
	public int getHouseSeats() {
		return this.houseSeats; 
	}
	public int getPopulation() {
		return this.population; 
	}
	public int getCovidCase() {
		return this.covidCase; 
	}
	public int getCovidDeath() {
		return this.covidDeath; 
	}
	public int getHouseIncome() {
		return this.houseIncome; 
	}
	public double getViolentCrime() {
		return this.violentCrime; 
	}
	public double getCFR() {
		return caseRateAlg(covidDeath, covidCase);
	}
	public double getCaseRate() {
		return caseRateAlg(covidCase, population);
	}
	public double getDeathRate() {
		return caseRateAlg(covidDeath, population);
	}

	/**
	 * Method to calculate the Case Fatality Rate.
	 * @param death - int covid deaths
	 * @param cases - int covid cases
	 * @return double CFR
	 */
	public static double cfrAlg(int death, int cases) {
		double cfr = (double) death / (double) cases;
		return cfr;
	}

	/**
	 * Method to calculate the Case Rate.
	 * @param cases - int covid cases
	 * @param population - int population of state
	 * @return double Case Rate
	 */
	public static double caseRateAlg(int cases, int population) {
		double caseRate = (double) cases / (double) population;
		return caseRate * 100000;
	}

	/**
	 * Method to calculate the Death Rate.
	 * @param death - int covid deaths
	 * @param population - int state population
	 * @return double Death Rate
	 */
	public static double deathRateAlg(int death, int population) {
		double deathRate = (double) death / (double) population;
		return deathRate * 100000;
	}

}