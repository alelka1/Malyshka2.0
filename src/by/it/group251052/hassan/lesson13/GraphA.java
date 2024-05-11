package by.it.group251052.hassan.lesson13;

import java.util.*;


public class GraphA {
    static Set<String> visitedNodes;
    static Map<String, ArrayList<String>> graphNodes;
    static Stack<String> topologicalOrder;


    public static void main(String[] args) {
        visitedNodes = new HashSet<>();
        graphNodes = new HashMap<>();
        topologicalOrder = new Stack<>();

        Scanner scanner = new Scanner(System.in);
        boolean finished = false;

        while (!finished) {
            String source = scanner.next();
            String arrow = scanner.next(); // Ignore the arrow "->"
            String destination = scanner.next();

            // Check if the input is finished
            if (destination.charAt(destination.length() - 1) == ',') {
                destination = destination.substring(0, destination.length() - 1);
            } else {
                finished = true;
            }

            // Add the edge to the graph
            if (!graphNodes.containsKey(source)) {
                graphNodes.put(source, new ArrayList<>());
            }
            graphNodes.get(source).add(destination);
        }

        // Sort the adjacency lists in reverse order
        for (ArrayList<String> neighbors : graphNodes.values()) {
            neighbors.sort(Comparator.reverseOrder());
        }

        // Perform DFS to find the topological order
        for (String node : graphNodes.keySet()) {
            if (!visitedNodes.contains(node)) {
                DFS(node);
            }
        }

        // Print the topological order
        while (!topologicalOrder.isEmpty()) {
            System.out.print(topologicalOrder.pop() + " ");
        }

        scanner.close();
    }

    private static void DFS(String node) {
        visitedNodes.add(node);
        ArrayList<String> neighbors = graphNodes.get(node);
        if (neighbors != null) {
            for (String neighbor : neighbors) {
                if (!visitedNodes.contains(neighbor)) {
                    DFS(neighbor);
                }
            }
        }
        topologicalOrder.push(node);
    }
}


