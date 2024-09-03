package hope.class87;

/**
 * @author Mr.Du
 * @ClassName Code02_PickNumbersClosedSum
 * @date 2024年08月19日 下午 05:50
 */
// 选择k个数字使得两集合累加和相差不超过1
// 给定一个正数n，表示1~n这些数字都可以选择
// 给定一个正数k，表示要从1~n中选择k个数字组成集合A，剩下数字组成集合B
// 希望做到集合A和集合B的累加和相差不超过1
// 如果能做到，返回集合A选择了哪些数字，任何一种方案都可以
// 如果不能做到，返回长度为0的数组
// 2 <= n <= 10^6
// 1 <= k <= n
public class Code02_PickNumbersClosedSum {
    // 那么数组的和是奇数的时候 分别是 sum/2 sum/2 +1 ，和为偶数的时候 sum/2
    //
    public static int[] pick(int n, int k) {
        long sum = (n + 1) * n / 2;
        int[] ans = generate(sum / 2, n, k); // 和为偶数
        if (ans.length == 0 && (sum & 1) == 1) {  // 和为奇数
            ans = generate(sum / 2 + 1, n, k);
        }
        return ans;
    }

    // 1~n这些数字挑选k个，能不能凑够sum，如果能返回挑选的数的数组，如果不能返回长度为0的空数组
    public static int[] generate(long sum, int n, int k) {
        long minSum = (k + 1) * k / 2;
        int range = n - k;
        if (sum < minSum || sum > minSum + (long) range * k) {
            return new int[0];
        }
        // sum = 100
        // minSum = 15
        // need = 85
        long need = sum - minSum;
        // 需要几个数去右边进行互补
        int rightSize = (int) (need / range);
        int midIndex = (k - rightSize) + (int) (need % range);
        int leftSize = (k - rightSize) - (need % range == 0 ? 0 : 1);
        int[] ans = new int[k];
        for (int i = 0; i < leftSize; i++) {
            ans[i] = i + 1;
        }
        if (need % range != 0) {
            ans[leftSize] = midIndex;
        }
        for (int i = k - 1, j = 0; j < rightSize; i--, j++) {
            ans[i] = n - j;
        }
        return ans;
    }

    // 为了验证
    // 检验得到的结果是否正确
    public static boolean pass(int n, int k, int[] ans) {
        if (ans.length == 0) {
            if (canSplit(n, k)) {
                return false;
            } else {
                return true;
            }
        } else {
            if (ans.length != k) {
                return false;
            }
            int sum = (n + 1) * n / 2;
            int pickSum = 0;
            for (int num : ans) {
                pickSum += num;
            }
            return Math.abs(pickSum - (sum - pickSum)) <= 1;
        }
    }

    // 能不能做到-对数器验证
    public static boolean canSplit(int n, int k) {
        int sum = (n + 1) * n / 2;
        int wantSum = (sum / 2) + ((sum & 1) == 0 ? 0 : 1);
        int[][][] dp = new int[n + 1][k + 1][wantSum + 1];
        return f(n, 1, k, wantSum, dp);
    }

    // 当前来到i位置，还剩下k个要选，还剩下s需要搞定
    public static boolean f(int n, int i, int k, int s, int[][][] dp) {
        if (k < 0 || s < 0) {
            return false;
        }
        if (i == n + 1) {
            return s == 0 && k == 0;
        }
        if (dp[i][k][s] != 0) {
            return dp[i][k][s] == 1;
        }
        boolean ans = f(n, i + 1, k, s, dp) || f(n, i + 1, k - 1, s - i, dp);
        dp[i][k][s] = ans ? 1 : -1;
        return ans;
    }
}
