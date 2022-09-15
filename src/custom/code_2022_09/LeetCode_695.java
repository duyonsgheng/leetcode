package custom.code_2022_09;

/**
 * @ClassName LeetCode_695
 * @Author Duys
 * @Description
 * @Date 2022/9/8 16:04
 **/
// 695. 岛屿的最大面积
public class LeetCode_695 {

    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ans = Math.max(ans, dfs(i, j, grid));
            }
        }
        return ans;
    }

    public static int dfs(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) {
            return 0;
        }
        // 修改
        grid[x][y] = 0;
        int ans = 1;// 当前是1，算一个
        // 上下左右
        ans += dfs(x - 1, y, grid);
        ans += dfs(x + 1, y, grid);
        ans += dfs(x, y - 1, grid);
        ans += dfs(x, y + 1, grid);
        return ans;
    }
}
