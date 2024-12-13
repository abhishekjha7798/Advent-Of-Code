import java.io.*;

public class b {
    static long ans = 0;
    // solve linear equations in 2 variables
    public static void solve(long a1, long a2, long b1, long b2, long c1, long c2) {
        long det = a1 * b2 - a2 * b1;
        long detX = c1 * b2 - c2 * b1;
        long detY = a1 * c2 - a2 * c1;
        if (det != 0) {
            long x = detX % det == 0 ? detX / det : -1;
            long y = detY % det == 0 ? detY / det : -1;
            // System.out.println(x + " " + y);
            if (x >= 0 && y >= 0)
                ans+=(3*x+y);
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            int i = 0;
            long a1 = 0, a2 = 0, b1 = 0, b2 = 0, c1 = 0, c2 = 0;
            while ((line = br.readLine()) != null) {
                if (line == null || line.isEmpty()) {
                    continue;
                }
                String[] val = line.split(":")[1].trim().split(",");
                if (i == 0) {
                    a1 = Long.parseLong(val[0].trim().split("\\+")[1]);
                    a2 = Long.parseLong(val[1].trim().split("\\+")[1]);
                    i++;
                } else if (i == 1) {
                    b1 = Long.parseLong(val[0].trim().split("\\+")[1]);
                    b2 = Long.parseLong(val[1].trim().split("\\+")[1]);
                    i++;
                } else {
                    c1 = Long.parseLong(val[0].split("=")[1]);
                    c2 = Long.parseLong(val[1].split("=")[1]);
                    c1 += 1e13;
                    c2 += 1e13;
                    solve(a1, a2, b1, b2, c1, c2);
                    i=0;
                }
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
