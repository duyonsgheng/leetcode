package hope.subArray1;

/**
 * @author Mr.Du
 * @ClassName Code04_HouseRobberII
 * @date 2023年12月25日 18:22
 */
// 环形数组中不能选相邻元素的最大累加和
// 给定一个数组nums，长度为n
// nums是一个环形数组，下标0和下标n-1是连在一起的
// 可以随意选择数字，但是不能选择相邻的数字
// 返回能得到的最大累加和
// 测试链接 : https://leetcode.cn/problems/house-robber-ii/
public class Code04_HouseRobberII {
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        // 从1位置开始，和 0位置开始谁大选谁
        return Math.max(best(nums, 1, n - 1), nums[0] + best(nums, 2, n - 2));
    }

    public static int best(int[] arr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return arr[l];
        }
        if (l + 1 == r) {
            return Math.max(arr[l], arr[r]);
        }
        int prepre = arr[l];
        int pre = Math.max(arr[l], arr[l + 1]);
        for (int i = l + 2, cur; i <= r; i++) {
            cur = Math.max(pre, arr[i] + Math.max(0, prepre));
            prepre = pre;
            pre = cur;
        }
        return pre;
    }
}
