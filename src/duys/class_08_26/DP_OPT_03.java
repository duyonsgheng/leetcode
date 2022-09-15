package duys.class_08_26;

/**
 * @ClassName DP_OPT_03
 * @Author Duys
 * @Description 四边形不等式 - 石子合并问题 属于范围尝试模型
 * @Date 2021/8/27 10:04
 **/
public class DP_OPT_03 {
    /**
     * 题目三：
     * 摆放着n堆石子。现要将石子有次序地合并成一堆
     * 规定每次只能选相邻的2堆石子合并成新的一堆，
     * 并将新的一堆石子数记为该次合并的得分
     * 求出将n堆石子合并成一堆的最小得分（或最大得分）合并方案
     */

    // 暴力解1：递归
    public static int getMinStore1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sum = sumArr(arr);
        return process1(0, arr.length - 1, sum);
    }

    public static int process1(int L, int R, int[] sum) {
        // 只剩下一堆了。那么合并的代价0
        if (L == R) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        // L .... R的范围进行合并
        // 枚举每一次 划分的范围
        for (int i = L; i < R; i++) {
            next = Math.min(process1(L, i, sum) + process1(i + 1, R, sum), next);
        }
        // 得出的 合并最小值后，需要加上L - R范围的累加和
        return next + sum(sum, L, R);
    }


    // dp解法1：
    public static int getMinStore2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sum = sumArr(arr);
        int N = arr.length;
        int[][] dp = new int[N][N];
        // L >  R 的位置无效
        // L == R 为0 ，对角线全部是0，也就是说 N-1位置不用填了
        for (int L = N - 2; L >= 0; L--) {
            for (int R = 1; R < N; R++) {
                int next = Integer.MAX_VALUE;
                // L .... R的范围进行合并
                // 枚举每一次 划分的范围
                for (int i = L; i < R; i++) {
                    next = Math.min(dp[L][i] + dp[i + 1][R], next);
                }
                // 得出的 合并最小值后，需要加上L - R范围的累加和
                dp[L][R] = next + sum(sum, L, R);
            }
        }
        return dp[0][N - 1];
    }

    // dp解法2：通过dp解法1可以知道，每一个普遍位置依赖自己的左和下，那么左和下具有指导意义
    public static int getMinStore3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sum = sumArr(arr);
        int N = arr.length;
        int[][] dp = new int[N][N];
        // 把每一个位置的最佳划分点也记录下来
        int[][] best = new int[N][N];
        // dp[i][i]   // L == R 为0 ，对角线全部是0，也就是说 N-1位置不用填了
        // 位置依赖的规律
        /**
         *  例如  当前位置 要求 5 ~ 9
         *  已知： 4 ~ 8 范围的划分 最小值得到了，最小值的划分也得到了
         *  由于数组是非负的，那么 5 ~ 9 范围只会在 4 ~8 范围进行右扩或者不变
         *  同理 6 ~ 9 的范围 如果已知最佳划分的结果和位置，那么 5 ~ 9 的最佳划分位置一定是 4 ~ 8 和 6 ~ 9 划分位置之间
         *  只有两堆的时候，划分必然在中间，也就是i 位置，那么合并的代价是 两个数之和
         */
        // 倒数第二条对角线
        for (int i = 0; i < N; i++) {
            // 这其实是第二条对角线， 例如 3~3位置的划分，只有一个数，所以合并代价是0。那么 3~4范围的合并代价是 3位置和4位置的累加和
            dp[i][i + 1] = sum(sum, i, i + 1);
            // 例如 3~3位置的划分，只有一个数，没有划分意义。那么 3~4范围的划分只能是3，所以
            best[i][i + 1] = i;
        }
        // N-1 行只有一个对角线的位置需要填
        // N-2 行只有两个位置 第一条对角线和第二条对角线位置都已经填了
        // N-3行开始
        for (int L = N - 3; L >= 0; L--) {
            // L = R是对角线不需要填了，上面填好了
            // L= L+1 第二条对角线
            // 从L+2 开始
            for (int R = L + 2; R < N; R++) {
                int next = Integer.MAX_VALUE;
                // L .... R的范围进行合并
                int choose = -1;
                // 从左边的最右拿出来，把下边的最优拿出来，然后在两个最优中间尝试
                for (int leftEnd = best[L][R - 1]; leftEnd < best[L + 1][R]; leftEnd++) {
                    int cur = dp[L][leftEnd] + dp[leftEnd + 1][R];
                    // 如果得出的结果更小，那么更新，否则不变
                    if (cur < next) {
                        next = cur;
                        choose = leftEnd;
                    }
                }
                // 得出的 合并最小值后，需要加上L - R范围的累加和
                best[L][R] = choose;
                dp[L][R] = next + sum(sum, L, R);
            }
        }
        return dp[0][N - 1];
    }


    public static int sum(int[] sum, int L, int R) {
        return sum[R + 1] - sum[L];
    }

    public static int[] sumArr(int[] arr) {
        int[] sum = new int[arr.length + 1];
        sum[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return sum;
    }
}
