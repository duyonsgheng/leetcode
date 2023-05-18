package custom.code_2023_04;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName LeetCode_1654
 * @Author Duys
 * @Description
 * @Date 2023/4/24 10:34
 **/
// 1654. 到家的最少跳跃次数
public class LeetCode_1654 {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int limit = 8000;
        Set<Integer> set = new HashSet<>(); // 不能去的点
        for (int num : forbidden) {
            set.add(num);
        }
        Queue<int[]> queue = new PriorityQueue<>((m, n) -> m[3] - n[3]);
        queue.add(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int cx = cur[0];
                int flag = cur[1];
                int dist = cur[2];
                if (cx == x) {
                    return dist;
                }
                if (cx + a < limit && !set.contains(cx + a)) {
                    set.add(cx + a);
                    queue.add(new int[]{cx + a, 0, dist + 1});
                }
                if (flag == 0 && cx - b > 0 && !set.contains(cx - b)) {
                    queue.add(new int[]{cx - b, 1, dist + 1});
                }
            }
        }
        return -1;
    }
}
