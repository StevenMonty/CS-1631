import java.util.ArrayList;
import java.util.Random;

public class Miner {

	final double SILVER_VALUE = 1.31; // $ per ounce
	final double GOLD_VALUE = 20.67; // $ per ounce
	private double silverValue = 0.0;
	private double goldValue = 0.0;
	private double totalValue = 0.0;

	protected int silver; // total ounces of silver mined
	protected int gold; // total ounces of gold mined
	private int days; // days traveled
	private int number; // unique identifier
	private int todaysGold; // gold mined today
	private int todaysSilver; // silver mined today

	private ArrayList<Location> visited; // list of all cities traveled to
	private Location currentLocation; // current city the miner is visited
	private Random gen; // Random generator for decision - injected from Game
	private Map map; // Map used to navigate cities - injected from Game

	public Miner(int number, Random gen, Map map) {
		this.number = number;
		this.gold = 0;
		this.silver = 0;
		this.visited = new ArrayList<Location>();

		// random number that uses seed(req: 2)
		// Dependency Injection
		this.gen = gen;
		this.map = map;

		// a prospector shall always start in Sutter Creek (req: 3)
		this.currentLocation = map.getCurrentLocationInformation("SutterCreek");
		visited.add(currentLocation);
	}

	/******** GAME LOGIC **********/
	public void play(int days) {
		this.days = days;
		int numLocations = visited.size();
		work(); // mine at currentLoction
		displayWork(); // print todays findings to the console
		if (numLocations < 4) {
			// travel if gold and silver mined is zero (req: 6)
			if (shouldTravel1_3(getTodaysGold(), getTodaysSilver())) {
				travel();
			}
		} else if (numLocations < 6) {
			// travel if gold <= 1 and silver <= 2 mined is zero (req: 7)
			if (shouldTravelLastTwo(getTodaysGold(), getTodaysSilver())) {
				travel();
			}
		}
	}

	/******** WORK **********/
	// utility method, which makes it easier to call mineGold and mineSilver in here
	public void work() {
		this.mineGold();
		this.mineSilver();
	}

	public int mineGold() {
		// get the max gold from the Location
		int maxGoldAtLocation = currentLocation.getMAX_GOLD();
		int todaysWork = 0;

		if (maxGoldAtLocation == 0) // need to check if 0, otherwise error
			todaysWork = 0;
		else
			todaysWork = gen.nextInt(maxGoldAtLocation); // find random amount of gold in range [0, MAX_GOLD]

		this.gold += todaysWork;

		// if (todaysWork == 0) {} // do nothing - will use Silver to deal with "No
		// precious metals"

		this.todaysGold = todaysWork;
		return todaysWork;
	}

	public int mineSilver() {
		// get Location's max silver
		int maxSilverAtLocation = currentLocation.getMAX_SILVER();
		int todaysWork = 0;

		if (maxSilverAtLocation == 0) {
			todaysWork = 0;
		} else {
			todaysWork = gen.nextInt(maxSilverAtLocation); // find random amount of silver in range [0, MAX_SILVER]
		}

		this.silver += todaysWork;

		// if there is no gold or silver mined in todaysWork,
		// then call noPreciousMetals
		if (todaysWork == 0) {
			noPreciousMetals();
		}
		this.todaysSilver = todaysWork;
		return todaysWork;
	}

	// when no precious metals are found in today's work (req: 11)
	public void noPreciousMetals() {
		if (getTodaysGold() == 0 && getTodaysSilver() == 0) {
			System.out.println("\tNo precious metals were found.");
		}
	}

	/*************** TRAVEL *****************/
	public void travel() {
		Location nextLocation = map.nextLocation(currentLocation);

		// printing out location
		System.out.println("Heading from " + currentLocation.getName() + " to " + nextLocation.getName() + ", holding "
				+ gold + " " + ounce(gold) + " of gold and " + silver + " " + ounce(silver) + " of silver.");

		this.currentLocation = nextLocation;
		visited.add(currentLocation);
	}

	public boolean shouldTravel1_3(int todaysGold, int todaysSilver) {
		boolean shouldTravel = false;
		if (todaysGold == 0) {
			if (todaysSilver == 0) {
				shouldTravel = true;
			}
		}
		return shouldTravel;
	}

	public boolean shouldTravelLastTwo(int todaysGold, int todaysSilver) {
		boolean shouldTravel = false;
		if (todaysGold <= 1) {
			if (todaysSilver <= 2) {
				shouldTravel = true;
			}
		}
		return shouldTravel;
	}

	/************* DISPLAY **********************/
	// matching output from sample.txt (req: 19)
	public void displayWork() {
		displayWorkGold(this.gold);
		displayWorkSilver(this.silver);
	}

	public void displayWorkGold(int gold) {
		if (getTodaysGold() > 0) {
			System.out.println("\tFound " + getTodaysGold() + " " + ounce(todaysGold) + " of gold in "
					+ currentLocation.getName() + ".");
		}
	}

	public void displayWorkSilver(int silver) {
		if (getTodaysSilver() > 0) {
			System.out.println("\tFound " + getTodaysSilver() + " " + ounce(todaysSilver) + " of silver in "
					+ currentLocation.getName() + ".");
		}
	}

	public void displayFinalValues() {
		headingHome();
		displayTotals();
	}

	protected void headingHome() {
		System.out.println("After " + days + " days, Prospector " // we have to subtract one from days
				+ "#" + getCurrentMinerNum() + " returned to San Francisco with:");
	}

	// displaying information (req: 16)
	public void displayTotals() {
		System.out.println("\t" + this.gold + " " + ounce(this.gold) + " of gold." + "\n\t" + this.silver + " "
				+ ounce(this.silver) + " of silver." + "\n\tHeading home with " + formatTotalValue() + ".");
	}

	/************ CALCULATE VALUE ************/
	// ounce or ounces (req: 17)
	public String ounce(int ounce) {
		String ounceOrOunces = "ounce";
		if (ounce != 1) {
			ounceOrOunces = "ounces";
		}
		return ounceOrOunces;
	}

	public double calculateGoldValue(int gold) {
		goldValue = gold * GOLD_VALUE;
		return goldValue;
	}

	public double calculateSilverValue(int silver) {
		silverValue = silver * SILVER_VALUE;
		return silverValue;
	}

	// calculating value of mined gold and silver (req: 15)
	public double calculateTotalValue(int gold, int silver) {
		totalValue = calculateSilverValue(silver) + calculateGoldValue(gold);
		return totalValue;
	}

	// displaying monetary string (req: 16)
	public String formatTotalValue() {
		calculateTotalValue(this.gold, this.silver);
		String value = String.format("%.2f", totalValue);
		return "$" + value;
	}

	/******** GETTERS **********/

	public int getTodaysGold() {
		return this.todaysGold;
	}

	public int getTodaysSilver() {
		return this.todaysSilver;
	}

	public Location getCurrentLocation() {
		return this.currentLocation;
	}

	public int getNumVisited() {
		return this.visited.size();
	}

	public int getCurrentMinerNum() {
		return this.number + 1;
	}

	/******** SETTERS **********/
	// for testing
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = map.getCurrentLocationInformation(currentLocation);
	}

}
