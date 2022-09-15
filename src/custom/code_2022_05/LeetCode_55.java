package custom.code_2022_05;

/**
 * @ClassName LeetCode_55
 * @Author Duys
 * @Description
 * @Date 2022/5/6 11:21
 **/
//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
//数组中的每个元素代表你在该位置可以跳跃的最大长度。
//判断你是否能够到达最后一个下标。
//55. 跳跃游戏
public class LeetCode_55 {

    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return false;
        }
        return process(nums, 0);
    }

    public static boolean canJump2(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return false;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i] + i);
            if (nums[i] == 0 && max == i && i != nums.length - 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean process(int[] nums, int index) {
        if (index == nums.length - 1) {
            return true;
        }
        int cur = nums[index];
        if (cur <= 0) {
            return false;
        }
        for (int i = 1; i <= cur; i++) {
            if (process(nums, index + i)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 1, 4};
    }
}
