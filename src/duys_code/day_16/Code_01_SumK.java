package duys_code.day_16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Code_01_SumK
 * @Author Duys
 * @Description
 * @Date 2021/10/28 13:19
 **/
public class Code_01_SumK {
    /**
     * 题意：
     * 给定一个有正、有负、有0的数组arr，
     * 给定一个整数k，
     * 返回arr的子集是否能累加出k
     * 1）正常怎么做？
     * 2）如果arr中的数值很大，但是arr的长度不大，怎么做？
     */

    public static boolean isSum1(int[] arr, int k) {
        if (arr == null || arr.length < 1) {
            return false;
        }
        if (k == 0) {
            return true;
        }
        return process1(arr, 0, k);
    }

    public static boolean process1(int[] arr, int index, int rest) {
        if (rest == 0) {
            return true;
        }
        if (index == arr.length) {
            return false;
        }
        boolean p1 = process1(arr, index + 1, rest);
        if (p1) {
            return p1;
        }
        return process1(arr, index + 1, rest - arr[index]);
    }


    public static boolean isSum2(int[] arr, int k) {
        if (arr == null || arr.length < 1) {
            return false;
        }
        if (k == 0) {
            return true;
        }
        return process2(arr, 0, k, new HashMap<>());
    }

    public static boolean process2(int[] arr, int index, int rest, HashMap<Integer, HashMap<Integer, Boolean>> dp) {
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
            return dp.get(index).get(rest);
        }
        boolean ans = false;
        if (rest == 0) {
            ans = true;
        } else if (index == arr.length) {
            ans = false;
        } else {
            boolean p1 = process2(arr, index + 1, rest, dp);
            if (p1) {
                ans = p1;
            } else {
                ans = process2(arr, index + 1, rest - arr[index], dp);
            }
        }
        if (!dp.containsKey(index)) {
            dp.put(index, new HashMap<>());
        }
        dp.get(index).put(rest, ans);
        return ans;
    }

    // 位置依赖
    public static boolean isSum3(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        // sum != 0
        if (arr == null || arr.length == 0) {
            return false;
        }
        // arr有数，sum不为0
        int min = 0;
        int max = 0;
        // 把负数全部累加，正数全部累加
        for (int num : arr) {
            min += num < 0 ? num : 0;
            max += num > 0 ? num : 0;
        }
        // min~max
        if (sum < min || sum > max) {
            return false;
        }
        int N = arr.length;
        boolean[][] dp = new boolean[N][max - min - 1];

        // 因为数组不能表示我们的负数的位置、
        // dp[0][0] -> dp[0][-min]
        dp[0][-min] = true;
        //
        dp[0][arr[0] - max] = true;
        //
        for (int i = 1; i < N; i++) {
            for (int j = min; j <= max; j++) {
                // 不要当前数
                dp[i][j - min] = dp[i - 1][j - min];
                // 要当前数
                int next = j - min - arr[i];
                if (next >= 0 && next <= max - min) {
                    dp[i][j - min] |= dp[i - 1][next];
                }
            }
        }
        return dp[N - 1][sum - min];
    }

    // arr中的值可能为正，可能为负，可能为0
    // 自由选择arr中的数字，能不能累加得到sum
    // 分治的方法
    // 如果arr中的数值特别大，动态规划方法依然会很慢
    // 此时如果arr的数字个数不算多(40以内)，哪怕其中的数值很大，分治的方法也将是最优解
    public static boolean isSum4(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        if (arr.length == 1) {
            return arr[0] == sum;
        }
        int N = arr.length;
        int mid = N >> 1;
        HashSet<Integer> leftSum = new HashSet<>();
        HashSet<Integer> rightSum = new HashSet<>();
        process4(arr, 0, mid, 0, leftSum);
        process4(arr, mid, N, 0, rightSum);
        for (int l : leftSum) {
            if (rightSum.contains(sum - l)) {
                return true;
            }
        }
        return false;
    }

    // 算开头，不算结尾。 [l~r)
    public static void process4(int[] arr, int l, int r, int pre, HashSet<Integer> set) {
        if (l == r) {
            set.add(pre);
        } else {
            process4(arr, l + 1, r, pre, set);
            process4(arr, l + 1, r, pre + arr[l], set);
        }
    }
}
