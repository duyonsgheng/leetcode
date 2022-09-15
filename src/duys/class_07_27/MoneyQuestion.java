package duys.class_07_27;

/**
 * @ClassName MoneyQuestion
 * @Author Duys
 * @Description - 斜率优化，观察临近位置，观察位置包含关系
 * @Date 2021/7/28 14:39
 **/
public class MoneyQuestion {
    /**
     * 题意：
     * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
     * 每个值都认为是一种面值，且认为张数是无限的。
     * 返回组成aim的最少货币数
     */
    /**
     * 比如：[2,5,10] 2面值的钱无线张，5面值的无限张，10面值的无限张， 给一个 1002 问最少需要多少张 100张10的，和一张2快的 101张
     */

    public static int minMoneyNums(int[] arr, int aim) {
        if (arr == null || aim < 0) {
            return Integer.MAX_VALUE;
        }
        return process1(arr, 0, aim);
    }

    // 用Integer.MAX_VALUE标记搞不定当前的钱
    public static int process1(int[] arr, int index, int rest) {
        int N = arr.length;
        // 如果没钱了，并且剩余的钱为0了。
        if (index == N) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        // 每一个位置的面值都全部试，
        for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
            int next = process1(arr, index + 1, rest - zhang * arr[index]);
            if (next != Integer.MAX_VALUE) {
                // 当前选了多少张，下一个位置需要多少张，然后加起来
                ans = Math.min(ans, next + zhang);
            }
        }
        return ans;
    }

    public static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        // 根据暴力解，填值
        // 当index = N的时候，只有rest==0 的位置是0，其他的位置都是Integer.MAX_VALUE
        dp[N][0] = 0;
        // 否则
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                // 每一个位置的面值都全部试，
                for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * arr[index]];
                    if (next != Integer.MAX_VALUE) {
                        // 当前选了多少张，下一个位置需要多少张，然后加起来
                        ans = Math.min(ans, next + zhang);
                    }
                }
                dp[index][rest] = ans;
            }
        }


        return dp[0][aim];
    }

    /**  0 1 2 3 4  -- aim
     * 0
     * 1
     * 2
     * 3 0 x x x x
     * index
     */
    // 看位置依赖

    /**
     * 比如  aim = 14  arr[2,3,4,5]   index = 2  4
     * - 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14  aim
     * 0
     * 1
     * 2                     [x]         [v]
     * 3    [d]     [c]      [b]         [a]
     * index
     */
    // index位置依赖index+1 的部分位置，ans+next
    // 如上面分析 位置v依赖 a b c d四个位置  a位置表示使用了0张  b使用了1张 ...
    // 所以位置v就是 a+0 b+1 c+2 d+3 这四个中求一个最小的
    // 需要观察位置x是怎么实现来的
    // x 来说 b+0 , c+1 ,d+2 谁小选谁 那么 v就是 (x+1,a)求一个最小
    // 最终的到是 dp[i][j]  = Math.min(dp[i+1][j],dp[i][j-arr[i]]+1)
    // 其他位置，开始填值,从下往上填
    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        // 根据暴力解，填值
        // 当index = N的时候，只有rest==0 的位置是0，其他的位置都是Integer.MAX_VALUE
        dp[N][0] = 0;
        // 否则
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                // 保证不越界，和数据有效
                if (rest - arr[index] >= 0
                        && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index + 1][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }
}
