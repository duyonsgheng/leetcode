package week.week_2023_04_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_03_LeetCode_1602
 * @Author Duys
 * @Description
 * @Date 2023/4/27 13:10
 **/
// 2602. 使数组元素全部相等的最少操作次数
public class Code_03_LeetCode_1602 {
    // 排序 + 二分 + 前缀和
    public List<Long> minOperations(int[] nums, int[] queries) {
        int n = nums.length;
        Arrays.sort(nums);
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        List<Long> ans = new ArrayList<>();
        int left, right;
        long cur = 0;
        for (int v : queries) {
            left = bs(nums, v);
            // 左边有多少个小于v的数
            cur = (long) (left + 1) * v - sum(sums, 0, left);
            // 右边有几个大于当前的数
            right = bs(nums, v + 1);
            cur += sum(sums, right + 1, n - 1) - (long) (n - right - 1) * v;
            ans.add(cur);
        }
        return ans;
    }

    public static long sum(long[] sum, int l, int r) {
        return l > r ? 0 : (sum[r + 1] - sum[l]);
    }

    // 找到 < v 最右的位置
    public static int bs(int[] arr, int v) {
        int l = 0;
        int r = arr.length - 1;
        int m, ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] < v) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }
}
