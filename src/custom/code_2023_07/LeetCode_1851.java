package custom.code_2023_07;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1851
 * @date 2023年07月18日
 */
// 1851. 包含每个查询的最小区间
// https://leetcode.cn/problems/minimum-interval-to-include-each-query/
public class LeetCode_1851 {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 开头小的在前
        int[][] que = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            que[i][0] = queries[i];
            que[i][1] = i;
        }
        // 查询的值小的在前面
        Arrays.sort(que, (a, b) -> a[0] - b[0]);
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        // 把区间长度小的在前面
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - a[0] - b[1] + b[0]);
        int index = 0;
        for (int i = 0; i < queries.length; i++) {
            // 都已经有序了，那么就直接可以用当前查询，然后满足需求的全部加到队列去
            while (index < intervals.length && que[i][0] >= intervals[index][0]) {
                queue.offer(new int[]{intervals[index][0], intervals[index][1]});
                index++;
            }
            // 把过期的，不合法的全部弹出
            while (!queue.isEmpty() && queue.peek()[1] < que[i][0]) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                int[] t = queue.peek();
                ans[que[i][1]] = t[1] - t[0] + 1;
            }
        }
        return ans;
    }
}
