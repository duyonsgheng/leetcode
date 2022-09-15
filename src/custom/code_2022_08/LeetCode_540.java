package custom.code_2022_08;

/**
 * @ClassName LeetCode_540
 * @Author Duys
 * @Description
 * @Date 2022/8/23 15:22
 **/
// 540. 有序数组中的单一元素
public class LeetCode_540 {
    // 二分
    public int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            // 如果m是偶数，m和m+1 比较，相同就是右边找
            if (nums[m] == nums[m ^ 1]) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return nums[l];
    }

}
