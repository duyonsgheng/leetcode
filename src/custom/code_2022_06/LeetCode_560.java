package custom.code_2022_06;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_560
 * @Author Duys
 * @Description
 * @Date 2022/6/13 17:09
 **/
// 560. 和为 K 的子数组
// 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
public class LeetCode_560 {

    // 窗口
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {-1, -1, 1};
        int n = 0;
        System.out.println(subarraySum(arr, n));
    }
}
