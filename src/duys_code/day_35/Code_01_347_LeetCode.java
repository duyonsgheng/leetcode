package duys_code.day_35;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName Code_01_347_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/top-k-frequent-elements/
 * @Date 2021/12/7 17:55
 **/
public class Code_01_347_LeetCode {
    // 先词频统计
    // 在往小根堆里面放


    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length <= 0 || k < 1) {
            return new int[0];
        }
        Map<Integer, Node> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new Node(num));
            } else
                map.get(num).count++;
        }

        // 往堆放
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.count - b.count);
        for (Node cur : map.values()) {
            // 堆没满或者堆满了，但是来了一个更牛逼的，先放进去，然后再调整
            if (heap.size() < k || (heap.size() == k && cur.count > heap.peek().count)) {
                heap.add(cur);
            }
            // 小根堆，把最小的弹出去，不要了
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] ans = new int[k];
        int index = 0;
        while (!heap.isEmpty()) {
            ans[index++] = heap.poll().num;
        }
        return ans;
    }

    public static class Node {
        public int num;
        public int count;

        public Node(int k) {
            num = k;
            count = 1;
        }
    }
}
