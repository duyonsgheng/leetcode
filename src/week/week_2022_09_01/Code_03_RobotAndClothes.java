package week.week_2022_09_01;

import java.util.Arrays;

/**
 * @ClassName Code_03_RobotAndClothes
 * @Author Duys
 * @Description
 * @Date 2022/9/8 11:24
 **/
// 来自美团
// 给定一个正数n, 表示从0位置到n-1位置每个位置放着1件衣服
// 从0位置到n-1位置不仅有衣服，每个位置还摆着1个机器人
// 给定两个长度为n的数组，powers和rates
// powers[i]表示i位置的机器人的启动电量
// rates[i]表示i位置的机器人收起1件衣服的时间
// 使用每个机器人只需要付出启动电量
// 当i位置的机器人收起i位置的衣服，它会继续尝试往右收起i+1位置衣服
// 如果i+1位置的衣服已经被其他机器人收了或者其他机器人正在收
// 这个机器人就会停机, 不再收衣服。
// 不过如果它不停机，它会同样以rates[i]的时间来收起这件i+1位置的衣服
// 也就是收衣服的时间为每个机器人的固定属性，当它收起i+1位置的衣服，
// 它会继续检查i+2位置...一直到它停机或者右边没有衣服可以收了
// 形象的来说，机器人会一直尝试往右边收衣服，收k件的话就耗费k * rates[i]的时间
// 但是当它遇见其他机器人工作的痕迹，就会认为后面的事情它不用管了，进入停机状态
// 你手里总共有电量b，准备在0时刻将所有想启动的机器人全部一起启动
// 过后不再启动新的机器人，并且启动机器人的电量之和不能大于b
// 返回在最佳选择下，假快多久能收完所有衣服
// 如果无论如何都收不完所有衣服，返回-1
// 给定数据: int n, int b, int[] powers, int[] rates
// 数据范围:
// powers长度 == rates长度 == n <= 1000
// 1 <= b <= 10^5
// 1 <= powers[i]、rates[i] <= 10^5
// 0号 : 10^5 * 10^3 -> 10^8
// log 10^8 * N^2  -> 27 * 10^6 -> 10^7
// 优化之后 : (log10^8) -> 27 * 1000 * 10
public class Code_03_RobotAndClothes {

    // 1.动态规划
    public static int fast1(int n, int b, int[] powers, int[] rates) {
        int[][] dp = new int[n][b + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        return process(powers, rates, n, 0, b, dp);
    }

    public static int process(int[] p, int[] r, int n, int i, int rest, int[][] dp) {
        if (i == n) {
            return 0;
        }
        // 当前机器人开机电量大于了剩余了，搞不定了
        if (p[i] > rest) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][rest] != -1) {
            return dp[i][rest];
        }
        int ans = Integer.MAX_VALUE;
        // 当前机器人搞定 i 那么 i+1 到后面调递归
        // 当前机器人搞定 i i+1 ，那么 i+2 到后面调用递归
        // .......
        for (int j = i; j < n; j++) {
            int curCost = (j - i + 1) * r[i];
            int nextCost = process(p, r, n, j + 1, rest - p[i], dp);
            // 多个机器人同时工作，取最大完成时间的机器人
            int curAns = Math.max(curCost, nextCost);
            ans = Math.min(curAns, ans);
        }
        dp[i][rest] = ans;
        return ans;
    }

    // 2.二分+线段树
    // 第一个机器人必须打开，否则第一件衣服收不了
    // 那么最差情况就是第一个机器人收所有的衣服
    public static int fast2(int n, int b, int[] p, int[] ri) {
        if (n == 0) {
            return 0;
        }
        // 没有电，或者第一个机器人都启动不了
        if (b == 0 || p[0] > b) {
            return -1;
        }
        int l = 1;
        int r = ri[0] * n;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) >> 1;
            if (process2(p, ri, m) <= b) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    // 在limit的时间限制内，返回收完所有的衣服，需要的最小电量
    public static int process2(int[] p, int[] ri, int limit) {
        int n = p.length;
        int[] dp = new int[n + 1];
        SegmentTree st = new SegmentTree(n + 1);
        st.update(n, 0);
        for (int i = n - 1; i >= 0; i--) {
            // 电量大了，搞不定
            if (ri[i] > limit) {
                dp[i] = Integer.MAX_VALUE;
            } else {
                // 当前机器人最多可以负责多少衣服
                int j = Math.min(i + (limit / ri[i]) - 1, n - 1);
                int next = st.min(i + 1, j + 1);
                int ans = next == Integer.MAX_VALUE ? next : (p[i] + next);
                dp[i] = ans;
            }
            st.update(i, dp[i]);
        }
        return dp[0];
    }

    public static class SegmentTree {
        private int n;
        private int[] min;

        public SegmentTree(int size) {
            n = size + 1;
            min = new int[n << 2];
            Arrays.fill(min, Integer.MAX_VALUE);
        }

        public int min(int l, int r) {
            l++;
            r++;
            return min(l, r, 1, n, 1);
        }

        public void update(int l, int c) {
            l++;
            update(l, l, c, 1, n, 1);
        }

        private void pushUp(int rt) {
            min[rt] = Math.min(min[rt], Math.min(min[rt << 1], min[rt << 1 | 1]));
        }

        private int min(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return min[rt];
            }
            int mid = (l + r) >> 1;
            int left = Integer.MAX_VALUE;
            int right = Integer.MAX_VALUE;
            if (L <= mid) {
                left = min(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = min(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.min(left, right);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= L && R >= r) {
                min[rt] = C;
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
    }

}
