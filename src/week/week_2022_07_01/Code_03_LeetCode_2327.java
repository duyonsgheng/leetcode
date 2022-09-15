package week.week_2022_07_01;

/**
 * @ClassName Code_03_LeetCode_2327
 * @Author Duys
 * @Description
 * @Date 2022/7/7 17:48
 **/
// 在第 1天，有一个人发现了一个秘密。
// 给你一个整数delay，表示每个人会在发现秘密后的 delay天之后，
// 每天给一个新的人分享秘密。
// 同时给你一个整数forget，表示每个人在发现秘密forget天之后会忘记这个秘密。
// 一个人不能在忘记秘密那一天及之后的日子里分享秘密。
// 给你一个整数n，请你返回在第 n天结束时，知道秘密的人数。
// 由于答案可能会很大，请你将结果对109 + 7取余后返回。
// https://leetcode.cn/problems/number-of-people-aware-of-a-secret/
public class Code_03_LeetCode_2327 {

    // 普通动态规划
    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        long mod = 1000000007;
        long[] kows = new long[n + 1]; // 第i天知道秘密的人
        long[] forgets = new long[n + 1]; // 第i天忘掉秘密的人
        long[] shares = new long[n + 1]; // 第i天分享秘密的人

        kows[1] = 1;
        if (1 + delay <= n) {
            // 第i天分享秘密的人至少一个了
            shares[1 + delay] = 1;
        }
        if (1 + forget <= n) {
            forgets[1 + forget] = 1;
        }
        for (int i = 2; i <= n; i++) {
            // 头一天知道的+ 当天分享的 - 当天遗忘的
            kows[i] = (mod + kows[i - 1] + shares[i] - forgets[i]) % mod;

            if (i + forget <= n) {
                // 我i+forget 忘记秘密的人，就是我shares在第i天分享出去的人
                forgets[i + forget] = shares[i];
            }
            if (i + delay <= n) {
                // i + delay - 1天分享的，第二天就可以分享了+ 当天分享的 - 在 i+delay要忘记的人
                shares[i + delay] = (mod + shares[i + delay - 1] + shares[i] - forgets[i + delay]) % mod;
            }
        }
        return (int) kows[n];
    }
}
