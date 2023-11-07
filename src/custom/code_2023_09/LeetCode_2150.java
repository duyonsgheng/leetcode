package custom.code_2023_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2150
 * @date 2023年09月01日
 */
// 2150. 找出数组中的所有孤独数字
// https://leetcode.cn/problems/find-all-lonely-numbers-in-the-array/
public class LeetCode_2150 {

    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            if (cnt.get(num) == 1 && !cnt.containsKey(num - 1) && !cnt.containsKey(num + 1)) {
                ans.add(num);
            }
        }
        return ans;
    }
}
