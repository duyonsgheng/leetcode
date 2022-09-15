package duys_code.day_31;

/**
 * @ClassName Code_03_130LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/surrounded-regions/
 * @Date 2021/11/29 16:43
 **/
public class Code_03_130_LeetCode {
    // 岛问题的衍生，一路感染

    public void solve(char[][] board) {
        if (board == null || board.length <= 2 || board[0] == null || board[0].length <= 2) {
            return;
        }
        int N = board.length;
        int M = board[0].length;
        // 四个最外层的边去感染

        for (int i = 0; i < N; i++) {
            // 左边的边
            if (board[i][0] == 'O') {
                can(board, i, 0);
            }
            // 最右的边
            if (board[i][M - 1] == 'O') {
                can(board, i, M - 1);
            }
        }

        for (int i = 1; i < M - 1; i++) {
            // 最上的边
            if (board[0][i] == 'O') {
                can(board, 0, i);
            }
            // 最下的边
            if (board[N - 1][i] == 'O') {
                can(board, N - 1, i);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'F') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // 从这儿开始，上下左右，去感染去吧，并且一路改
    public static void can(char[][] arr, int i, int j) {
        if (i < 0 || i == arr.length || j < 0 || j == arr[0].length || arr[i][j] != 'O') {
            return;
        }
        arr[i][j] = 'F';
        can(arr, i + 1, j);
        can(arr, i - 1, j);
        can(arr, i, j + 1);
        can(arr, i, j - 1);
    }

}
