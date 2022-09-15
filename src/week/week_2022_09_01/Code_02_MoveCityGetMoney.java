package week.week_2022_09_01;

import java.util.Arrays;

/**
 * @ClassName Code_02_MoveCityGetMoney
 * @Author Duys
 * @Description
 * @Date 2022/9/8 10:48
 **/
// 来自美团
// 有n个城市，城市从0到n-1进行编号。小美最初住在k号城市中
// 在接下来的m天里，小美每天会收到一个任务
// 她可以选择完成当天的任务或者放弃该任务
// 第i天的任务需要在ci号城市完成，如果她选择完成这个任务
// 若任务开始前她恰好在ci号城市，则会获得ai的收益
// 若她不在ci号城市，她会前往ci号城市，获得bi的收益
// 当天的任务她都会当天完成
// 任务完成后，她会留在该任务所在的ci号城市直到接受下一个任务
// 如果她选择放弃任务，她会停留原地，且不会获得收益
// 小美想知道，如果她合理地完成任务，最大能获得多少收益
// 输入描述: 第一行三个正整数n, m和k，表示城市数量，总天数，初始所在城市
// 第二行为m个整数c1, c2,...... cm，其中ci表示第i天的任务所在地点为ci
// 第三行为m个整数a1, a2,...... am，其中ai表示完成第i天任务且地点不变的收益
// 第四行为m个整数b1, b2,...... bm，其中bi表示完成第i天的任务且地点改变的收益
// 0 <= k, ci <= n <= 30000
// 1 <= m <= 30000
// 0 <= ai, bi <= 10^9
// 输出描述 输出一个整数，表示小美合理完成任务能得到的最大收益
public class Code_02_MoveCityGetMoney {
    // 1.动态规划
    // 可能性：1.选择做当前任务 2.选择不做当前任务
    public static int maxPorfit1(int n, int m, int k, int[] c, int[] a, int[] b) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(k, 0, m, c, a, b, dp);
    }

    // cur - 当前在哪一座城市
    // i - 当前的任务编号
    // m c a b 题意给出的固定参数
    public static int process(int cur, int i, int m, int[] c, int[] a, int[] b, int[][] dp) {
        if (i == m) { // 没有任务了
            return 0;
        }
        if (dp[cur][i] != -1) {
            return dp[cur][i];
        }
        int p1 = process(cur, i + 1, m, c, a, b, dp);
        int p2 = 0;
        // 当前城市就是面临着任务的城市
        if (cur == c[i]) {
            p2 = a[i] + process(cur, i + 1, m, c, a, b, dp);
        } else {
            p2 = b[i] + process(c[i], i + 1, m, c, a, b, dp);
        }
        int ans = Math.max(p1, p2);
        dp[cur][i] = ans;
        return ans;
    }


    // 2.线段树的解法
    // 当前面临的是i号任务，
    //  那么有两种情况：
    //  情况1：0到i-1 上最大，i+1 到 n-1上最大，这两种情况表示从其他的城市来到i号任务所在的城市，获取b[i]
    //  情况2：当前i号任务就是在i号任务所在的城市，获得a[i]
    //  然后取三者最大，更新好当前位置的值
    // 范围上查询最大，线段树
    public static int maxPorfit2(int n, int m, int k, int[] c, int[] a, int[] b) {
        SegmentTree st = new SegmentTree(n);
        st.update(k, 0);
        int ans = 0;
        //
        for (int i = 0; i < m; i++) {
            int cur = Math.max(Math.max(st.max(0, c[i] - 1), st.max(c[i] + 1, n - 1)) + b[i], st.max(c[i], c[i]) + a[i]);
            ans = Math.max(cur, ans);
            st.update(c[i], cur);
        }
        return ans;
    }

    public static class SegmentTree {
        private int n;
        private int[] max;

        public SegmentTree(int size) {
            n = size;
            max = new int[(n + 1) << 2];
            Arrays.fill(max, Integer.MIN_VALUE);
        }

        public int max(int l, int r) {
            l++;
            r++;
            if (l > r) {
                return Integer.MIN_VALUE;
            }
            return max(l, r, 1, n, 1);
        }

        public void update(int i, int c) {
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                max[rt] = Math.max(max[rt], C);
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
            max[rt] = Math.max(max[rt], Math.max(max[rt << 1], max[rt << 1 | 1]));
        }

        private int max(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            int left = Integer.MIN_VALUE;
            int right = Integer.MIN_VALUE;
            if (L <= mid) {
                left = max(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = max(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.max(left, right);
        }

    }
}
