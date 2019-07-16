public class GoldRush {

	public static void main(String[] args) {
		// Verify that the arguments are valid

		// If arguments are valid, create a new game of
		// using args[0] = seed, args[1] = numMiners
		// Then play it!
		// Otherwise, show proper usage message and exit program
		if (checkArgs(args)) {
			Game game = new Game(Integer.parseInt(args[0]), Integer.parseInt(args[1])); // new Game(seed, numMiners)
			game.play();
		} else {
			showUsageAndExit();
		}
	}

	/********* REQUIREMENT #1 *********/
	// If there are not two arguments,
	// the arguments cannot be interpreted as
	// integers, or the number of prospectors is
	// not a non-negative integer, the system
	// shall report usage and exit with exit code 1.
	public static boolean checkArgs(String[] args) {
		// if there are 2 arguments and both are ints above zero, then print true
		// else false (to exit program)
		try {
			if (args.length == 2 && Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0)
				return true;
		} catch (NumberFormatException e) {
		} // gracefully catch exception without crashing
		return false;
	}

	// Print the usage message to STDOUT and then exit the program
	// Note that it exits with code 1, meaning there was an error
	// (0 is generally used to indicate "no error")
	protected static void showUsageAndExit() {
		System.out.println("Usage:");
		System.out.println("java GoldRush *seed* *num_prospectors"); // replacing with his example sample_output.txt
		System.out.println("*seed* should be an integer");
		System.out.println("*num_prospectors* should be a non-negative integer");
//		System.exit(1);
	}

}
