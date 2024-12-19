import java.util.*;
import java.io.*;

public class a {
    static int ans = 0;
    static int R = 71, C = 71;
    static int bytes = 1024;
    static int[][] grid = new int[R][C];
    static List<Integer> rowValues = new ArrayList<>();
    static List<Integer> colValues = new ArrayList<>();

    static int dijkstra(int src, int dest) {
        int[] dist = new int[R * C];
        boolean[] visited = new boolean[R * C];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < R * C; i++) {
            int u = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < R * C; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }
            if (u == -1) {
                break;
            }
            visited[u] = true;
            int x = u / C;
            int y = u % C;
            if (x > 0 && grid[x - 1][y] == 0) {
                if (dist[u - C] > dist[u] + 1) {
                    dist[u - C] = dist[u] + 1;
                }
            }
            if (x < R - 1 && grid[x + 1][y] == 0) {
                if (dist[u + C] > dist[u] + 1) {
                    dist[u + C] = dist[u] + 1;
                }
            }
            if (y > 0 && grid[x][y - 1] == 0) {
                if (dist[u - 1] > dist[u] + 1) {
                    dist[u - 1] = dist[u] + 1;
                }
            }
            if (y < C - 1 && grid[x][y + 1] == 0) {
                if (dist[u + 1] > dist[u] + 1) {
                    dist[u + 1] = dist[u] + 1;
                }
            }
        }
        return dist[dest];
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(",");
                int cc = Integer.parseInt(parts[0]);
                int cr = Integer.parseInt(parts[1]);
                rowValues.add(cr);
                colValues.add(cc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /// CODE HERE
        for (int i = 0; i < bytes; i++) {
            int cr = rowValues.get(i);
            int cc = colValues.get(i);
            grid[cr][cc] = 1;
        }

        ans = dijkstra(0, R * C - 1);
        /// 
        try (PrintWriter pr = new PrintWriter("output.txt")) {
            pr.println("Answer: " + ans);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}
