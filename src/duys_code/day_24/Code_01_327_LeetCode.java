package duys_code.day_24;

/**
 * @ClassName Code_01_327_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/count-of-range-sum/
 * @Date 2021/11/12 16:11
 **/
public class Code_01_327_LeetCode {
    /**
     * 给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含lower和upper）之内的 区间和的个数 。
     * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
                + merge(sum, L, M, R, lower, upper);
    }

    public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;
        // [windowL, windowR)
        // 先不merge
        // 遍历右边的数组，看看左边的数有几个在我们的范围内
        for (int i = M + 1; i <= R; i++) {
            // 要求整个区间的和都在 [low,up]
            // 那么我右边就是 [arr[i]-up , arr[i]-low]
            // 看看我的左边哪些值在这个区间内
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            // 先看看我的有边界可以扩展在哪里，因为左右分别有序
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            // 看看我的左边界在哪里。慢慢的排除哪些不满足需要的数
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            // 在整个窗口内，有效的数有几个
            ans += windowR - windowL;
        }
        // 左右两边合并
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        /**
         * [2147483647,-2147483648,-1,0]
         * -1
         * 0
         */
        System.out.println(countRangeSum(new int[]{2147483647, -2147483648, -1, 0}, -1, 0));
    }
}
