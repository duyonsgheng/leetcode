package custom.code_2022_09;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @ClassName LeetCode_1636
 * @Author Duys
 * @Description
 * @Date 2022/9/19 9:13
 **/
// 1636. 按照频率将数组升序排序
public class LeetCode_1636 {
    public static int[] frequencySort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        // 0 - 频次
        // 1 - 数据
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int key : nums) {
            queue.add(new int[]{count.get(key), key});
        }
        int[] ans = new int[nums.length];
        int index = 0;
        while (!queue.isEmpty())
            ans[index++] = queue.poll()[1];
        return ans;
    }
}
