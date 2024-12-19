import java.util.*;
import java.io.*;

public class b {
    static long ans = 0;
    static List<String> availablePatterns = new ArrayList<>();
    static List<String> desiredPatternCombinations = new ArrayList<>();
    static Map<String, Long> memo = new HashMap<>();

    static long numberOfPossiblePatternArrangements(String pattern) {
        if (pattern.length() == 0) {
            return 1;
        }
        if (memo.containsKey(pattern)) {
            return memo.get(pattern);
        }
        long sAns = 0;
        for (String availablePattern : availablePatterns) {
            if (pattern.startsWith(availablePattern)) {
                sAns += numberOfPossiblePatternArrangements(pattern.substring(availablePattern.length()));
            }
        }
        memo.put(pattern, sAns);
        return sAns;
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            boolean flag = false;
            while ((line = br.readLine()) != null) {
                if (line.length() == 0) {
                    flag = true;
                    continue;
                }
                if (!flag) {
                    String[] parts = line.split(",");
                    for (String part : parts) {
                        availablePatterns.add(part.trim());
                    }
                } else {
                    desiredPatternCombinations.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /// CODE HERE
        for (String desiredPatternCombination : desiredPatternCombinations) {
            ans += numberOfPossiblePatternArrangements(desiredPatternCombination);
        }
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
