package custom.code_2023_02;

/**
 * @ClassName LeetCode_1504
 * @Author Duys
 * @Description
 * @Date 2023/2/3 13:53
 **/
// 1504. 统计全 1 子矩形
public class LeetCode_1504 {
    // 1.枚举
    public int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] arr = new int[n][m];
        // 算每一行连续的1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    arr[i][j] = mat[i][j];
                } else if (mat[i][j] != 0) {
                    arr[i][j] = arr[i][j - 1] + 1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }
        // 以每个点为右下角
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int col = arr[i][j];
                for (int k = i; k >= 0 && col != 0; k--) {
                    col = Math.min(col, arr[k][j]);
                    ans += col;
                }
            }
        }
        return ans;
    }
}
