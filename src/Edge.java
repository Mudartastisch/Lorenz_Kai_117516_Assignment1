public class Edge {
	private CityNode origin;
	private CityNode destination;
	private String name;
	private int distance;

	public Edge(CityNode origion, CityNode destination, int distance) {
		this.origin = origion;
		this.destination = destination;
		this.name = origin.getName() + destination.getName();
		this.distance = distance;
	}

	public CityNode getOrigin() {
		return origin;
	}

	public CityNode getDestination() {
		return destination;
	}

	public String getName() {
		return name;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
