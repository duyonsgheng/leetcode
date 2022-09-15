package week.week_2022_07_02;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName Code_03_LeetCode_778
 * @Author Duys
 * @Description
 * @Date 2022/7/14 10:37
 **/
// 778. 水位上升的泳池中游泳
// https://leetcode.cn/problems/swim-in-rising-water/
public class Code_03_LeetCode_778 {
    /**
     * 两种解法：
     * 1.并查集
     * 2.dijkstra
     */


    // 并查集
    public static int swimInWater(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        // 对数据进行封装一下
        // [0,0,3] -> (0,0)这个点是3
        int[][] points = new int[row * col][3];
        int pIndex = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                points[pIndex][0] = i;
                points[pIndex][1] = j;
                points[pIndex++][2] = grid[i][j];
            }
        }
        // 根据天数排个序
        Arrays.sort(points, (a, b) -> a[2] - b[2]);
        // 初始化并查集
        UnionFind uf = new UnionFind(row, col);
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            int r = points[i][0];
            int c = points[i][1];
            int time = points[i][2];
            // 上下左右去链接 合并
            // 如果我得上边 是小于我的可以合并。
            if (r > 0 && grid[r - 1][c] <= time) {
                uf.union(r, c, r - 1, c);
            }
            // 下边
            if (r < row - 1 && grid[r + 1][c] <= time) {
                uf.union(r, c, r + 1, c);
            }
            // 左边
            if (c > 0 && grid[r][c - 1] <= time) {
                uf.union(r, c, r, c - 1);
            }
            // 右边
            if (c < col - 1 && grid[r][c + 1] <= time) {
                uf.union(r, c, r, c + 1);
            }
            // 开头和结束位置 已经合并到一起了
            if (uf.isOnce(0, 0, row - 1, col - 1)) {
                ans = time;
                break;
            }
        }
        return ans;
    }

    public static class UnionFind {
        public int col;// 需要把二维矩阵转换成一维
        public int pointSize;
        public int[] father;
        public int[] size;
        public int[] help;

        public UnionFind(int n, int m) {
            col = n;
            pointSize = n * m;
            father = new int[pointSize];
            size = new int[pointSize];
            help = new int[pointSize];
            for (int i = 0; i < pointSize; i++) {
                father[i] = i;
                size[i] = 1;
            }
        }

        private int index(int x, int y) {
            return x * col + y;
        }

        private int find(int p) {
            int index = 0;
            // 一直往上找
            while (p != father[p]) {
                help[index++] = p;
                p = father[p];
            }
            // 整个链路上都改为p
            while (index > 0) {
                father[help[--index]] = p;
            }
            return p;
        }

        public boolean isOnce(int x1, int y1, int x2, int y2) {
            return find(index(x1, y1)) == find(index(x2, y2));
        }

        public void union(int x1, int y1, int x2, int y2) {
            int p1 = find(index(x1, y1));
            int p2 = find(index(x2, y2));
            if (p1 == p2) {
                return;
            }
            if (size[p1] > size[p2]) {
                size[p1] += size[p2];
                father[p2] = p1;
            } else {
                size[p2] += size[p1];
                father[p1] = p2;
            }
        }
    }

    // dijkstra得解法
    public static int swimInWater1(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[n][m];
        heap.add(new int[]{0, 0, grid[0][0]});
        int ans = 0;
        while (!heap.isEmpty()) {
            int[] curs = heap.poll();
            int r = curs[0];
            int c = curs[1];
            int t = curs[2];
            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;
            // 到达目标了
            if (r == n - 1 && c == m - 1) {
                ans = t;
                return ans;
            }
            // 否则四个方向去搞
            add(heap, visited, grid, r - 1, c, t);
            add(heap, visited, grid, r + 1, c, t);
            add(heap, visited, grid, r, c - 1, t);
            add(heap, visited, grid, r, c + 1, t);
        }
        // 说明到不了
        return -1;
    }

    public static void add(PriorityQueue<int[]> heap, boolean[][] visited, int[][] grid, int r, int c, int pre) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && !visited[r][c]) {
            heap.add(new int[]{r, c, pre + Math.max(0, grid[r][c] - pre)});
        }
    }
}
