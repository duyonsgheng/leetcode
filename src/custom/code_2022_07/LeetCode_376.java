package custom.code_2022_07;

/**
 * @ClassName LeetCode_376
 * @Author Duys
 * @Description
 * @Date 2022/7/22 9:17
 **/
// 376. 摆动序列
// 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
//链接：https://leetcode.cn/problems/wiggle-subsequence
public class LeetCode_376 {

    public static int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums == null ? 0 : nums.length;
        }
        return process(nums, 0, 0, 0);
    }

    public static int process(int[] arr, int i, int pre, int state) {
        if (i == arr.length) {
            return 1;
        }

        int p1 = process(arr, i + 1, pre, state);
        int p2 = 0;
        // 表示之前的是负数
        if (state == 0 && arr[i] > pre) {
            p2 = 1 + process(arr, i + 1, arr[i], 1);
        }
        int p3 = 0;
        if (state == 1 && arr[i] < pre) {
            p3 = 1 + process(arr, i + 1, arr[i], 0);
        }
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int wiggleMaxLength1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums == null ? 0 : nums.length;
        }
        if (nums.length == 2) {
            return nums[0] == nums[1] ? 1 : 2;
        }
        // 子序列必须以i位置结束
        int n = nums.length;
        int[] dp = new int[n + 1];
        int state = -1;
        dp[0] = 1;
        // 跳过开头相等的区域
        int index = 1; // 下一个不等的开头
        while (index < n) {
            if (nums[index] == nums[index - 1]) {
                dp[index] = dp[index - 1];
            } else {
                break;
            }
            index++;
        }
        if (index == n) {
            return dp[n - 1];
        }
        if (index == n - 1) {
            return dp[n - 1] + 1;
        }
        // 计算state开头的状态
        if (nums[index] > nums[index - 1]) {
            state = 1; // 正数
        } else {
            state = 0; // 负数
        }
        dp[index] = dp[index - 1] + 1;
        index++;
        for (; index < n; index++) {
            // 之前依然是相等的 state = -1
            if (nums[index] == nums[index - 1]) {
                dp[index] = dp[index - 1];
            } else {
                // 之前是负数，现在就需要是正数了
                if ((state == 0 && nums[index] > nums[index - 1]) || (state == 1 && nums[index] < nums[index - 1])) {
                    dp[index] = dp[index - 1] + 1;
                    state ^= 1;
                } else {
                    dp[index] = dp[index - 1];
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        /*int[] arr = {1, 7, 4, 9, 2, 5};
        System.out.println(wiggleMaxLength(arr));
        System.out.println(wiggleMaxLength1(arr));
        int[] arr1 = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println(wiggleMaxLength(arr1));
        System.out.println(wiggleMaxLength1(arr1));
        int[] arr2 = {3, 3, 3, 2, 5};
        System.out.println(wiggleMaxLength(arr2));
        System.out.println(wiggleMaxLength1(arr2));*/

        int[] arr3 = {1, 2};
        System.out.println(wiggleMaxLength(arr3));
        System.out.println(wiggleMaxLength1(arr3));
    }
}
