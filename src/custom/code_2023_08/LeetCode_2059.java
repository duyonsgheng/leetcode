package custom.code_2023_08;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2059
 * @date 2023年08月07日
 */
// 2059. 转化数字的最小运算数
// https://leetcode.cn/problems/minimum-operations-to-convert-number/
public class LeetCode_2059 {
    public int minimumOperations(int[] nums, int start, int goal) {
        Deque<Integer> queue = new ArrayDeque<>();
        // 记录来到当前数字的时候，操作了几次了
        Map<Integer, Integer> map = new HashMap<>();
        queue.addLast(start);
        map.put(start, 0);
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            int step = map.getOrDefault(cur, 0);
            for (int num : nums) {
                // 接下来看看那些数字能操作的
                int[] nexts = new int[]{cur + num, cur - num, cur ^ num};
                for (int next : nexts) {
                    if (next == goal) {
                        return step + 1;
                    }
                    if (next < 0 || next > 1000 || map.containsKey(next)) {
                        continue;
                    }
                    map.put(next, step + 1);
                    queue.addLast(next);
                }
            }
        }
        return -1;
    }
}
