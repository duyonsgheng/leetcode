package week.week_2022_07_03;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName Code_03_LeetCode_347
 * @Author Duys
 * @Description
 * @Date 2022/7/21 9:41
 **/
// 347. 前 K 个高频元素
public class Code_03_LeetCode_347 {

    public static int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 1) {
            return new int[0];
        }
        Map<Integer, Node> counts = new HashMap<>();
        for (int n : nums) {
            if (!counts.containsKey(n)) {
                counts.put(n, new Node(n));
            }
            counts.get(n).count++;
        }
        // 准备一个堆
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.count - b.count);
        for (Node node : counts.values()) {
            // 堆没满或者满了 ,来的元素厉害了
            if (heap.size() < k || (heap.size() == k && node.count > heap.peek().count)) {
                heap.add(node);
            }

            if (heap.size() <= k) {
                continue;
            }
            // 剔除 最小的
            heap.poll();
        }
        int[] ans = new int[k];
        int index = 0;
        while (!heap.isEmpty()) {
            ans[index++] = heap.poll().num;
        }
        return ans;
    }

    public static class Node {
        int num;
        int count;

        public Node(int n) {
            num = n;
            count = 0;
        }
    }
}
