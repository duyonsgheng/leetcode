package duys_code.day_28;

/**
 * @ClassName Code_09_36_LettCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/valid-sudoku/
 * @Date 2021/11/23 15:58
 **/
public class Code_09_36_LeetCode {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10]; // 第i行出现了哪个数字
        boolean[][] col = new boolean[9][10]; // 第i列出现了哪个数字
        boolean[][] bucket = new boolean[9][10]; // 第i个桶出现了哪个数字
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // 第几号桶
                int bind = 3 * (i / 3) + (j / 3);
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                // 看看列 行 桶里面出现了没
                if (row[i][num] || col[j][num] || bucket[bind][num]) {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                bucket[bind][num] = true;
            }
        }
        return true;
    }
}
