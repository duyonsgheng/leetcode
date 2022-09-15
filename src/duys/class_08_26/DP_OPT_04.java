package duys.class_08_26;

/**
 * @ClassName DP_OPT_04
 * @Author Duys
 * @Description 四边形不等式 - 画匠问题，从左到右尝试模型
 * @Date 2021/8/27 15:20
 **/
public class DP_OPT_04 {
    /**
     * 给定一个整型数组 arr，数组中的每个值都为正数，表示完成一幅画作需要的时间，再 给定 一个整数 num，
     * 表示画匠的数量，每个画匠只能画连在一起的画作。所有的画家 并行工作，请 返回完成所有的画作需要的最少时间。
     * 【举例】
     * arr=[3,1,4]，num=2。
     * 最好的分配方式为第一个画匠画 3 和 1，所需时间为 4。第二个画匠画 4，所需时间 为 4。 因为并行工作，
     * 所以最少时间为 4。如果分配方式为第一个画匠画 3，所需时 间为 3。第二个画 匠画 1 和 4，
     * 所需的时间为 5。那么最少时间为 5，显然没有第一 种分配方式好。所以返回 4。
     * arr=[1,1,1,4,3]，num=3。
     * 最好的分配方式为第一个画匠画前三个 1，所需时间为 3。第二个画匠画 4，所需时间 为 4。 第三个画匠画 3，所需时间为 3。返回 4。
     */

    /**
     * 以最后一个画家为划分
     * 比如： [3,4,5,6] k =3
     * 0-3 范围上2个画家 最后一个画家不用
     * 0-2 范围上2个画家 3 - 3范围一个画家
     * 0-1 范围2个画家，2 - 3范围上第三个画家
     * ......
     */

    /**
     * dp[i][j] 0~i 副画 j个画家的最优时间
     * i=0 表示只有一幅画 就是arr[0]
     * j=0 表示0个画家无意义 j=1 表示一个画家 直接 数组和就可以了
     * 每一个普遍位置依赖他的左边哪一列的格子。
     * 转换一下：自己下边格子也会依赖下边位置的左边
     */

    /**
     * 不优化，直接进行枚举行为
     * O(N^2 * K)
     * <p>
     * nums - 表示 完成每一幅画需要的时间，K 多少个画家
     */
    public static int splitArray1(int[] nums, int K) {
        int N = nums.length;
        int[] sum = sumArr(nums);
        int[][] dp = new int[N][K + 1];
        // dp[i]p[j] - 表示 0到i副画 j个画家 的最优完成时间
        for (int i = 1; i <= K; i++) {
            // 只有副画- 所以 最佳完成时间，都是就是num[0]
            dp[0][i] = nums[0];
        }
        for (int i = 1; i < N; i++) {
            // 只有一个画家，那么完成所有的画，最优就是前缀和
            dp[i][1] = sum(sum, 0, i);
        }
        // 现在已经有了第0行和第一列，因为0个画家，是没有意义的
        // 从上往下，从左往右填就好了
        for (int i = 1; i < N; i++) {
            for (int j = 2; j <= K; j++) {
                int ans = Integer.MAX_VALUE;
                // 最后一个画家负责的哪些画
                for (int leftEnd = 0; leftEnd <= i; leftEnd++) {
                    // 左边0 到 leftEnd 的画由j-1个画家负责
                    int leftAns = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    // 右边的从leftEnd+1 到i的画由最后一个画家负责
                    int rightAns = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    // 因为是最早结束时间，所以这里求两边的最大值
                    int cur = Math.max(leftAns, rightAns);
                    if (cur < ans) {
                        ans = cur;
                    }
                }
                dp[i][j] = ans;
            }
        }
        return dp[N - 1][K];

    }

    // 使用四边形不等式进行优化 枚举行为
    // O(N*K) -直接降低了一维
    public static int splitArray2(int[] nums, int K) {
        int N = nums.length;
        int[] sum = sumArr(nums);
        int[][] dp = new int[N][K + 1];
        // 存放每一个位置的最优划分情况
        int[][] best = new int[N][K + 1];
        // dp[i]p[j] - 表示 0到i副画 j个画家 的最优完成时间
        for (int i = 1; i <= K; i++) {
            // 只有1副画- 所以 最佳完成时间，都是就是num[0]
            dp[0][i] = nums[0];
            best[0][i] = -1;// 只有一幅画，划分没意义
        }
        for (int i = 1; i < N; i++) {
            // 只有一个画家，那么完成所有的画，最优就是前缀和
            dp[i][1] = sum(sum, 0, i);
            // 只有一个画家，划分最优也没意义
            best[i][1] = -1;
        }
        // 分析指导位置了
        /**
         * 比如： [5][3] 依赖 [4][2],[3][2],[2][2],[1,2],[0,2]，每一个位置
         * 要进行位置换算了，已知 第一列的值和第0行的值，那么第二列依赖第一列 ，最后一行的每一个位置，只依赖自己的上一列的值，
         * 对于普遍的位置来说，可以使用左边和下边的位置进行代替
         */
        // 从左往右，从下往上
        for (int j = 2; j <= K; j++) {
            for (int i = N - 1; i >= 1; i--) {
                // 问我左边的格子要一个下限，
                // 问我右边格子要一个上限，
                /**
                 * 这里上下限，来源，因为
                 * 当画家固定，画增加 ，结果增加
                 * 当画家固定，画减少 ，结果减小
                 * 当画固定，画家增多，结果减小
                 * 当画固定，画家增多，结果增加
                 * 画家和画的关系 相对结果来说，是反向关系的。
                 * 所以，我左边格子是啥，画家固定，画减少，结果较小，那么指导位置的下限
                 * 所以，我下边格子是啥，画固定，画家增加，结果增加，那么指导位置的上限
                 */
                int down = best[i][j - 1];
                // 上限 -当是最后一行的时候，不需要指导，直接就是N-1
                int up = i == N - 1 ? N - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int bestIndex = -1;
                // 在上限和下限之前进行枚举
                for (int leftEnd = down; leftEnd <= up; leftEnd++) {
                    // 如果在划分迭代的时候遇到是-1的说明之前的划分无意义，那么答案就是0
                    int leftAns = leftEnd == -1 ? 0 : dp[leftEnd][j - 1];
                    // 如果划分到了当前的i位置，说明啥，最后一个画家鸡毛不干，闲着
                    int rightAns = leftEnd == i ? 0 : sum(sum, leftEnd + 1, i);
                    int cur = Math.max(leftAns, rightAns);
                    if (cur < ans) {
                        ans = cur;
                        bestIndex = leftEnd;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = bestIndex;
            }
        }
        return dp[N - 1][K];
    }

    public static int[] sumArr(int[] arr) {
        int[] sum = new int[arr.length + 1];
        sum[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return sum;
    }

    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    // 这是画匠问题得最优解
    // 首先我们要得最优，一定在 nums得累加和里面
    // 我画固定，需要在cur时间搞定，需要几个画家
    public static int splitArray3(int[] nums, int K) {
        int N = nums.length;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum = +nums[i];
        }
        long l = 0;
        long r = sum;
        long ans = 0;
        // 二分找
        while (l <= r) {
            long mid = (l + r) >> 2;
            long childAns = getNeedParts(nums, mid);
            if (childAns <= K) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) ans;
    }

    // 在整个数组中，给定一个目标值，看看需要几个画家可以搞定这个目标
    // 就和油桶加油一样 ,如果 1 位置和2位置相加正好是一个油桶得容量，那么对于3位置来说，需要一个新得油桶来装
    public static int getNeedParts(int[] arr, long mid) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > mid) {
                return Integer.MAX_VALUE;
            }
        }
        int ans = 1;
        int all = arr[0];
        for (int i = 1; i < arr.length; i++) {
            // 如果当前得位置和之前得和 超过了目标，那么需要增加画家
            if (all + arr[i] > mid) {
                ans++;
                all = arr[i];
            } else {
                // 如果没有超过，说明之前得最后一个画家依然可以搞定
                all += arr[i];
            }
        }
        return ans;
    }
}
