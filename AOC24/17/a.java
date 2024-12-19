import java.io.*;
import java.util.*;

public class a {
    static String ans;
    static long a = 0, b = 0, c = 0;
    static List<Long> seq = new ArrayList<>();
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                if (i == 0) {
                    a = Long.parseLong(line.split(":")[1].trim());
                } else if (i == 1) {
                    b = Long.parseLong(line.split(":")[1].trim());
                } else if (i == 2) {
                    c = Long.parseLong(line.split(":")[1].trim());
                } else {
                    String[] parts = line.split(":")[1].trim().split(",");
                    for (String part : parts) {
                        seq.add(Long.parseLong(part));
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //// CODE HERE
        // System.out.println(a + " " + b + " " + c);
        // System.out.println(seq);
        int i = 0;
        StringBuilder sb = new StringBuilder();
        long opcode = -1;
        long operand = -1;
        long opValue = -1;
        while (i < seq.size()) {
            if (i % 2 == 0) {
                opcode = seq.get(i);
            } else {
                operand = seq.get(i);
                if (operand == 4) {
                    opValue = a;
                } else if (operand == 5) {
                    opValue = b;
                } else if (operand == 6) {
                    opValue = c;
                } else {
                    opValue = operand;
                }
            }
            if (opcode != -1 && operand != -1) {
                if (opcode == 0) {
                    a = (a / (long)Math.pow(2, opValue));
                } else if (opcode == 1) {
                    b = (b ^ operand);
                } else if (opcode == 2) {
                    b = opValue % 8;
                } else if (opcode == 3) {
                    if (a != 0) {
                        i = (int)opValue;
                        opcode = -1;
                        operand = -1;
                        opValue = -1;
                        continue;
                    }
                } else if (opcode == 4) {
                    b = b ^ c;
                } else if (opcode == 5) {
                    sb.append((opValue % 8)).append(",");
                } else if (opcode == 6) {
                    b = (a / (long)Math.pow(2, opValue));
                } else if (opcode == 7) {
                    c = (a / (long)Math.pow(2, opValue));
                }
                System.out.println(opcode + " " + operand + " " + opValue);
                System.out.println(a + " " + b + " " + c);
                System.out.println();
                opcode = -1;
                operand = -1;
                opValue = -1;
            }
            i++;
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
            sb.setLength(sb.length() - 1);
        }
        ans = sb.toString();
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
