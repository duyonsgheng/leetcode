package duys_code.day_28;

/**
 * @ClassName Code_07_26_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 * @Date 2021/11/23 15:32
 **/
public class Code_07_26_LeetCode {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int cur = nums[0];
        int left = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != cur) {
                nums[left++] = nums[i];
                cur = nums[i];
            }
        }
        return left;
    }


}
