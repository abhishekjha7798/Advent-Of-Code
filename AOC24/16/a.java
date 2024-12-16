import java.io.*;
import java.util.*;

public class a {
    static List<List<Character>> grid = new ArrayList<>();
    static int sx = -1, sy = -1;
    static int ex = -1, ey = -1;


    static int minCost = Integer.MAX_VALUE;
    static int[][][] dp = new int[200][200][4];
    public static int getDirection(char dir) {
        if (dir == 'U') {
            return 0;
        } else if (dir == 'D') {
            return 1;
        } else if (dir == 'L') {
            return 2;
        } else {
            return 3;
        }
    }
    /** Sample Input - # means wall, . means empty space, S means start, E means end
    ###############
    #.......#....E#
    #.#.###.#.###.#
    #.....#.#...#.#
    #.###.#####.#.#
    #.#.#.......#.#
    #.#.#####.###.#
    #...........#.#
    ###.#.#####.#.#
    #...#.....#.#.#
    #.#.#.###.#.#.#
    #.....#...#.#.#
    #.###.#.#.#.#.#
    #S..#.....#...#
    ###############
    */
    // depth first search - minimise the cost, every step costs 1, every turn costs 1000
    public static void dfs(int x, int y, char dir, int currentCost) {
        if (x < 0 || x >= grid.size() || y < 0 || y >= grid.get(0).size() || grid.get(x).get(y) == '#') {
            return;
        }
        if (x == ex && y == ey) {
            minCost = Math.min(minCost, currentCost);
            return;
        }
        if (currentCost >= minCost) {
            return;
        }
        if (dp[x][y][getDirection(dir)] <= currentCost) {
            return;
        }
        dp[x][y][getDirection(dir)] = currentCost;
        grid.get(x).set(y, '#');
        if (dir == 'U') {
            dfs(x - 1, y, 'U', currentCost + 1);
            dfs(x, y + 1, 'R', currentCost + 1001);
            dfs(x, y - 1, 'L', currentCost + 1001);
        } else if (dir == 'D') {
            dfs(x + 1, y, 'D', currentCost + 1);
            dfs(x, y + 1, 'R', currentCost + 1001);
            dfs(x, y - 1, 'L', currentCost + 1001);
        } else if (dir == 'L') {
            dfs(x, y - 1, 'L', currentCost + 1);
            dfs(x + 1, y, 'D', currentCost + 1001);
            dfs(x - 1, y, 'U', currentCost + 1001);
        } else if (dir == 'R') {
            dfs(x, y + 1, 'R', currentCost + 1);
            dfs(x + 1, y, 'D', currentCost + 1001);
            dfs(x - 1, y, 'U', currentCost + 1001);
        }
        if (x == sx && y == sy) {
            grid.get(x).set(y, 'S');
        } else {
            grid.get(x).set(y, '.');
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        boolean flag = false;
        List<Character> sequence = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    flag = true;
                    continue;
                }
                if (!flag) {
                    int j = 0;
                    List<Character> row = new ArrayList<>();
                    for (char ch : line.toCharArray()) {
                        if (ch == 'S') {
                            sx = i;
                            sy = j;
                        }
                        if (ch == 'E') {
                            ex = i;
                            ey = j;
                        }
                        row.add(ch);
                        j++;
                    }
                    grid.add(row);
                    i++;
                } else {
                    for (char ch : line.toCharArray()) {
                        sequence.add(ch);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //// CODE HERE
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        dfs(sx, sy, 'R', 0);
        ////
        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"))) {
            out.println("Answer: " + minCost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}
