package week.week_2022_12_02;

/**
 * @ClassName Code_02_LeetCode_827
 * @Author Duys
 * @Description
 * @Date 2022/12/15 10:21
 **/
// 827. 最大人工岛
public class Code_02_LeetCode_827 {
    // 思路感染的过程
    // 然后枚举每一个0，收集最大
    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fn = 2; // 岛的编号从2开始，
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    infect(grid, i, j, fn++, m, n);
                }
            }
        }
        // 收集每个岛对应的数量
        int[] size = new int[fn];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 1) {
                    ans = Math.max(ans, ++size[grid[i][j]]);
                }
            }
        }
        // 枚举0的位置
        boolean[] visited = new boolean[fn];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }
                int up = i - 1 >= 0 ? grid[i - 1][j] : 0;
                int down = i + 1 < m ? grid[i + 1][j] : 0;
                int left = j - 1 >= 0 ? grid[i][j - 1] : 0;
                int right = j + 1 < n ? grid[i][j + 1] : 0;
                int cur = size[up] + 1;
                visited[up] = true;
                if (!visited[down]) {
                    cur += size[down];
                    visited[down] = true;
                }
                if (!visited[left]) {
                    cur += size[left];
                    visited[left] = true;
                }
                if (!visited[right]) {
                    cur += size[right];
                    visited[right] = true;
                }
                ans = Math.max(ans, cur);
                visited[up] = false;
                visited[down] = false;
                visited[left] = false;
                visited[right] = false;
            }
        }
        return ans;
    }

    // 当前的感染过程。把当前遇到的全部改为v
    public void infect(int[][] arr, int x, int y, int v, int m, int n) {
        if (x < 0 || x >= m || y < 0 || y >= n || arr[x][y] != 1) {
            return;
        }
        arr[x][y] = v;
        infect(arr, x + 1, y, v, m, n);
        infect(arr, x - 1, y, v, m, n);
        infect(arr, x, y + 1, v, m, n);
        infect(arr, x, y - 1, v, m, n);
    }
}
