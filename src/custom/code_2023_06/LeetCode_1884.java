package custom.code_2023_06;

/**
 * @ClassName LeetCode_1884
 * @Author Duys
 * @Description
 * @Date 2023/6/14 9:16
 **/
// 1884. 鸡蛋掉落-两枚鸡蛋
public class LeetCode_1884 {
    public int twoEggDrop(int n) {
        // 当前来到的楼层和剩余的鸡蛋数量
        int[][] dp = new int[n + 1][3];
        for (int j = 1; j <= 2; j++) {
            dp[1][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int i = 2; i <= n; i++) {
            dp[i][2] = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                int curr = Math.max(dp[j - 1][1], dp[i - j][2]) + 1;
                dp[i][2] = Math.min(dp[i][2], curr);
            }
        }
        return dp[n][2];
    }
}
