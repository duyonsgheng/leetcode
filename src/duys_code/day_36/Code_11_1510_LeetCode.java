package duys_code.day_36;

/**
 * @ClassName Code_11_1510_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/stone-game-iv/
 * @Date 2021/12/14 13:51
 **/
public class Code_11_1510_LeetCode {
    // 来自哈喽单车
    // 递归的解答
    public boolean winnerSquareGame(int n) {
        // 当前先手，上来面临着0，显然输了
        if (n == 0) {
            return false;
        }
        // 当前作为先手，会尝试所有的拿法
        for (int i = 1; i * i <= n; i++) {
            // 当前作为先手拿了，如果后手输了，先手赢
            if (!winnerSquareGame(n - i * i)) {
                return true;
            }
        }
        return false;
    }

    public boolean winnerSquareGame1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = -1;
        return process(n, dp);
    }

    public static boolean process(int n, int[] dp) {
        if (dp[n] != 0) {
            return dp[n] == 1 ? true : false;
        }
        boolean ans = false;
        // 当前作为先手，会尝试所有的拿法
        for (int i = 1; i * i <= n; i++) {
            // 当前作为先手拿了，如果后手输了，先手赢
            if (!process(n - i * i, dp)) {
                ans = true;
                break;
            }
        }
        dp[n] = ans ? 1 : -1;
        return ans;
    }

    // 严格位置依赖
    public boolean winnerSquareGame2(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            // 作为先手可以尝试任务情况的拿
            for (int j = 1; j * j <= i; j++) {
                // i - j*j 这是后手的情况
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

}
