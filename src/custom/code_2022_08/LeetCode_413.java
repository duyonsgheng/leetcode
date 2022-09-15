package custom.code_2022_08;

/**
 * @ClassName LeetCode_413
 * @Author Duys
 * @Description
 * @Date 2022/8/9 13:18
 **/
//
public class LeetCode_413 {

    // 子数组，那么我们考虑使用dp，
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int ans = 0;
        int pre = nums[1];
        int prePre = nums[0];
        int len = 2;
        for (int i = 2; i < n; i++) {
            // 能构成了
            if (nums[i] - pre == pre - prePre) {
                ans += len - 1;
                len++;
            } else {
                len = 2;
            }
            prePre = pre;
            pre = nums[i];
        }
        return ans;
    }
}
