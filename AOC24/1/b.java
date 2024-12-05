
import java.io.*;
import java.util.*;

public class b {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    list1.add(Integer.parseInt(parts[0]));
                    list2.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : list2) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int ans = 0;

        for (int i : list1) {
            if (map.containsKey(i)) {
                ans += i * map.get(i);
            }
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"))) {
            out.println("Answer: " + ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}