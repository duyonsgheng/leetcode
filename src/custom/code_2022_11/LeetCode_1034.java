package custom.code_2022_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_1034
 * @Author Duys
 * @Description
 * @Date 2022/11/7 10:14
 **/
//1034. 边界着色
public class LeetCode_1034 {

    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> d = new LinkedList<>();
        d.add(new int[]{row, col});
        while (!d.isEmpty()) {
            int[] poll = d.poll();
            int x = poll[0], y = poll[1], cnt = 0;
            for (int[] di : dirs) {
                int nx = x + di[0], ny = y + di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (grid[x][y] != grid[nx][ny]) continue;
                cnt++;
                if (ans[nx][ny] != 0) continue;
                d.add(new int[]{nx, ny});
            }
            ans[x][y] = cnt == 4 ? grid[x][y] : color;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ans[i][j] == 0) ans[i][j] = grid[i][j];
            }
        }
        return ans;
    }

    // 并查集解法
    public static int[][] colorBorder1(int[][] grid, int row, int col, int color) {
        int[][] arr = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        UnionFind uf = new UnionFind(grid);
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 4; k++) {
                    int ni = i + arr[k][0];
                    int nj = j + arr[k][1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m && grid[i][j] == grid[ni][nj]) {
                        uf.union(getIndex(i, j, m), getIndex(ni, nj, m));
                    }
                }
            }
        }
        if (row < 0 || row > n || col < 0 || col > m) {
            return grid;
        }
        List<int[]> list = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        boolean[][] visited = new boolean[n][m];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (visited[cur[0]][cur[1]]) {
                    continue;
                }
                list.add(cur);
                visited[cur[0]][cur[1]] = true;
                for (int k = 0; k < 4; k++) {
                    int ni = cur[0] + arr[k][0];
                    int nj = cur[1] + arr[k][1];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                        if (uf.isOnce(getIndex(cur[0], cur[1], m), getIndex(ni, nj, m))) {
                            queue.add(new int[]{ni, nj});
                        }
                    }
                }
            }
        }
        visited = new boolean[n][m];
        for (int[] cur : list) {
            int x = cur[0];
            int y = cur[1];
            if (visited[x][y]) {
                continue;
            }
            boolean change = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + arr[i][0];
                int ny = y + arr[i][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    change = false;
                    break;
                }
                if (!uf.isOnce(getIndex(x, y, m), getIndex(nx, ny, m))) {
                    change = false;
                    break;
                }
            }
            if (!change) {
                grid[x][y] = color;
            }
            visited[x][y] = true;
        }
        return grid;
    }


    public static int getIndex(int i, int j, int col) {
        return i * col + j;
    }


    static class UnionFind {
        public int[][] grid;
        public int[] parent;
        public int[] size;
        public int[] help;
        int n = 0;
        int m = 0;

        public UnionFind(int[][] arr) {
            grid = arr;
            m = arr.length;
            n = arr[0].length;
            int s = m * n;
            parent = new int[s];
            size = new int[s];
            help = new int[s];
            for (int i = 0; i < s; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        public void union(int a, int b) {
            int fa = find(a);
            int fb = find(b);
            if (fa == fb) {
                return;
            }
            if (size[fa] >= size[fb]) {
                size[fa] += size[fb];
                parent[fb] = fa;
            } else {
                size[fb] += size[fa];
                parent[fa] = fb;
            }
        }

        public int find(int x) {
            int hi = 0;
            while (x != parent[x]) {
                help[hi++] = x;
                x = parent[x];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = x;
            }
            return x;
        }
    }

    public static void main(String[] args) {
        // [[1,1],[1,2]]
        //0
        //0
        //3
        int[][] arr = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int row = 1, col = 1, color = 2;
        colorBorder(arr, row, col, color);
    }
}
