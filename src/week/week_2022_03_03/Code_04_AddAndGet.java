package week.week_2022_03_03;

import java.util.Arrays;

/**
 * @ClassName Code_04_AddAndGet
 * @Author Duys
 * @Description TODO
 * @Date 2022/3/17 15:54
 **/
public class Code_04_AddAndGet {
// 来自美团
// void add(int L, int R, int C)代表在arr[L...R]上每个数加C
// int get(int L, int R)代表查询arr[L...R]上的累加和
// 假设你可以在所有操作开始之前，重新排列arr
// 请返回每一次get查询的结果都加在一起最大能是多少
// 输入参数:
// int[] arr : 原始数组
// int[][] ops，二维数组每一行解释如下:
// [a,b,c]，如果数组有3个数，表示调用add(a,b,c)
// [a,b]，如果数组有2个数，表示调用get(a,b)
// a和b表示arr范围，范围假设从1开始，不从0开始
// 输出：
// 假设你可以在开始时重新排列arr，返回所有get操作返回值累计和最大是多少？

    // 这个题意就是我们按照get次数最多的位置，进行add

    public static int maxGets(int[] arr, int[][] ops) {
        int n = arr.length;
        SegmentTree getTree = new SegmentTree(n);
        for (int[] op : ops) {
            // 统计get的次数
            if (op.length == 2) {
                getTree.add(op[0], op[1], 1);
            }
        }
        // 位置对应的get次数
        int[][] getCnts = new int[n][2];
        for (int i = 1, j = 0; i <= n; i++, j++) {
            getCnts[i][0] = j; // 对应的下标
            getCnts[i][1] = getTree.get(i, i); // 被get的次数
        }
        // 根据get次数排序
        Arrays.sort(getCnts, (a, b) -> a[1] - b[1]);
        Arrays.sort(arr);
        int[] reArrange = new int[n];
        // 拿到我们get次数的从低到高排序
        for (int i = 0; i < n; i++) {
            reArrange[getCnts[i][0]] = arr[i];
        }
        // 根据对应位置的get
        SegmentTree segmentTree = new SegmentTree(reArrange);
        int ans = 0;
        for (int[] op : ops) {
            if (op.length == 3) {
                segmentTree.add(op[0], op[1], op[2]);
            } else {
                ans += segmentTree.get(op[0], op[1]);
            }
        }
        return ans;
    }

    public static class SegmentTree {
        private int n;
        private int[] arr;
        private int[] sum;
        private int[] lazy;

        public SegmentTree(int size) {
            n = size + 1;
            lazy = new int[n << 2];
            sum = new int[n << 2];
            n--;
        }

        public SegmentTree(int[] org) {
            n = org.length + 1;
            arr = new int[n];
            for (int i = 1; i < n; i++) {
                arr[i] = org[i - 1];
            }
            sum = new int[n << 2];
            lazy = new int[n << 2];
            build(1, --n, 1);
        }

        private void build(int l, int r, int root) {
            if (l == r) {
                sum[root] = arr[l];
                return;
            }
            int mid = l + (r - l) >> 1;
            build(l, mid, root << 1);
            build(mid + 1, r, root << 1 | 1);
            pushUp(root);
        }

        private void pushUp(int root) {
            sum[root] = sum[root << 1] + sum[root << 1 | 1];
        }

        private void pushDown(int root, int ln, int rn) {
            if (lazy[root] != 0) {
                lazy[root << 1] += lazy[root];
                sum[root << 1] += lazy[root] * ln;

                lazy[root << 1 | 1] += lazy[root];
                sum[root << 1 | 1] += lazy[root] * rn;

                lazy[root] = 0;
            }
        }

        public void add(int L, int R, int C) {
            add(L, R, C, 1, n, 1);
        }

        private void add(int L, int R, int C, int l, int r, int root) {
            if (L <= l && R >= r) {
                sum[root] += C * (r - l + 1);
                lazy[root] += C;
                return;
            }
            int mid = L + (R - L) >> 1;
            pushDown(root, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, root << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, root << 1 | 1);
            }
            pushUp(root);
        }

        public int get(int L, int R) {
            return query(L, R, 1, n, 1);
        }

        private int query(int L, int R, int l, int r, int root) {
            if (L <= l && R >= r) {
                return sum[root];
            }
            int mid = l + (r - l) >> 1;
            pushDown(root, l - mid + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, root << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, root << 1 | 1);
            }
            return ans;
        }
    }
}
