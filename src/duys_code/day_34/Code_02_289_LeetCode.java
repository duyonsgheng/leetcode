package duys_code.day_34;

/**
 * @ClassName Code_02_289_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/game-of-life/
 * @Date 2021/12/6 17:32
 **/
public class Code_02_289_LeetCode {
    // 非常秒的做法
    // 1. 我们把每一个位置的新状态放在之前哪个位置的整数的二进制第二位，老的状态放在二进制的第一位
    // 2. 然后每一个位置八个方向去看看有多少1，满足条件的我们把新的状态放到二进位第二位上
    // 3. 最后我们把每一位向左移1位，把新一轮的状态放到最右边去
    public void gameOfLife(int[][] board) {
        if (board == null || board.length <= 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int fill1 = count(board, i, j);
                // 1.如果当前位置周围是3个或者的细胞，那么当前位置不论死活，都会有一个1
                // 2.如果当前位置本来就是1，而且周围已经有两个1，下一轮还是会活着
                if (fill1 == 3 || (board[i][j] == 1 && fill1 == 2)) {
                    board[i][j] |= 2; // 用二进制位的第二位来表示新得状态
                }
            }
        }
        // 把新得状态覆盖掉来的状态
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    // 看看当前i j位置周围八个方向一共几个1
    public static int count(int[][] b, int i, int j) {
        return fill(b, i - 1, j - 1)
                + fill(b, i - 1, j)
                + fill(b, i - 1, j + 1)

                + fill(b, i + 1, j + 1)
                + fill(b, i + 1, j - 1)
                + fill(b, i + 1, j)

                + fill(b, i, j + 1)
                + fill(b, i, j - 1);
    }

    // 看看当前的i j位置是不是1
    public static int fill(int[][] b, int i, int j) {
        // 因为我们需要用的是i j位置的老状态信息，所以 & 1 就可以了
        return (i >= 0 && i < b.length && j >= 0 && j < b[0].length) && ((b[i][j] & 1) == 1) ? 1 : 0;
    }
}
