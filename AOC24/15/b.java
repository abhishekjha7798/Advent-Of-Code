import java.io.*;
import java.util.*;

public class b {
    static int ans = 0;
    static List<List<Character>> grid = new ArrayList<>();
    static List<List<Character>> gridTwice = new ArrayList<>();
    static List<Character> sequence = new ArrayList<>();
    static int sx = -1, sy = -1;
    static int r = 0, c = 0;
    static int rr = 0, cc = 0;

    public static void fillGridTwice() {
        rr = r;
        cc = 2*c;
        sy*=2;
        for (List<Character> row : grid) {
            List<Character> newRow = new ArrayList<>();
            for (char ch : row) {
                if (ch == '#') {
                    newRow.add('#');
                    newRow.add('#');
                } else if (ch == '.') {
                    newRow.add('.');
                    newRow.add('.');
                } else if (ch == '@') {
                    newRow.add('@');
                    newRow.add('.');
                } else {
                    newRow.add('[');
                    newRow.add(']');
                }
            }
            gridTwice.add(newRow);
        }
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static int getDir(char ch) {
        if (ch == '^') return 3;
        if (ch == 'v') return 2;
        if (ch == '<') return 1;
        return 0;
    }

    public static boolean allUnvisted(boolean[][] visited) {
        for (int i = 0; i < rr; i++) {
            for (int j = 0; j < cc; j++) {
                if (visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void solve() {
        int nx = sx;
        int ny = sy;
        int nsx = sx;
        int nsy = sy;
        for (char ch : sequence) {
            int dir = getDir(ch);
            int ix = dx[dir];
            int iy = dy[dir];
            nx += ix;
            ny += iy;
            if (nx < 0 || nx >= rr || ny < 0 || ny >= cc || gridTwice.get(nx).get(ny) == '#') {
                nx = nsx;
                ny = nsy;
            } else if (gridTwice.get(nx).get(ny) == '.') {
                gridTwice.get(nx).set(ny, '@');
                gridTwice.get(nsx).set(nsy, '.');
                nsx = nx;
                nsy = ny;
            } else if (gridTwice.get(nx).get(ny) == '[' || gridTwice.get(nx).get(ny) == ']') {
                Deque<List<Integer>> queue = new ArrayDeque<>();
                queue.add(Arrays.asList(nsx, nsy));
                boolean[][] visited = new boolean[rr][cc];
                boolean ok = true;
                while (!queue.isEmpty()) {
                    List<Integer> curr = queue.poll();
                    int x = curr.get(0);
                    int y = curr.get(1);
                    if (visited[x][y]) {
                        continue;
                    }
                    visited[x][y] = true;
                    int rrr = x + ix;
                    int ccc = y + iy;
                    if (gridTwice.get(rrr).get(ccc) == '#') {
                        ok = false;
                        break;
                    }
                    if (gridTwice.get(rrr).get(ccc) == '[') {
                        queue.add(Arrays.asList(rrr, ccc));
                        queue.add(Arrays.asList(rrr, ccc+1));
                    }
                    if (gridTwice.get(rrr).get(ccc) == ']') {
                        queue.add(Arrays.asList(rrr, ccc));
                        queue.add(Arrays.asList(rrr, ccc-1));
                    }
                }
                if (!ok) {
                    nx = nsx;
                    ny = nsy;
                } else {
                    while (!allUnvisted(visited)) {
                        for (int i = 0; i < rr; i++) {
                            for (int j = 0; j < cc; j++) {
                                // System.out.print(visited[i][j] ? 1 : 0);
                                if (visited[i][j]) {
                                    int nrr = i + ix;
                                    int ncc = j + iy;
                                    if (!visited[nrr][ncc] && gridTwice.get(nrr).get(ncc) == '.') {
                                        gridTwice.get(nrr).set(ncc, gridTwice.get(i).get(j));
                                        gridTwice.get(i).set(j, '.');
                                        visited[i][j] = false;
                                    }
                                }
                            }
                            // System.out.println();
                        }
                    }
                    nsx = nx;
                    nsy = ny;
                }
            }
        }
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        boolean flag = false;
    
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
                    c = j;
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
        r = grid.size();
        c = grid.get(0).size();
        fillGridTwice();
        // for (int i = 0; i < gridTwice.size(); i++) {
        //     for (int j = 0; j < gridTwice.get(0).size(); j++) {
        //         System.out.print(gridTwice.get(i).get(j));
        //     }
        //     System.out.println();
        // }
        System.out.println();
        solve();
        for (int i = 0; i < gridTwice.size(); i++) {
            for (int j = 0; j < gridTwice.get(0).size(); j++) {
                if (gridTwice.get(i).get(j) == '[') {
                    ans+=(100*i + j);
                }
                // System.out.print(gridTwice.get(i).get(j));
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
        System.out.println("Execution time: " + executionTime + " milliseconds"); // 8700 + 203 = 
    }
}
