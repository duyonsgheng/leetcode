package custom.code_2022_05;

/**
 * @ClassName LeetCode_48
 * @Author Duys
 * @Description
 * @Date 2022/5/5 16:51
 **/
// 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
//你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
//链接：https://leetcode-cn.com/problems/rotate-image
public class LeetCode_48 {

    // 一圈一圈的来

    /**
     * 1   2   3
     * 4   5   6
     * 7   8   9
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            // 这一圈控制交换的数
            // 下标换算，
            // 比如3 * 3的矩阵 除去一个尾巴，每行需要交换2次，每一列需要交换2次
            // 比如 5 *5的矩阵，出去一个尾巴，需要交换4次
            for (int j = 0; j < n - 1; j++) {
              /*  int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = tmp;*/
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = tmp;
            }
        }
    }

    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // [0][4] -> [4][4]
                // [0][3] -> [3][4]
                // [0][2] -> [2][4]
                // [0][1] -> [1][4]
                // [0][0] -> [0][4]
                clone[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = clone[i][j];
            }
        }
    }
}
