package duys_code.day_33;

/**
 * @ClassName Code_05_238_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/product-of-array-except-self/
 * @Date 2021/12/6 13:58
 **/
public class Code_05_238_LeetCode {

    public int[] productExceptSelf(int[] nums) {
        // 准备一个从右往左的累乘积
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = nums[0];
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i];
        }
        int right = 1;
        for (int i = n - 1; i >= 1; i--) {
            ans[i] = ans[i - 1] * right;
            right *= nums[i];
        }
        ans[0] = right;
        return ans;
    }
}
