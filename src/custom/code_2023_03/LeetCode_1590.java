package custom.code_2023_03;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1590
 * @Author Duys
 * @Description
 * @Date 2023/3/28 11:21
 **/
// 1590. 使数组和能被 P 整除
public class LeetCode_1590 {
    // 用余数
    public int minSubarray(int[] nums, int p) {
        int x = 0;
        for (int num : nums) {
            x = (x + num) % p;
        }
        if (x == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int cur = 0, ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            map.put(cur, i);
            cur = (cur + nums[i]) % p;
            // p=7 整体余数是x=6 当前余数cur是3
            // 那么我就需要找到与之对应的余数位置
            if (map.containsKey((cur - x + p) % p)) {
                ans = Math.min(ans, i - map.get((cur - x + p) % p) + 1);
            }

        }
        return ans == nums.length ? -1 : ans;
    }
}
