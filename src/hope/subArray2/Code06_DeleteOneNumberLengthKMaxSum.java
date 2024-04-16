package hope.subArray2;

/**
 * @author Mr.Du
 * @ClassName Code06_DeleteOneNumberLengthKMaxSum
 * @date 2023年12月28日 10:12
 */
// 删掉1个数字后长度为k的子数组最大累加和
// 给定一个数组nums，求必须删除一个数字后的新数组中
// 长度为k的子数组最大累加和，删除哪个数字随意
// 对数器验证
public class Code06_DeleteOneNumberLengthKMaxSum {
    public static int maxSum(int[] nums, int k) {
        int n = nums.length;
        if (n <= k) {
            return 0;
        }
        // 单调队列，窗口内最小值更新结构
        int[] window = new int[n];
        int l = 0, r = 0;
        long sum = 0; // 窗口内累加和
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            while (l < r && nums[window[r - 1]] >= nums[i]) {
                r--;
            }
            window[r++] = i;
            sum += nums[i];
            if (i >= k) {
                ans = Math.max(ans, (int) (sum - nums[window[l]]));
                if (window[l] == i - k) { // 边界问题处理
                    l++;
                }
                sum -= nums[i - k];
            }
        }
        return ans;
    }
}
