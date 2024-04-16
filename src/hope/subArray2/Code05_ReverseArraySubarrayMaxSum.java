package hope.subArray2;

/**
 * @author Mr.Du
 * @ClassName Code05_ReverseArraySubarrayMaxSum
 * @date 2023年12月28日 9:46
 */
// 可以翻转1次的情况下子数组最大累加和
// 给定一个数组nums，
// 现在允许你随意选择数组连续一段进行翻转，也就是子数组逆序的调整
// 比如翻转[1,2,3,4,5,6]的[2~4]范围，得到的是[1,2,5,4,3,6]
// 返回必须随意翻转1次之后，子数组的最大累加和
// 对数器验证
public class Code05_ReverseArraySubarrayMaxSum {
    public static int maxSumReverse(int[] nums) {
        int n = nums.length;
        // start[i] 所有必须以i开头的子数组中，最大的累加和是多少
        int[] start = new int[n];
        start[n - 1] = nums[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            start[i] = Math.max(nums[i], start[i + 1] + nums[i]);
        }
        int ans = start[0];
        int end = nums[0]; //子数组必须以i-1结尾，其中最大的累加和
        int maxEnd = nums[0];
        // 子数组必须以0结尾，其中最大的累加和
        // 子数组必须以1结尾，其中最大的累加和
        // .....
        // 子数组必须以i-1结尾，其中最大累加和
        // 所有情况中取最大的，就是maxEnd
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, maxEnd + start[i]);
            end = Math.max(nums[i], end + nums[i]);
            maxEnd = Math.max(maxEnd, end);
        }
        ans = Math.max(ans, maxEnd);
        return ans;
    }
}
