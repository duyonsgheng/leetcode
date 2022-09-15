package custom.code_2022_04;

/**
 * @ClassName LeetCode_27
 * @Author Duys
 * @Description
 * @Date 2022/4/27 9:39
 **/
// https://leetcode-cn.com/problems/remove-element/
// 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
//不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
//元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
//链接：https://leetcode-cn.com/problems/remove-element
public class LeetCode_27 {

    // 两个指针
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] == val ? 1 : 0;
        }
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            if (nums[l] == val) {
                swap(nums, l, r--);
            } else {
                l++;
            }
        }
        return l;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 2, 3, 4, 3, 6};
        System.out.println(removeElement(arr, 3));
    }
}
