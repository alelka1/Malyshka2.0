package by.it.group251052.hassan.lesson13;

import java.util.*;

public class GraphB {
    private Map<String, List<String>> adjacencyList;

    public GraphB(String input) {
        adjacencyList = new HashMap<>();
        buildGraph(input);
    }

    private void buildGraph(String input) {
        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] nodes = edge.split(" -> ");
            String from = nodes[0];
            String to = nodes[1];
            adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        }
    }

    private boolean hasCycle() {
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        for (String vertex : adjacencyList.keySet()) {
            if (hasCycleUtil(vertex, visited, recursionStack))
                return true;
        }
        return false;
    }

    private boolean hasCycleUtil(String vertex, Set<String> visited, Set<String> recursionStack) {
        if (recursionStack.contains(vertex))
            return true;

        if (!visited.contains(vertex)) {
            visited.add(vertex);
            recursionStack.add(vertex);

            List<String> neighbors = adjacencyList.get(vertex);
            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (hasCycleUtil(neighbor, visited, recursionStack))
                        return true;
                }
            }

            recursionStack.remove(vertex);
        }

        return false;
    }

    public String hasCyclePhrase() {
        return hasCycle() ? "yes" : "no";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        GraphB graph = new GraphB(input);
        System.out.println(graph.hasCyclePhrase());
        scanner.close();
    }
}
