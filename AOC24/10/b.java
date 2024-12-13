import java.io.*;
import java.util.*;
public class b {
    // public static boolean visited[][] = new boolean[100][100];
    static int ans = 0;

    public static void dfs(List<List<Character>> list, int i, int j, int prev) {
        if (i < 0 || i >= list.size() || j < 0 || j >= list.get(0).size()
         || list.get(i).get(j) == '.' || prev + 1 != list.get(i).get(j)) {
            return;
        }
        // visited[i][j] = true;
        if (list.get(i).get(j) == '9') {
            ans++;
        }
        dfs(list, i+1, j, list.get(i).get(j));
        dfs(list, i-1, j, list.get(i).get(j));
        dfs(list, i, j+1, list.get(i).get(j));
        dfs(list, i, j-1, list.get(i).get(j));
    }
    public static void main(String[] args) {
        //////////////////////////////////////////////////////////////////
        long startTime = System.currentTimeMillis();
        //////////////////////////////////////////////////////////////////
        List<List<Character>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> l = new ArrayList<>();
                char[] arr = line.toCharArray();
                for (int i=0; i < arr.length; i++) {
                    l.add(arr[i]);
                }
                list.add(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /// code here
        for (int i=0; i<list.size(); i++) {
            for (int j=0; j<list.get(0).size(); j++) {
                if (list.get(i).get(j) == '0') {
                    dfs(list, i, j, '0' - 1);
                    // for (int k = 0; k < visited.length; k++) {
                    //     Arrays.fill(visited[k], false);
                    // }
                }
            }
        }
        ///

        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"))) {
            out.println("Answer: " + ans);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis(); // End time
        long executionTime = endTime - startTime; // Calculate execution time

        System.out.println("Execution time: " + executionTime + " milliseconds");
    }
}
