package custom.code_2022_09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName LeetCode_743
 * @Author Duys
 * @Description
 * @Date 2022/9/16 13:47
 **/

public class LeetCode_743 {
    public static void main(String[] args) {
        int[][] times = {{1, 2, 1}};
        int n = 2, k = 2;
        System.out.println(networkDelayTime(times, n, k));
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        if (times == null || times.length == 0 || times[0] == null || times[0].length == 0) {
            return -1;
        }
        if (n <= 1) {
            return 0;
        }
        if (k <= 0 || k > n) {
            return -1;
        }
        k--;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            int source = time[0] - 1;
            int target = time[1] - 1;
            int dest = time[2];
            if (!map.containsKey(source)) {
                map.put(source, new HashMap<>());
            }
            map.get(source).put(target, dest);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[k] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.offer(new int[]{k, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int pot = cur[0];
            int dst = cur[1];
            if (dist[pot] < dst) {
                continue;
            }
            Map<Integer, Integer> nexts = map.get(pot);
            if (nexts == null) {
                continue;
            }
            for (int next : nexts.keySet()) {
                int d = nexts.get(next) + dst;
                if (dist[next] > d) {
                    dist[next] = d;
                    queue.add(new int[]{next, d});
                }
            }
        }
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans >= Integer.MAX_VALUE / 2 ? -1 : ans;
    }
}
