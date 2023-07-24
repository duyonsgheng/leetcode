package custom.code_2023_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1958
 * @date 2023年07月14日
 */
// https://leetcode.cn/problems/check-if-move-is-legal/
// 1958. 检查操作是否合法
public class LeetCode_1958 {
    public static int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        // 以当前点向四周发散
        // 8种可能
        List<Character> cur = new ArrayList<>();
        for (int i = 0; i < dirs.length; i++) {
            cur.clear();
            cur.add(color);
            process(cur, board, rMove, cMove, dirs[i]);
            // 看看当前的cur是否合法
            // 首尾相等
            if (check(cur)) {
                return true;
            }
        }
        return false;
    }

    public static boolean check(List<Character> cur) {
        if (cur.size() >= 3) {
            // B W W B B W
            int r = 1;
            while (r < cur.size()) {
                // 最左与第一个相等的位置
                if (cur.get(0) == cur.get(r)) {
                    break;
                }
                r++;
            }
            // 一路都不相等，则可以返回失败了、
            if (r == cur.size()) {
                return false;
            }
            // 不足三个了
            if (r + 1 < 3) {
                return false;
            }
            for (int j = 1; j < r; j++) {
                // 不用看了
                if (cur.get(j) == '.' || cur.get(j) == cur.get(0)) {
                    return false;
                }
                if (j > 1 && cur.get(j) != cur.get(j - 1)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void process(List<Character> cur, char[][] board, int r, int c, int[] dir) {
// 下一个要去的位置
        int rn = r + dir[0];
        int cn = c + dir[1];
        if (rn >= 0 && rn < 8 && cn >= 0 && cn < 8) {
            cur.add(board[rn][cn]);
            process(cur, board, rn, cn, dir);
        }
    }

    public static void main(String[] args) {
        // board = {{'.','.','.','B','.','.','.','.'},{'.','.','.','W','.','.','.','.'},{'.','.','.','W','.','.','.','.'},{'.','.','.','W','.','.','.','.'},{'W','B','B','.','W','W','W','B'},{'.','.','.','B','.','.','.','.'},{'.','.','.','B','.','.','.','.'},{'.','.','.','W','.','.','.','.'}},
        // rMove = 4, cMove = 3, color = "B"
        System.out.println(checkMove(new char[][]{{'B', 'W', '.', 'W', '.', 'W', 'W', '.'}, {'B', 'W', '.', '.', 'W', 'W', 'W', '.'}, {'W', 'B', '.', 'W', 'W', 'W', 'W', '.'}, {'B', 'B', 'W', '.', 'W', '.', '.', 'W'}, {'B', '.', 'B', 'B', 'B', '.', 'B', '.'}, {'B', 'W', 'B', '.', 'W', 'B', 'B', '.'}, {'.', '.', '.', 'B', 'W', 'B', '.', '.'}, {'W', 'B', '.', 'B', '.', '.', 'B', 'W'}}
                , 2
                , 7
                , 'B'));
    }

}
