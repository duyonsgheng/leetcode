package custom.code_2022_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_523
 * @Author Duys
 * @Description
 * @Date 2022/8/22 11:12
 **/
// 523. 连续的子数组和
public class LeetCode_523 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 前缀和是0，位置是-1
        int pre = 0;
        for (int i = 0; i < n; i++) {
            pre = (nums[i] + pre) % k;
            if (map.containsKey(pre)) {
                int preIndex = map.get(pre);
                if (i - preIndex >= 2) {
                    return true;
                }
            } else {
                map.put(pre, i);
            }
        }
        return false;
    }
}
