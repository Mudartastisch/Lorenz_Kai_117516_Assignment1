package main;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Edge> connections = new ArrayList<Edge>();
	
	public Graph() {}
	
	public void addConnection(Edge connection) {
		if(!connectionExists(connection)) {
			connections.add(connection);
		}
	}
	
	public void removeCity(CityNode city) {
		for(Edge con : connections) {
			if(con.getOrigin().equals(city) || con.getDestination().equals(city)) {
				System.out.println("Removed connection between " + con.getOrigin().getName() +
									" and " + con.getDestination().getName());
				connections.remove(con);
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Graph connections= \n");
		for(Edge con : connections) {
			str.append(con.getOrigin().getName());
			str.append(" to ");
			str.append(con.getDestination().getName());
			str.append(" with a distance of ");
			str.append(con.getDistance());
			str.append(" km (context assumed \n");
		}
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

	private boolean connectionExists(Edge connection) {
		for(Edge con : connections) {
			if(con.getOrigin().equals(connection.getOrigin()) && con.getDestination().equals(connection.getDestination())) {
				System.err.println("Can't add a second connection between " + 
									connection.getOrigin().getName() + " and " + 
									connection.getDestination().getName());
				return true;
			}
		}
		return false;
	}
	
}
