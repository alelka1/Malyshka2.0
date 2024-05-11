package by.it.group251052.hassan.lesson13;

import java.util.*;


public class GraphC {

    public static void main(String[] args) {
        Map<String, List<String>> adjacencyList = new HashMap<>();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        Scanner scanner = new Scanner(System.in);
        String[] inputValues = scanner.nextLine().split(",");
        scanner.close();

        buildAdjacencyList(inputValues, adjacencyList);

        topologicalSort(adjacencyList, stack, visited);

        Map<String, List<String>> transposedAdjacencyList = transposeGraph(adjacencyList);

        printConnectedComponents(stack, visited, transposedAdjacencyList);
    }

    public static class LexicalComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }
    private static void buildAdjacencyList(String[] inputValues, Map<String, List<String>> adjacencyList) {
        for (String input : inputValues) {
            String[] vertices = input.trim().split("");
            String startVertex = vertices[0];
            String endVertex = vertices[vertices.length - 1];

            adjacencyList.computeIfAbsent(startVertex, k -> new ArrayList<>()).add(endVertex);
        }

        adjacencyList.forEach((vertex, neighbours) -> neighbours.sort(new LexicalComparator()));
    }

    private static void topologicalSort(Map<String, List<String>> adjacencyList, Stack<String> stack, Set<String> visited) {
        for (String vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                depthFirstSearch(adjacencyList, vertex, visited, stack);
            }
        }
    }

    private static void depthFirstSearch(Map<String, List<String>> adjacencyList,
                                         String vertex, Set<String> visited, Stack<String> stack) {
        visited.add(vertex);
        if (adjacencyList.containsKey(vertex)) {
            for (String child : adjacencyList.get(vertex)) {
                if (!visited.contains(child)) {
                    depthFirstSearch(adjacencyList, child, visited, stack);
                }
            }
        }
        stack.push(vertex);
    }

    private static Map<String, List<String>> transposeGraph(Map<String, List<String>> adjacencyList) {
        Map<String, List<String>> transposedAdjacencyList = new HashMap<>();
        for (String vertex : adjacencyList.keySet()) {
            for (String child : adjacencyList.get(vertex)) {
                transposedAdjacencyList.computeIfAbsent(child, k -> new ArrayList<>()).add(vertex);
            }
        }
        return transposedAdjacencyList;
    }

    private static void printConnectedComponents(Stack<String> stack, Set<String> visited, Map<String, List<String>> transposedAdjacencyList) {
        visited.clear();
        while (!stack.empty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                StringBuilder connectedComponent = new StringBuilder();
                depthFirstSearchTransposed(transposedAdjacencyList, vertex, visited, connectedComponent);
                char[] charArray = connectedComponent.toString().toCharArray();
                Arrays.sort(charArray);
                System.out.println(new String(charArray));
            }
        }
    }

    private static void depthFirstSearchTransposed(Map<String, List<String>> transposedAdjacencyList,
                                                   String vertex, Set<String> visited, StringBuilder connectedComponent) {
        visited.add(vertex);
        connectedComponent.append(vertex);
        if (transposedAdjacencyList.containsKey(vertex)) {
            for (String child : transposedAdjacencyList.get(vertex)) {
                if (!visited.contains(child)) {
                    depthFirstSearchTransposed(transposedAdjacencyList, child, visited, connectedComponent);
                }
            }
        }
    }
}

