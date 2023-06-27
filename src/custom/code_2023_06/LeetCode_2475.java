package custom.code_2023_06;

/**
 * @ClassName LeetCode_2475
 * @Author Duys
 * @Description
 * @Date 2023/6/13 9:17
 **/
// 2475. 数组中不等三元组的数目
public class LeetCode_2475 {

    // 4,4,2,4,3
    public int unequalTriplets(int[] nums) {
        int n = nums.length;
        // 卡住开头，卡住结尾
        int ans = 0;
        for (int l = 0; l < n; l++) {
            for (int r = n - 1; r >= 0; r--) {
                for (int k = l + 1; k < r; k++) {
                    if (nums[l] != nums[r] && nums[k] != nums[r] && nums[l] != nums[k]) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
