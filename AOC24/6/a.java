import java.io.*;
import java.util.*;
public class a {
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

        int r = sr, c = sc;
        int d = 0;

        while(true) {
            if (d == 0) {
                while (r > 0 && list.get(r-1).get(c) != '#') {
                    list.get(r).set(c, 'X');
                    r--;
                }

                if (r == 0) {
                    break;
                }
                d = 1;
            }
            if (d == 1) {
                while (c < list.get(0).size()-1 && list.get(r).get(c+1) != '#') {
                    list.get(r).set(c, 'X');
                    c++;
                }
                if (c == list.get(0).size()-1) {
                    break;
                }
                d = 2;
            }
            if (d == 2) {
                while (r < list.size()-1 && list.get(r+1).get(c) != '#') {
                    list.get(r).set(c, 'X');
                    r++;
                }
                if (r == list.size()-1) {
                    break;
                }
                d = 3;
            }
            if (d == 3) {
                while (c > 0 && list.get(r).get(c-1) != '#') {
                    list.get(r).set(c, 'X');
                    c--;
                }
                if (c == 0) {
                    break;
                }
                d = 0;
            }
        }

        for (List<Character> l : list) {
            for (char ch : l) {
                if (ch == 'X') {
                    ans++;
                }
            }
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
