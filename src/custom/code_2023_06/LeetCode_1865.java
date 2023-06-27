package custom.code_2023_06;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1865
 * @Author Duys
 * @Description
 * @Date 2023/6/9 14:58
 **/
// 1865. 找出和为指定值的下标对
public class LeetCode_1865 {
    class FindSumPairs {
        private int[] arr1;
        private int[] arr2;
        Map<Integer, Integer> cnt;

        public FindSumPairs(int[] nums1, int[] nums2) {
            arr1 = nums1;
            arr2 = nums2;
            cnt = new HashMap<>();
            for (int num : nums2) {
                cnt.put(num, cnt.getOrDefault(num, 0) + 1);
            }
        }

        public void add(int index, int val) {
            // 先减少一个
            cnt.put(arr2[index], cnt.get(arr2[index]) - 1);
            arr2[index] += val;
            cnt.put(arr2[index], cnt.getOrDefault(arr2[index], 0) + 1);
        }

        public int count(int tot) {
            int ans = 0;
            for (int num : arr1) {
                ans += cnt.getOrDefault(tot - num, 0);
            }
            return ans;
        }
    }

}
