package main;

public class Edge {
	private CityNode origin;	
	private CityNode destination;
	private int distance;
	
	public Edge(CityNode origion, CityNode destination, int distance) {
		this.origin = origion;
		this.destination = destination;
		this.distance = distance;
	}

	public CityNode getOrigin() {
		return origin;
	}

	public void setOrigin(CityNode origin) {
		this.origin = origin;
	}

	public CityNode getDestination() {
		return destination;
	}

	public void setDestination(CityNode destination) {
		this.destination = destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
	
}
