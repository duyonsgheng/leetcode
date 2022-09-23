package custom.code_2022_09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_823
 * @Author Duys
 * @Description
 * @Date 2022/9/23 16:27
 **/
// 823. 带因子的二叉树
public class LeetCode_823 {
    int mod = 1000000007;

    public int numFactoredBinaryTrees(int[] arr) {
        Map<Integer, Integer> index = new HashMap<>();
        int n = arr.length;
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            index.put(arr[i], i);
        }
        // 以为每一个位置是头的树
        long[] dp = new long[n + 1];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] % arr[j] == 0) {
                    int right = arr[i] / arr[j];
                    if (index.containsKey(right)) {
                        dp[i] = (dp[i] + dp[j] * dp[index.get(right)]) % mod;
                    }
                }
            }
        }
        long ans = 0;
        for (long x : dp) ans += x;
        return (int) (ans % mod);
    }
}
