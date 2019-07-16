// Graph implementation resource: https://github.com/mihneadb/Graphs/blob/master/Graph.java

import java.util.ArrayList;

class Road {
	private String name;
	private Location v1, v2;
	
	public Road(Location v1, Location v2, String name) {
		this.v1 = v1;
		this.v2 = v2;
		this.name = name;
	}
	
	public Road(Location v1, Location v2) {
		this(v1, v2, "");
	}
	
	public Location getV1() {
		return v1;
	}
	
	public Location getV2() {
		return v2;
	}
}

public class Graph {
	private ArrayList<Location> V = new ArrayList<Location>();
	private ArrayList<Road> E = new ArrayList<Road>();

	public void addLocation(Location v) {
		V.add(v);
	}
	
	public void addRoad(Road e) {
		E.add(e);
	}
	
	public ArrayList<Location> getAdjacentVertices(Location v) {
		ArrayList<Location> adj = new ArrayList<Location>();
		for (Road e: E) {
			if (e.getV1() == v) {
				adj.add(e.getV2());
				continue;
			}
			if (e.getV2() == v) {
				adj.add(e.getV1());
				continue;
			}
		}
		return adj;
	}
}
