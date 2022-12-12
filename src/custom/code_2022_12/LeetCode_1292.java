package custom.code_2022_12;

/**
 * @ClassName LeetCode_1292
 * @Author Duys
 * @Description
 * @Date 2022/12/5 11:36
 **/
// 1292. 元素和小于等于阈值的正方形的最大边长
public class LeetCode_1292 {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        long[][] sums = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 当前位置等于左边的+上边的 减去重复的那一部分
                sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int l = ans;
                int r = Math.min(m - i, n - j);
                while (l <= r) {
                    int mid = l + ((r - l) >> 1);
                    if (sums[i + mid][j + mid] - sums[i + mid][j] - sums[i][j + mid] + sums[i][j] <= threshold) {
                        ans = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            }
        }
        return ans;
    }
}
