package custom.code_2023_04;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName LeetCode_1626
 * @Author Duys
 * @Description
 * @Date 2023/4/20 17:06
 **/
// 1626. 无矛盾的最佳球队
public class LeetCode_1626 {
    public static int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{scores[i], ages[i]};
        }
        // 如果分数相同就根据年龄排序
        Arrays.sort(arr, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // 分数比我小的，如果年龄比我
                if (arr[j][1] <= arr[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += arr[i][0];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(bestTeamScore(new int[]{4, 5, 6, 5}, new int[]{2, 1, 2, 1}));
    }

}
