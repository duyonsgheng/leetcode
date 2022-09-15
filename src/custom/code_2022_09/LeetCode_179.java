package custom.code_2022_09;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_179
 * @Author Duys
 * @Description
 * @Date 2022/9/14 9:39
 **/
// 179. 最大数
public class LeetCode_179 {
    public String largestNumber(int[] nums) {
        PriorityQueue<String> queue = new PriorityQueue<>((x, y) -> (y + x).compareTo(x + y));
        for (int num : nums) {
            queue.offer(String.valueOf(num));
        }
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            builder.append(queue.poll());
        }
        if (builder.toString().startsWith("0")) {
            return "0";
        }
        return builder.toString();
    }
}
