package custom.code_2023_03;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_1577
 * @Author Duys
 * @Description
 * @Date 2023/3/24 14:09
 **/
// 1577. 数的平方等于两数乘积的方法数
public class LeetCode_1577 {
    // nums1[i]^2 = nums2[j]*nums2[k]
    // nums2[i]^2 = nums1[j]*nums1[k]
    public int numTriplets(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int num : nums1) {
            map1.put(num, map1.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            map2.put(num, map2.getOrDefault(num, 0) + 1);
        }
        return process(map1, map2) + process(map2, map1);
    }

    public int process(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        int ans = 0;
        for (int num1 : map1.keySet()) {
            int cnt1 = map1.get(num1);
            long cur = (long) num1 * num1;
            for (int num2 : map2.keySet()) {
                if (cur % num2 == 0 && cur / num2 <= Integer.MAX_VALUE) {
                    int num3 = (int) (cur / num2);
                    if (num2 == num3) {
                        int cnt2 = map2.get(num2);
                        ans += cnt1 * cnt2 * (cnt2 - 1) / 2;
                    } else if (num2 < num3 && map2.containsKey(num3)) {
                        ans += map2.get(num2) * map2.get(num3) * cnt1;
                    }
                }
            }
        }
        return ans;
    }
}


