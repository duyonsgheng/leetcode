package custom.code_2023_06;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1906
 * @Author Duys
 * @Description
 * @Date 2023/6/27 9:44
 **/
// 1906. 查询差绝对值的最小值
public class LeetCode_1906 {
    public int[] minDifference(int[] nums, int[][] queries) {
        // 记录1-100这之间的数出现的次数，i位置的时候，这些数出现的次数是
        int[][] cnt = new int[nums.length][101];
        int[] arr = new int[101];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
            cnt[i] = arr.clone();
        }
        int n = queries.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 10001);
        for (int i = 0; i < n; i++) {
            int[] cur = queries[i];
            int l = cur[0], r = cur[1];
            // l 到 r之间所有的数出现的次数
            arr = new int[101];
            // 因为是从1到100的遍历，记录一下之前出现的数字
            int pre = 0;
            arr[nums[l]]++;
            for (int j = 0; j < 101; j++) {
                arr[j] += cnt[r][j] - cnt[l][j];
                if (arr[j] > 0) {
                    // 如果之前有数字出现了，那么就计算差值，从小到大遍历的，一定会存在答案
                    if (pre > 0) {
                        ans[i] = Math.min(j - pre, ans[i]);
                    }
                    pre = j;
                }
            }
            ans[i] = ans[i] == 10001 ? -1 : ans[i];
        }
        return ans;
    }
}
