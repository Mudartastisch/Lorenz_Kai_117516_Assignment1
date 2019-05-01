package main;

import java.util.Scanner;

public class Interface {
	static Scanner scn = new Scanner(System.in);

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
		System.out.println(graph.toString());
	}

	public static void printCities(Graph graph) {
		System.out.println(graph.citiesToString());
	}

	public static void help() {
		System.out.println("Usage: ");
		System.out.println("help --- print usage");
		System.out.println("add city 'cityName' ---- Add a new City");
		System.out.println(
				"add connection 'cityName1' 'cityName2' 'distance' ---- Add a new connection between existing cities");
		System.out.println("remove connection 'connectionName' ---- remove a connection");
		System.out.println("remove city 'cityName' ---- remove a city and it's connection");
		System.out.println("clear ---- remove all cities and connections");
		System.out.println("print graph ---- show all connections in the graph");
		System.out.println("print cities ---- show all cities in your session");
		System.out.println("exit ---- end the programm");
		System.out.println("\n");
	}

	private static void parse(String line, Graph graph) {
		String[] parsing = line.split(" ");
		for (String str : parsing) {
			System.out.println(str);
		}
		switch (parsing[0]) {
		case "add":
			System.out.println("Debug: case add");
			switch (parsing[1]) {
			case "city":
				System.out.println("Debug: case add city");
				graph.addCity(parsing[2]);
				System.out.println("added City");
				break;

			case "connection":
				System.out.println("Debug: case add connection");
				if (!graph.findCity(parsing[2]).equals(null) && !graph.findCity(parsing[3]).equals(null)) {
					graph.addConnection(new Edge(graph.findCity(parsing[2]), graph.findCity(parsing[3]),
							Integer.parseInt(parsing[4])));
					System.out.println("added connection");
				} else {
					System.err.println("invalid parameters");
				}
				break;

			default:
				System.out.println("Debug: case add default");
				System.out.println("invalid add command structure");
			}
			break;

		case "remove":
			System.out.println("Debug: case remove");
			switch (parsing[1]) {
			case "connection":
				System.out.println("Debug: case remove connection");
				if (graph.removeConnection(parsing[2])) {
					System.out.println("done");
				} else {
					System.out.println("No connection matched you input");
				}
				break;

			case "city":
				System.out.println("Debug: case remove city");
				if (graph.removeCity(graph.findCity(parsing[2]))) {
					graph.getCities().remove(graph.findCity(parsing[2]));
					System.out.println("removed all connections and city it self " + parsing[2]);
				} else {
					System.out.println("No city matched your input");
				}
				break;

			default:
				System.out.println("Debug: case remove default");
				System.out.println("invalid remove command structure");
				break;
			}
			break;
		case "clear":
			System.out.println("Debug: case clear");
			graph.clear();
			System.out.println("removed all cities and connections");
			break;

		case "print":
			System.out.println("Debug: case print");
			switch (parsing[1]) {
			case "graph":
				System.out.println("Debug: case print graph");
				printGraph(graph);
				break;

			case "cities":
				System.out.println("Debug: case print city");
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
