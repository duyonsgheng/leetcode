package custom.code_2022_11;

/**
 * @ClassName LeerCode_1004
 * @Author Duys
 * @Description
 * @Date 2022/11/2 11:10
 **/
// 1004. 最大连续1的个数 III
public class LeetCode_1004 {

    // 窗口
    public int longestOnes(int[] nums, int k) {
        int l = 0;
        int r = 0;
        int n = nums.length;
        int zero = 0;
        int ans = 0;
        for (; r < n; r++) {
            if (nums[r] == 0) {
                zero++;
            }
            while (zero > k) {
                if (nums[l++] == 0) {
                    zero--;
                }
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
