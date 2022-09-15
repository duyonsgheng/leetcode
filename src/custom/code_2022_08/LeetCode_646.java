package custom.code_2022_08;

import java.util.Arrays;

/**
 * @ClassName LeetCode_646
 * @Author Duys
 * @Description
 * @Date 2022/8/31 8:38
 **/
//
public class LeetCode_646 {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length <= 0 || pairs[0] == null || pairs[0].length <= 0) {
            return 0;
        }
        // 根据结束位置来排序
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int pre = Integer.MIN_VALUE;
        int ans = 0;
        for (int[] arr : pairs) {
            if (pre < arr[0]) {
                pre = arr[1];
                ans++;
            }
        }
        return ans;
    }

    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int N = pairs.length;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        // 以每一个数对为结束位置看看能不能搞到最大，
        for (int j = 1; j < N; ++j) {
            for (int i = 0; i < j; ++i) {
                if (pairs[i][1] < pairs[j][0])
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }

        int ans = 0;
        for (int x : dp) if (x > ans) ans = x;
        return ans;
    }
}
