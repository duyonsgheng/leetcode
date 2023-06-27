package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1824
 * @Author Duys
 * @Description
 * @Date 2023/5/24 13:03
 **/
// 1824. 最少侧跳次数
public class LeetCode_1824 {
    public int minSideJumps1(int[] obstacles) {
        int a = 0b010, cur;
        int[] arr = new int[]{0b111, 0b110, 0b101, 0b011};
        int n = obstacles.length;
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            cur = arr[obstacles[i]];
            a &= cur;
            if (a == 0) {
                ans++;
                a = cur & arr[obstacles[i - 1]];
            }
        }
        return ans;
    }

    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length - 1;
        int[] dp = new int[3];
        Arrays.fill(dp, 1);
        dp[1] = 0; // 从第一条跑道开始

        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE / 2;
            for (int j = 0; j < 3; j++) {
                // 表示当前跑道有障碍
                if (j == obstacles[i] - 1) {
                    dp[j] = Integer.MAX_VALUE / 2;
                } else
                    min = Math.min(min, dp[j]);
            }
            for (int j = 0; j < 3; j++) {
                // 表示当前跑道有障碍
                if (j == obstacles[i] - 1) {
                    continue;
                } else
                    dp[j] = Math.min(min + 1, dp[j]);
            }
        }
        return Math.min(Math.min(dp[0], dp[1]), dp[2]);
    }
}
