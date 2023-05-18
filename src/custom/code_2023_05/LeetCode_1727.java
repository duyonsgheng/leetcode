package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1727
 * @Author Duys
 * @Description
 * @Date 2023/5/11 16:45
 **/
// 1727. 重新排列后的最大子矩阵
public class LeetCode_1727 {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    // 当前位置上面连续的1有几个
                    matrix[i][j] = matrix[i - 1][j] + 1;
                }
            }
        }
        int ans = 0;
        // matrix = [
        // [0,0,1],
        // [1,1,1],
        // [1,0,1]]
        // 0 0 1
        // 1 1 2
        // 2 1 3
        // 看看能否交换
        for (int i = 0; i < m; i++) {
            // 这一行的都排个序
            Arrays.sort(matrix[i]);
            for (int j = n - 1; j >= 0; j--) {
                // 当前高度是 matrix[i][j]
                // 当前的宽度是 m-j
                ans = Math.max(ans, matrix[i][j] * (n - j));
            }
        }
        return ans;
    }
}
