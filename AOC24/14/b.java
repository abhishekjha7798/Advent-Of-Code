import java.io.*;
import java.util.*;

public class b {
    static int ans = 0;
    static int r = 101, c = 103;
    static int sz = 0, tmpSz = 0;
    static int[][] grid = new int[r][c];
    static boolean[][] visited = new boolean[r][c];
    static List<List<Integer>> points = new ArrayList<>();
    public static void solve(int x, int y, int vx, int vy, int r, int c) {
        int x1 = ((x + vx) % r + r)%r;
        int y1 = ((y + vy) % c + c)%c;
        grid[x1][y1] = 1;
    }
    public static void countMaxSizeOfConnectedComponent(int[][] grid, int i, int j, int r, int c) {
        if (i < 0 || i >= r || j < 0 || j >= c || grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        tmpSz++;
        sz = Math.max(sz, tmpSz);
        countMaxSizeOfConnectedComponent(grid, i + 1, j, r, c);
        countMaxSizeOfConnectedComponent(grid, i - 1, j, r, c);
        countMaxSizeOfConnectedComponent(grid, i, j + 1, r, c);
        countMaxSizeOfConnectedComponent(grid, i, j - 1, r, c);
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] metrics = line.split(" ");
                String[] pos = metrics[0].split("=")[1].split(",");
                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);
                String[] velocity = metrics[1].split("=")[1].split(",");
                int vx = Integer.parseInt(velocity[0]);
                int vy = Integer.parseInt(velocity[1]);
                List<Integer> point = new ArrayList<>();
                point.add(x);
                point.add(y);
                point.add(vx);
                point.add(vy);
                points.add(point);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //// CODE HERE
        int k = 0;
        while (true) {
            for (int i = 0; i < points.size(); i++) {
                List<Integer> point = points.get(i);
                int x = point.get(0);
                int y = point.get(1);
                int vx = point.get(2);
                int vy = point.get(3);
                solve(x, y, (k+1)*vx, (k+1)*vy, r, c);
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (grid[i][j] == 1) {
                        countMaxSizeOfConnectedComponent(grid, i, j, r, c);
                        tmpSz = 0;
                    }
                }
            }
            // printing the grid in the hope of finding christmas tree
            if (sz >= 50) {
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        System.out.print(grid[i][j]);
                    }
                    System.out.println();
                }
                if (ans == 0) {
                    ans = k+1;
                }
            }
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    grid[i][j] = 0;
                    visited[i][j] = false;
                }
            }
            sz = 0;
            if (k++ >= 10000) {
                break;
            }
        }
        ////
        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"))) {
            out.println("Answer: " + ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}
