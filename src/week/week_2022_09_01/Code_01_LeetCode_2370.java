package week.week_2022_09_01;

import java.util.Arrays;

/**
 * @ClassName Code_01_LeetCode_2370
 * @Author Duys
 * @Description
 * @Date 2022/9/7 22:12
 **/
// 2370. 最长理想子序列
public class Code_01_LeetCode_2370 {

    // 1.dp的解法
    public static int longestIdealString1(String s, int k) {
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
        }
        int[][] dp = new int[n][27];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(arr, 0, 26, k, dp);
    }

    // index- 当前来到的位置
    // pre - 之前做的决定，选择的哪一个字符 0-25 表示 a-z
    public static int process(int[] arr, int index, int pre, int k, int[][] dp) {
        if (index == arr.length) {
            return 0;
        }
        if (dp[index][pre] != -1) {
            return dp[index][pre];
        }
        // 不选择当前的
        int p1 = process(arr, index + 1, pre, k, dp);

        int p2 = 0;
        // 之前没做出选择
        if (pre == 26 || Math.abs(arr[index] - pre) <= k) {
            p2 = process(arr, index + 1, arr[index], k, dp) + 1;
        }
        int ans = Math.max(p1, p2);
        dp[index][pre] = ans;
        return ans;
    }

    //2.线段树的解法
    // 来到当前位置，我去找之前满足需求的位置 [cur-k ,cur +k]这个范围上的最大值，，范围上的最大值，线段树
    public static int longestIdealString2(String s, int k) {
        SegmentTree st = new SegmentTree(26);
        int cur = 0;
        int pre = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            cur = s.charAt(i) - 'a' + 1;// 线段树中下标从1开始
            pre = st.max(Math.max(cur - k, 1), Math.min(cur + k, 26));
            ans = Math.max(ans, 1 + pre);
            st.update(cur, 1 + pre);
        }
        return ans;
    }

    // 单点更新的
    public static class SegmentTree {
        private int n;
        private int[] max;

        public SegmentTree(int size) {
            n = size + 1;
            max = new int[n << 2 + 1];
        }

        public void update(int index, int c) {
            update(index, index, c, 1, n, 1);
        }

        public int max(int l, int r) {
            return max(l, r, 1, n, 1);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                max[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private int max(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            int ans = 0;
            if (L <= mid) {
                ans = Math.max(ans, max(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                ans = Math.max(ans, max(L, R, mid + 1, r, rt << 1 | 1));
            }
            return ans;
        }
    }

}
