package duys.class_09_09;

/**
 * @ClassName BurstBalloons
 * @Author Duys
 * @Description 力扣的原题： https://leetcode-cn.com/problems/burst-balloons/
 * @Date 2021/9/9 15:40
 **/
public class BurstBalloons {
    /**
     * 题意： 比如现在有 3个气球，每一个气球都有分数 [3,2,5] 得分是左边没爆离我最近的 * 右边没爆的气球离我最近的 * 我的分数
     * 比如先打爆2这个气球， 3*2*5
     * 比如先打爆3这个气球，左边是没有的就认为是1，右边是2 那么等分就是1*3*2
     */

    /**
     * 猜法1：再L~R的位置上，第一个打爆的气球的位置是哪里。那么这个递归的参数，将变得很复杂，因为L~R范围上，那么其他的范围会影响到L~R的解
     * 猜法2：再L~R上，必须满足L-1和R+1位置上没爆。
     * 那么可能性分析：再L~R上，最后一个打爆的气球是什么位置
     * 例如 [2,3,4,5,6]
     * -----0 1 2 3 4
     * 1.0位置是最后打爆的 那么子过程就变成了ans2=f(1,4) ,结果是 ans2+ 0位置的数，因为0的左边我们认为是1，0的右边我们认为已经爆了，也是1
     * 2.1位置的是组后打爆的，那么子过程是啥 ans1=f(0,0),ans2=f(2,4),结果是 ans1+ans2+1位置的数，1位置的左边爆了，右边也爆了，
     * .....
     * 2位置最后打爆
     * 3位置最后打爆
     * 求最大值
     * 那么为了防止越界 原数组变成 -> [1,2,3,4,5,6,1]左右各自增加一个数 主函数调用f(1,5)
     */
    public static int maxCoins0(int[] arr) {
        int N = arr.length;
        int[] newArr = new int[N + 2];
        for (int i = 0; i < N; i++) {
            newArr[i + 1] = arr[i];
        }
        newArr[0] = 1;
        newArr[N + 1] = 1;
        return process1(newArr, 1, N);
    }

    // L-1位置的气球和R+1位置的气球一定没爆，因为主函数对arr已经发生了变更，所以L-1和R+1位置都不会越界
    // 返回L ~ R位置上所有的气球都爆，最大的得分
    public static int process1(int[] arr, int L, int R) {
        // 只有一个气球了 ,因为L-1 和R+1 位置上的气球是没有爆的。
        // base case
        if (L == R) {
            return arr[L - 1] * arr[R] * arr[R + 1];
        }
        // 尝试L~R位置上，所有位置最后一个打爆的情况

        // L位置最后打爆
        int p1 = process1(arr, L + 1, R) + arr[L] * arr[L - 1] * arr[R + 1];
        // R位置最后打爆
        int p2 = process1(arr, L, R - 1) + arr[L - 1] * arr[R] * arr[R + 1];
        int max = Math.max(p1, p2);
        // 然后就是 L ~ R 的中间位置进行尝试 ，L 和 R两个位置都单独讨论过了
        for (int i = L + 1; i < R; i++) {
            // 这里的意思就是 i 位置的气球最后爆，递归的含义是整个L ~ R上，讨论最后一个爆的气球的位置
            int left = process1(arr, L, i - 1);
            int right = process1(arr, i + 1, R);
            int lastI = arr[L - 1] * arr[i] * arr[R + 1];
            int cur = left + right + lastI;
            max = Math.max(cur, max);
        }
        return max;
    }

    // 改动态规划
    public static int maxCoins1(int[] arr) {
        int N = arr.length;
        int[] newArr = new int[N + 2];
        for (int i = 0; i < N; i++) {
            newArr[i + 1] = arr[i];
        }
        newArr[0] = 1;
        newArr[N + 1] = 1;
        // 对于新得这个数组来做状态转移表
        // dp[i][j]是啥含义？
        // 是 i ~ j 范围上的最大得分
        int[][] dp = new int[N + 2][N + 2];
        // 这一张dp表的对角线是 L=R 的位置，也就是递归里的base case。
        // 递归中从1位置开始算的，0位置没意义，所以从1开始
        for (int i = 1; i <= N; i++) {
            dp[i][i] = newArr[i - 1] * newArr[i] * newArr[i + 1];
        }
        // 普遍位置 ，递归是从 L 和 R位置进行讨论的，并且L > R的区域是无效的
        // 比如 (1,5) 这个位置依赖哪些，依赖 自己左边的和下面的
        // 那么我们怎么填，最下边一行没有下，默认是1，
        // 从下往上，从左往右
        for (int L = N; L >= 1; L--) {
            // 对角线的上半区域才是有效的
            for (int R = L + 1; R <= N; R++) {
                // L位置最后打爆
                int p1 = dp[L + 1][R] + newArr[L] * newArr[L - 1] * newArr[R + 1];
                // R位置最后打爆
                int p2 = dp[L][R - 1] + newArr[L - 1] * newArr[R] * newArr[R + 1];
                int max = Math.max(p1, p2);
                // 然后就是 L ~ R 的中间位置进行尝试 ，L 和 R两个位置都单独讨论过了
                for (int i = L + 1; i < R; i++) {
                    // 这里的意思就是 i 位置的气球最后爆，递归的含义是整个L ~ R上，讨论最后一个爆的气球的位置
                    int left = dp[L][i - 1];
                    int right = dp[i + 1][R];
                    int lastI = newArr[L - 1] * newArr[i] * newArr[R + 1];
                    int cur = left + right + lastI;
                    max = Math.max(cur, max);
                }
                dp[L][R] = max;
            }
        }
        return dp[1][N];
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 5};
        System.out.println(maxCoins0(arr));
    }
}
