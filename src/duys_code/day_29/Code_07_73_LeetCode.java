package duys_code.day_29;

/**
 * @ClassName Code_07_73_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/set-matrix-zeroes/
 * @Date 2021/11/25 17:40
 **/
public class Code_07_73_LeetCode {

    // 1.用两个变量来记录，行需不需要变0，列需不需要变0
    // 2.然后行表示第0行是否需要变0
    // 3.然后列表示第0列是都需要变0
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0] == null || matrix[0].length <= 0) {
            return;
        }
        boolean rowZeroes = false;
        boolean colZeroes = false;
        // 第0列上有0，那么第0列变0
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colZeroes = true;
                break;
            }
        }
        // 第0行上有0，那么第0行变0
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                rowZeroes = true;
                break;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowZeroes) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
        if (colZeroes) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // 优化 - 减少一个参数。
    public static void setZeroes1(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0] == null || matrix[0].length <= 0) {
            return;
        }
        // 0 0 位置表示这一列是否变0
        boolean col = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    // 如果是第0列。我们不设置这行的，给一个标记
                    if (j == 0) {
                        col = true;
                    } else {
                        matrix[0][j] = 0;
                    }
                }
            }
        }
        // 从下往上，因为第0行的位置我们不代表
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (col) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
