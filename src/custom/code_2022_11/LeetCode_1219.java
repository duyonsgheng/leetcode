package custom.code_2022_11;


/**
 * @ClassName LeetCode_1219
 * @Author Duys
 * @Description
 * @Date 2022/11/28 10:02
 **/
// 1219. 黄金矿工
public class LeetCode_1219 {
    boolean[][] visited;
    int[][] arr;
    int n, m;
    int[][] dist = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int getMaximumGold(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        arr = grid;
        visited = new boolean[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    visited[i][j] = true;
                    ans = Math.max(ans, process(i, j));
                    visited[i][j] = false;
                }
            }
        }

        return ans;
    }

    public int process(int sx, int sy) {
        int ans = arr[sx][sy];
        for (int i = 0; i < 4; i++) {
            int nx = dist[i][0] + sx;
            int ny = dist[i][1] + sy;
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && arr[nx][ny] != 0) {
                visited[nx][ny] = true;
                ans = Math.max(process(nx, ny) + arr[sx][sy], ans);
                visited[nx][ny] = false;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        LeetCode_1219 lc = new LeetCode_1219();
        int[][] arr = new int[][]{
                {1, 0, 7, 0, 0, 0},
                {2, 0, 6, 0, 1, 0},
                {3, 5, 6, 7, 4, 2},
                {4, 3, 1, 0, 2, 0},
                {3, 0, 5, 0, 20, 0}};
        int maximumGold = lc.getMaximumGold(arr);
        System.out.println(maximumGold);
    }
}
