package duys_code.day_35;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_04_454_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/4sum-ii/
 * @Date 2021/12/8 13:24
 **/
public class Code_04_454_LeetCode {
    // 再每一个数组中找一个数，满足  a + b + c + d = 0
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 第一个for循环 在nums1 和nums2中找两个数计算累加和
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                sum = nums1[i] + nums2[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, 1);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                sum = nums3[i] + nums4[j];
                if (map.containsKey(-sum)) {
                    ans += map.get(-sum);
                }
            }
        }
        return ans;
    }
}
