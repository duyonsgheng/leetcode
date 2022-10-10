package custom.code_2022_10;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_873
 * @Author Duys
 * @Description
 * @Date 2022/10/10 11:22
 **/
// 873. 最长的斐波那契子序列的长度
public class LeetCode_873 {
    // arr = [1,3,7,11,12,14,18]
    public static int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        // dp[i][j]  arr[i]为最后一位，arr[j]为倒数第二位的时候最长的长度
        int[][] dp = new int[n + 1][n + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0 && j + 2 > ans; j--) {
                if (arr[i] - arr[j] >= arr[j]) {
                    break;
                }
                int index = map.getOrDefault(arr[i] - arr[j], -1);
                if (index == -1) {
                    continue;
                }
                dp[i][j] = Math.max(3, dp[j][index] + 1);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 7, 11, 12, 14, 18};
        System.out.println(lenLongestFibSubseq(arr));
    }
}
