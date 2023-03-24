package custom.code_2023_01;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1814
 * @Author Duys
 * @Description
 * @Date 2023/1/17 9:51
 **/
//1814. 统计一个数组中好对子的数目
public class LeetCode_1814 {
    static int mod = 1_000_000_007;

    // nums[i] + res(nums[j]) = nums[j] + res(nums[i])
    // nums[i] - res(nums[i]) = nums[j] - res(nums[j])
    public static int countNicePairs(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int cur = num;
            int j = 0; // 这是反转后的数字
            while (cur > 0) {
                j = j * 10 + cur % 10;
                cur /= 10;
            }
            ans = (ans + map.getOrDefault(num - j, 0)) % mod;
            map.put(num - j, map.getOrDefault(num - j, 0) + 1);
        }
        return ans;
    }


}
