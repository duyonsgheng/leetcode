package duys_code.day_16;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @ClassName Code_02_NotSum
 * @Author Duys
 * @Description
 * @Date 2021/10/28 14:10
 **/
public class Code_02_NotSum {
    /**
     * 题意：
     * 给定一个正数数组arr，
     * 返回arr的子集不能累加出的最小正数
     * 1）正常怎么做？
     * 2）如果arr中肯定有1这个值，怎么做？
     */

    public static int unformedSum1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 1;
        }
        HashSet<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        // 从最小的开始计算
        for (int i = min + 1; i != Integer.MIN_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    public static void process(int[] arr, int index, int sum, HashSet<Integer> set) {
        if (index == arr.length) {
            set.add(sum);
            return;
        }
        process(arr, index + 1, sum, set);
        process(arr, index + 1, sum + arr[index], set);
    }

    public static int unformedSum2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 1;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        int N = arr.length;
        boolean[][] dp = new boolean[N][sum + 1];
        // 第一列，就是sum = 0，可以不可以搞出来，一个数都不取，就是0
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        // 第一行默认就是false
        // arr[0] = arr[0]
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                // 要或者不要当前位置，或者就可以了
                dp[i][j] = dp[i - 1][j] || (j >= arr[i]) ? (dp[i - 1][j - arr[i]]) : false;
            }
        }
        // 最后遍历一下，我们全部数组元素都要，然后从最小值查看
        for (int j = min; j <= sum; j++) {
            if (!dp[N - 1][j]) {
                return j;
            }
        }
        return sum + 1;
    }

    // 已知arr中肯定有1这个数
    public static int unformedSum3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int range = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }
}
