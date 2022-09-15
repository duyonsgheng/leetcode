package week.week_2022_04_03;

/**
 * @ClassName Code_04_SumEvenSubNumber
 * @Author Duys
 * @Description
 * @Date 2022/4/21 10:12
 **/

// 总长度为n的数组中，所有长度为k的子序列里，有多少子序列的和为偶数
public class Code_04_SumEvenSubNumber {

    // 思路：
    // 这种问题遇到了很多回了，子序列问题是范围上的尝试
    // dp[i][j] 表示 0到i位置上 长度为k的子序列种，有多少子序列的和为偶数
    // 可能性分析：
    // 可能性1：不要当前的数
    // 可能性2：要当前的数
    //2.1.当前arr[i] 是奇数
    //2.2.当前arr[i] 是偶数
    // 所以需要两个dp 一次推下去
    public static int sunEvenSubNumber(int[] arr, int k) {
        if (arr == null || arr.length < 1 || k < 1 || k > arr.length) {
            return 0;
        }
        int n = arr.length;
        // 偶数
        int[][] even = new int[n + 1][k + 1];
        // 偶数
        int[][] odd = new int[n + 1][k + 1];
        // 就是0到i的范围上选0个数，和是偶数，就是一个都不选
        for (int i = 0; i < n; i++) {
            even[i][0] = 1;
        }
        // 普遍位置的依赖
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                even[i][j] = even[i - 1][j];
                odd[i][j] = odd[i - 1][j];
                // 要当前数的时候份情况了
                even[i][j] += (arr[i - 1] & 1) == 0 ? even[i - 1][j - 1] : odd[i - 1][j - 1];
                odd[i][j] += (arr[i - 1] & 1) == 1 ? even[i - 1][j - 1] : odd[i - 1][j - 1];
            }
        }
        return even[n][k];
    }
}
