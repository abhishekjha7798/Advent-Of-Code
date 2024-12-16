import java.io.*;
import java.util.*;

public class a {
    static int ans = 0;
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<List<Character>> grid = new ArrayList<>();
        boolean flag = false;
        List<Character> sequence = new ArrayList<>();
        int sx = -1, sy = -1;
        try (BufferedReader br = new BufferedReader(new FileReader("a.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    flag = true;
                    continue;
                }
                if (!flag) {
                    int j = 0;
                    List<Character> row = new ArrayList<>();
                    for (char ch : line.toCharArray()) {
                        if (ch == '@') {
                            sx = i;
                            sy = j;
                        }
                        row.add(ch);
                        j++;
                    }
                    grid.add(row);
                    i++;
                } else {
                    for (char ch : line.toCharArray()) {
                        sequence.add(ch);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //// CODE HERE
        for (int i = 0; i < sequence.size(); i++) {
            char ch = sequence.get(i);
            int nextx = -1, nexty = -1;
            // for (int a = 0; a < grid.size(); a++) {
            //     for (int b = 0; b < grid.get(0).size(); b++) {
            //         System.out.print(grid.get(a).get(b));
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            if (ch == '^') {
                int counter = sx;
                while (counter >= 0) {
                    if (grid.get(counter).get(sy) == '.') {
                        nextx = counter;
                        nexty = sy;
                        break;
                    } else if (grid.get(counter).get(sy) == '#') {
                        break;
                    }
                    counter--;
                }
                if (nextx != -1 && nexty != -1) {
                    if (grid.get(sx-1).get(sy) == '.') {
                        grid.get(sx-1).set(sy, '@');
                        grid.get(sx).set(sy, '.');
                    } else {
                        grid.get(sx-1).set(sy, '@');
                        grid.get(sx).set(sy, '.');
                        grid.get(nextx).set(nexty, 'O');
                    }
                    sx--;
                }
            } else if (ch == 'v') {
                int counter = sx;
                while (counter < grid.size()) {
                    if (grid.get(counter).get(sy) == '.') {
                        nextx = counter;
                        nexty = sy;
                        break;
                    } else if (grid.get(counter).get(sy) == '#') {
                        break;
                    }
                    counter++;
                }
                if (nextx != -1 && nexty != -1) {
                    if (grid.get(sx+1).get(sy) == '.') {
                        grid.get(sx+1).set(sy, '@');
                        grid.get(sx).set(sy, '.');
                    } else {
                        grid.get(sx+1).set(sy, '@');
                        grid.get(sx).set(sy, '.');
                        grid.get(nextx).set(nexty, 'O');
                    }
                    sx++;
                }
            } else if (ch == '<') {
                int counter = sy;
                while (counter >= 0) {
                    if (grid.get(sx).get(counter) == '.') {
                        nextx = sx;
                        nexty = counter;
                        break;
                    } else if (grid.get(sx).get(counter) == '#') {
                        break;
                    }
                    counter--;
                }
                if (nextx != -1 && nexty != -1) {
                    if (grid.get(sx).get(sy-1) == '.') {
                        grid.get(sx).set(sy-1, '@');
                        grid.get(sx).set(sy, '.');
                    } else {
                        grid.get(sx).set(sy-1, '@');
                        grid.get(sx).set(sy, '.');
                        grid.get(nextx).set(nexty, 'O');
                    }
                    sy--;
                }
            } else if (ch == '>') {
                int counter = sy;
                while (counter < grid.get(0).size()) {
                    if (grid.get(sx).get(counter) == '.') {
                        nextx = sx;
                        nexty = counter;
                        break;
                    } else if (grid.get(sx).get(counter) == '#') {
                        break;
                    }
                    counter++;
                }
                if (nextx != -1 && nexty != -1) {
                    if (grid.get(sx).get(sy+1) == '.') {
                        grid.get(sx).set(sy+1, '@');
                        grid.get(sx).set(sy, '.');
                    } else {
                        grid.get(sx).set(sy+1, '@');
                        grid.get(sx).set(sy, '.');
                        grid.get(nextx).set(nexty, 'O');
                    }
                    sy++;
                }
            }
        }
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if (grid.get(i).get(j) == 'O') {
                    ans+=(100*i + j);
                }
                // System.out.print(grid.get(i).get(j));
            }
            // System.out.println();
        }
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
