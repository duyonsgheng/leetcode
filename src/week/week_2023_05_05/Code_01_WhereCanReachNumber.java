package week.week_2023_05_05;

/**
 * @ClassName Code_01_WhereCanReachNumber
 * @Author Duys
 * @Description
 * @Date 2023/6/2 13:36
 **/
// 来自字节
// 给定一个n*m的二维矩阵，每个位置都是字符
// U、D、L、R表示传送带的位置，会被传送到 : 上、下、左、右
// . 、O分别表示空地、目标，一定只有一个目标点
// 可以在空地上选择上、下、左、右四个方向的一个
// 到达传送带的点会被强制移动到其指向的下一个位置
// 如果越界直接结束，返回有几个点可以到达O点
public class Code_01_WhereCanReachNumber {
    // 从o点开始往外宽度优先遍历

    public static int number(char[][] map) {
        int n = map.length;
        int m = map[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] queue = new int[n * m][2]; // 记录行坐标 和 列坐标 // 当然也可以用经典的Dequeue，为了节省空间，使用数组
        int l = 0, r = 0, ans = 0;
        // 先找到o
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'O') {
                    visited[i][j] = true;
                    queue[r][0] = i;
                    queue[r++][1] = j;
                    break;
                }
            }
        }
        // 如果队列中还有
        while (l < r) {
            ans++;// 来到一个点 算一次
            int[] cur = queue[l++];
            int x = cur[0];
            int y = cur[1];
            // 从我的上边到的我
            if (x - 1 >= 0 && (map[x - 1][y] == 'D' || map[x - 1][y] == '.') && !visited[x - 1][y]) {
                visited[x - 1][y] = true;
                queue[r][0] = x - 1;
                queue[r++][1] = y;
            }
            // 从我的下边到的我
            if (x + 1 < n && (map[x + 1][y] == 'U' || map[x + 1][y] == '.') && !visited[x + 1][y]) {
                visited[x + 1][y] = true;
                queue[r][0] = x + 1;
                queue[r++][1] = y;
            }
            // 从我的左边到的我
            if (y - 1 >= 0 && (map[x][y - 1] == 'R' || map[x][y - 1] == '.') && !visited[x][y - 1]) {
                visited[x][y - 1] = true;
                queue[r][0] = x;
                queue[r++][1] = y - 1;
            }
            // 从我的右边到的我
            if (y + 1 < m && (map[x][y + 1] == 'L' || map[x][y + 1] == '.') && !visited[x][y + 1]) {
                visited[x][y + 1] = true;
                queue[r][0] = x;
                queue[r++][1] = y + 1;
            }
        }
        return ans;
    }
}
