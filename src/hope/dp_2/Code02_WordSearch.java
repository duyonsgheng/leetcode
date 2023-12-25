package hope.dp_2;

/**
 * @author Mr.Du
 * @ClassName Code02_WordSearch
 * @date 2023年12月11日 10:27
 */
// 单词搜索（无法改成动态规划）
// 给定一个 m x n 二维字符网格 board 和一个字符串单词 word
// 如果 word 存在于网格中，返回 true ；否则，返回 false 。
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成
// 其中"相邻"单元格是那些水平相邻或垂直相邻的单元格
// 同一个单元格内的字母不允许被重复使用
// 测试链接 : https://leetcode.cn/problems/word-search/
public class Code02_WordSearch {
    public static boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (f(board, i, j, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 来到i,j位置，能不能走出来w[k....]
    // 带路径的递归，改不了动态规划
    public static boolean f(char[][] arr, int i, int j, char[] w, int k) {
        if (k == w.length) {
            return true;
        }
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] != w[k]) {
            return false;
        }
        // 如果还有位置 ，并且 i,j位置元素 = w[k]
        char cur = arr[i][j];
        arr[i][j] = 0;
        boolean ans = f(arr, i - 1, j, w, k + 1) || f(arr, i + 1, j, w, k + 1) || f(arr, i, j - 1, w, k + 1) || f(arr, i, j + 1, w, k + 1);
        arr[i][j] = cur;
        return ans;
    }
}
