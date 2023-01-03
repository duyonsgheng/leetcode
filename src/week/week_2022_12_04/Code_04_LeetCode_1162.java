package week.week_2022_12_04;

import java.util.Arrays;

/**
 * @ClassName Code_04_LeetCode_1162
 * @Author Duys
 * @Description
 * @Date 2022/12/29 11:20
 **/
// 1162. 地图分析
public class Code_04_LeetCode_1162 {
    public int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // 从所有的陆地开始，然后一圈一圈的向外感染
    public int[][] queue = new int[10000][2];
    public int l;
    public int r;
    public boolean[][] visited = new boolean[100][100];
    public int find;// 发现了多少海样了

    public int maxDistance(int[][] grid) {
        l = 0;
        r = 0;
        find = 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < 100; i++) {
            Arrays.fill(visited[i], false);
        }
        // 先把所有的陆地加入队列，并且统计有多少海洋
        int seas = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    visited[i][j] = true;
                    queue[r][0] = i;
                    queue[r++][1] = j;
                } else {
                    seas++;
                }
            }
        }
        int dist = 0;
        while (l < r && find < seas) {
            // 一次搞一层
            int size = r - l;
            for (int i = 0; i < size && find < seas; i++, l++) {
                int x = queue[l][0];
                int y = queue[l][1];
                // 四个方向去走吧
                for (int q = 0; q < 4; q++) {
                    add(x + dirs[q][0], y + dirs[q][1], n, m, grid);
                }
            }
            dist++;
        }
        // 最右没有海洋。就返回-1
        return find == 0 ? -1 : dist;
    }

    public void add(int x, int y, int n, int m, int[][] arr) {
        if (x >= 0 && x < n && y >= 0 && y < m && arr[x][y] == 0 && !visited[x][y]) {
            queue[r][0] = x;
            queue[r++][1] = y;
            visited[x][y] = true;
            find++;
        }
    }
}
