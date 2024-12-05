import java.io.*;
import java.util.*;

public class b {
    public static void main(String[] args) {
        List<List<Character>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                list.add(new ArrayList<>());
                for (char c : line.toCharArray()) {
                    list.get(list.size() - 1).add(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long ans = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (list.get(i).get(j) == 'A') {
                    if (i - 1 >=0 && j - 1 >= 0 && i + 1 < list.size() && j + 1 < list.get(i).size()) {
                        if (list.get(i - 1).get(j - 1) == 'M' && list.get(i + 1).get(j + 1) == 'S' && list.get(i - 1).get(j + 1) == 'M' && list.get(i + 1).get(j - 1) == 'S') {
                            ans++;
                        }
                        if (list.get(i - 1).get(j - 1) == 'M' && list.get(i + 1).get(j + 1) == 'S' && list.get(i - 1).get(j + 1) == 'S' && list.get(i + 1).get(j - 1) == 'M') {
                            ans++;
                        }
                        if (list.get(i - 1).get(j - 1) == 'S' && list.get(i + 1).get(j + 1) == 'M' && list.get(i - 1).get(j + 1) == 'M' && list.get(i + 1).get(j - 1) == 'S') {
                            ans++;
                        }
                        if (list.get(i - 1).get(j - 1) == 'S' && list.get(i + 1).get(j + 1) == 'M' && list.get(i - 1).get(j + 1) == 'S' && list.get(i + 1).get(j - 1) == 'M') {
                            ans++;
                        }
                    }
                }
            }
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"))) {
            out.println("Answer: " + ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
