package custom.code_2023_05;

/**
 * @ClassName LeetCode_1744
 * @Author Duys
 * @Description
 * @Date 2023/5/17 13:28
 **/
// 1744. 你能在你最喜欢的那天吃到你最喜欢的糖果吗？
public class LeetCode_1744 {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int n = candiesCount.length;
        int m = queries.length;
        boolean[] ans = new boolean[m];
        long[] sums = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + candiesCount[i - 1];
        }
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1] + 1, c = queries[i][2];
            // 最早的时间，来到当前c天，看看最早吃完要多少天
            long f = sums[a] / c + 1;
            // 最晚的时间
            long e = sums[a + 1];
            // 看看当前时间是不是在最早和最晚之间
            ans[i] = f <= b && b <= e;
        }
        return ans;
    }

}
