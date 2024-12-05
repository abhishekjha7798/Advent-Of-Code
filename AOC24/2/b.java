import java.io.*;
import java.util.*;

public class b {

    private static int ans = 0;

    private static boolean isValid(List<Integer> list) {
        int diff = 0;
        for (int i = 1; i < list.size(); i++) {
            int d = list.get(i) - list.get(i - 1);
            // System.out.printf("%d %d %d\n", list.get(i), list.get(i - 1), d);
            if (d > 0) {
                if (diff == -1) {
                    return false;
                }
                diff = 1;
            }

            if (d < 0) {
                if (diff == 1) {
                    return false;
                }
                diff = -1;
            }
            int temp = Math.abs(d);
            if (temp < 1 || temp > 3) {
                return false;
            }
        }
        return true;
    }

    private static void solve(List<Integer> list) {
        if (isValid(list)) {
            ans++;
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            List<Integer> newList = new ArrayList<>(list);
            newList.remove(i);
            if (isValid(newList)) {
                ans++;
                return;
            }
        }
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                for (String part : parts) {
                    list.add(Integer.parseInt(part));
                }
                solve(list);
                // System.out.println(ans);
                list.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"))) {
            out.println("Answer: " + ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
