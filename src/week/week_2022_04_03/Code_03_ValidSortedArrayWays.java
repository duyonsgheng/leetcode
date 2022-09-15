package week.week_2022_04_03;

/**
 * @ClassName Code_03_ValidSortedArrayWays
 * @Author Duys
 * @Description
 * @Date 2022/4/19 15:30
 **/
// 来自腾讯音乐
// 原本数组中都是大于0、小于等于k的数字，是一个单调不减的数组
// 其中可能有相等的数字，总体趋势是递增的
// 但是其中有些位置的数被替换成了0，我们需要求出所有的把0替换的方案数量：
// 1）填充的每一个数可以大于等于前一个数，小于等于后一个数
// 2）填充的每一个数不能大于k
public class Code_03_ValidSortedArrayWays {

    public static long ways(int[] arr, int k) {
        if (arr == null || arr.length < 1 || k <= 0) {
            return 0;
        }
        int n = arr.length;
        // dp[i][j] 就是数组的前i个数，总共有j种数可以选择，满足需求的总共有多少种方案
        long[][] dp = new long[n + 1][k + 1];
        // k = 1的时候，只有1个方案，全部填1
        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
        }
        // 当数组只有一个位置，那么每一个位置都有k种
        for (int i = 1; i <= k; i++) {
            dp[1][i] = i;
        }
        // 普遍位置
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                // 当前位置的数和i位置保持一致
                // 当前位置的数和j-1的相等
                // 可能性1.当前位置的数，就是不用最小的，那么就和之前i-1位置保持一致 dp[i-1][j]
                // 可能性2.当前位置的数，就是使用最小的，那么就是，已经使用了一个数，格子还是有这么多，dp[i][j-1]
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        long res = 1;
        for (int i = 0, j = 0; i < n; i++) {
            if (arr[i] != 0) {
                continue;
            }
            j = i + 1;
            // 把临近位置是0的全部漏出来
            while (j < n && arr[j] == 0) {
                j++;
            }
            // 把当前是0的区域左右两边的数拿出来，如果是开始和结束位置，那么开始位置就是1，结束位置就是k
            int left = i - 1 > 0 ? arr[i - 1] : 1;
            int right = j < n ? arr[j] : k;
            // 比如 a 0 0 0 b 0 0 c d 0 0 0
            // 就是几个区域不通方法数乘积
            // dp[j-i] 表示当前有几个为0的位置
            // dp[right-left +1]表示有多少数可以选择，算上两边的边界 ，所以+1
            res *= dp[j - i][right - left + 1];
            i = j; // 下一次从这个位置开始了。i-j中间的饿都算过了。
        }
        return res;
    }
}
