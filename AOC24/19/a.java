import java.util.*;
import java.io.*;

public class a {
    static long ans = 0;
    static List<String> availablePatterns = new ArrayList<>();
    static List<String> desiredPatternCombinations = new ArrayList<>();

    static boolean isPatternPossible(String pattern) {
        if (pattern.length() == 0) {
            return true;
        }
        boolean isPossible = false;
        for (String availablePattern : availablePatterns) {
            if (pattern.startsWith(availablePattern)) {
                isPossible = isPatternPossible(pattern.substring(availablePattern.length()));
                if (isPossible) {
                    return true;
                }
            }
        }
        return false;
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
            if (isPatternPossible(desiredPatternCombination)) {
                ans++;
            }
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
