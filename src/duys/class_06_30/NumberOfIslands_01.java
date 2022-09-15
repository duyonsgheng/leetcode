package duys.class_06_30;

/**
 * @ClassName NumberOfIslands
 * @Author Duys
 * @Description 力扣原题：https://leetcode-cn.com/problems/number-of-islands/
 * @Date 2021/6/30 14:44
 **/
public class NumberOfIslands_01 {

    /**
     * 题意：把一个二维数组中，相邻位置是'1' 的区域有多少个(相邻的意思只能是上下左右)
     */

    /**
     * 1.递归核心思想是遇到了1，就开始从上下左右四个位置出发，把遇到相邻的1全部改为2
     * 已经是最优解了 O(i*j) -> 每个数遍历一次，整个过程结束
     */
    public static int numIslands(char[][] board) {
        int islands = 0;
        // i 类似矩阵的行
        for (int i = 0; i < board.length; i++) {
            // j 类似矩阵得列
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    infect(board, i, j);
                    islands++;
                }
            }
        }
        return islands;
    }

    public static void infect(char[][] board, int i, int j) {
        // i 越界 j越界 或者 board[i][j] !='1'都直接不进行下去了
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            return;
        }
        board[i][j] = 2;
        // 上下左右继续出发
        infect(board, i - 1, j);
        infect(board, i + 1, j);
        infect(board, i, j - 1);
        infect(board, i, j + 1);
    }
}
