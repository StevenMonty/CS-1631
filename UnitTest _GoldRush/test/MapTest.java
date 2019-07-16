import java.util.ArrayList;
import java.util.Random;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MapTest {

	// Initialize a Map (which creates the Location objects as vertices)
	int seed = 1;
	Random gen = new Random(seed);
	Map map = new Map(gen);
	Location SutterCreek = map.getCurrentLocationInformation("SutterCreek");
	Location AngelsCamp = map.getCurrentLocationInformation("AngelsCamp");
	Location Coloma = map.getCurrentLocationInformation("Coloma");
	Location NevadaCity = map.getCurrentLocationInformation("NevadaCity");
	Location VirginiaCity = map.getCurrentLocationInformation("VirginiaCity");
	Location Midas = map.getCurrentLocationInformation("Midas");
	Location ElDoradoCanyon = map.getCurrentLocationInformation("ElDoradoCanyon");

	@Test
	public void initializationOfLocations() {
		// Confirm that all of the Location objects initialized
		// above are not Null, i.e, we can begin testing
		Assert.assertNotNull(SutterCreek);
		Assert.assertNotNull(AngelsCamp);
		Assert.assertNotNull(Coloma);
		Assert.assertNotNull(NevadaCity);
		Assert.assertNotNull(VirginiaCity);
		Assert.assertNotNull(Midas);
		Assert.assertNotNull(ElDoradoCanyon);
	}

	/*******
	 * 
	 * REQUIREMENT #3
	 * 
	 * Does the Map graph identify the correct neighboring Locations (i.e.,
	 * vertices).
	 * 
	 *******/

	@Test
	public void checkNeighbors_SutterCreek() {
		ArrayList<Location> neighbors = map.getAllNeighbors(SutterCreek);
		ArrayList<Location> expectedNeighbors = new ArrayList<>();
		expectedNeighbors.add(AngelsCamp);
		expectedNeighbors.add(Coloma);
		Assert.assertEquals(expectedNeighbors, neighbors);
	}

	@Test
	public void checkNeighbors_AngelsCamp() {
		ArrayList<Location> neighbors = map.getAllNeighbors(AngelsCamp);
		ArrayList<Location> expectedNeighbors = new ArrayList<>();
		expectedNeighbors.add(SutterCreek);
		expectedNeighbors.add(NevadaCity);
		expectedNeighbors.add(VirginiaCity);
		Assert.assertEquals(expectedNeighbors, neighbors);
	}

	@Test
	public void checkNeighbors_NevadaCity() {
		ArrayList<Location> neighbors = map.getAllNeighbors(NevadaCity);
		ArrayList<Location> expectedNeighbors = new ArrayList<>();
		expectedNeighbors.add(AngelsCamp);
		Assert.assertEquals(expectedNeighbors, neighbors);
	}

	@Test
	public void checkNeighbors_VirginiaCity() {
		ArrayList<Location> neighbors = map.getAllNeighbors(VirginiaCity);
		ArrayList<Location> expectedNeighbors = new ArrayList<>();
		expectedNeighbors.add(AngelsCamp);
		expectedNeighbors.add(Coloma);
		expectedNeighbors.add(Midas);
		expectedNeighbors.add(ElDoradoCanyon);
		Assert.assertEquals(expectedNeighbors, neighbors);
	}

	@Test
	public void checkNeighbors_Coloma() {
		ArrayList<Location> neighbors = map.getAllNeighbors(Coloma);
		ArrayList<Location> expectedNeighbors = new ArrayList<>();
		expectedNeighbors.add(SutterCreek);
		expectedNeighbors.add(VirginiaCity);
		Assert.assertEquals(expectedNeighbors, neighbors);
	}

	@Test
	public void checkNeighbors_Midas() {
		ArrayList<Location> neighbors = map.getAllNeighbors(Midas);
		ArrayList<Location> expectedNeighbors = new ArrayList<>();
		expectedNeighbors.add(VirginiaCity);
		expectedNeighbors.add(ElDoradoCanyon);
		Assert.assertEquals(expectedNeighbors, neighbors);
	}

	@Test
	public void checkNeighbors_ElDoradoCanyon() {
		ArrayList<Location> neighbors = map.getAllNeighbors(ElDoradoCanyon);
		ArrayList<Location> expectedNeighbors = new ArrayList<>();
		expectedNeighbors.add(Midas);
		expectedNeighbors.add(VirginiaCity);
		Assert.assertEquals(expectedNeighbors, neighbors);
	}

	@Test
	public void locationToString() {
		Assert.assertTrue(Midas.getName().contains("Midas"));
	}

	/*******
	 * 
	 * REQUIREMENT #8
	 * 
	 * Does the miner travel to a valid location (i.e., neighboring vertice)
	 * 
	 *******/
	@Test
	public void checkNextLocation_SutterCreek() {
		Location expected = null;
		Location actual = map.nextLocation(SutterCreek);
		Location neighbor1 = AngelsCamp;
		Location neighbor2 = Coloma;

		if (actual.equals(neighbor1)) {
			expected = neighbor1;
		}
		if (actual.equals(neighbor2)) {
			expected = neighbor2;
		}
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void checkNextLocation_AngelsCamp() {
		Location expected = null;
		Location actual = map.nextLocation(AngelsCamp);
		Location neighbor1 = SutterCreek;
		Location neighbor2 = NevadaCity;
		Location neighbor3 = VirginiaCity;

		if (actual.equals(neighbor1)) {
			expected = neighbor1;
		}
		if (actual.equals(neighbor2)) {
			expected = neighbor2;
		}
		if (actual.equals(neighbor3)) {
			expected = neighbor3;
		}
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void checkNextLocation_NevadaCity() {
		Location expected = null;
		Location actual = map.nextLocation(NevadaCity);
		Location neighbor1 = AngelsCamp;

		if (actual.equals(neighbor1)) {
			expected = neighbor1;
		}

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void checkNextLocation_Coloma() {
		Location expected = null;
		Location actual = map.nextLocation(Coloma);
		Location neighbor1 = SutterCreek;
		Location neighbor2 = VirginiaCity;

		if (actual.equals(neighbor1)) {
			expected = neighbor1;
		}
		if (actual.equals(neighbor2)) {
			expected = neighbor2;
		}
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void checkNextLocation_VirginiaCity() {
		Location expected = null;
		Location actual = map.nextLocation(VirginiaCity);
		Location neighbor1 = AngelsCamp;
		Location neighbor2 = Coloma;
		Location neighbor3 = Midas;
		Location neighbor4 = ElDoradoCanyon;

		if (actual.equals(neighbor1)) {
			expected = neighbor1;
		}
		if (actual.equals(neighbor2)) {
			expected = neighbor2;
		}
		if (actual.equals(neighbor3)) {
			expected = neighbor3;
		}
		if (actual.equals(neighbor4)) {
			expected = neighbor4;
		}
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void checkNextLocation_Midas() {
		Location expected = null;
		Location actual = map.nextLocation(Midas);
		Location neighbor1 = ElDoradoCanyon;
		Location neighbor2 = VirginiaCity;

		if (actual.equals(neighbor1)) {
			expected = neighbor1;
		}
		if (actual.equals(neighbor2)) {
			expected = neighbor2;
		}
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void checkNextLocation_ElDoradoCanyon() {
		Location expected = null;
		Location actual = map.nextLocation(ElDoradoCanyon);
		Location neighbor1 = VirginiaCity;
		Location neighbor2 = Midas;

		if (actual.equals(neighbor1)) {
			expected = neighbor1;
		}
		if (actual.equals(neighbor2)) {
			expected = neighbor2;
		}
		Assert.assertEquals(expected, actual);
	}

	/*******
	 * 
	 * REQUIREMENT #10
	 * 
	 * Are the max gold and silver set in the location.
	 * 
	 *******/

	@Test
	public void SutterCreek_MaxSilver() {
		int actual = SutterCreek.getMAX_SILVER();
		int expected = 0;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void SutterCreek_MaxGold() {
		int actual = SutterCreek.getMAX_GOLD();
		int expected = 2;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void Coloma_MaxSilver() {
		int actual = Coloma.getMAX_SILVER();
		int expected = 0;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void Coloma_MaxGold() {
		int actual = Coloma.getMAX_GOLD();
		int expected = 3;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void AngelsCamp_MaxSilver() {
		int actual = AngelsCamp.getMAX_SILVER();
		int expected = 0;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void AngelsCamp_MaxGold() {
		int actual = AngelsCamp.getMAX_GOLD();
		int expected = 4;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void NevadaCity_MaxSilver() {
		int actual = NevadaCity.getMAX_SILVER();
		int expected = 0;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void NevadaCity_MaxGold() {
		int actual = NevadaCity.getMAX_GOLD();
		int expected = 5;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void VirginiaCity_MaxSilver() {
		int actual = VirginiaCity.getMAX_SILVER();
		int expected = 3;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void VirginiaCity_MaxGold() {
		int actual = VirginiaCity.getMAX_GOLD();
		int expected = 3;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void Midas_MaxSilver() {
		int actual = Midas.getMAX_SILVER();
		int expected = 5;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void Midas_MaxGold() {
		int actual = Midas.getMAX_GOLD();
		int expected = 0;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void ElDoradoCanyon_MaxSilver() {
		int actual = ElDoradoCanyon.getMAX_SILVER();
		int expected = 10;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void ElDoradoCanyon_MaxGold() {
		int actual = ElDoradoCanyon.getMAX_GOLD();
		int expected = 0;
		Assert.assertEquals(expected, actual);
	}

}
