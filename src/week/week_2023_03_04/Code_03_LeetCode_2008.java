package week.week_2023_03_04;

import java.util.Arrays;

/**
 * @ClassName Code_03_LeetCode_2008
 * @Author Duys
 * @Description
 * @Date 2023/3/23 11:36
 **/
// 2008. 出租车的最大盈利
// 你驾驶出租车行驶在一条有 n 个地点的路上
// 这 n 个地点从近到远编号为 1 到 n ，你想要从 1 开到 n
// 通过接乘客订单盈利。你只能沿着编号递增的方向前进，不能改变方向
// 乘客信息用一个下标从 0 开始的二维数组 rides 表示
// 其中 rides[i] = [starti, endi, tipi]
// 表示第 i 位乘客需要从地点 starti 前往 endi
// 愿意支付 tipi 元的小费
// 每一位 你选择接单的乘客 i ，你可以 盈利 endi - starti + tipi 元
// 你同时 最多 只能接一个订单。
// 给你 n 和 rides ，请你返回在最优接单方案下，你能盈利 最多 多少元。
// 注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。
// https://leetcode.cn/problems/maximum-earnings-from-taxi/
public class Code_03_LeetCode_2008 {
    // 根据题意，可以很直观的看出是线段树，但是用线段树太重了
    // 使用dp
    // 首先把位置点做离散化，
    // 然后来到当前点的开始位置去更新当前点的结束位置时候最大钱数
    public int maxn = 100001;
    public int[] sorted = new int[maxn];
    public long[] dp = new long[maxn];

    public long maxTaxiEarnings(int n, int[][] rides) {
        int m = rides.length;
        // 准备离散化
        for (int i = 0, j = 0; i < m; i++) {
            sorted[j++] = rides[i][0];
            sorted[j++] = rides[i][1];
        }
        // 根据开始位置来排序
        Arrays.sort(rides, (a, b) -> a[0] - b[0]);
        Arrays.sort(sorted, 0, m << 1);
        Arrays.fill(dp, 0, m << 1, 0);
        int dpi = 0;
        long pre = 0;
        long ans = 0;
        for (int[] ride : rides) {
            int start = ride[0];
            int end = ride[1];
            int tip = ride[2];
            int s = ran(sorted, m << 1, start);
            int e = ran(sorted, m << 1, end);
            // 找到前面最大
            while (dpi <= s) {
                pre = Math.max(pre, dp[dpi++]);
            }
            long money = pre + end - start + tip;
            ans = Math.max(money, ans);
            dp[e] = Math.max(dp[e], money);
        }
        return ans;
    }

    public int ran(int[] sorted, int len, int num) {
        int ans = 0;
        int l = 0, r = len - 1, m = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (sorted[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
