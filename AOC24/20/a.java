import java.util.*;
import java.io.*;

public class a {
    static long ans = 0;
    static List<List<Character>> grid = new ArrayList<>();
    static int sr = -1, sc = -1;
    static int er = -1, ec = -1;
    static List<List<Integer>> path = new ArrayList<>();

    static void constructPath(int[][][] parent) {
        int x = er;
        int y = ec;
        while (x != sr || y != sc) {
            path.add(Arrays.asList(x, y));
            int[] p = parent[x][y];
            x = p[0];
            y = p[1];
        }
        path.add(Arrays.asList(sr, sc));
        Collections.reverse(path);
    }

    // trace the shortest path and store it in path list
    static void dijkstra() {
        int n = grid.size();
        int m = grid.get(0).size();
        int[][] dist = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        int[][][] parent = new int[n][m][2];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[sr][sc] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{sr, sc, 0});
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int x = current[0];
            int y = current[1];
            int d = current[2];

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;

            if (x == er && y == ec) {
                constructPath(parent);
                return;
            }

            for (int[] dir: directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || grid.get(nx).get(ny) == '#') {
                    continue;
                }
                if (d + 1 < dist[nx][ny]) {
                    dist[nx][ny] = dist[x][y] + 1;
                    pq.add(new int[]{nx, ny, dist[nx][ny]});
                    parent[nx][ny] = new int[]{x, y};
                }
            }
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                grid.add(new ArrayList<>());
                char[] arr = line.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    grid.get(i).add(arr[j]);
                    if (arr[j] == 'S') {
                        sr = i;
                        sc = j;
                    }
                    if (arr[j] == 'E') {
                        er = i;
                        ec = j;
                    }
                }
                i++; 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /// CODE HERE
        dijkstra();
        // // System.out.println("Path: " + path);
        // int total = 0;
        for (List<Integer> p: path) {
            int x = p.get(0);
            int y = p.get(1);
            int[][] directions = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
            int[][] directions2 = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int i = 0; i < 4; i++) {
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];
                if (nx < 0 || nx >= grid.size() || ny < 0 || ny >= grid.get(0).size() || grid.get(nx).get(ny) == '#') {
                    continue;
                }
                int mx = x + directions2[i][0];
                int my = y + directions2[i][1];
                if (grid.get(mx).get(my) != '#') {
                    continue;
                }
                // find (nx, ny) in path, if exists, then get the index and store the reduction in path
                int idx = path.indexOf(Arrays.asList(nx, ny));
                if (idx != -1) {
                    int diff = Math.abs(idx - path.indexOf(p)) - 2;
                    if (diff >= 100) {
                        // System.out.println("Reduction: " + p + " -> " + path.get(idx) + " = " + diff);
                        ans++;
                    }
                }
            }
        }
        ans /= 2;
        // System.out.println("Steps: " + steps);
        // System.out.println("Total: " + total/2);
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
