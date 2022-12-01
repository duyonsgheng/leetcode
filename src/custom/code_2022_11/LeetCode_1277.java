package custom.code_2022_11;

/**
 * @ClassName LeetCode_1277
 * @Author Duys
 * @Description
 * @Date 2022/11/30 16:52
 **/
// 1277. 统计全为 1 的正方形子矩阵
public class LeetCode_1277 {
    public static void main(String[] args) {
        //[[0,1,1,1],
        // [1,1,0,1],
        // [1,1,1,1],
        // [1,0,1,0]]
        int[][] m = {{0, 1, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 1}, {1, 0, 1, 0}};
        System.out.println(countSquares(m));
    }

    // 以每一个1作为左上角看看能扩到哪里
    public static int countSquares(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (matrix[0][i] == 1) {
                ans++;
            }
        }
        for (int i = 1; i < n; i++) {
            if (matrix[i][0] == 1) {
                ans++;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                int cur = Math.min(matrix[i - 1][j], Math.min(matrix[i][j - 1], matrix[i - 1][j - 1]));
                if (cur == 0) {
                    ans++;
                } else {
                    matrix[i][j] = cur + 1;
                    ans += matrix[i][j];
                }
            }
        }
        return ans;
    }

    // 以当前点作为左上角看看有多少个正方形
    public static int process(int row, int col, int[][] m, int[][] right, int[][] down) {
        int len = Math.min(right[row][col], down[row][col]);
        int ans = 1;
        // 看看2到len边长的正方形是否存在
        for (int i = 2; i <= len; i++) {
            // 当前来到row 和 col
            int nrow = row + i - 1;
            int ncol = col + i - 1;
            if (nrow >= m.length || ncol >= m[0].length) {
                continue;
            }
        }
        return ans;
    }

    public static void build(int[][] m, int[][] right, int[][] down) {
        int row = m.length, col = m[0].length;
        // 最后一个位置
        if (m[row - 1][col - 1] == 1) {
            right[row - 1][col - 1] = 1;
            down[row - 1][col - 1] = 1;
        }
        // 算最后一列的答案
        for (int i = row - 2; i >= 0; i--) {
            if (m[i][col - 1] == 1) {
                down[i][col - 1] = down[i + 1][col - 1] + 1;
                right[i][col - 1] = 1;
            }
        }
        // 算最后一行的答案
        for (int i = col - 2; i >= 0; i--) {
            if (m[row - 1][i] == 1) {
                right[row - 1][i] = right[row - 1][i + 1] + 1;
                down[row - 1][i] = 1;
            }
        }
        // 普遍位置
        for (int r = row - 2; r >= 0; r--) {
            for (int c = col - 2; c >= 0; c--) {
                if (m[r][c] == 1) {
                    down[r][c] = down[r + 1][c] + 1;
                    right[r][c] = right[r][c + 1] + 1;
                }
            }
        }
    }
}
