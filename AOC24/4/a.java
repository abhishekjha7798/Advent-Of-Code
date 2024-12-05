import java.io.*;
import java.util.*;

public class a {
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
        // System.out.println(list.size());
        // System.out.println(list.get(0).size());

        // find occurrence of "XMAS" horizontal, vertical, diagonal,
        // written backwards, or even overlapping other words in List<List<Character>> list
        // and increment ans by 1 for each occurrence
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                // System.out.printf("%c", list.get(i).get(j));
                if (j + 4 <= list.get(i).size()) {
                    if (list.get(i).get(j) == 'X' && list.get(i).get(j + 1) == 'M' && list.get(i).get(j + 2) == 'A' && list.get(i).get(j + 3) == 'S') {
                        ans++;
                    }
                }
                if (i + 4 <= list.size()) {
                    if (list.get(i).get(j) == 'X' && list.get(i + 1).get(j) == 'M' && list.get(i + 2).get(j) == 'A' && list.get(i + 3).get(j) == 'S') {
                        ans++;
                    }
                }
                if (j - 3 >=0) {
                    if (list.get(i).get(j) == 'X' && list.get(i).get(j - 1) == 'M' && list.get(i).get(j - 2) == 'A' && list.get(i).get(j - 3) == 'S') {
                        ans++;
                    }
                }
                if (i - 3 >=0) {
                    if (list.get(i).get(j) == 'X' && list.get(i - 1).get(j) == 'M' && list.get(i - 2).get(j) == 'A' && list.get(i - 3).get(j) == 'S') {
                        ans++;
                    }
                }
                if (i + 4 <= list.size() && j + 4 <= list.get(i).size()) {
                    if (list.get(i).get(j) == 'X' && list.get(i + 1).get(j + 1) == 'M' && list.get(i + 2).get(j + 2) == 'A' && list.get(i + 3).get(j + 3) == 'S') {
                        ans++;
                    }
                }
                if (i + 4 <= list.size() && j - 3 >= 0) {
                    if (list.get(i).get(j) == 'X' && list.get(i + 1).get(j - 1) == 'M' && list.get(i + 2).get(j - 2) == 'A' && list.get(i + 3).get(j - 3) == 'S') {
                        ans++;
                    }
                }
                if (i - 3 >=0 && j + 4 <= list.get(i).size()) {
                    if (list.get(i).get(j) == 'X' && list.get(i - 1).get(j + 1) == 'M' && list.get(i - 2).get(j + 2) == 'A' && list.get(i - 3).get(j + 3) == 'S') {
                        ans++;
                    }
                }
                if (i - 3 >=0 && j - 3 >= 0) {
                    if (list.get(i).get(j) == 'X' && list.get(i - 1).get(j - 1) == 'M' && list.get(i - 2).get(j - 2) == 'A' && list.get(i - 3).get(j - 3) == 'S') {
                        ans++;
                    }
                }
            }
            // System.out.printf("\n");
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"))) {
            out.println("Answer: " + ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
