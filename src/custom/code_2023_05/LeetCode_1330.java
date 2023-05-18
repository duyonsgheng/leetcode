package custom.code_2023_05;

/**
 * @ClassName LeetCode_1330
 * @Author Duys
 * @Description
 * @Date 2023/5/12 9:57
 **/
// 1330. 翻转子数组得到最大的数组值
public class LeetCode_1330 {
    public int maxValueAfterReverse(int[] nums) {
        int base = 0, d = 0, n = nums.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int a = nums[i - 1], b = nums[i];
            int cur = Math.abs(a - b);
            base += cur;
            max = Math.max(max, Math.min(a, b));
            min = Math.min(min, Math.max(a, b));
            d = Math.max(d, Math.max(Math.abs(nums[0] - b) - cur, Math.abs(nums[n - 1] - a) - cur));
        }
        return base + Math.max(d, 2 * (max - min));
    }
}
