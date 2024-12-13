import java.util.*;
import java.io.*;
public class a {
    static long ans = 0;
    static boolean found = false;

    private static void solve(List<Long> list, Long curr, int i, long value) {
        if (curr > value) {
            return;
        }
        if (i == list.size()) {
            if (curr == value && !found) {
                found = true;
                ans+=value;
            }
            return;
        }
        if (!found) {
            solve(list, curr * list.get(i), i+1, value);
            solve(list, curr + list.get(i), i+1, value);
            solve(list, Long.parseLong(curr.toString() + list.get(i).toString()), i+1, value);
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                List<Long> list = new ArrayList<>();
                for (String part:parts[1].trim().split(" ")) {
                    list.add(Long.parseLong(part));
                }
                solve(list, list.get(0), 1, Long.parseLong(parts[0]));
                found = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //// CODE HERE
        
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
