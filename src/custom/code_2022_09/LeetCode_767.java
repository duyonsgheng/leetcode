package custom.code_2022_09;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_767
 * @Author Duys
 * @Description
 * @Date 2022/9/19 16:29
 **/
// 767. 重构字符串
public class LeetCode_767 {

    // 基于大根堆的贪心，优先使用较多的
    public String reorganizeString(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int n = s.length();
        int[] counts = new int[26];
        int maxCount = 0;
        for (int i = 0, index = 0; i < n; i++) {
            index = s.charAt(i) - 'a';
            counts[index]++;
            maxCount = Math.max(maxCount, counts[index]);
        }
        if (maxCount > (n + 1) / 2) {
            return "";
        }
        // 把数量多的排后面
        PriorityQueue<Character> queue = new PriorityQueue<>((a, b) -> counts[b - 'a'] - counts[a - 'a']);
        // 入堆
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                queue.offer((char) (i + 'a'));
            }
        }
        StringBuilder ans = new StringBuilder();
        while (queue.size() > 1) {
            // 先拿多的
            Character first = queue.poll();
            Character second = queue.poll();
            ans.append(first);
            ans.append(second);
            //用了就去掉一个
            counts[first - 'a']--;
            counts[second - 'a']--;
            // 没用完下一次继续
            if (counts[first - 'a'] > 0) {
                queue.offer(first);
            }
            if (counts[second - 'a'] > 0) {
                queue.offer(second);
            }
        }
        if (!queue.isEmpty()) {
            ans.append(queue.poll());
        }
        return ans.toString();
    }
}
