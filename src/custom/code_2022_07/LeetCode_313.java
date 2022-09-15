package custom.code_2022_07;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_313
 * @Author Duys
 * @Description
 * @Date 2022/7/14 18:04
 **/
// 313. 超级丑数
public class LeetCode_313 {

    // 不好理解
    public static int nthSuperUglyNumber1(int n, int[] primes) {
        if (n < 1) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int len = primes.length;
        int[] points = new int[len];
        int[] nums = new int[len];
        Arrays.fill(nums, 1);
        for (int i = 1; i <= n; i++) {
            int min = Arrays.stream(nums).min().getAsInt();
            dp[i] = min;
            for (int j = 0; j < len; j++) {
                if (nums[j] == min) {
                    points[j]++;
                    nums[j] = dp[points[j]] * primes[j];
                }
            }
        }
        return dp[n];
    }

    // 好理解的解答1
    // 多路并用
    public static int nthSuperUglyNumber2(int n, int[] primes) {
        int m = primes.length;
        // int[3] : [0] -> 是由primes中哪一个因子来的，[1] -> primes中的index，[2] -> 丑数的位置
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < m; i++) {
            heap.add(new int[]{primes[i], i, 0});
        }
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1; i < n; ) {
            int[] cur = heap.poll();
            int value = cur[0];
            int index = cur[1];
            int index_ans = cur[2];
            if (value != ans[i - 1]) {
                ans[i++] = value;
            }
            heap.add(new int[]{ans[index_ans + 1] * primes[index], index, index_ans + 1});
        }
        return ans[n - 1];
    }

    // 好理解的解答2
    // 优先级队列
    public static int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        while (n-- > 0) {
            int cur = queue.poll();
            if (n == 0) {
                return cur;
            }
            for (int i : primes) {
                if (i <= (Integer.MAX_VALUE / cur)) {
                    queue.add(cur * i);
                }
                if (cur % i == 0) {
                    break;
                }
            }
        }
        return -1;
    }
}
