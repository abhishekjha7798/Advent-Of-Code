import java.util.*;
import java.io.*;
public class b {
    private static boolean[][] visited = new boolean[1000][1000];

    private static void dfs(int i, int j, List<List<Character>> grid, int[] areaPerimeter, char crop) {
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid.get(0).size() || visited[i][j]
         || grid.get(i).get(j) != crop) {
            return;
        }
        visited[i][j] = true;
        areaPerimeter[0]++;
        if (i==0 || grid.get(i-1).get(j) != grid.get(i).get(j)) {
            if (j==0 || grid.get(i).get(j-1) != crop) {
                areaPerimeter[1]++;
            } else {
                if (i > 0 && grid.get(i-1).get(j-1) == crop) {
                    areaPerimeter[1]++;
                }
            }
        }
        if (i==grid.size()-1 || grid.get(i+1).get(j) != grid.get(i).get(j)) {
            if (j==grid.get(0).size()-1 || grid.get(i).get(j+1) != crop) {
                areaPerimeter[1]++;
            } else {
                if (i < grid.size()-1 && grid.get(i+1).get(j+1) == crop) {
                    areaPerimeter[1]++;
                }
            }
        }
        if (j==0 || grid.get(i).get(j-1) != grid.get(i).get(j)) {
            if (i==0 || grid.get(i-1).get(j) != crop) {
                areaPerimeter[1]++;
            } else {
                if (j > 0 && grid.get(i-1).get(j-1) == crop) {
                    areaPerimeter[1]++;
                }
            }
        }
        if (j==grid.get(0).size()-1 || grid.get(i).get(j+1) != grid.get(i).get(j)) {
            if (i==grid.size()-1 || grid.get(i+1).get(j) != crop) {
                areaPerimeter[1]++;
            } else {
                if (j < grid.get(0).size()-1 && grid.get(i+1).get(j+1) == crop) {
                    areaPerimeter[1]++;
                }
            }
        }
        dfs(i+1, j, grid, areaPerimeter, crop);
        dfs(i-1, j, grid, areaPerimeter, crop);
        dfs(i, j+1, grid, areaPerimeter, crop);
        dfs(i, j-1, grid, areaPerimeter, crop);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<List<Character>> grid = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> l = new ArrayList<>();
                for (char c:line.toCharArray()) {
                    l.add(c);
                }
                grid.add(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //// CODE HERE
        int ans = 0;
        for (int i=0; i < grid.size(); i++) {
            List<Character> l = grid.get(i);
            for (int j=0; j < l.size(); j++) {
                if (!visited[i][j]) {
                    int areaPerimeter[] = new int[2];
                    dfs(i, j, grid, areaPerimeter, grid.get(i).get(j));
                    ans += areaPerimeter[0] * areaPerimeter[1];
                    // System.out.println("Area: " + areaPerimeter[0] + " Perimeter: " + areaPerimeter[1]);
                    areaPerimeter[0] = 0;
                    areaPerimeter[1] = 0;
                }
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
