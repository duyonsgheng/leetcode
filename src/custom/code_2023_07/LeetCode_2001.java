package custom.code_2023_07;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2001
 * @date 2023年07月25日
 */
// 2001. 可互换矩形的组数
// https://leetcode.cn/problems/number-of-pairs-of-interchangeable-rectangles/
public class LeetCode_2001 {
    public long interchangeableRectangles(int[][] rectangles) {
        Map<Long, Long> cnt = new HashMap<>();
        long ans = 0;
        for (int[] cur : rectangles) {
            int avr = gcb(cur[0], cur[1]);
            Long key = (cur[0] / avr) + (cur[1] / avr) * 10000l;
            ans += cnt.getOrDefault(key, 0l);
            cnt.put(key, cnt.getOrDefault(key, 0l) + 1);
        }
        return ans;
    }

    public int gcb(int a, int b) {
        return b == 0 ? a : gcb(b, a % b);
    }
}
