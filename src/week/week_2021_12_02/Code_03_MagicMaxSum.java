package week.week_2021_12_02;

import java.util.Arrays;

/**
 * @ClassName Code_03_MagicMaxSum
 * @Author Duys
 * @Description
 * @Date 2022/4/15 17:35
 **/
// arr数组长度为n, magic数组长度为m
// 含义：
// arr = { 3, 1, 4, 5, 7 }
// 如果完全不改变arr中的值，那么收益就是累加和 = 3 + 1 + 4 + 5 + 7 = 20
// magics = {
//      {0,2,5} 表示arr[0~2]中的任何一个值，都能改成5
//      {3,4,3} 表示arr[3~4]中的任何一个值，都能改成3
//      {1,3,7} 表示arr[1~3]中的任何一个值，都能改成7
// }
// 就是说，magics中的任何一组数据{a,b,c}，都表示一种操作，
// 你可以选择arr[a~b]中任何一个数字，变成c。
// 并且每一种操作，都可以执行任意次
// 其中 0 <= a <= b < n
// 那么经过若干次的魔法操作，你当然可能得到arr的更大的累加和
// 返回arr尽可能大的累加和
// n <= 10^7
// m <= 10^6
// arr中的值和c的范围 <= 10^12
public class Code_03_MagicMaxSum {
    // 暴力解，写出来为了验证正式方法而已
    public static int maxSum1(int[] arr, int[][] magics) {
        int[] help = Arrays.copyOf(arr, arr.length);
        for (int[] m : magics) {
            int l = m[0];
            int r = m[1];
            int mc = m[2];
            for (int i = l; i < r; i++) {
                help[i] = Math.max(help[i], mc);
            }
        }
        int sum = 0;
        for (int num : help) {
            sum += num;
        }
        return sum;
    }

    // 线段树的解答。维持一个区间内最大的值
    public static int maxSum2(int[] arr, int[][] magics) {
        int n = arr.length;
        // 线段树的下标是从1开始的
        SegmentTree1 st1 = new SegmentTree1(n);
        // 根据可以改变的值排序
        Arrays.sort(magics, (a, b) -> a[2] - b[2]);
        for (int[] m : magics) {
            // magics 是按照从小到大排序的，那么就把对应位置的值更新到对应的位置去
            st1.update(m[0] + 1, m[1] + 1, m[2], 1, n, 1);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 去线段树上查询，
            ans += Math.max(arr[i], st1.query(i + 1, i + 1, 1, n, 1));
        }
        return ans;
    }

    public static class SegmentTree1 {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree1(int n) {
            int size = n + 1;
            max = new int[size << 2];
            change = new int[size << 2];
            update = new boolean[size << 2];
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (!update[rt]) {
                return;
            }
            // 懒住的信息往下更新
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            max[rt << 1] = change[rt];
            max[rt << 1 | 1] = change[rt];
            update[rt] = false;
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                update[rt] = true;
                change[rt] = C;
                max[rt] = C;
                return;
            }
            int mid = L + ((R - L) >> 1);
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
                return max[rt];
            }
            int mid = l + ((r - l) >> 1);
            pushDown(rt, mid - l + 1, r - mid);
            int left = 0;
            int right = 0;
            if (L <= mid) {
                left = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = query(L, R, mid + 1, r, rt << 1 | 1);
            }
            // 返回这个区间最大的
            return Math.max(left, right);
        }
    }
}
