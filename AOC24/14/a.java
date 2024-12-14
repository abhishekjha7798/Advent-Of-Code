import java.io.*;

public class a {
    static int ans = 0;
    static int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
    public static void solve(int x, int y, int vx, int vy, int r, int c) {
        int count = 100;
        int x1 = (((x + vx * count) % r) + r) % r;
        int y1 = (((y + vy * count) % c) + c) % c;
        if (x1 >= 0 && x1 < r/2 && y1 >= 0 && y1 < c/2) {
            q1++;
        }
        if (x1 > r/2 && x1 < r && y1 >= 0 && y1 < c/2) {
            q2++;
        }
        if (x1 >= 0 && x1 < r/2 && y1 > c/2 && y1 < c) {
            q3++;
        }
        if (x1 > r/2 && x1 < r && y1 > c/2 && y1 < c) {
            q4++;
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            int r = 101, c = 103;
            while ((line = br.readLine()) != null) {
                String[] metrics = line.split(" ");
                String[] pos = metrics[0].split("=")[1].split(",");
                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);
                String[] velocity = metrics[1].split("=")[1].split(",");
                int vx = Integer.parseInt(velocity[0]);
                int vy = Integer.parseInt(velocity[1]);
                solve(x, y, vx, vy, r, c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //// CODE HERE
        // System.out.println(q1 + " " + q2 + " " + q3 + " " + q4);
        ans += q1 * q2 * q3 * q4;
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
