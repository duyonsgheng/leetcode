package custom.code_2022_09;

/**
 * @ClassName LeetCode_775
 * @Author Duys
 * @Description
 * @Date 2022/9/19 16:58
 **/
// 775. 全局倒置与局部倒置
public class LeetCode_775 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 2, 1, 0};
    }

    public static boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        int min = n;
        for (int i = n - 1; i >= 2; i--) {
            min = Math.min(nums[i], min);
            if (nums[i - 2] > min) {
                return false;
            }
        }
        return true;
    }
}
