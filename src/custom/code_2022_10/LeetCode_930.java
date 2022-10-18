package custom.code_2022_10;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_930
 * @Author Duys
 * @Description
 * @Date 2022/10/18 14:14
 **/
// 930. 和相同的二元子数组
public class LeetCode_930 {

    // 窗口啊
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int l1 = 0;
        int l2 = 0;
        int r = 0;
        int sum1 = 0, sum2 = 0;
        int ans = 0;
        while (r < n) {
            sum1 += nums[r];
            // 窗口缩进一下
            while (l1 <= r && sum1 > goal) {
                sum1 -= nums[l1];
                l1++;
            }

            sum2 += nums[r];
            while (l2 <= r && sum2 >= goal) {
                sum2 -= nums[l2];
                l2++;
            }
            ans += l2 - l1;
            r++;
        }
        return ans;
    }

    // 前缀和+map
    public static int numSubarraysWithSum1(int[] nums, int goal) {
        int n = nums.length;
        int ans = 0;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            int r = sum[i + 1]; // 当前的右边界
            int l = r - goal; // 需要的左边界
            ans += map.getOrDefault(l, 0); // 如果存在了，那么就类加上
            map.put(r, map.getOrDefault(r, 0) + 1); // 把当前的右边界记录一下
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 0, 1};
        int g = 2;
        System.out.println(numSubarraysWithSum(arr, g));
    }
}
