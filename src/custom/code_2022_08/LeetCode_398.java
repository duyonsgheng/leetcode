package custom.code_2022_08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName LeetCode_398
 * @Author Duys
 * @Description
 * @Date 2022/8/8 16:05
 **/
//398. 随机数索引
public class LeetCode_398 {
    class Solution {
        Random random = new Random();
        Map<Integer, List<Integer>> map = new HashMap<>();

        public Solution(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], new ArrayList<>());
                }
                map.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> list = map.get(target);
            return list.get(random.nextInt(list.size()));
        }
    }
}
