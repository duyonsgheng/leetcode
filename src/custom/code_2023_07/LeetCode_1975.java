package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1975
 * @date 2023年07月18日
 */
// 1975. 最大方阵和
// https://leetcode.cn/problems/maximum-matrix-sum/
public class LeetCode_1975 {
    public long maxMatrixSum(int[][] matrix) {
        long ans = 0;
        int flag = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] < 0) {
                    flag++;
                }
                min = Math.min(min, Math.abs(matrix[i][j]));
                ans += Math.abs(matrix[i][j]);
            }
        }
        // 偶数个负号，直接加上就好，否则需要减去最小的那个负数
        return (flag & 1) == 0 ? ans : ans - min * 2;
    }
}
