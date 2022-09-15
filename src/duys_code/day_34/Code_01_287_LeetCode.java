package duys_code.day_34;

/**
 * @ClassName Code_01_287_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/find-the-duplicate-number/
 * @Date 2021/12/6 16:49
 **/
public class Code_01_287_LeetCode {
    // 有 n+1 个数  其中每个数再 1到n。那么数组有一个重复数，找出这个重复的数字，要求时间复杂度O(N) 空间复杂度O(1)

    // 单链表有环，返回入环节点 类似

    // 快慢指针
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 快指针回到0
        fast = 0;
        while (fast != slow) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
