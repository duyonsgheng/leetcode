package custom.code_2022_12;

/**
 * @ClassName LeetCode_1314
 * @Author Duys
 * @Description
 * @Date 2022/12/8 16:18
 **/
// 1314. 矩阵区域和
public class LeetCode_1314 {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int p = i - k, q = j - k, p1 = i + k, q1 = j + k;
                p = p < 0 ? 0 : p;
                q = q < 0 ? 0 : q;
                p1 = p1 >= m ? m : p1 + 1;
                q1 = q1 >= n ? n : q1 + 1;
                ans[i][j] = sum[p1][q1] + sum[p][q] - sum[p1][q] - sum[p][q1];
            }
        }
        return ans;
    }
}
