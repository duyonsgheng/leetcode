package custom.code_2022_09;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_659
 * @Author Duys
 * @Description
 * @Date 2022/9/5 16:34
 **/
// 659. 分割数组为连续子序列
public class LeetCode_659 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 4, 4, 5, 5};
        isPossible(arr);
    }

    public static boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new PriorityQueue<>());
            }
            // 包含了num-1，合并
            if (map.containsKey(num - 1)) {
                int lastLevel = map.get(num - 1).poll();
                if (map.get(num - 1).isEmpty()) {
                    map.remove(num - 1);
                }
                // 之前的长度合并了，并且以当前结尾了，防止重复数字的时候，算重
                map.get(num).offer(lastLevel + 1);
            } else {
                map.get(num).offer(1);
            }
        }
        for (Integer key : map.keySet()) {
            PriorityQueue<Integer> queue = map.get(key);
            if (queue.peek() < 3) {
                return false;
            }
        }
        return true;
    }
}
