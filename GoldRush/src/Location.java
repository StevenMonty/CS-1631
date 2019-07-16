/********
 * 
 * The Location class stores the name of the city and it's max gold and silver.
 * 
 ********/

public class Location {

	private String name;
	private int MAX_GOLD;
	private int MAX_SILVER;

	public Location(String name, int MAX_SILVER, int MAX_GOLD) {
		this.name = name;
		this.MAX_SILVER = MAX_SILVER;
		this.MAX_GOLD = MAX_GOLD;
	}

	/********* GETTERS **********/
	public String getName() {
		return this.name;
	}

	public int getMAX_GOLD() {
		return this.MAX_GOLD;
	}

	public int getMAX_SILVER() {
		return this.MAX_SILVER;
	}

}