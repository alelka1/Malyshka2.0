package by.it.group251051.korneliuk.lesson13;

import java.util.*;

public class GraphB {
    private static boolean DFS(String node, Map<String, ArrayList<String>> graph, Set<String> visited) {
        visited.add(node);
        boolean res = false;
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!res)
                    res = visited.contains(next_node) || DFS(next_node, graph, new HashSet<>(visited));
            }
        return res;
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();

        Scanner in = new Scanner(System.in);

        boolean isEnd = false;

        while (!isEnd) {
            String a = in.next();
            String s = in.next();
            String b = in.next();

            if (b.charAt(b.length() - 1) == ',')
                b = b.substring(0, s.length() - 1);
            else
                isEnd = true;

            if (!graph.containsKey(a))
                graph.put(a, new ArrayList<>());

            graph.get(a).add(b);
        }

        boolean res = false;

        for (String node : graph.keySet())
            if (!res)
                res = DFS(node, graph, new HashSet<>());


        System.out.println(!res ? "no" : "yes");

        in.close();
    }
}
