import java.util.ArrayList;

public class Graph {
	private ArrayList<Edge> connections = new ArrayList<Edge>();
	private static ArrayList<CityNode> cities = new ArrayList<CityNode>();

	public Graph() {
	}

	public void addConnection(Edge connection) {
		if (!connectionExists(connection)) {
			connections.add(connection);
		} else {
			System.err.println("Can't add a second connection between " + connection.getOrigin().getName() + " and "
					+ connection.getDestination().getName());
		}
	}
	// a utility class to match a string to an object
	public CityNode findCity(String city) {
		for (CityNode ct : getCities()) {
			if (city.matches(ct.getName())) {
				return ct;
			}
		}
		return null;
	}
	
	// adding a node by a String, making sure it does not already exists
	public void addCity(String name) {
		if (findCity(name) == null) {
			getCities().add(new CityNode(name));
		} else {
			System.err.println("City already exists");
		}
	}

	//Java 8 method to remove Objects from an ArrayList while iterating over it
	public boolean removeCity(CityNode city) {
		connections.removeIf(con -> (con.getOrigin().equals(city) || con.getDestination().equals(city)));
		return true;
	}

	public void clear() {
		connections.clear();
		cities.clear();
	}

	public String graphToString() {
		StringBuilder str = new StringBuilder();
		str.append("\nGraph connections:\n");
		for (Edge con : connections) {
			str.append(con.getOrigin().getName());
			str.append(" to ");
			str.append(con.getDestination().getName());
			str.append(" with a distance of ");
			str.append(con.getDistance());
			str.append(" km (context assumed) name: " + con.getName() + "\n");
		}
		return str.toString();
	}

	public String citiesToString() {
		StringBuilder str = new StringBuilder();
		str.append("\nAvailible cities:\n");
		for (CityNode ct : getCities()) {
			str.append(ct.getName() + "\n");
		}
		str.append("\n");
		return str.toString();
	}

	public void updateDistance(Edge connection, int distance) {
		try {
			connections.get(connections.indexOf(connection)).setDistance(distance);
		} catch (Exception e) {
			System.err.println("This connection does not exist yet");
			e.printStackTrace();
		}

	}

	public boolean connectionExists(Edge connection) {
		for (Edge con : connections) {
			if (con.getOrigin().equals(connection.getOrigin())
					&& con.getDestination().equals(connection.getDestination())) {
				return true;
			}
		}
		return false;
	}
	
	public Edge getConnection(String name) {
		for (Edge con : connections) {
			if (con.getName().equals(name)) {				
				return con;
			}
		}
		return null;
	}

	public boolean removeConnection(String name) {
		for (Edge con : connections) {
			if (con.getName().equals(name)) {
				System.out.println("Found connection, removing it");
				connections.remove(con);
				return true;
			}
		}
		return false;
	}

	public ArrayList<CityNode> getCities() {
		return cities;
	}

}
