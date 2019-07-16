import java.util.ArrayList;
import java.util.Random;

public class Game {

	private int days = 0;
	private ArrayList<Miner> miners;
	private Random gen;
	private Map map;
	protected boolean nextExists = true;
	protected Miner currentMiner;

	/******************* Constructors ******************/
	public Game(int seed, int numMiners) {
		this.gen = new Random(seed);
		this.map = new Map(gen);
		this.miners = new ArrayList<>(numMiners);
		initMiners(numMiners);
		this.currentMiner = miners.get(0);
	}

	/**
	 * For each slot in the ArrayList of Miners, initialize each miner with their
	 * number. Use Dependency Injection to supply the Random number generator and
	 * the Map
	 * 
	 * @param numMiners the number of miners
	 */
	private void initMiners(int numMiners) {
		for (int i = 0; i < numMiners; i++) {
			miners.add(new Miner(i, gen, map));
		}
	}

	/**
	 * Able to be called at the end of a game iteration, accepts a ref to the
	 * currentMiner playing the game and uses a lookup method to find the index of
	 * the next miner.
	 * 
	 * @param current the miner that finished the game when called
	 */
	protected void nextMiner(Miner current) {
		int next = miners.indexOf(current) + 1;

		if (next >= miners.size()) { // skip if currentMiner is the last in the ArrayList
			nextExists = false;
			return;
		}
		currentMiner = miners.get(next);
	}

	/***************** Game Play **********************/

	public void play() {
		do {
			// display the prospector number (req: 18) and current location (req: 4, for #1)
			System.out.println("\nProspector #" + getCurrentMinerNum() + " starting in "
					+ currentMiner.getCurrentLocation().getName() + ".");

			// once a prospector has visited 5 locations (repeats allowed), we stop (req: 9)
			do {
				days++;
				currentMiner.play(days);
			} while (currentMiner.getNumVisited() < 6);

			// after a prospector has visited five locations and thus finished prospecting,
			// the number of ounces of gold and silver the prospector has discovered shall
			// be displayed. (req: 14)
			currentMiner.displayFinalValues();

			nextMiner(currentMiner); // switch to the next miner

			this.days = 0; // reset number of days after last miner

		} while (nextExists); // continues until no more miners
	}

	// simulation must keep track of the prospector number
	// (there shall be no prospector #0). (req. 18)
	public int getCurrentMinerNum() {
		return (miners.indexOf(this.currentMiner) + 1);
	}
}
