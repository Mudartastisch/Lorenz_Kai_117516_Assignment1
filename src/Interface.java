import java.util.Scanner;

public class Interface {
	static Scanner scn = new Scanner(System.in);
	static boolean numeric = true;

	// just some example values
	private static void generateExamples(Graph graph) {

		System.out.println("Generating City");
		graph.addCity("Erfurt");
		System.out.println("Generating City");
		graph.addCity("Weimar");
		System.out.println("Generating City");
		graph.addCity("Jena");

		try {
			graph.addConnection(new Edge(graph.findCity("Erfurt"), graph.findCity("Weimar"), 24));
			System.out.println("Generating Connection");
			graph.addConnection(new Edge(graph.findCity("Weimar"), graph.findCity("Jena"), 23));
			System.out.println("Generating Connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printGraph(Graph graph) {
		System.out.println(graph.graphToString());
	}

	public static void printCities(Graph graph) {
		System.out.println(graph.citiesToString());
	}

	public static void help() {
		System.out.println("Usage: ");
		System.out.println("help --- print usage");
		System.out.println("add city 'cityName' ---- Add a new City");
		System.out.println(
				"add connection 'cityName1' 'cityName2' 'distance(integer)' ---- Add a new connection between existing cities");
		System.out.println("remove connection 'connectionName' ---- remove a connection");
		System.out.println("remove city 'cityName' ---- remove a city and it's connection");
		System.out.println("clear ---- remove all cities and connections");
		System.out.println("update 'connectionName' 'newDistance(integer)'");
		System.out.println("print graph ---- show all connections in the graph");
		System.out.println("print cities ---- show all cities in your session");
		System.out.println("exit ---- end the programm");
		System.out.println("\n");
	}

	// this entire block is about the command parsing
	private static void parse(String line, Graph graph) {
		String[] parsing = line.split(" ");

		switch (parsing[0]) {
		case "add":
			switch (parsing[1]) {
			case "city":
				graph.addCity(parsing[2]);
				System.out.println("added City");
				break;

			case "connection":
				if (!graph.findCity(parsing[2]).equals(null) && !graph.findCity(parsing[3]).equals(null)) {
					graph.addConnection(new Edge(graph.findCity(parsing[2]), graph.findCity(parsing[3]),
							Integer.parseInt(parsing[4])));
					System.out.println("added connection");
				} else {
					System.err.println("invalid parameters");
				}
				break;

			default:
				System.out.println("invalid add command structure");
			}
			break;

		case "remove":
			switch (parsing[1]) {
			case "connection":
				if (graph.removeConnection(parsing[2])) {
					System.out.println("done");
				} else {
					System.out.println("No connection matched you input");
				}
				break;

			case "city":
				if (graph.removeCity(graph.findCity(parsing[2]))) {
					graph.getCities().remove(graph.findCity(parsing[2]));
					System.out.println("removed all connections and city it self " + parsing[2]);
				} else {
					System.out.println("No city matched your input");
				}
				break;

			default:
				System.out.println("invalid remove command structure");
				break;
			}
			break;
		case "clear":
			graph.clear();
			System.out.println("removed all cities and connections");
			break;

		case "update":
			try {
				if (graph.connectionExists(graph.getConnection(parsing[2]))) {
					try {
						@SuppressWarnings("unused")
						int num = Integer.parseInt(parsing[3]);
					} catch (NumberFormatException e) {
						numeric = false;
					}
					if (numeric) {
						graph.updateDistance(graph.getConnection(parsing[2]), Integer.parseInt(parsing[3]));
						System.out.println("done");
					}
				}
			} catch (NullPointerException e) {
				System.out.println("NullPointer");
				System.out.println("No connection matched you input");
				e.printStackTrace();
			}

		case "print":
			switch (parsing[1]) {
			case "graph":
				printGraph(graph);
				break;

			case "cities":
				printCities(graph);
				break;
			}
			break;

		case "exit":
			System.out.println("Goodby!");
			System.exit(0);

		case "help":
			help();
			break;

		default:
			System.out.println("Invalid command");
			break;

		}

	}

	public static void main(String[] args) {
		System.out.println("Generating example graph");
		Graph germany = new Graph();
		generateExamples(germany);
		System.out.println("Showing example graph");
		printGraph(germany);
		printCities(germany);
		help();
		while (true) {
			String command = scn.nextLine();
			parse(command, germany);
		}
	}

}
