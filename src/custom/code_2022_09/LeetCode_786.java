package custom.code_2022_09;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_786
 * @Author Duys
 * @Description
 * @Date 2022/9/20 13:32
 **/
// 786. 第 K 个最小的素数分数
public class LeetCode_786 {
    // [1,2,3,5,7,11,13,17,19,23,29]
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        // a/b - c/d
        // a*d - c*b
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> arr[a[0]] * arr[b[1]] - arr[a[1]] * arr[b[0]]);
        for (int i = 1; i < n; i++) {
            queue.offer(new int[]{0, i});
        }
        for (int i = 1; i < k; i++) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            if (x + 1 < y) {
                queue.offer(new int[]{x + 1, y});
            }
        }
        return new int[]{arr[queue.peek()[0]], arr[queue.peek()[1]]};
    }
}
