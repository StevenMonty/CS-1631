import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

class GameTest {

	@Mock
	Miner minerMock = Mockito.mock(Miner.class);
	Location locationMock = Mockito.mock(Location.class);
	Map mapMock = Mockito.mock(Map.class);
	GoldRush game = Mockito.mock(GoldRush.class);

	// Testing an existing object - AS: Need to test neighbors.
	int seed = 1;
	Random gen = new Random(seed);
	Map map = new Map(gen);

	Miner testMiner;

	// Tests to make sure Locations are intialized to some value.
	// Further tests will test content.

	/*******
	 * REQUIREMENT #1
	 * 
	 * Tests checkArgs() and ensures that it accepts only two non-negative
	 * parameters.
	 * 
	 * @throws IOException
	 *******/

	@Test
	public void testGameConstructor() {
		Game game = new Game(0, 1);
		Assert.assertTrue(game instanceof Game);
	}

	@Test
	public void testCurrentMinerNum() {
		Game game = new Game(0, 1);
		Assert.assertTrue(game.getCurrentMinerNum() == 1);
	}

	@Test
	public void testNextMiner() {
		Game game = new Game(0, 2); // 2 miners
		game.nextMiner(game.currentMiner);
		Assert.assertTrue(game.currentMiner.getCurrentMinerNum() == 2);
	}

	@Test
	public void testNoNextMiner() {
		Game game = new Game(0, 1); // 1 miners
		game.nextMiner(game.currentMiner); // won't change minerNum since only 1 total
		Assert.assertTrue(game.currentMiner.getCurrentMinerNum() == 1);
	}

	@Test
	public void testUsage() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream(); // redirect System.out for comparison
		System.setOut(new PrintStream(out));
		GoldRush.showUsageAndExit();
		out.flush();
		String result = new String(out.toByteArray());
		Assert.assertTrue(result.contains("Usage:"));
	}

	@Test
	public void testPlay() {
		Game game = new Game(0, 1);
		game.play();
		Assert.assertFalse(game.nextExists); // game loop terminated once nextExist is false
	}

	@Test
	public void checkCorrectCheckArgs() {
		String[] args = { "1", "1" };
		try {
			boolean result = GoldRush.checkArgs(args);
			Assert.assertTrue(result);
		} catch (Exception e) {
		}
	}

	@Test
	public void checkIncorrectCheckArgsString() {
		String[] args = { "blue", "1" };
		try {
			boolean result = GoldRush.checkArgs(args);
			Assert.assertFalse(result);
		} catch (Exception e) {
		}
	}

	@Test
	public void checkIncorrectCheckArgsString2() {
		String[] args = { "1", "blue" };
		try {
			boolean result = GoldRush.checkArgs(args);
			Assert.assertFalse(result);
		} catch (Exception e) {
		}
	}

	@Test
	public void checkZeroArg() {
		String[] args = { "0", "1" };
		try {
			boolean result = GoldRush.checkArgs(args);
			Assert.assertFalse(result);
		} catch (Exception e) {
		}
	}

	@Test
	public void checkZeroArgTwo() {
		String[] args = { "1", "0" };
		try {
			boolean result = GoldRush.checkArgs(args);
			Assert.assertFalse(result);
		} catch (Exception e) {
		}
	}

	@Test
	public void checkNegativeArg() {
		String[] args = { "-1", "1" };
		try {
			boolean result = GoldRush.checkArgs(args);
			Assert.assertFalse(result);
		} catch (Exception e) {
		}
	}

	@Test
	public void checkNegativeArgTwo() {
		String[] args = { "1", "-1" };
		try {
			boolean result = GoldRush.checkArgs(args);
			Assert.assertFalse(result);
		} catch (Exception e) {
		}
	}

	@Test
	public void checkFloatsArg() {
		String[] args = { "1.0", "1" };
		try {
			boolean result = GoldRush.checkArgs(args);
			Assert.assertFalse(result);
		} catch (Exception e) {
		}
	}

	@Test
	public void checkFloatsArgTwo() {
		String[] args = { "1", "1.0" };
		try {
			boolean result = GoldRush.checkArgs(args);
			Assert.assertFalse(result);
		} catch (Exception e) {
		}
	}

	/*******
	 * REQUIREMENT #15
	 * 
	 * Checks to see that the correct amount of money is displayed
	 * 
	 *******/

	@Test
	public void testMoney() {
		Miner miner = new Miner(1, gen, map);
		double expected = (5 * 20.67) + (2 * 1.31);
		double value = miner.calculateTotalValue(5, 2); // 5 gold x 20.67 = 103.35 // 2 silver x 1.31 = 2.62
		Assert.assertTrue(expected == value);
	}

	/*******
	 * REQUIREMENT #16
	 * 
	 * Checks the display of money (i.e., includes dollar sign $0.00)
	 * 
	 ********/

	@Test
	public void displayTest() {
		Miner miner = new Miner(1, gen, map);

		miner.gold = 5; // 5 x 20.67 = 103.35
		miner.silver = 2; // 2 x 1.31 = 2.62

		String actual = miner.formatTotalValue();
		Double expectedValue = (double) Math.round(((5 * 20.67) + (2 * 1.31)) * 100) / 100; // multiple, must round(),
																							// then divide by 100 to get
																							// two decimals
		String expected = "$" + expectedValue;

		Assert.assertTrue(expected.equals(actual));
	}

	/*******
	 * 
	 * REQUIREMENT #17
	 * 
	 * Testing to see if the correct ounce/ounces appears
	 * 
	 *******/

	@Test
	public void testOunceOrOunces() {
		Mockito.when(minerMock.ounce(1)).thenReturn("Yes");
		String result = minerMock.ounce(1);
		Assert.assertEquals("Yes", result);
	}

	@Test
	public void testOunceOrOunces2() {
		Mockito.when(minerMock.ounce(0)).thenReturn("No");
		String result = minerMock.ounce(0);
		Assert.assertEquals("No", result);
	}

	@Test
	public void testOunceOrOuncesWithMiner() {
		Miner miner = new Miner(1, gen, map);
		String result = miner.ounce(1);
		String expected = "ounce";
		Assert.assertSame(expected, result);
	}

	@Test
	public void testOunceOrOunces2WithMiner() {
		Miner miner = new Miner(1, gen, map);
		String result = miner.ounce(2);
		String expected = "ounces";
		Assert.assertSame(expected, result);
	}

	@Test
	public void testOunceOrOunces0WithMiner() {
		Miner miner = new Miner(1, gen, map);
		String result = miner.ounce(0);
		String expected = "ounces";
		Assert.assertSame(expected, result);
	}

	/********
	 * 
	 * REQUIREMENT #18
	 * 
	 * Does the Game indicate the correct number of miners (e.g., "Prospector #n",
	 * where n > 0).
	 * 
	 *********/

	@Test
	public void getCurrentMinerNumber() {
		Miner miner = new Miner(2, gen, map); // if this index is 2, then it would be miner #3
		int actual = miner.getCurrentMinerNum(); // should be 3
		int expected = 3;

		Assert.assertEquals(expected, actual);
	}

}