package duys_code.day_39;

/**
 * @ClassName Code_04_Jump
 * @Author Duys
 * @Description
 * @Date 2021/12/21 10:50
 **/
public class Code_04_Jump {

    // 来自京东
    // 给定一个二维数组matrix，matrix[i][j] = k代表:
    // 从(i,j)位置可以随意往右跳<=k步，或者从(i,j)位置可以随意往下跳<=k步
    // 如果matrix[i][j] = 0，代表来到(i,j)位置必须停止
    // 返回从matrix左上角到右下角，至少要跳几次
    // 已知matrix中行数n <= 5000, 列数m <= 5000
    // matrix中的值，<= 5000
    // 最弟弟的技巧也过了。最优解 -> dp+枚举优化(线段树，体系学习班)

    // 暴力方法，我就两个位置都跳
    public static int jump1(int[][] m) {
        return process1(m, 0, 0);
    }

    public static int process1(int[][] m, int row, int col) {
        // 已经来到了最右下角的位置了
        if (row == m.length - 1 && col == m[0].length - 1) {
            return 0;
        }
        // 来到当前位置，发现跳不动了，没办法
        if (m[row][col] == 0) {
            return Integer.MAX_VALUE;
        }
        int next = Integer.MIN_VALUE;
        // 往右
        for (int right = col + 1; right < m[0].length && (right - col) <= m[row][col]; right++) {
            next = Math.min(next, process1(m, row, right));
        }
        // 往下
        for (int down = row + 1; down < m.length && (down - row) <= m[row][col]; down++) {
            next = Math.min(next, process1(m, down, col));
        }
        // 算上当前这一步
        return next == Integer.MAX_VALUE ? next : next + 1;
    }

    // 优化掉枚举行为
    // 在这个范围上查询最小的，是啥结构，线段树
    // 我们把每一行当成一个线段树
    // 把每一列当成一个线段树
    public static int jump2(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        // 因为线段树是从1开始的，所以我们补了一行一列
        int[][] map = new int[n + 1][m + 1];
        for (int a = 0, b = 1; a < n; a++, b++) {
            for (int c = 0, d = 1; c < m; c++, d++) {
                map[b][d] = arr[a][c];
            }
        }
        SegmentTree[] rowTrees = new SegmentTree[n + 1];
        for (int i = 1; i < n + 1; i++) {
            rowTrees[i] = new SegmentTree(m);
        }
        SegmentTree[] colTrees = new SegmentTree[m + 1];
        for (int i = 1; i < m + 1; i++) {
            colTrees[i] = new SegmentTree(n);
        }
        // 最后一个位置是0
        rowTrees[n].update(m, m, 0, 1, m, 1);
        colTrees[m].update(n, n, 0, 1, n, 1);
        // 最后一列
        for (int col = m - 1; col >= 1; col--) {
            if (map[n][col] != 0) {
                // 当前位置的左边范围
                int left = col + 1;
                int right = Math.min(col + map[n][col], m);
                int next = rowTrees[n].query(left, right, 1, m, 1);
                if (next != Integer.MAX_VALUE) {
                    rowTrees[n].update(col, col, next + 1, 1, m, 1);
                    colTrees[col].update(n, n, next + 1, 1, n, 1);
                }
            }
        }
        // 最后一行
        for (int row = n - 1; row >= 1; row--) {
            if (map[row][m] != 0) {
                // 当前位置的左边范围
                int up = row + 1;
                int down = Math.min(row + map[row][m], n);
                int next = colTrees[n].query(up, down, 1, n, 1);
                if (next != Integer.MAX_VALUE) {
                    rowTrees[row].update(m, m, next + 1, 1, m, 1);
                    colTrees[m].update(row, row, next + 1, 1, n, 1);
                }
            }
        }
        // 普遍位置
        for (int row = n - 1; row >= 1; row--) {
            for (int col = m - 1; col >= 1; col--) {
                if (map[row][col] == 0) {
                    continue;
                }
                int left = col + 1;
                int right = Math.min(col + map[row][col], m);
                int next1 = rowTrees[row].query(left, right, 1, m, 1);

                int up = row + 1;
                int dowm = Math.max(row + map[row][col], n);
                int next2 = colTrees[col].query(up, dowm, 1, n, 1);
                int next = Math.min(next1, next2);
                if (next != Integer.MAX_VALUE) {
                    rowTrees[row].update(col, col, next + 1, 1, m, 1);
                    colTrees[col].update(row, row, next + 1, 1, n, 1);
                }
            }
        }
        return rowTrees[1].query(1, 1, 1, m, 1);
    }

    public static class SegmentTree {
        private int[] min;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int size) {
            int n = size + 1;
            min = new int[size << 2];
            change = new int[size << 2];
            update = new boolean[size << 2];
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                update[rt] = true;
                change[rt] = C;
                min[rt] = C;
                return;
            }
            int mid = (l + r) / 2;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r >= R) {
                return min[rt];
            }
            int mid = (l + r) / 2;
            pushDown(rt, mid - l + 1, r - mid);
            int left = Integer.MAX_VALUE;
            int right = Integer.MAX_VALUE;
            if (L <= mid) {
                left = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.min(left, right);
        }

        private void pushUp(int rt) {
            min[rt] = Math.min(min[rt << 1], min[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                min[rt << 1] = change[rt];
                min[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }
        }
    }
}
