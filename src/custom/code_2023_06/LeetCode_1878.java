package custom.code_2023_06;

/**
 * @ClassName LeetCode_1878
 * @Author Duys
 * @Description
 * @Date 2023/6/13 9:32
 **/
// 1878. 矩阵中最大的三个菱形和
public class LeetCode_1878 {
    int m, n;
    int[][] grid;

    public int[] getBiggestThree(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int[][] sums1 = new int[m][n];
        int[][] sums2 = new int[m][n];
        for (int i = 0; i < n; i++) {
            fill(sums1, 0, i, 1, 1, 0);// 左上到右下累加和
            fill(sums2, 0, i, 1, -1, 0);// 右上到左下累加和
        }
        for (int i = 1; i < m; i++) {
            fill(sums1, i, 0, 1, 1, 0);
            fill(sums2, i, n - 1, 1, -1, 0);
        }
//		PriorityQueue<Integer> queue = new PriorityQueue<>();
        int a = -1, b = -1, c = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 考虑头三个点可能性，取两遍距离短的成菱形
                int max = Math.min(j + 1, n - j);
                for (int k = 0; k < max; k++) {
                    if (i + 2 * k < m) {// 最下点越界，不成菱形
                        int girth = this.girth(i, j, k, sums1, sums2);
                        if (girth == a || girth == b || girth == c) {
                            continue;
                        }
                        if (girth > a) {
                            c = b;
                            b = a;
                            a = girth;
                        } else if (girth > b) {
                            c = b;
                            b = girth;
                        } else if (girth > c) {
                            c = girth;
                        }
                    }
                }
            }
        }
        int size = b != -1 && c != -1 ? 3 : b != -1 ? 2 : 1;
        int[] ans = new int[size];
        ans[0] = a;
        if (b != -1) {
            ans[1] = b;
        }
        if (c != -1) {
            ans[2] = c;
        }
        return ans;
    }

    private int girth(int x, int y, int k, int[][] sums1, int[][] sums2) {
        // k==0 一个点的菱形
        if (k == 0) {
            return this.grid[x][y];
        }
        int x1 = x + k, y1 = y - k;
        int x2 = x + k, y2 = y + k;
        int x3 = x + 2 * k, y3 = y;
        int d1 = this.dist(x1, y1, x, y, sums2, -1, 1);
        int d2 = this.dist(x2, y2, x, y, sums1, 0, 0);
        int d3 = this.dist(x3, y3, x1, y1, sums1, 0, 0);
        int d4 = this.dist(x3, y3, x2, y2, sums2, 0, 0);
        return d1 + d2 + d3 + d4 - this.grid[x3][y3];
    }

    // 两点距离
    private int dist(int s1, int s2, int e1, int e2, int[][] sum, int d1, int d2) {
        e1 += d1;
        e2 += d2;
        // 头越界，不做差
        int e = 0;
        // 头没越界，左差
        if (e1 >= 0 && e1 < m && e2 >= 0 && e2 < n) {
            e = sum[e1][e2];
        }
        return sum[s1][s2] - e;
    }

    private void fill(int[][] sums1, int x, int y, int dx, int dy, int sum) {
        if (x >= 0 && x < m && y >= 0 && y < n) {
            sums1[x][y] = this.grid[x][y] + sum;
            this.fill(sums1, x + dx, y + dy, dx, dy, sums1[x][y]);
        }
    }

}
