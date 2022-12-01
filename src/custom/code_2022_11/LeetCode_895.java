package custom.code_2022_11;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ClassName LeetCode_895
 * @Author Duys
 * @Description
 * @Date 2022/11/30 9:55
 **/
// 895. 最大频率栈
public class LeetCode_895 {
    class FreqStack {
        // 记录元素次数
        Map<Integer, Integer> times;
        // 记录元素出现次数的元素
        Map<Integer, Deque<Integer>> indexs;
        // 当前出现最多，离栈顶最近的次数
        int max;

        public FreqStack() {
            times = new HashMap<>();
            indexs = new HashMap<>();
        }

        public void push(int val) {
            times.put(val, times.getOrDefault(val, 0) + 1);
            indexs.putIfAbsent(times.get(val), new LinkedList<>());
            indexs.get(times.get(val)).addLast(val);
            max = Math.max(max, times.get(val));
        }

        public int pop() {
            int v = indexs.get(max).peekLast();
            times.put(v, times.get(v) - 1);
            indexs.get(max).pollLast();
            if (indexs.get(max).isEmpty()) {
                max = 0;
            }
            return v;
        }
    }
}
