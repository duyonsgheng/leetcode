package hope.infect;

/**
 * @author Mr.Du
 * @ClassName Code03_MakingLargeIsland
 * @date 2023年11月14日 11:48
 */
// 最大人工岛
// 给你一个大小为 n * n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
// 返回执行此操作后，grid 中最大的岛屿面积是多少？
// 岛屿 由一组上、下、左、右四个方向相连的 1 形成
// 测试链接 : https://leetcode.cn/problems/making-a-large-island/
public class Code03_MakingLargeIsland {
    // 把每个集合都编号，然后计算大小，看看把0位置改为1后，上下联合能得到最大的数量
    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int num = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, n, m, i, j, num++);
                }
            }
        }
        int[] sizes = new int[num];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] > 1) {
                    ans = Math.max(ans, ++sizes[grid[i][j]]);
                }
            }
        }
        boolean[] visited = new boolean[num];
        int up, down, left, right, merge;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 讨论每一个0点
                if (grid[i][j] != 0) {
                    continue;
                }
                up = i > 0 ? grid[i - 1][j] : 0;
                down = i + 1 < n ? grid[i + 1][j] : 0;
                left = j > 0 ? grid[i][j - 1] : 0;
                right = j + 1 < m ? grid[i][j + 1] : 0;
                visited[up] = true;
                merge = 1 + sizes[up];
                if (!visited[down]) {
                    merge += sizes[down];
                    visited[down] = true;
                }
                if (!visited[left]) {
                    merge += sizes[left];
                    visited[left] = true;
                }
                if (!visited[right]) {
                    merge += sizes[right];
                    visited[right] = true;
                }
                ans = Math.max(ans, merge);
                visited[up] = false;
                visited[down] = false;
                visited[left] = false;
                visited[right] = false;
            }
        }
        return ans;
    }

    public static void dfs(int[][] grid, int n, int m, int i, int j, int num) {
        if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = num;
        dfs(grid, n, m, i - 1, j, num);
        dfs(grid, n, m, i + 1, j, num);
        dfs(grid, n, m, i, j - 1, num);
        dfs(grid, n, m, i, j + 1, num);
    }
}
