package duys.class_08_26;

/**
 * @ClassName DP_OPT_01
 * @Author Duys
 * @Description 四边形不等式
 * @Date 2021/8/26 17:12
 **/
public class DP_OPT_01 {
    /**
     * 题目一：
     * 给定一个非负数组arr，长度为N，
     * 那么有N-1种方案可以把arr切成左右两部分
     * 每一种方案都有，min{左部分累加和，右部分累加和}
     * 求这么多方案中，min{左部分累加和，右部分累加和}的最大值是多少？
     * 整个过程要求时间复杂度O(N)
     */
    /**
     * 解法：前缀和
     * 以0位置结束的子数组
     */
    public static int getMaxChildSumMin(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int sumAll = 0;
        // 前缀和
        for (int i : arr) {
            sumAll += i;
        }
        int ans = 0;
        int leftSum = 0;
        for (int i = 0; i < N; i++) {
            leftSum += arr[i];
            int rightSum = sumAll - leftSum;
            ans = Math.max(ans, Math.min(leftSum, rightSum));
        }
        return ans;
    }
}
