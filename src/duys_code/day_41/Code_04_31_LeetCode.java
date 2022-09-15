package duys_code.day_41;

/**
 * @ClassName Code_04_31_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/next-permutation/
 * @Date 2021/12/29 17:36
 **/
public class Code_04_31_LeetCode {
    // 给定一个数组，然后返回当前数组的下一个排列是啥？根据字典序排序

    // 从右往左遍历，第一次遇到降序的位置
    // 然后找到从右往左刚刚比降序位置数大的数，交换
    // 然后从n-1 到降序位置逆序排列
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int firstLess = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstLess = i;
                break;
            }
        }
        if (firstLess == -1) {
            reverse(nums, 0, n - 1);
        } else {
            int rightMore = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (nums[i] > nums[firstLess]) {
                    rightMore = i;
                    break;
                }
            }
            swap(nums, firstLess, rightMore);
            reverse(nums, firstLess + 1, n - 1);
        }
    }

    // 逆序
    public static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            swap(arr, l++, r--);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
