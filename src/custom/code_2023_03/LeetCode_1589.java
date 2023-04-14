package custom.code_2023_03;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1589
 * @Author Duys
 * @Description
 * @Date 2023/3/28 9:51
 **/
// 1589. 所有排列中的最大和
public class LeetCode_1589 {
    // 1.线段树，统计所有的区间查询的次数，然后根据次数对数据进行排序，按照次数多的位置放数大的
    // 2.差分
    int mod = 1_000_000_007;

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        int[] arr = new int[n + 1];
        for (int i = 0; i < requests.length; i++) {
            arr[requests[i][0]]++;
            arr[requests[i][1] + 1]--;
        }
        int[] cnt = new int[n];
        for (int i = 0, sum = 0; i < n; i++) {
            cnt[i] = sum += arr[i];
        }
        Arrays.sort(nums);
        Arrays.sort(cnt);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // 当前数，当前位置查询的次数
            ans = (ans + 1l * nums[i] * cnt[i]) % mod;
        }
        return (int) ans;
    }

}
