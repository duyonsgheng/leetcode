package custom.code_2022_08;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_636
 * @Author Duys
 * @Description
 * @Date 2022/8/30 17:08
 **/
public class LeetCode_636 {
    // 0:start:0
    public int[] exclusiveTime(int n, List<String> logs) {
        // queue中表示 函数代号，和函数开始时间
        // 遇到结束时间就结算
        Deque<int[]> queue = new LinkedList<>();
        int[] res = new int[n];
        for (String log : logs) {
            String[] split = log.split(":");
            int index = Integer.valueOf(split[0]);
            String type = split[1];
            int time = Integer.valueOf(split[2]);
            if ("start".equals(type)) {
                if (!queue.isEmpty()) {
                    res[queue.peek()[0]] += time - queue.peek()[1];
                    queue.peek()[1] += time;
                }
                queue.push(new int[]{index, time});
            } else {
                int[] last = queue.poll();
                res[last[0]] += time - last[1] + 1;
                if (!queue.isEmpty()) {
                    queue.peek()[1] = time + 1;
                }
            }
        }
        return res;
    }
}
