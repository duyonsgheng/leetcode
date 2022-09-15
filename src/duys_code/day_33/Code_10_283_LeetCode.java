package duys_code.day_33;

/**
 * @ClassName Code_10_283_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/move-zeroes/
 * @Date 2021/12/6 15:47
 **/
public class Code_10_283_LeetCode {

    // 把所有的0移动到最后，保持原数组的相对位置不变
    public static void moveZeroes(int[] nums) {
        // 双指针
        int left = 0;// 有用区域
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, left++);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0,1};
        moveZeroes(arr);
        for (int a : arr) {
            System.out.print(" " + a);
        }
    }
}
