import java.io.*;
import java.util.*;
public class a {
    private static Map<String, Long> map = new HashMap<>();
    private static long solve(Long x, Long turns) {
        if (turns == 0) {
            return 1;
        }
        String s = x.toString();
        if (map.containsKey(String.format("%s|%s", s, turns.toString()))) {
            return map.get(String.format("%s|%s", s, turns.toString()));
        } else {
            if (s.length()%2 == 0) {
                long n1 = Long.parseLong(s.substring(0, s.length()/2));
                long n2 = Long.parseLong(s.substring(s.length()/2));
                long sAns1 = solve(n1, turns-1);
                long sAns2 = solve(n2, turns-1);
                map.put(String.format("%s|%s", s, turns.toString()), sAns1+sAns2);
                return sAns1 + sAns2;
            } else {
                if (x == 0) {
                    long sAns1 = solve(1L, turns-1);
                    map.put(String.format("%s|%s", "0", turns.toString()), sAns1);
                    return sAns1;
                } else {
                    long sAns1 = solve(x*2024, turns-1);
                    map.put(String.format("%s|%s", x, turns.toString()), sAns1);
                    return sAns1;
                }
            }
        }
    }
    public static void main(String[] args) {
        //////////////////////////////////////////////////////////////////
        long startTime = System.currentTimeMillis();
        //////////////////////////////////////////////////////////////////

        long ans = 0;
        List<Long> list = new ArrayList<>();
        long turns = 75;

        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // split by space
                for (String x:line.split(" ")) {
                    Long n = Long.parseLong(x);
                    list.add(n);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        for (Long x:list) {
            ans += solve(x, turns);
        }



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
