package custom.code_2023_05;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1726
 * @Author Duys
 * @Description
 * @Date 2023/5/11 16:21
 **/
// 1726. 同积元组
public class LeetCode_1726 {
    // 给你一个由 不同 正整数组成的数组 nums ，请你返回满足a * b = c * d 的元组 (a, b, c, d) 的数量。其中 a、b、c 和 d 都是 nums 中的元素，且 a != b != c != d 。
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        Map<Long, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long cur = nums[i] * nums[j] * 1l;
                cnt.put(cur, cnt.getOrDefault(cur, 0) + 1);
            }
        }
        int ans = 0;
        for (Long key : cnt.keySet()) {
            int cur = cnt.get(key);
            // 如果大于了2，说明从这些满足要求中选出2个，就是C m 2的计算方式
            if (cur >= 2) {
                ans += ((cur - 1) * cur / 2) * 8;
            }
        }
        return ans;
    }
}
