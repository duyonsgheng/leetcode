package week.week_2022_07_02;

import java.util.Arrays;

/**
 * @ClassName Code_05_LeetCode_850
 * @Author Duys
 * @Description
 * @Date 2022/7/14 14:10
 **/
// 850. 矩形面积 II
// 天际线问题之一
// 也叫轮廓线问题
// 返回 总面积 。因为答案可能太大，返回 10^9 + 7 的 模 。
public class Code_05_LeetCode_850 {
    // 我们计算每一个举行得左边 和 右边
    // 每一个矩形得左边 就+1 右边就-1 ，表示区域内整体入线段树和整体出线段树
    public static int rectangleArea(int[][] rectangles) {
        int n = rectangles.length;
        // 每x轴上的点 在y轴上的投影
        // 比如
        // x轴 1 y轴上 y1 ~ y2
        // x轴 3 y轴   y1 ~ y2
        // 这样就能表示一个矩形了，也能算面积
        // 所以我们只需要每一次在x轴上加入一条线，然后结束位置的时候减去整个区域
        long[][] arr = new long[n << 1][4];
        long max = 0;
        for (int i = 0; i < n; i++) {
            int x1 = rectangles[i][0];
            // 线段是从1开始
            int y1 = rectangles[i][1] + 1;
            int x2 = rectangles[i][2];
            int y2 = rectangles[i][3];

            arr[i][0] = x1;
            arr[i][1] = y1;
            arr[i][2] = y2;
            arr[i][3] = 1; // 加入  当前矩形的开始

            arr[i + n][0] = x2;
            arr[i + n][1] = y1;
            arr[i + n][2] = y2;
            arr[i + n][3] = -1; // 要减去 当前矩形的结束
            max = Math.max(max, y2);
        }
        return cover(arr, n << 1, max);
    }

    // 所有的事件都整理在arr中
    public static int cover(long[][] arr, int n, long max) {
        // 以x轴谁先出现，就先算
        Arrays.sort(arr, (a, b) -> a[0] <= b[0] ? -1 : 1);
        // max - 用来创建开点线段树
        DynamicSegmentTree segmentTree = new DynamicSegmentTree(max);
        long mod = 1000000007;
        long prex = 0;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // segmentTree.query()查询当前y轴的真实长度，乘以x轴上的距离，就是当前矩形面积
            // 第一条线进来，0 面积
            // 当第二条线进来，就可以算面积了
            ans += segmentTree.query() * (arr[i][0] - prex);
            ans %= mod;
            prex = arr[i][0];
            // 在当前y轴的投影上 + - ，表示用过了和删除了
            segmentTree.add(arr[i][1], arr[i][2], arr[i][3]);
        }
        return (int) ans;
    }

    // 动态开点线段树
    public static class DynamicSegmentTree {
        public Node root;
        public long size;

        public DynamicSegmentTree(long max) {
            root = new Node();
            size = max;
        }

        public void add(long l, long r, long cover) {
            add(root, 1, size, l, r, cover);
        }

        public long query() {
            return root.len;
        }

        private void add(Node cur, long l, long r, long L, long R, long cover) {
            if (L <= l && R >= r) {
                cur.cover += cover;
            } else {
                //
                if (cur.left == null) {
                    cur.left = new Node();
                }
                if (cur.right == null) {
                    cur.right = new Node();
                }
                long mid = l + ((r - l) >> 1);
                if (L <= mid) {
                    add(cur.left, l, mid, L, R, cover);
                }
                if (R > mid) {
                    add(cur.right, mid + 1, r, L, R, cover);
                }
            }
            pushUp(cur, l, r);
        }

        private void pushUp(Node cur, long l, long r) {
            if (cur.cover > 0) {
                cur.len = r - l + 1;
            } else {
                cur.len = (cur.left != null ? cur.left.len : 0) + (cur.right != null ? cur.right.len : 0);
            }
        }
    }

    public static class Node {
        public long cover;
        public long len;
        public Node left;
        public Node right;
    }
}
