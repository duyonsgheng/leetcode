package custom.code_2022_11;

/**
 * @ClassName LeetCode_1267
 * @Author Duys
 * @Description
 * @Date 2022/11/30 11:13
 **/
// 1267. 统计参与通信的服务器
public class LeetCode_1267 {

    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当前这一行或者一列有其他的机器。可以算
                if (grid[i][j] == 1 && (rows[i] > 1 || cols[j] > 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }


}
