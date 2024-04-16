package hope.class80_status1;

/**
 * @author Mr.Du
 * @ClassName Code01_CanIWin
 * @date 2024年04月16日 上午 09:24
 */
// 我能赢吗
// 给定两个整数n和m
// 两个玩家可以轮流从公共整数池中抽取从1到n的整数（不放回）
// 抽取的整数会累加起来（两个玩家都算）
// 谁在自己的回合让累加和 >= m，谁获胜
// 若先出手的玩家能稳赢则返回true，否则返回false
// 假设两位玩家游戏时都绝顶聪明，可以全盘为自己打算
// 测试链接 : https://leetcode.cn/problems/can-i-win/
public class Code01_CanIWin {
    public static boolean canIWin(int n, int m) {
        if (m == 0) {
            return true;
        }
        // 累加和都小于m了
        if (n * (n - 1) / 2 < m) {
            return false;
        }
        int[] dp = new int[1 << (n + 1)];
        return f(n, (1 << (n + 1)) - 1, m, dp);
    }

    // 累加和还剩下rest
    public static boolean f(int n, int s, int rest, int[] dp) {
        if (rest <= 0) { // 如果当前先手一进来面对的是一个0，输了
            return false;
        }
        if (dp[s] != 0) {
            return dp[s] == 1;
        }
        boolean ans = false;
        for (int i = 1; i <= n; i++) {
            // 当前位置可以选
            // 并且后手没赢的话，我赢了
            if ((s & (1 << i)) != 0 && !f(n, (s ^ (1 << i)), rest - i, dp)) {
                ans = true;
                break;
            }
        }
        dp[s] = ans ? 1 : -1;
        return ans;
    }
}
