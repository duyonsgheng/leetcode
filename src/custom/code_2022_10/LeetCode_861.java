package custom.code_2022_10;

/**
 * @ClassName LeetCode_861
 * @Author Duys
 * @Description
 * @Date 2022/10/9 10:13
 **/
// 861. 翻转矩阵后的得分
public class LeetCode_861 {
    // 要想结果大，那么每一行都大，每一行开始为1
    public int matrixScore(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            // 开头是0的这一行先反转
            if (grid[i][0] == 0) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = 1 - grid[i][j];
                }
            }
        }
        // 每一行开始都是1
        int ans = n * (1 << (m - 1));
        // 从第一列开始，保证每一列的1，比较多
        for (int i = 1; i < m; i++) {
            int ones = 0;
            for (int j = 0; j < n; j++) {
                ones += grid[j][i];
            }
            // 每一列，取0和1 谁多的
            ones = Math.max(ones, n - ones);
            ans += ones * (1 << (m - i - 1));
        }
        return ans;
    }
}
