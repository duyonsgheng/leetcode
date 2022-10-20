package custom.code_2022_10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_954
 * @Author Duys
 * @Description
 * @Date 2022/10/20 16:00
 **/
//954. 二倍数对数组
public class LeetCode_954 {
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : arr)
            count.put(num, count.getOrDefault(num, 0) + 1);
        List<Integer> nums = new ArrayList<>();
        for (int num : count.keySet()) {
            nums.add(num);
        }
        Collections.sort(nums, (a, b) -> Math.abs(a) - Math.abs(b));
        for (int num : nums) {
            // 当前来2 ，找4的个数，如果4的个数少了，不能继续匹配了
            if (count.getOrDefault(num * 2, 0) < count.get(num)) {
                return false;
            }
            // 消耗掉 2倍数
            count.put(2 * num, count.getOrDefault(2 * num, 0) - count.get(num));
        }
        return true;
    }
}
