package custom.code_2022_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_525
 * @Author Duys
 * @Description
 * @Date 2022/8/22 13:23
 **/
// 525. 连续数组
// 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
public class LeetCode_525 {

    // 前缀和，我们把0当作-1来处理
    // 求前缀和为0的最长
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int n = nums.length;
        int ans = 0;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + (nums[i - 1] == 0 ? -1 : 1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 1; i <= n; i++) {
            int cur = sum[i];
            if (cur != 0) {
                continue;
            }
            // 比如当前cur =2 之前遇到了一个为2的，2 - 2不就是0吗。可以计算长度
            if (map.containsKey(cur)) {
                ans = Math.max(ans, i - map.get(cur));
            } else {
                map.put(cur, i);
            }
        }
        return ans;
    }
}
