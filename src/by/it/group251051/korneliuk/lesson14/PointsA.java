package by.it.group251051.korneliuk.lesson14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PointsA {
    private static class DSU {
        private final int[] parent;
        private final int[] size;
        private DSU(int n){
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        private int find(int v) {
            if (v != parent[v])
                parent[v] = find(parent[v]);
            return parent[v];
        }
        private void union (int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;

            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }

    }

    private record Point(int x, int y, int z) { }

    private static boolean check(Point p1, Point p2, int distance) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2) + Math.pow(p2.z - p1.z, 2)) < distance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int distanceThreshold = scanner.nextInt();
        int amount_count = scanner.nextInt();

        Point[] points = new Point[amount_count];
        for (int i = 0; i < amount_count; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            points[i] = new Point(x, y, z);
        }

        DSU dsu = new DSU(amount_count);
        for (int i = 0; i < amount_count; i++)
            for (int j = i + 1; j < amount_count; j++)
                if (check(points[i], points[j], distanceThreshold))
                    dsu.union(i, j);

        boolean[] visited = new boolean[amount_count];
        List<Integer> sizes = new ArrayList<>();

        for (int i = 0; i < amount_count; i++) {
            int root = dsu.find(i);
            if (!visited[root]){
                visited[root] = true;
                sizes.add(dsu.size[root]);
            }
        }

        sizes.sort(Collections.reverseOrder());
        for (int size : sizes) {
            System.out.print(size);
            System.out.print(" ");
        }
    }
}