package hope.unionFind;

/**
 * @author Mr.Du
 * @ClassName Code05_NumberOfIslands
 * @date 2023年11月10日 10:08
 */
// 岛屿数量
// 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成
// 此外，你可以假设该网格的四条边均被水包围
// 测试链接 : https://leetcode.cn/problems/number-of-islands/
public class Code05_NumberOfIslands {
    // 岛屿问题，使用并查集可以做
    // 但是最优解不是并查集，是洪水填充
    public static int numIslands(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        build(n, m, board);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '1') {
                    // 看看和左边的能不能合并
                    if (i > 0 && board[i - 1][j] == '1') {
                        union(i, j, i - 1, j);
                    }
                    // 看看和上边的能不能合并
                    if (j > 0 && board[i][j - 1] == '1') {
                        union(i, j, i, j - 1);
                    }
                }
            }
        }
        return sets;
    }

    public static int MAXSIZE = 100001;
    public static int[] father = new int[MAXSIZE];

    public static int cols;

    public static int sets;

    public static void build(int n, int m, char[][] board) {
        cols = m;
        sets = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0, index; j < m; j++) {
                if (board[i][j] == '1') {
                    index = index(i, j);
                    father[index] = index;
                    sets++;
                }
            }
        }
    }

    public static int find(int x) {
        if (x != father[x]) {
            father[x] = find(father[x]);
        }
        return father[x];
    }

    public static void union(int x1, int y1, int x2, int y2) {
        int f1 = find(index(x1, y1));
        int f2 = find(index(x2, y2));
        if (f1 == f2) {
            return;
        }
        father[f1] = f2;
        sets--;
    }

    public static int index(int a, int b) {
        return a * cols + b;
    }
}
