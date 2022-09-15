package week.week_2022_08_05;

/**
 * @ClassName Code_05_OkStr
 * @Author Duys
 * @Description
 * @Date 2022/9/1 10:39
 **/
// 京东原题
// 给定n表示长度，小写字母任意选择，求组曾的串至少存在两个red的串有多少
public class Code_05_OkStr {

    public static long isOkStr(int n) {
        if (n <= 6) {
            return n == 6 ? 1 : 0;
        }
        // 1.分析至少有一个red串的情况
        long[] dp1 = new long[n + 1];
        dp1[3] = 1;
        for (int i = 4; i <= n; i++) {
            // 以结尾位置讨论
            // 1.当就是以red结尾 那么就是 dp[i] = 26^(i-3)
            // 2.a...z结尾除去d的情况 那么就是dp[i] = dp[i-1]*25
            // 3.以d结尾的情况，dp[i] = dp[i-1] - dp[i-3] 要把以red结尾的情况减去
            dp1[i] = (long) Math.pow(26, i - 3) + 26 * dp1[i - 1] - dp1[i - 3];
        }
        // 现在我们来算至少存在两个的情况
        long[] dp2 = new long[n + 1];
        // 依然一样的情况
        dp2[6] = 1;
        for (int i = 7; i <= n; i++) {
            // 1.red 就是最后的
            // 2.a...z 就是最后的
            // 3.d最后另算
            dp2[i] = dp1[i - 3] + 26 * dp2[i - 1] - dp2[i - 3];
        }
        return dp2[n];
    }

}
