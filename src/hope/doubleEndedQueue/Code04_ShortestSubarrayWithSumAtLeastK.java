package hope.doubleEndedQueue;

/**
 * @author Mr.Du
 * @ClassName Code04_ShortestSubarrayWithSumAtLeastK
 * @date 2023年11月09日 9:41
 */
// 和至少为K的最短子数组
// 给定一个数组arr，其中的值有可能正、负、0
// 给定一个正数k
// 返回累加和>=k的所有子数组中，最短的子数组长度
// 测试链接 : https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/
public class Code04_ShortestSubarrayWithSumAtLeastK {
    public static int MAXN = 100001;

    // sum[0] : 前0个数的前缀和
    // sum[i] : 前i个数的前缀和
    public static long[] sum = new long[MAXN];

    public static int[] deque = new int[MAXN];

    public static int h, t;

    public static int shortestSubarray(int[] arr, int k) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        h = t = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            // 队列里是从小到大的，来到当前位置，看看和前面那些累加和之差满足 >= k
            while (h < t && sum[i] - sum[deque[h]] >= k)
                ans = Math.min(ans, i - deque[h++]);
            // 当前数要进来了，从尾巴上把大于当前前缀和的弹出，因为前面位置和更大，作差过后就更小了
            while (h < t && sum[deque[t - 1]] >= sum[i])
                t--;
            deque[t++] = i;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
