package duys_code.day_30;

/**
 * @ClassName Code_01_79_LeetCode
 * @Author Duys
 * @Description 力扣： https://leetcode-cn.com/problems/word-search/
 * @Date 2021/11/26 13:11
 **/
public class Code_01_79_LeetCode {
    /**
     * 在一个 m*n的矩阵中，查询是否有target这个单词
     */
    public static boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process1(board, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 当前来到i j位置，从i j位置出发能不能搞定 word从index出发以及往后的所有字符
    // 0~index-1 的所有字符不用考虑了，已经搞定了
    public static boolean process1(char[][] board, int i, int j, char[] word, int index) {
        if (index == word.length) {
            return true;
        }
        // 深度优先遍历

        if (board[i][j] != word[index]) {
            return false;
        }
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) {
            return false;
        }
        char tmp = board[i][j];
        board[i][j] = 0;
        boolean success = (process1(board, i - 1, j, word, index + 1)) || (process1(board, i + 1, j, word, index + 1)) ||
                (process1(board, i, j - 1, word, index + 1)) || (process1(board, i, j + 1, word, index + 1));
        board[i][j] = tmp;
        return success;
    }

    public static void main(String[] args) {
        char[][] b = new char[][]{{'a'}};
        System.out.println(exist(b, "a"));
    }
}
