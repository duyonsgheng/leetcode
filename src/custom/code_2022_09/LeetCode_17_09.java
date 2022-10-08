package custom.code_2022_09;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName LeetCode_17_09
 * @Author Duys
 * @Description
 * @Date 2022/9/28 9:10
 **/
// 面试题 17.09. 第 k 个数
// 关联264题，丑数问题
public class LeetCode_17_09 {
    public int getKthMagicNumber1(int k) {
        int[] arr = new int[]{3, 5, 7};
        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        set.add(1l);
        queue.offer(1l);
        int ans = 0;
        for (int i = 0; i < k; i++) {
            long cur = queue.poll();
            ans = (int) cur;
            for (int a : arr) {
                long next = a * cur;
                if (!set.contains(next)) {
                    set.add(next);
                    queue.offer(next);
                }
            }
        }
        return ans;
    }

    public int getKthMagicNumber2(int k) {
        int[] dp = new int[k + 1];
        dp[1] = 1;
        int p3 = 1;
        int p5 = 1;
        int p7 = 1;
        for (int i = 2; i <= k; i++) {
            int a3 = dp[p3] * 3;
            int a5 = dp[p5] * 5;
            int a7 = dp[p7] * 7;
            dp[i] = Math.min(Math.min(a3, a5), a7);
            if (dp[i] == a3) {
                p3++;
            }
            if (dp[i] == a5) {
                p5++;
            }
            if (dp[i] == a7) {
                p7++;
            }
        }
        return dp[k];
    }
}
