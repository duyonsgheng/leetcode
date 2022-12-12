package custom.code_2022_12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1296
 * @Author Duys
 * @Description
 * @Date 2022/12/5 15:30
 **/
// 1296. 划分数组为连续数字的集合
public class LeetCode_1296 {
    // 1.排序
    // 2.以当前开始的数 x ，到 x+k-1 如果都存在数字。那么合法的，否则不合法
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;
        if (k > n || n % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
        }
        for (int x : nums) {
            if (!cnt.containsKey(x)) {
                continue;
            }
            // 以x为头，到 x+k-1
            for (int i = 0; i < k; i++) {
                int next = x + i;
                if (!cnt.containsKey(next)) {
                    return false;
                }
                cnt.put(next, cnt.get(next) - 1);
                if (cnt.get(next) <= 0) {
                    cnt.remove(next);
                }
            }
        }
        return true;
    }
}
