package duys_code.day_01;

/**
 * @ClassName MaxStemp_Code_05
 * @Author Duys
 * @Description T
 * @Date 2021/9/15 14:40
 **/
public class Code_05_MaxStemp {
    /**
     * 给定一个二维数组matrix，
     * 你可以从任何位置出发，走向上下左右四个方向
     * 返回能走出来的最长的递增链长度
     */
    public static int max1(int[][] matrix) {
        int ans = 0;
        // 行
        int N = matrix.length;
        // 列
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    // 当前来到i，j位置做选择，可以往上下左右走，只要不越界
    public static int process1(int[][] matrix, int i, int j) {
        // 向上走，并且要是递增链，意思就是走向下一步的值比当前值要大，从0开始的， i>0 就保障了 i-1 不会越界 ， i 和 j 初始位置不会越界
        int up = i > 0 && matrix[i][j] < matrix[i - 1][j] ? process1(matrix, i - 1, j) : 0;
        // 下走
        int down = (i < (matrix.length - 1)) && (matrix[i][j] < matrix[i + 1][j]) ? process1(matrix, i + 1, j) : 0;
        // 左走
        int left = j > 0 && matrix[i][j] < matrix[i][j - 1] ? process1(matrix, i, j - 1) : 0;
        // 右走
        int right = j < matrix[0].length - 1 && matrix[i][j] < matrix[i][j + 1] ? process1(matrix, i, j + 1) : 0;

        // 返回从哪里走最大的链路
        return Math.max(Math.max(down, up), Math.max(left, right)) + 1; // 要加上从i,j 位置过去的这一步
    }

    // 傻缓存，严格位置依赖有点难度，请不要花费太多时间来做位置依赖，情况太多了
    public static int max2(int[][] matrix) {
        int ans = 0;
        // 行
        int N = matrix.length;
        // 列
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process2(matrix, i, j, dp));
            }
        }
        return ans;
    }

    // 当前来到i，j位置做选择，可以往上下左右走，只要不越界
    public static int process2(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        // 向上走，并且要是递增链，意思就是走向下一步的值比当前值要大，从0开始的， i>0 就保障了 i-1 不会越界 ， i 和 j 初始位置不会越界
        int up = i > 0 && matrix[i][j] < matrix[i - 1][j] ? process2(matrix, i - 1, j, dp) : 0;
        // 下走
        int down = (i < (matrix.length - 1)) && (matrix[i][j] < matrix[i + 1][j]) ? process2(matrix, i + 1, j, dp) : 0;
        // 左走
        int left = j > 0 && matrix[i][j] < matrix[i][j - 1] ? process2(matrix, i, j - 1, dp) : 0;
        // 右走
        int right = j < matrix[0].length - 1 && matrix[i][j] < matrix[i][j + 1] ? process2(matrix, i, j + 1, dp) : 0;
        // 返回从哪里走最大的链路
        // 要加上从i,j 位置过去的这一步
        int ans = Math.max(Math.max(down, up), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }
}
