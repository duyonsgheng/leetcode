package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1997
 * @date 2023年07月25日
 */
// 1997. 访问完所有房间的第一天
// https://leetcode.cn/problems/first-day-where-you-have-been-in-all-the-rooms/
public class LeetCode_1997 {
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        // dp[i][0] 奇数次数到达i号房间的天数
        // dp[i][1] 偶数次数到达i号房间的天数
        long[][] dp = new long[n][2];
        // 0号房间，题目给出1天到达
        dp[0][1] = 1;
        int mod = 1_000_000_007;
        for (int i = 1; i < n; i++) {
            // 奇数到达，实在前一个房间的偶数次数上+1
            dp[i][0] = (dp[i - 1][1] + 1) % mod;
            // +1 到达奇数次dp[nextVisit[i]][0],此时想要再次到达i,需要先偶数次的到达i-1
            // + (dp[i - 1][1] - dp[nextVisit[i]][0]),再次到达i-1需要的天数
            dp[i][1] = (dp[i][0] + 1 + (mod + dp[i - 1][1] - dp[nextVisit[i]][0]) + 1) % mod;
        }
        return (int) dp[n - 1][0];
    }
}
