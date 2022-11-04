package week.week_2022_11_01;

import java.util.Arrays;

/**
 * @ClassName Code_04_ValueNoMoreXDigitsToY
 * @Author Duys
 * @Description
 * @Date 2022/11/3 10:59
 **/
// 来自CISCO
// 给定两个正整数x、y，都是int类型
// 返回0 ~ x以内，每位数字加起来是y的数字个数
// 比如，x = 20、y = 5，返回2
// 因为0 ~ x以内，每位数字加起来是5的数字有：5、14
// x、y范围是java里正整数的范围
public class Code_04_ValueNoMoreXDigitsToY {

    // 数位dp
    // 思路：题意给出是java的整型范围 ，java的整型最多10位。就算每一位都是9，那么最大也就是90，所以y的最大就是90，不会超过90-很重要
    // 然后拿出当前数，比如当前数8位，那么首先把7位到1位的答案算出来，然后8位的时候递归去找
    public static int[][] from = new int[11][91]; // 最多10位，最大90，我们扩大范围，先打表

    static {
        from[0][0] = 1;
        for (int len = 1; len <= 10; len++) {
            for (int sum = 0; sum <= len * 9; sum++) {
                for (int cur = 0; cur <= 9 && cur <= sum; cur++) {
                    from[len][sum] += from[len - 1][sum - cur];
                }
            }
        }
    }

    public static int num(int x, int y) {
        if (x < 0 || y > 90) {
            return 0;
        }
        if (x == 0) {
            return y == 0 ? 1 : 0;
        }
        // 当前是2345 ，那么offset就是 1000 len就是4
        int offset = 1;
        int len = 1;
        while (offset <= x / 10) {
            offset *= 10;
            len++;
        }
        int[][] dp = new int[len + 1][y + 1];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(x, offset, len, y, dp);
    }

    // 当前数 x
    // 当前的offset
    // 当前数的长度
    // 还剩余多少  rest
    public static int process(int x, int offset, int len, int rest, int[][] dp) {
        if (len == 0) {
            // 如果没有数了，那么rest刚刚处理完，就在找到了一种，之前做的决定
            return rest == 0 ? 1 : 0;
        }
        if (dp[len][rest] != -1) {
            return dp[len][rest];
        }
        int ans = 0;
        int cur = (x / offset) % 10; // 把当前数的最高位拿出来了
        // 比如当前是2345，最高位就是2，先统计4位的，然后最高位是0和1
        for (int i = 0; i < cur && i <= rest; i++) {
            ans += from[len - 1][rest - i];
        }
        // 单独统计3位的时候，跑递归去
        if (cur <= rest) {
            ans += process(x, offset / 10, len - 1, rest - cur, dp);
        }
        dp[len][rest] = ans;
        return ans;
    }
}
