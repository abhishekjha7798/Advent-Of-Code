import java.util.*;
import java.io.*;

public class b {
    static long ans = 0;
    static List<List<Character>> grid = new ArrayList<>();
    static int sr = -1, sc = -1;
    static int er = -1, ec = -1;
    static List<List<Integer>> path = new ArrayList<>();
    static Map<Integer, Integer> map = new HashMap<>();
    static int MAX_DEPTH = 20;
    static Set<List<Integer>> ends = new HashSet<>();
    static Set<List<Integer>> vis = new HashSet<>();

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
        for (int i = 0; i < path.size(); i++) {
            List<Integer> a = path.get(i);
            for (int j = i+1; j < path.size(); j++) {
                List<Integer> b = path.get(j);
                int dist = j - i;
                int jumpDist = Math.abs(a.get(0) - b.get(0)) + Math.abs(a.get(1) - b.get(1));
                if (jumpDist <= 20) {
                    int diff = dist-jumpDist;
                    map.put(diff, map.getOrDefault(diff, 0) + 1);
                }
            }
        }
        for (int key: map.keySet()) {
            if (key >= 100) {
                ans += map.get(key);
            }
        }
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
