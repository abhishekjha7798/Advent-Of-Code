import java.io.*;
import java.util.*;

public class b {
    static String actualAns;
    static long ans = 0;
    static long a1 = 0, b1 = 0, c1 = 0;
    static List<Long> seq = new ArrayList<>();
    private static long solve(int index, String output, List<Long> prev) {
        if (index == -1) {
            return prev.stream().min(Comparator.naturalOrder()).get();
        }
        String expectedAns = output.isEmpty() ? String.valueOf(seq.get(index)) : seq.get(index) + "," + output;
        List<Long> next = new ArrayList<>();
        for (long num: prev) {
            long a = num << 3;
            for (int i = 0; i < 8; i++) {
                String actualAns = solve(a + i);
                if (actualAns.equals(expectedAns)) {
                    // if (index == 0) {
                    //     System.out.println("a: " + (a + i));
                    //     System.out.println("Actual: " + actualAns);
                    // }
                    next.add(a + i);
                }
            }
        }
        // System.out.println("Expected: " + expectedAns);
        // System.out.println("Next: " + next);
        return solve(index - 1, expectedAns, next);
    }
    private static String solve(long x) {
        long a = x;
        long b = b1;
        long c = c1;
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
                    a = a >> opValue;
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
                    b = a >> opValue;
                } else if (opcode == 7) {
                    c = a >> opValue;
                }
                opcode = -1;
                operand = -1;
                opValue = -1;
            }
            i++;
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
            sb.setLength(sb.length() - 1);
        }
        return String.join(",", sb);
    }
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
                    a1 = Long.parseLong(line.split(":")[1].trim());
                } else if (i == 1) {
                    b1 = Long.parseLong(line.split(":")[1].trim());
                } else if (i == 2) {
                    c1 = Long.parseLong(line.split(":")[1].trim());
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
        ans = solve(seq.size() - 1, "", List.of(0L));
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
