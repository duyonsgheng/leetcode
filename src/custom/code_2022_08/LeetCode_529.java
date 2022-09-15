package custom.code_2022_08;

/**
 * @ClassName LeetCode_529
 * @Author Duys
 * @Description
 * @Date 2022/8/22 17:36
 **/
// 529. 扫雷游戏
public class LeetCode_529 {


    // 1.如果当前点是 未被挖出的雷 那么直接改为X 返回了
    // 2.当前点击的是未被挖出的空方块，统计周围相邻方块中有多少雷 count ，如果count=0，那么执行规则2，将其改为B ，然后递归处理周围 8个 未被挖出的方块。
    // 递归的终止条件是规则4，没有更多的方块可被揭露，否则主席那个规则3，将其改为数字

    // 8个方向移动
    int[] actionX = {0, 1, 0, -1, 1, 1, -1, -1};
    int[] actionY = {1, 0, -1, 0, 1, -1, 1, -1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int sX = click[0];
        int sY = click[1];
        // 雷就直接结束
        if (board[sX][sY] == 'M') {
            board[sX][sY] = 'X';
        }
        // 跑一个深度优先
        else {
            dfs(board, sX, sY);
        }
        return board;
    }

    public void dfs(char[][] arr, int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int tX = x + actionX[i];
            int tY = y + actionY[i];
            if (tX < 0 || tX >= arr.length || tY < 0 || tY >= arr[0].length) {
                continue;
            }
            if (arr[tX][tY] == 'M') {
                count++;
            }
        }
        // 走规则3
        if (count > 0) {
            arr[x][y] = (char) (count + '0');
        } else {
            arr[x][y] = 'B';
            for (int i = 0; i < 8; i++) {
                int tX = x + actionX[i];
                int tY = y + actionY[i];
                if (tX < 0 || tX >= arr.length || tY < 0 || tY >= arr[0].length || arr[tX][tY] != 'E') {
                    continue;
                }
                dfs(arr, tX, tY);
            }
        }
    }
}
