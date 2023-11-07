package hope.towPoint;

/**
 * @author Mr.Du
 * @ClassName Code01_SortArrayByParityII
 * @date 2023年10月31日 9:30
 */
// 按奇偶排序数组II
// 给定一个非负整数数组 nums。nums 中一半整数是奇数 ，一半整数是偶数
// 对数组进行排序，以便当 nums[i] 为奇数时，i也是奇数
// 当 nums[i] 为偶数时， i 也是 偶数
// 你可以返回 任何满足上述条件的数组作为答案
// 测试链接 : https://leetcode.cn/problems/sort-array-by-parity-ii/
public class Code01_SortArrayByParityII {
    public static int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        for (int odd = 1, even = 0; odd < n && even < n; ) {
            if ((nums[n - 1] & 1) == 1) { // 奇数
                swap(nums, odd, n - 1);
                odd += 2;
            } else { // 偶数
                swap(nums, even, n - 1);
                even += 2;
            }
        }
        return nums;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
