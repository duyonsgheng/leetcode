package duys_code.day_23;

/**
 * @ClassName Code_04_1000_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/
 * @Date 2021/11/11 16:48
 **/
public class Code_04_1000_LeetCode {
    /**
     * 有 N 堆石头排成一排，第 i 堆中有stones[i]块石头。
     * 每次移动（move）需要将连续的K堆石头合并为一堆，而这个移动的成本为这K堆石头的总数。
     * 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
     */

    /**
     * 思路：首先我们最终的目标是合成1堆 在 0~n-1范围上
     * 1.L到R范围上 我们如果需要搞成1堆，就需要先 L到R范围上搞成K堆，然后合并
     */
    public static int mergeStones1(int[] stones, int K) {
        int n = stones.length;
        // 如果你都不能最后搞成一堆。那不行
        if ((n - 1) % (K - 1) > 0) {
            return -1;
        }
        // 0的前缀和是0
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }
        // 目标是0到n-1范围上搞出1份
        return process1(0, n - 1, 1, K, preSum);
    }

    // L~R范围上 搞成P份  我期望的是P份
    // 然后我们枚举 这P份中的第一份是哪个范围搞出来的---画家问题很像。最后一个画家搞定的画
    public static int process1(int L, int R, int P, int K, int[] preSum) {
        if (L == R) {// 只有一份了
            // 如果确实只需要搞出一份了。那么已经是一份了，代价就是0
            return P == 1 ? 0 : -1;
        }
        // 要我在L~R范围上搞成1份，那么肯定需要首先搞成K份。然后合并成一份
        if (P == 1) {
            int next = process1(L, R, K, K, preSum);
            if (next == -1) {
                return -1;
            } else {
                // 就是L到R范围的合并，就是累加和
                return next + preSum[R + 1] - preSum[L];
            }
        } else {
            int ans = Integer.MAX_VALUE;
            // 枚举这一份由谁搞定
            // 这里left+=k-1
            // 比如{6, 4, 9, 3, 1} k=3 left = 0。我们的k=3. left+1 +2 +.. k-2 的位置都已经计算了
            for (int left = L; left < R; left++) {
                // L~left 搞定一份，p-1份由 left+1 到R
                int next1 = process1(L, left, 1, K, preSum);
                int next2 = process1(left + 1, R, P - 1, K, preSum);
                // 都有效才能算游戏，其中一个无效，都算无效
                if (next1 != -1 && next2 != -1) {
                    ans = Math.min(ans, next1 + next2);
                } else {
                    ans = -1;
                }
            }
            return ans;
        }
    }


    public static int mergeStones2(int[] stones, int K) {
        int n = stones.length;
        // 如果你都不能最后搞成一堆。那不行
        if ((n - 1) % (K - 1) > 0) {
            return -1;
        }
        // 0的前缀和是0
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + stones[i];
        }
        int[][][] dp = new int[n + 1][n + 1][K + 1];
        // 目标是0到n-1范围上搞出1份
        return process2(0, n - 1, 1, K, preSum, dp);
    }

    // L~R范围上 搞成P份  我期望的是P份
    // 然后我们枚举 这P份中的第一份是哪个范围搞出来的---画家问题很像。最后一个画家搞定的画
    public static int process2(int L, int R, int P, int K, int[] preSum, int[][][] dp) {
        if (dp[L][R][P] != 0) {
            return dp[L][R][P];
        }
        if (L == R) {// 只有一份了
            // 如果确实只需要搞出一份了。那么已经是一份了，代价就是0
            return P == 1 ? 0 : -1;
        }
        // 要我在L~R范围上搞成1份，那么肯定需要首先搞成K份。然后合并成一份
        int min = Integer.MAX_VALUE;
        if (P == 1) {
            int next = process2(L, R, K, K, preSum, dp);
            if (next == -1) {
                min = -1;
            } else {
                // 就是L到R范围的合并，就是累加和
                min = next + preSum[R + 1] - preSum[L];
            }
        } else {
            int ans = Integer.MAX_VALUE;
            // 枚举这一份由谁搞定
            // 这里left+=k-1
            // 比如{6, 4, 9, 3, 1} k=3 left = 0。我们的k=3. left+1 +2 +.. k-2 的位置都已经计算了
            for (int left = L; left < R; left += K - 1) {
                // L~left 搞定一份，p-1份由 left+1 到R
                int next1 = process2(L, left, 1, K, preSum, dp);
                int next2 = process2(left + 1, R, P - 1, K, preSum, dp);

                // 都有效才能算游戏，其中一个无效，都算无效
                if (next1 != -1 && next2 != -1) {
                    ans = Math.min(ans, next1 + next2);
                } else {
                    ans = -1;
                }
            }
            min = ans;
        }
        dp[L][R][P] = min;
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {6, 4, 9, 3, 1};
        int k = 3;
        System.out.println(mergeStones1(arr, 3));
    }
}
