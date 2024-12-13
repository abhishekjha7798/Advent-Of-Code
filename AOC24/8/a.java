import java.util.*;
import java.io.*;

public class a {
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

    static long ans = 0;
    static long r = 0, c = 0;
    static Set<Pair> set = new HashSet<>();

    private static boolean isSafe(long x, long y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }

    private static void solve(List<Pair> list, Pair p) {
        for (Pair q : list) {
            if (p.equals(q)) continue;
            long xDist = Math.abs(p.a - q.a);
            long yDist = Math.abs(p.b - q.b);
            long xAntinode1, yAntinode1, xAntinode2, yAntinode2;
            if (p.a > q.a) {
                xAntinode1 = p.a + xDist;
                xAntinode2 = q.a - xDist;
            } else {
                xAntinode1 = p.a - xDist;
                xAntinode2 = q.a + xDist;
            }
            if (p.b > q.b) {
                yAntinode1 = p.b + yDist;
                yAntinode2 = q.b - yDist;
            } else {
                yAntinode1 = p.b - yDist;
                yAntinode2 = q.b + yDist;
            }
            // System.out.println(p.a + " " + p.b);
            // System.out.println(q.a + " " + q.b);
            if (isSafe(xAntinode1, yAntinode1)) {
                // System.out.println(xAntinode1 + " " + yAntinode1);
                set.add(new Pair(xAntinode1, yAntinode1));
            }
            if (isSafe(xAntinode2, yAntinode2)) {
                // System.out.println(xAntinode2 + " " + yAntinode2);
                set.add(new Pair(xAntinode2, yAntinode2));
            }
            // System.out.println();
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Map<Character, List<Pair>> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                char[] arr = line.toCharArray();
                c = arr.length;
                for (int j=0; j<arr.length; j++) {
                    char c = arr[j];
                    if (c != '.') {
                        if (!map.containsKey(c)) {
                            map.put(c, new ArrayList<>());
                        }
                        map.get(c).add(new Pair(i, j));
                    }
                }
                i++;
                r++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //// CODE HERE
        for (char c : map.keySet()) {
            List<Pair> list = map.get(c);
            for (Pair p : list) {
                solve(list, p);
            }
        }
        // for (Pair p : set) {
        //     System.out.println(p.a + " " + p.b);
        // }
        ans = set.size();
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
