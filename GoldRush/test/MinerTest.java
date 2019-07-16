import java.util.Random;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MinerTest {

	// Initialize a Map (which creates the Location objects as vertices)
	Map map = new Map(new Random());
	Location SutterCreek = map.getCurrentLocationInformation("SutterCreek");
	Location AngelsCamp = map.getCurrentLocationInformation("AngelsCamp");
	Location Coloma = map.getCurrentLocationInformation("Coloma");
	Location NevadaCity = map.getCurrentLocationInformation("NevadaCity");
	Location VirginiaCity = map.getCurrentLocationInformation("VirginiaCity");
	Location Midas = map.getCurrentLocationInformation("Midas");
	Location ElDoradoCanyon = map.getCurrentLocationInformation("ElDoradoCanyon");

	@Mock
	Random gen = Mockito.mock(Random.class);
	Miner mockMiner = Mockito.mock(Miner.class);

	/*******
	 * 
	 * REQUIREMENT #4
	 * 
	 * Does the miner always start in Sutter Creek?
	 * 
	 *******/

	@Test
	public void getMinerLocationAtStartNotNull() {
		Miner miner = new Miner(1, gen, map);
		Location currentLocation = miner.getCurrentLocation();
		Assert.assertNotNull(currentLocation);
	}

	@Test
	public void getMinerLocationAtStart() {
		Miner miner = new Miner(1, gen, map);
		Location currentLocation = miner.getCurrentLocation();
		Assert.assertEquals("Sutter Creek", currentLocation.getName());
	}

	/*******
	 * 
	 * REQUIREMENT #6
	 * 
	 * Does the miner travel for the first three locations, if not reaching gold or
	 * silver > 0.
	 * 
	 *******/

	@Test
	public void ifNoPreciousMetalsFirstTurn_ShouldTravel() {
		Miner miner = new Miner(1, gen, map);
		boolean shouldTravel = miner.shouldTravel1_3(0, 0);
		Assert.assertTrue(shouldTravel);
	}

	@Test
	public void ifNoPreciousMetalsFirstTurn_ShouldNotTravel_Gold() {
		Miner miner = new Miner(1, gen, map);
		boolean shouldTravel = miner.shouldTravel1_3(1, 0); // 1 gold, 0 silver
		Assert.assertFalse(shouldTravel);
	}

	@Test
	public void ifNoPreciousMetalsFirstTurn_ShouldNotTravel_Silver() {
		Miner miner = new Miner(1, gen, map);
		boolean shouldTravel = miner.shouldTravel1_3(0, 1); // 0 gold, 1 silver
		Assert.assertFalse(shouldTravel);
	}

	/*******
	 * 
	 * REQUIREMENT #7
	 * 
	 * Does the miner travel for the last two locations, if (gold <= 1 or silver <=
	 * 2).
	 * 
	 *******/

	@Test
	public void ifNoPreciousMetals_ShouldTravel_Gold() {
		Miner miner = new Miner(1, gen, map);
		boolean shouldTravel = miner.shouldTravelLastTwo(1, 0); // 1 gold, 0 silver
		Assert.assertTrue(shouldTravel);
	}

	@Test
	public void ifNoPreciousMetals_ShouldTravel_Silver() {
		Miner miner = new Miner(1, gen, map);
		boolean shouldTravel = miner.shouldTravelLastTwo(0, 2); // 0 gold, 2 silver
		Assert.assertTrue(shouldTravel);
	}

	@Test
	public void ifNoPreciousMetals_ShouldNotTravel_Gold() {
		Miner miner = new Miner(1, gen, map);
		boolean shouldTravel = miner.shouldTravelLastTwo(2, 0); // 2 gold, 0 silver
		Assert.assertFalse(shouldTravel);
	}

	@Test
	public void ifNoPreciousMetals_ShouldNotTravel_Silver() {
		Miner miner = new Miner(1, gen, map);
		boolean shouldTravel = miner.shouldTravelLastTwo(0, 3); // 0 gold, 3 silver
		Assert.assertFalse(shouldTravel);
	}

	@Test
	public void testOunce() {
		Miner miner = new Miner(1, gen, map);
		String ret = miner.ounce(1);
		Assert.assertTrue(ret.equals("ounce"));
	}

	@Test
	public void testOunces() {
		Miner miner = new Miner(1, gen, map);
		String ret = miner.ounce(2);
		Assert.assertTrue(ret.equals("ounces"));
	}

	@Test
	public void testSetCurrentLoc() {
		Miner miner = new Miner(1, gen, map);
		miner.setCurrentLocation("Midas");
		Assert.assertTrue(miner.getCurrentLocation().getName().equals("Midas"));
	}

}
