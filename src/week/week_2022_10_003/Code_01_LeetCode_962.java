package week.week_2022_10_003;

/**
 * @ClassName Code_01_LeetCode_962
 * @Author Duys
 * @Description
 * @Date 2022/10/20 9:13
 **/
// 962. 最大宽度坡
public class Code_01_LeetCode_962 {

    // 思路1：使用排序，把nums的索引记录下来，然后按照nums中的数字大小按照从小到大排序，然后遍历排序后的索引数组，来计算最大的差值 复杂度 N*log N
    // 思路2：单调栈，遍历数组，保证栈中是 小压 大，然后从后往前遍历数组，遇到比栈顶元素大的
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int[] stack = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            // 如果栈顶元素是大于当前的，那么当前的可以入栈
            if (r == 0 || nums[stack[r - 1]] > nums[i]) {
                stack[r++] = i;
            }
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (r != 0 && nums[stack[r - 1]] <= nums[i]) {
                // 因为开始栈为空是0，r=0标识栈为空
                int pre = stack[--r];
                ans = Math.max(ans, i - pre);
            }
        }
        return ans;
    }
}
