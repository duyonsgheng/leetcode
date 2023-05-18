package custom.code_2023_04;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1679
 * @Author Duys
 * @Description
 * @Date 2023/4/26 14:26
 **/
// 1679. K 和数对的最大数目
public class LeetCode_1679 {

    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : nums) {
            int t = k - num;
            int cur = cnt.getOrDefault(num, 0);
            if (cur <= 0) {
                continue;
            }
            cnt.put(num, cur - 1);
            int tr = cnt.getOrDefault(t, 0);
            if (tr > 0) {
                ans++;
                cnt.put(t, tr - 1);
            }
        }
        return ans;
    }
}
