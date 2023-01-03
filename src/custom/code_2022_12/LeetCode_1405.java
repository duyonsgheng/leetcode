package custom.code_2022_12;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1405
 * @Author Duys
 * @Description
 * @Date 2022/12/27 16:11
 **/
// 1405. 最长快乐字符串
public class LeetCode_1405 {
    // 尽可能使用较多
    public String longestDiverseString(int a, int b, int c) {
        StringBuffer buffer = new StringBuffer();
        PriorityQueue<int[]> queue = new PriorityQueue<>((m, n) -> n[1] - m[1]);
        if (a > 0) queue.add(new int[]{0, a});
        if (b > 0) queue.add(new int[]{1, b});
        if (c > 0) queue.add(new int[]{2, c});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int n = buffer.length();
            // 如果构建的字符串前两个和当前相同的不能使用当前的、
            if (n >= 2 && buffer.charAt(n - 1) - 'a' == cur[0] && buffer.charAt(n - 2) - 'a' == cur[0]) {
                if (queue.isEmpty()) {
                    break;
                }
                int[] next = queue.poll();
                buffer.append((char) (next[0] + 'a'));
                if (--next[1] != 0) { // 没用完，就放回去
                    queue.add(next);
                }
                queue.add(cur);
            } else { // 如果不同，可以使用当前的。
                buffer.append((char) (cur[0] + 'a'));
                if (--cur[1] != 0) {
                    queue.add(cur);
                }
            }
        }
        return buffer.toString();
    }
}
