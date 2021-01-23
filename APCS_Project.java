/*

Author: Paul Gutkovich

Date: 1/22/21

Usage: 'java apcs_project.java', then follow the prompts

In the graph contructor, you must first enter the number of vertices, and then there is a loop to generate edges.
You must input the first and second vertices, and the edge weight (if it is a regular graph, in Unweighted_Graph all weights are equal to 1).
After the graph is constructed, you can run dijkstra's algorithm to find the shortest path between start and end vertices
(you must input both) and, if it is an unweighted graph, the num_paths algorithm, which calculates the number of ways to get
from one vertex to another in a given number of steps.

Knowledge demonstrated:

This project demonstrates classes, ArrayLists, static variables, class methods, and class inheritance.

Description:

This is a program that creates graphs based on user input and runs dijkstra's algorithm and a path-counting algorithm

Graph class is a regular graph.

The constructor uses user input from the command prompt to build the edge matrix of the graph.

This classes fields are 'num_graphs', 'num_vertices', and 'edges'.

- 'num_graphs' is a static variable representing the number of graph instances. It increases by 1 every time a new graph instance is created.
- 'num_vertices' is an integer representing the number of vertices the graph has.
- 'edges' is a two dimensional arraylist that represents the edge matrix of the graph.

The class methods are 'contains', 'print_edge', 'dijkstra', and 'dijkstra_once'.

- 'contains' is a helper function that tells if a given arraylist contains a given element
- 'print_edge' prints the edge matrix into the console
- 'dijkstra' is a loop that gets user input and then runs dijkstra_once based on it
- 'dijkstra_once' is an implementation of dijkstra's algorithm

Unweighted_Graph inherits Graph. It is a graph where the edges have no weights (all weights are equal to 1)

Its constructor is the Graph constructor but it uses varargs to indicate that the graph will be unweighted.

This class does not contain any new fields.

This classes methods are 'row', 'column', 'dot', 'prod', 'set_mat', 'num_paths', 'num_paths_once'

- 'row' gets a given row of a given matrix (2d arraylist)
- 'column' gets a given column of a given matrix
- 'dot' computes the dot product of two vectors (1d arraylist)
- 'prod' computes the matri product of two matrices
- 'num_paths' is a loop that gets user input and then runs num_paths_once based on it
- 'num_paths_once' is an algorithm that finds the number of paths from one vertex to another in a given number of steps

*/

import java.util.*;

class Main {
    public static void main(String[] args) {
        Unweighted_Graph g1 = new Unweighted_Graph();
        g1.num_paths();
    }
}

// Creates graph with vertices and weighted edges, at most 1 edge between vertices

class Graph {
	public static int num_graphs;

	public int num_vertices;
	public ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

	// Constructor creates graph based on user input

	public Graph(int ...w) {
		int v1;
		int v2;
		int l;
		String input;

		num_graphs = num_graphs + 1;

		System.out.println("Enter the number of vertices: ");
		Scanner s = new Scanner(System.in);

		// Number of vertices of graph

		input = s.nextLine();
		this.num_vertices = Integer.parseInt(input);

		// Creates empty edge matrix

		for (int i = 0; i < this.num_vertices; i++){
			ArrayList<Integer> row = new ArrayList<Integer>();

			for (int j = 0; j < this.num_vertices; j++){
				row.add(0);
			}
			this.edges.add(row);
		}

		// Adds edges through user input

		while (true) {
			System.out.println("\nEnter first vertex (enter 0 to end): ");
			input = s.nextLine();
			v1 = Integer.parseInt(input);

			if (v1 == 0) {
				break;
			}

			if (v1 < 1 || v1 > this.num_vertices) {
				System.out.println("\nVertex must be between 1 and " + num_vertices);
				continue;
			}

			System.out.println("\nEnter second vertex: ");
			input = s.nextLine();
			v2 = Integer.parseInt(input);

			if (v2 < 1 || v2 > this.num_vertices) {
				System.out.println("\nVertex must be between 1 and " + num_vertices);
				continue;
			}

			if (v1 == v2) {
				System.out.println("\nThe vertices must be different");
				continue;
			}

			if (w.length == 0) {
				System.out.println("\nEnter edge length (positive integer less than 1000000000): ");
				input = s.nextLine();
				l = Integer.parseInt(input);

				if (l < 1) {
					System.out.println("\nMust be positive integer");
					continue;
				}

				if (l > 1000000000) {
					System.out.println("\nMust be less than 1000000000");
					continue;
				}
			}
			else {
				l = 1;
			}

			this.edges.get(v1-1).set(v2-1, l);
			this.edges.get(v2-1).set(v1-1, l);

			System.out.println("\nThis is the current edge matrix: ");
			this.print_edge();
		}
	}

	// Prints current edge matrix

	public void print_edge() {
		for (int i = 0; i < this.num_vertices; i++){
			for (int j = 0; j < this.num_vertices; j++){
				System.out.printf(this.edges.get(i).get(j)+" ");
			}
			System.out.println("");
		}
	}

	// Dijkstra loop that gets parameters through input and runs dijkstra's algorithm

	public void dijkstra() {
		int start;
		int end;
		String input;

		while (true){
			Scanner s = new Scanner(System.in);

			System.out.println("\nDijkstra's Algorithm");

			System.out.println("\nEnter starting vertex (enter 0 to end): ");
			input = s.nextLine();
			start = Integer.parseInt(input);

			if (start == 0) {
				break;
			}

			if (start < 1 || start > this.num_vertices) {
				System.out.println("\nVertex must be between 1 and " + num_vertices);
				continue;
			}

			System.out.println("\nEnter ending vertex: ");
			input = s.nextLine();
			end = Integer.parseInt(input);

			if (end < 1 || end > this.num_vertices) {
				System.out.println("\nVertex must be between 1 and " + num_vertices);
				continue;
			}

			if (start == end) {
				System.out.println("\nStart and end vertices must be different");
				continue;
			}

			dijkstra_once(start, end);
		}
	}

	// Single step of dijkstra's algorithm

	public void dijkstra_once(int start, int end) {
		int[] unvisited = new int[this.num_vertices];
		int[] distances = new int[this.num_vertices];
		int[] previous = new int[this.num_vertices];
		ArrayList<Integer> path = new ArrayList<Integer>();
		int v;

		int min_dist;
		int min_v;
		int alt_dist;

		for (int i = 0; i < num_vertices; i++) {
			distances[i] = 1000000000;
			previous[i] = 0;
			unvisited[i] = 1;
		}

		distances[start-1] = 0;
		
		while (contains(unvisited, 1)) {
			min_dist = 2000000000;
			min_v = 0;

			for (int i = 0; i < num_vertices; i++) {
				if (unvisited[i] == 1) {
					if (distances[i] <= min_dist) {
						min_dist = distances[i];
						min_v = i;
					}
				}
			}

			unvisited[min_v] = 0;

			for (int i = 0; i < num_vertices; i++) {
				if (this.edges.get(min_v).get(i) > 0){
					if (unvisited[i] == 1){
						alt_dist = distances[min_v] + this.edges.get(min_v).get(i);
						if (alt_dist < distances[i]){
							distances[i] = alt_dist;
							previous[i] = min_v;
						}
					}
				}
			}
		}
		
		v = end-1;
		path.add(0, v+1);
		while (true) {
			v = previous[v];
			path.add(0, v+1);
			if (v == start-1) {
				break;
			}
		}
		System.out.println(path);
		System.out.println(distances[end-1]);
	}

	// Function the check if array contains certain element

	public boolean contains(int[] int_array, int int_element) {
		for (int i : int_array) {
			if (i == int_element) {
				return true;
			}
		}

		return false;
	}
}

// Unweighted Graph, only possible edge length is 1

class Unweighted_Graph extends Graph {

	public Unweighted_Graph() {
		super(1);
	}

	// Get row of matrix

	public ArrayList<Integer> row(ArrayList<ArrayList<Integer>> mat, int r) {
		return mat.get(r);
	}

	// Get column of matrix

	public ArrayList<Integer> column(ArrayList<ArrayList<Integer>> mat, int c) {
		ArrayList<Integer> column = new ArrayList<Integer>();
		for (int i = 0; i < mat.size(); i++) {
			column.add(mat.get(i).get(c));
		}
		return column;
	}

	// Dot product of two vectors

	public int dot(ArrayList<Integer> vector1, ArrayList<Integer> vector2) {
		int d = 0;
		for (int i = 0; i < vector1.size(); i++) {
			d += vector1.get(i)*vector2.get(i);
		}
		return d;
	}

	// Product of two matrices

	public ArrayList<ArrayList<Integer>> prod(ArrayList<ArrayList<Integer>> mat1, ArrayList<ArrayList<Integer>> mat2) {
		int s = mat1.size();

		ArrayList<ArrayList<Integer>> product = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < s; i++) {
			ArrayList<Integer> new_row = new ArrayList<Integer>();

			for (int j = 0; j < this.num_vertices; j++){
				new_row.add(0);
			}
			product.add(new_row);
		}

		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				product.get(i).set(j, this.dot(row(mat1, i), column(mat2, j)));
			}
		}

		return product;
	}

	// Loops that gets input and runs num_paths_once

	public void num_paths() {
		int start;
		int end;
		int steps;
		String input;

		while (true){
			Scanner s = new Scanner(System.in);

			System.out.println("\nNum_Paths Algorithm");

			System.out.println("\nEnter starting vertex (enter 0 to end): ");
			input = s.nextLine();
			start = Integer.parseInt(input);

			if (start == 0) {
				break;
			}

			if (start < 1 || start > this.num_vertices) {
				System.out.println("\nVertex must be between 1 and " + num_vertices);
				continue;
			}

			System.out.println("\nEnter ending vertex: ");
			input = s.nextLine();
			end = Integer.parseInt(input);

			if (end < 1 || end > this.num_vertices) {
				System.out.println("\nVertex must be between 1 and " + num_vertices);
				continue;
			}

			if (start == end) {
				System.out.println("\nStart and end vertices must be different");
				continue;
			}

			System.out.println("\nEnter number of steps (positive integer below 20): ");
			input = s.nextLine();
			steps = Integer.parseInt(input);

			if (steps < 1) {
				System.out.println("\nMust be positive integer");
				continue;
			}

			if (steps > 20) {
				System.out.println("\nMust be below 20");
				continue;
			}


			System.out.println("\nNumber of paths: " + this.num_paths_once(start, end, steps));
		}
	}

	// Finds the number of ways to get from start to end in "steps" steps

	public int num_paths_once(int start, int end, int steps) {
		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < this.edges.size(); i++) {
			paths.add(this.edges.get(i));
		}

		for (int i = 0; i < steps-1; i++) {
			this.set_mat(paths, this.prod(paths, this.edges));
		}

		return paths.get(start-1).get(end-1);
	}

	// Function to set values of one matrix to values of another
	
	public void set_mat(ArrayList<ArrayList<Integer>> mat1, ArrayList<ArrayList<Integer>> mat2) {
		int s1 = mat1.size();

		for (int i = 0; i < s1; i++) {
			mat1.set(i, mat2.get(i));
		}
	}
}