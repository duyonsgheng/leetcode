package custom.code_2023_06;

/**
 * @ClassName LeetCode_1905
 * @Author Duys
 * @Description
 * @Date 2023/6/26 11:15
 **/
// 1905. 统计子岛屿
public class LeetCode_1905 {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid1.length, m = grid1[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid2[i][j] == 1) { // 从当前位置除法，把grid2中子岛屿全部变为0
                    if (dfs(grid1, grid2, i, j, n, m)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public boolean dfs(int[][] arr1, int[][] arr2, int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m) {
            return true;
        }
        if (arr2[i][j] == 0) {
            return true;
        }
        // 当前位置遍历了，变成海水，不让下一次重复计算
        arr2[i][j] = 0;
        boolean flag = true;
        // 如果再grid1中不是岛屿，那么当前grid2中的这个岛就一定不是子岛
        if (arr1[i][j] == 0) {
            // 为了后续把grid2中的岛全部变成海水，为了不重复计算
            flag = false;
        }
        // 继续
        boolean f1 = dfs(arr1, arr2, i + 1, j, n, m);
        boolean f2 = dfs(arr1, arr2, i - 1, j, n, m);
        boolean f3 = dfs(arr1, arr2, i, j + 1, n, m);
        boolean f4 = dfs(arr1, arr2, i, j - 1, n, m);
        return flag && f1 && f2 && f3 && f4;
    }
}
