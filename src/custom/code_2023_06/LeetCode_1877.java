package custom.code_2023_06;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * @ClassName LeetCode_1877
 * @Author Duys
 * @Description
 * @Date 2023/6/12 13:35
 **/
// 1877. 数组中最大数对和的最小值
public class LeetCode_1877 {
    // 排序
    // 贪心
    public int minPairSum(int[] nums) {
        int n = nums.length;
        if (n % 2 != 0) {
            return -1;
        }
        Arrays.sort(nums);
        // a1 a2 a3 a4
        // a1 + a4 和 a2+a3
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n / 2; i++) {
            ans = Math.max(ans, nums[i] + nums[n - i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {


    }
}
