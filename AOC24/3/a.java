import java.io.*;
import java.util.*;

public class a {
    public static void main(String[] args) {
        List<List<Long>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // finf all instances of substring "mul("
                int index = line.indexOf("mul(");
                while (index >= 0) {
                    int start = index + 4;
                    int end = line.indexOf(")", start);
                    String[] parts = line.substring(start, end).split(",");
                    if (parts.length == 2) {
                        List<Long> temp = new ArrayList<>();
                        try {
                            temp.add(Long.parseLong(parts[0]));
                            temp.add(Long.parseLong(parts[1]));
                            list.add(temp);
                        } catch (NumberFormatException e) {
                            System.out.println("The string cannot be parsed as a long.");
                        }
                    }
                    index = line.indexOf("mul(", start);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long ans = 0;

        for (List<Long> l : list) {
            System.out.println(l.get(0) + " * " + l.get(1) + " = " + l.get(0) * l.get(1));
            ans += l.get(0) * l.get(1);
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("output.txt"))) {
            out.println("Answer: " + ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}