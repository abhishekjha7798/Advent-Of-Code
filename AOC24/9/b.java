import java.util.*;
import java.io.*;

public class b {
    public static class Pair {
        public long a, b;
    
        public Pair(long a, long b) {
            this.a = a;
            this.b = b;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a && b == pair.b;
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long ans = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line = br.readLine();
            List<Integer> newLine = new ArrayList<>();
            int i = 0;
            int pos = 0;
            Deque<Pair> val = new ArrayDeque<>();
            List<Pair> spaces = new ArrayList<>();
            int x = 0;
            for (char c : line.toCharArray()) {
                if (pos%2 == 0) {
                    val.add(new Pair(x, Long.parseLong(String.valueOf(c))));
                    for (int j=0; j<Integer.parseInt(String.valueOf(c)); j++) {
                        newLine.add(i);
                        x++;
                    }
                    i++;
                } else {
                    spaces.add(new Pair(x, Long.parseLong(String.valueOf(c))));
                    for (int j=0; j<Integer.parseInt(String.valueOf(c)); j++) {
                        newLine.add(-1);
                        x++;
                    }
                }
                pos++;
            }
            // System.out.println(newLine);
            // System.out.println(val);
            // System.out.println(spaces);
            while(!val.isEmpty()) {
                Pair temp = val.pollLast();
                for (Pair space : spaces) {
                    if (temp.a > space.a && temp.b <= space.b) {
                        // System.out.println(space.a + " " + space.b + " " + temp.a + " " + temp.b);
                        // System.out.println(newLine);
                        for (int j=0; j<temp.b; j++) {
                            newLine.set((int)space.a + j, newLine.get((int)temp.a + j));
                            newLine.set((int)temp.a + j, -1);
                        }
                        // System.out.println(newLine);
                        space.a+=temp.b;
                        space.b-=temp.b;
                        break;
                    }
                }
            }
            for (int j=0; j < newLine.size(); j++) {
                if (newLine.get(j) != -1) {
                    ans+=(j*newLine.get(j));
                }
            }
            // System.out.println(newLine);
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
