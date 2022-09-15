package custom.code_2022_09;

import java.util.Arrays;

/**
 * @ClassName LeetCode_650
 * @Author Duys
 * @Description
 * @Date 2022/9/5 15:48
 **/
//650. 只有两个键的键盘
public class LeetCode_650 {


    public static int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        // 想要得到i个A
        // 先得到j个A
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    // 全量复制，至少还需要i/j步
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                    // 复制上一行的
                    dp[i] = Math.min(dp[i], dp[i / j] + j);
                }
            }
        }
        return dp[n];
    }


}
