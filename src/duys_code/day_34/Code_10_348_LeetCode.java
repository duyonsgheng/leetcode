package duys_code.day_34;

/**
 * @ClassName Code_10_348_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/design-tic-tac-toe/
 * @Date 2021/12/7 15:21
 **/
public class Code_10_348_LeetCode {
    // 设计井字棋
    class TicTacToe {
        private int[][] rows; // 每一行上不同的人下棋的结果
        private int[][] cols; // 每一列上不同的人下棋的结果
        private int[] leftUp; // 左边对角线下棋的结果
        private int[] rightUp;// 右边对角线下棋的结果
        private boolean[][] matrix; // 当前位置是否已经下过棋了
        private int N;

        public TicTacToe(int n) {
            rows = new int[n][3]; //0 1 2
            cols = new int[n][3];
            // leftUp[2] = 7 : 2这个人，在左对角线上，下了7个
            leftUp = new int[3];
            // rightUp[1] = 9 : 1这个人，在右对角线上，下了9个
            rightUp = new int[3];
            matrix = new boolean[n][n];
            N = n;
        }

        // 当前是p这个人来 row 行 col列上下棋
        public int move(int row, int col, int p) {
            if (matrix[row][col]) {
                // 已经下过了，谁也不赢
                return 0;
            }
            matrix[row][col] = true;
            rows[row][p]++;
            cols[col][p]++;
            if (row == col) {
                leftUp[p]++;
            }
            if (row + col == N - 1) {
                rightUp[p]++;
            }
            // 只要有一个下满了N。那么就表示这个人赢了
            if (rows[row][p] == N || cols[col][p] == N || leftUp[p] == N || rightUp[p] == N) {
                return p;
            }
            return 0;
        }
    }
}
