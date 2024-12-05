import java.io.*;
import java.util.*;

public class b {
    public static void main(String[] args) {
        List<List<Long>> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                boolean go = true;
                int prev = 0;            
                int index = line.indexOf("mul(");
                while (index >= 0) {
                    String substr = line.substring(prev, index);
                    if (go) {
                        int ind = substr.indexOf("don't()");
                        if (ind >= 0 && ind < index) {
                            go = false;
                        }
                    } else {
                        int ind = substr.indexOf("do()");
                        if (ind >= 0 && ind < index) {
                            go = true;
                        }
                    }
                    int start = index + 4;
                    int end = line.indexOf(")", start);
                    if (go) {
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
                    }
                    index = line.indexOf("mul(", start);
                    prev = start;
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
