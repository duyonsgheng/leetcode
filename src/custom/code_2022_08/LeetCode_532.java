package custom.code_2022_08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_532
 * @Author Duys
 * @Description
 * @Date 2022/8/22 18:04
 **/
// 532. 数组中的 k-diff 数对
public class LeetCode_532 {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : nums) {
            if (count.get(num) == 0) {
                continue;
            }
            if (k == 0) {
                if (count.get(num) > 1) {
                    ans++;
                }
            } else {
                if (count.getOrDefault(num - k, 0) > 0) {
                    ans++;
                }
                if (count.getOrDefault(num + k, 0) > 0) {
                    ans++;
                }
            }
            // 使用了就需要去掉
            count.put(num, 0);
        }
        return ans;
    }
}
