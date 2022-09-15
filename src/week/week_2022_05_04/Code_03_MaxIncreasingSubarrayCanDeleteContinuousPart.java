package week.week_2022_05_04;

import java.util.Arrays;

/**
 * @ClassName Code_03_MaxIncreasingSubarrayCanDeleteContinuousPart
 * @Author Duys
 * @Description TODO
 * @Date 2022/5/27 13:25
 **/
// 来自字节
// 5.6笔试
// 给定一个数组arr，长度为n，最多可以删除一个连续子数组，
// 求剩下的数组，严格连续递增子数组的最大长度
// n <= 10^6
public class Code_03_MaxIncreasingSubarrayCanDeleteContinuousPart {

    // 思路：这是一个dp，当我们来到i位置，如果当前的值是大于i-1的值，那么可能就是i-1 位置答案+1
    // 但是我们还得确定一个信息，如果当前位置的值不大于之前的，我们需要知道之前哪一个位置没有删除的时候已经达到了最大。这个点不是很容易。
    // 为了找到当前位置之前，哪个位置没有使用删除而达到了最大，我们就踩着之前达到最大的位置推得更大，如果使用dp，需要去遍历，所以使用线段树
    public static int maxLen(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int n = arr.length;
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = arr[i];
        }
        Arrays.sort(sorted);
        SegmentTree st = new SegmentTree(n);
        // 当前0位置的数，左边一个也不删，只有一个最长的
        st.update(rank(sorted, arr[0]), 1);
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int ans = 1;
        // 一个数字不删除，最长的递增子数组长度
        int cur = 1;
        for (int i = 1; i < n; i++) {
            // 看看当前的位置排名在哪里
            int rank = rank(sorted, arr[i]);
            int p1 = arr[i] > arr[i - 1] ? dp[i - 1] + 1 : 1;
            // 如果我当前不是排名第一，可以用之前小于我的最大的
            int p2 = rank > 1 ? (st.max(rank - 1) + 1) : 1;
            dp[i] = Math.max(p1, p2);
            ans = Math.max(ans, dp[i]);
            if (arr[i] > arr[i - 1]) {
                cur++;
            } else {
                cur = 1;
            }
            // 看看是不是把相同的数字的最长递增序列推高了。
            if (st.get(rank) < cur) {
                st.update(rank, cur);
            }
        }
        return ans;
    }

    public static int rank(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = -1;
        int ans = -1;
        while (l <= r) {
            m = (l + r) >> 1;
            if (arr[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans + 1;
    }

    public static class SegmentTree {
        private int n;
        private int[] max;
        private int[] update;

        public SegmentTree(int size) {
            n = size + 1;
            max = new int[n << 2];
            update = new int[n << 2];
            Arrays.fill(update, -1);
        }

        public int get(int index) {
            return max(index, index, 1, n, 1);
        }

        public void update(int index, int C) {
            update(index, index, C, 1, n, 1);
        }

        public int max(int index) {
            return max(index, index, 1, n, 1);
        }

        private void pushUp(int rt) {
            // 他的两个孩子谁更大一点
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt] == -1) {
                return;
            }
            update[rt << 1] = update[rt];
            max[rt << 1] = update[rt];

            update[rt << 1 | 1] = update[rt];
            max[rt << 1 | 1] = update[rt];

            update[rt] = -1;
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            // 全部包含，没话说
            if (L <= l && R >= r) {
                max[rt] = C;
                update[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            // 往下发
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
    }
}
