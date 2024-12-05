import java.io.*;
import java.util.*;
public class b {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        long ans = 0;
        // List<List<Integer>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            boolean p = false;
            while ((line = br.readLine()) != null) {
                if (line == "" || line.length() == 0) {
                    p = true;
                    continue;
                }
                if (!p) {
                    set.add(line);
                } else {
                    String[] parts = line.split(",");
                    List<Integer> l = new ArrayList<>();
                    for (String part : parts) {
                        l.add(Integer.parseInt(part));
                    }
                    List<Integer> lcopy = new ArrayList<>(l);
                    l.sort((a, b) -> set.contains(String.format("%s|%s", b, a)) ? 1 : -1);
                    if (!l.equals(lcopy)) {
                        ans+=l.get(l.size()/2);
                    }
                }
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
