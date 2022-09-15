package custom.code_2022_06;

/**
 * @ClassName LeetCode_581
 * @Author Duys
 * @Description
 * @Date 2022/6/13 17:52
 **/
// 581. 最短无序连续子数组
public class LeetCode_581 {

    // 两种做法：1.排序，然后对比 2.遍历的同时满足 a b c三个区域内数据的特性
    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = nums.length;
        int left = -1;
        int right = -1;
        for (int i = 0; i < n; i++) {
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }

            if (min < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                min = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 3, 4, 5, 7};
        System.out.println(findUnsortedSubarray(arr));
    }
}
