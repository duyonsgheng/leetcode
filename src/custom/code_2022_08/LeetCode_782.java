package custom.code_2022_08;

/**
 * @ClassName LeetCode_782
 * @Author Duys
 * @Description
 * @Date 2022/8/23 8:46
 **/
// 782. 变为棋盘
// 一个n x n的二维网络board仅由0和1组成。每次移动，你能任意交换两列或是两行的位置。
//返回 将这个矩阵变为 “棋盘”所需的最小移动次数。如果不存在可行的变换，输出 -1。
//“棋盘” 是指任意一格的上下左右四个方向的值均与本身不同的矩阵。
//链接：https://leetcode.cn/problems/transform-to-chessboard
public class LeetCode_782 {
    /**
     * 分析：
     * 1.棋盘如果有效，那么一定是两种状态交替的
     * 2.n为奇数和偶数分别讨论
     * 3.第一行0开头和1开头分别讨论
     */
    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int rowState = 0;
        int colState = 0;
        // 把第一行和第一列拿出来
        for (int i = 0; i < n; i++) {
            rowState |= (board[0][i] << i);
            colState |= (board[i][0] << i);
        }
        // 判断n是奇数还是偶数
        boolean mod = (n % 2 == 0);
        // 行是0打头还是1打头
        int rowModState = (rowState ^ ((1 << n) - 1));
        // 列是0打头还是1打头
        int colModState = (colState ^ ((1 << n) - 1));

        int rowCount = 0;
        int colCount = 0;
        for (int i = 0; i < n; i++) {
            int curRowState = 0;
            int curColState = 0;
            for (int j = 0; j < n; j++) {
                curRowState |= (board[i][j] << j);
                curColState |= (board[j][i] << j);
            }
            // 检测每一行的状态
            if (curRowState != rowState && curRowState != rowModState) {
                return -1;
            } else if (curRowState == rowState) {
                // 与第一行相同的
                rowCount++;
            }
            // 检测每一列
            if (curColState != colState && curColState != colModState) {
                return -1;
            } else if (curColState == colState) {
                // 与第一列相同的
                colCount++;
            }
        }
        int rowMoves = moves(rowState, rowCount, n);
        int colMoves = moves(colState, colCount, n);
        return (rowMoves == -1 || colMoves == -1) ? -1 : (rowMoves + colMoves);
    }

    public int moves(int state, int count, int n) {
        int onces = Integer.bitCount(state);
        if ((n & 1) == 1) { // 奇数
            // 如果奇数，每一行0和1的差值为1
            if (Math.abs(n - 2 * onces) != 1 || Math.abs(n - 2 * count) != 1) {
                return -1;
            }
            // 0开头
            if (onces == (n >> 1)) {
                return n / 2 - Integer.bitCount(state & 0xAAAAAAAA);
            }
            // 1开头
            else {
                return (n + 1) / 2 - Integer.bitCount(state & 0x55555555);
            }
        } else {
            if (onces != (n >> 1) || count != (n >> 1)) {
                return -1;
            }
            return Math.min(n / 2 - Integer.bitCount(state & 0x55555555), n / 2 - Integer.bitCount(state & 0xAAAAAAAA));
        }
    }

}
