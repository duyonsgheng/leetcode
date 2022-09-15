package duys.class_08_23;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName SubsquenceMaxModM
 * @Author Duys
 * @Description
 * @Date 2021/8/24 10:47
 **/
public class SubsquenceMaxModM {
    /**
     * 题意：给定一个非负数组arr，和一个正数m。 返回arr的所有子序列中累加和%m之后的最大值。
     */

    public static int max1(int[] arr, int m) {
        Set<Integer> set = new HashSet<>();
        process1(arr, 0, 0, set);
        int max = 0;
        for (Integer i : set) {
            max = Math.max(max, i % m);
        }
        return max;
    }

    public static void process1(int[] arr, int index, int sum, Set<Integer> set) {
        // 都到最后了。没数了，直接添加
        if (index == arr.length) {
            set.add(sum);
        } else {
            // 当前位置要不要的问题
            process1(arr, index + 1, sum, set);
            process1(arr, index + 1, sum + arr[index], set);
        }
    }

    // 第一种动态规划，当数据量都不是很大的时候,局限在sum如果很大的情况下，不能用
    public static int max2(int[] arr, int m) {
        int N = arr.length;
        int sum = 0;
        for (int num : arr)
            sum += num;

        //dp[i][j]的含义 从0-i，能否凑出sum来
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                // 同样的思路，要不要当前位置
                //不要当前位置。不要i位置，那么就是i-1位置的值
                dp[i][j] = dp[i - 1][j];
                // 要当前位置
                if (j >= arr[i]) {
                    dp[i][j] |= dp[i - 1][j - arr[i]];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[N - 1][i]) {
                ans = Math.max(ans, i % m);
            }
        }
        return ans;
    }

    // 这种就是在m不是很大的情况下
    public static int max3(int[] arr, int m) {

        //dp[i][j]的含义：0-i位置组成的sum % m的余数是不是j
        int N = arr.length;
        boolean[][] dp = new boolean[N][m];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                // 当前的j要不要问题
                // 不要
                dp[i][j] = dp[i - 1][j];
                int cur = arr[i] % m;
                // 如果要了当前的，那么当前的算出来后，需要j减去当前的
                if (cur < j) {
                    dp[i][j] |= dp[i - 1][j - cur];
                } else {
                    dp[i][j] |= dp[i - 1][m + j - cur];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (dp[N - 1][i]) {
                ans = i;
            }
        }
        return ans;
    }

    // 当arr累加和很大，m也很大的情况下，显然不能使用上面两种情况了
    // 分治来了
    // 数组直接分为两半
    public static int max4(int[] arr, int m) {
        if (arr.length == 1) {
            return arr[0] % m;
        }
        int mid = arr.length - 1 >> 1;
        TreeSet<Integer> leftSet = new TreeSet<>();
        process4(arr, 0, mid, 0, m, leftSet);
        TreeSet<Integer> rightSet = new TreeSet<>();
        process4(arr, mid + 1, arr.length - 1, 0, m, rightSet);
        int ans = 0;
        for (Integer l : leftSet) {
            // 比如左边的余数是1，右边的就要小于等于7 离7最近的
            ans = Math.max(ans, l + rightSet.floor(m - 1 - l));
        }
        return ans;
    }

    public static void process4(int[] arr, int index, int end, int sum, int m, TreeSet<Integer> set) {
        if (index == end + 1) {
            set.add(sum % m);
        } else {
            process4(arr, index + 1, end, sum, m, set);
            process4(arr, index + 1, end, sum + arr[index], m, set);
        }
    }
}
