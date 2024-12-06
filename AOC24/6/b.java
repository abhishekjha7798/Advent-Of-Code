import java.io.*;
import java.util.*;
public class b {
    public static class Triple {
        int r, c, d;
        public Triple(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Triple triple = (Triple) o;
            return r == triple.r && c == triple.c && d == triple.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c, d);
        }
    }
    private static int solve(List<List<Character>> list, int sr, int sc) {
        int ans = 0;

        for (int i=0; i < list.size(); i++) {
            for (int j=0; j < list.get(0).size(); j++) {
                int r = sr, c = sc;
                int d = 0;
                Set<Triple> visited = new HashSet<>();
                if (list.get(i).get(j) == '.' && (i != sr || j != sc)) {
                    list.get(i).set(j, '#');
                    boolean foundLoop = false;
                    while(true) {
                        if (foundLoop) {
                            break;
                        }
                        if (d == 0) {
                            while (r > 0 && list.get(r-1).get(c) != '#') {
                                // if (i==6 && j==3) {
                                //     System.out.printf("%d %d %d\n", r, c, d);
                                // }
                                if (visited.contains(new Triple(r, c, d))) {
                                    foundLoop = true;
                                    break;
                                }
                                visited.add(new Triple(r, c, d));
                                r--;
                            }
                            if (foundLoop || r == 0) {
                                break;
                            }
                            d = 1;
                        }
                        if (d == 1) {
                            while (c < list.get(0).size()-1 && list.get(r).get(c+1) != '#') {
                                // if (i==6 && j==3) {
                                //     System.out.printf("%d %d %d\n", r, c, d);
                                // }
                                if (visited.contains(new Triple(r, c, d))) {
                                    foundLoop = true;
                                    break;
                                }
                                visited.add(new Triple(r, c, d));
                                c++;
                            }
                            if (foundLoop || c == list.get(0).size()-1) {
                                break;
                            }
                            d = 2;
                        }
                        if (d == 2) {
                            while (r < list.size()-1 && list.get(r+1).get(c) != '#') {
                                // if (i==6 && j==3) {
                                //     System.out.printf("%d %d %d\n", r, c, d);
                                // }
                                if (visited.contains(new Triple(r, c, d))) {
                                    foundLoop = true;
                                    break;
                                }
                                visited.add(new Triple(r, c, d));
                                r++;
                            }
                            if (foundLoop || r == list.size()-1) {
                                break;
                            }
                            d = 3;
                        }
                        if (d == 3) {
                            while (c > 0 && list.get(r).get(c-1) != '#') {
                                // if (i==6 && j==3) {
                                //     System.out.printf("%d %d %d\n", r, c, d);
                                // }
                                if (visited.contains(new Triple(r, c, d))) {
                                    foundLoop = true;
                                    break;
                                }
                                visited.add(new Triple(r, c, d));
                                c--;
                            }
                            if (foundLoop || c == 0) {
                                break;
                            }
                            d = 0;
                        }
                    }
                    if (foundLoop) {
                        ans++;
                    }
                    list.get(i).set(j, '.');
                }
                // if (i==6 && j==3) {
                //     System.out.printf("%d %d %d\n", r, c, d);
                // }
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        //////////////////////////////////////////////////////////////////
        long startTime = System.currentTimeMillis();
        //////////////////////////////////////////////////////////////////

        int ans = 1;
        int sr = -1, sc = -1;
        List<List<Character>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            int j = 0;
            while ((line = br.readLine()) != null) {
                List<Character> l = new ArrayList<>();
                char[] arr = line.toCharArray();
                for (int i=0; i < arr.length; i++) {
                    if (arr[i] == '^') {
                        sr = j;
                        sc = i;
                        arr[i] = '.';
                    }
                    l.add(arr[i]);
                }
                list.add(l);
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ans = solve(list, sr, sc);

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
