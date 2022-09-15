package duys_code.day_28;

/**
 * @ClassName Code_10_37_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/sudoku-solver/
 * @Date 2021/11/23 16:36
 **/
public class Code_10_37_LeetCode {

    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10]; // 第i行出现了哪个数字
        boolean[][] col = new boolean[9][10]; // 第i列出现了哪个数字
        boolean[][] bucket = new boolean[9][10]; // 第i个桶出现了哪个数字
        init(board, row, col, bucket);
        process(board, 0, 0, row, col, bucket);
    }

    // i j 当前来到的位置
    public static boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        // 来到了最后，说明之前做的决定有效了
        if (i == board.length) {
            return true;
        }
        // i j 的下一个位置
        // 从左往右填的，如果没有到最后一列，i不变，否则要加1
        int nextI = j == 8 ? i + 1 : i;
        // 到了最后一列了，下一次从下一行的第0列开始
        int nextJ = j == 8 ? 0 : j + 1;
        // 如果之前的是 . 说明当前可以填，如果不是 . 那么说明已经有数字了，不需要填了
        if (board[i][j] != '.') {
            return process(board, nextI, nextJ, row, col, bucket);
        } else {
            int bid = 3 * (i / 3) + (j / 3);
            // 尝试 1 ~ 9
            for (int next = 1; next <= 9; next++) {
                // 当前任意有一个已经存在了，就不能尝试了
                if (!row[i][next] && !col[j][next] && !bucket[bid][next]) {
                    row[i][next] = true;
                    col[j][next] = true;
                    bucket[bid][next] = true;
                    // 深度优先遍历
                    board[i][j] = (char) (next + '0');
                    if (process(board, nextI, nextJ, row, col, bucket)) {
                        return true;
                    }
                    row[i][next] = false;
                    col[j][next] = false;
                    bucket[bid][next] = false;
                    board[i][j] = '.';
                }
            }
            return false;
        }
    }

    public static void init(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int bind = 3 * (i / 3) + (j / 3);
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                row[i][num] = true;
                col[j][num] = true;
                bucket[bind][num] = true;
            }
        }
    }
}
