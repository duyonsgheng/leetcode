package custom.code_2022_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_229
 * @Author Duys
 * @Description
 * @Date 2022/7/13 11:21
 **/
// 229. 多数元素 II
// 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
public class LeetCode_229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return ans;
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        int times = nums.length / 3;
        for (int num : count.keySet()) {
            if (count.get(num) > times) {
                ans.add(num);
            }
        }
        return ans;
    }
}
