package hope.towPoint;

/**
 * @author Mr.Du
 * @ClassName Code07_FirstMissingPositive
 * @date 2023年10月31日 10:15
 */
// 缺失的第一个正数
// 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
// 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
// 测试链接 : https://leetcode.cn/problems/first-missing-positive/
public class Code07_FirstMissingPositive {
    // 用l和r来区分有效区和垃圾区
    // l左边的位置都是有效的，满足i位置上都是i+1
    // r包括r位置都是垃圾区
    public static int firstMissingPositive(int[] nums) {
        int l = 0, r = nums.length;
        while (l < r) {
            if (nums[l] == l + 1) { // 有效区往后扩展
                l++;
            }
            // 三种情况。
            // 1.如果当前数小于了 i，说明数不满足
            // 2.当前数都大于了有效区的范围，不满足
            // 3.有重复数字。不满足
            else if (nums[l] <= l || nums[l] > r || nums[nums[l] - 1] == nums[l]) {
                swap(nums, l, --r);
            } else {
                // 否则当前的数去到正确的位置上
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
