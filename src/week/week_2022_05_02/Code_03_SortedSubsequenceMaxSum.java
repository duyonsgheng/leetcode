package week.week_2022_05_02;

import java.util.Arrays;

/**
 * @ClassName Code_03_SortedSubsequenceMaxSum
 * @Author Duys
 * @Description
 * @Date 2022/5/12 15:31
 **/
// 来自字节
// 一共有n个人，从左到右排列，依次编号0~n-1
// h[i]是第i个人的身高
// v[i]是第i个人的分数
// 要求从左到右选出一个子序列，在这个子序列中的人，从左到右身高是不下降的
// 返回所有符合要求的子序列中，分数最大累加和是多大
// n <= 10的5次方, 1 <= h[i] <= 10的9次方, 1 <= v[i] <= 10的9次方
public class Code_03_SortedSubsequenceMaxSum {

    // 思路1：暴力解答，
    public static int maxSum1(int[] h, int[] v) {
        return process1(h, v, 0, 0);
    }

    public static int process1(int[] h, int[] v, int index, int preValue) {
        if (index == h.length) {
            return 0;
        }
        // 不要当前的
        int p1 = process1(h, v, index + 1, preValue);
        int p2 = h[index] >= preValue ? (v[index] + process1(h, v, index + 1, h[index])) : 0;
        return Math.max(p1, p2);
    }

    // 思路2：想办法把之前按照身高的已经加好了，当前来到的身高直接去找 <= 当前身高的最大值，然后当前身高的得分加上去，更新好
    // 线段树
    public static int maxSum(int[] h, int[] v) {
        int n = h.length;
        // 把身高数组复制
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = h[i];
        }
        // 然后按照身高排个序
        Arrays.sort(rank);
        SegmentTree segmentTree = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            // 当前身高是h[i] 去rank中查询当前身高排第几的，找出小于=当前身高的最左的位置
            int right = rank(rank, h[i]);
            segmentTree.update(right, segmentTree.max(1, right) + v[i]);
        }
        return segmentTree.max(1, n);
    }

    // 当前的身高是num，去数组中找到 <= num最左的位置
    public static int rank(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = 0;
        while (l <= r) {
            m = (l + r) >> 1;
            if (arr[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static class SegmentTree {
        private int n;
        private int[] max;
        private int[] update;

        public SegmentTree(int maxSize) {
            n = maxSize + 1;
            max = new int[n << 2];
            update = new int[n << 2];
            Arrays.fill(update, -1);
        }

        public void update(int index, int c) {
            update(index, index, c, 1, n, 1);
        }

        public int max(int l, int r) {
            return max(l, r, 1, n, 1);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            // 整个范围包含了
            if (L <= l && R >= r) {
                max[rt] = C;
                update[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private int max(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans += max(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += max(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

        private void pushUp(int rt) {
            // 子节点更大的
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt] != -1) {
                update[rt << 1] = update[rt];
                max[rt << 1] = update[rt];

                update[rt << 1 | 1] = update[rt];
                max[rt << 1 | 1] = update[rt];
                update[rt] = -1;
            }
        }

    }
}
