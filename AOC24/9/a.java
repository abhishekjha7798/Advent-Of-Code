import java.util.*;
import java.io.*;

public class a {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long ans = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line = br.readLine();
            List<Integer> newLine = new ArrayList<>();
            int i = 0;
            int pos = 0;
            Deque<Integer> val = new ArrayDeque<>();
            Queue<Integer> spaces = new LinkedList<>();
            int x = 0;
            for (char c : line.toCharArray()) {
                if (pos%2 == 0) {
                    for (int j=0; j<Integer.parseInt(String.valueOf(c)); j++) {
                        newLine.add(i);
                        val.add(x);
                        x++;
                    }
                    i++;
                } else {
                    for (int j=0; j<Integer.parseInt(String.valueOf(c)); j++) {
                        newLine.add(-1);
                        spaces.add(x);
                        x++;
                    }
                }
                pos++;
            }
            // System.out.println(newLine);
            // System.out.println(val.peekLast());
            // System.out.println(spaces.peek());
            while (!val.isEmpty() && !spaces.isEmpty() && val.peekLast() > spaces.peek()) {
                int temp = val.pollLast();
                newLine.set(spaces.poll(), newLine.get(temp));
                newLine.set(temp, -1);
            }
            for (int j=0; j < newLine.size(); j++) {
                if (newLine.get(j) != -1) {
                    ans+=(j*newLine.get(j));
                }
            }
            // System.out.println(new String(arr));
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
