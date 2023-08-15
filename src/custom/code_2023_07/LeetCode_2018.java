package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2018
 * @date 2023年07月31日
 */
// 2018. 判断单词是否能放入填字游戏内
// https://leetcode.cn/problems/check-if-word-can-be-placed-in-crossword/
public class LeetCode_2018 {
    public boolean placeWordInCrossword(char[][] board, String word) {
        String word1 = new StringBuffer(word).reverse().toString(); // 逆序
        int n = board.length, m = board[0].length;
        int len = word.length();
        StringBuffer str1 = new StringBuffer(); // 横向的
        StringBuffer str2 = new StringBuffer(); // 纵向的
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != '#') {
                    str1.append(board[i][j]);
                }
                if (j == m - 1 || board[i][j] == '#') {
                    if (len == str1.length() && (check(word, str1.toString()) || check(word1, str1.toString()))) {
                        return true;
                    }
                    str1 = new StringBuffer();
                }
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (board[i][j] != '#') {
                    str2.append(board[i][j]);
                }
                if (i == n - 1 || board[i][j] == '#') {
                    if (len == str2.length() && (check(word, str2.toString()) || check(word1, str2.toString()))) {
                        return true;
                    }
                    str2 = new StringBuffer();
                }
            }
        }
        return false;
    }

    private boolean check(String s1, String s2) {
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s2.charAt(i) == ' ') continue;
            if (s1.charAt(i) != s2.charAt(i)) return false;
        }
        return true;
    }

}
