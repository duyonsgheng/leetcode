package hope.towPoint;

/**
 * @author Mr.Du
 * @ClassName Code02_FindTheDuplicateNumber
 * @date 2023年10月31日 9:40
 */
// 寻找重复数
// 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n）
// 可知至少存在一个重复的整数。
// 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
// 测试链接 : https://leetcode.cn/problems/find-the-duplicate-number/
public class Code02_FindTheDuplicateNumber {

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
        fast = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
