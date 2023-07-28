package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2012
 * @date 2023年07月26日
 */
// 2012. 数组美丽值求和
// https://leetcode.cn/problems/sum-of-beauty-in-the-array/
public class LeetCode_2012 {
    // 每一个位置 左边的最大，右边的最小都抓出来
    // 2，对于所有 0 <= j < i 且 i < k <= nums.length - 1 ，满足 nums[j] < nums[i] < nums[k]
    // 1，如果满足 nums[i - 1] < nums[i] < nums[i + 1] ，且不满足前面的条件
    // 0，如果上述条件全部不满足
    // 0....i-1 i...k-1 k...n-1
    public static int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        left[1] = nums[0];
        int[] right = new int[n];
        right[n - 2] = nums[n - 1];
        // 2,4,6,4
        for (int i = 2, max = nums[0]; i < n; i++) {
            max = Math.max(max, nums[i - 1]);
            left[i] = max;
        }
        for (int i = n - 3, min = nums[n - 1]; i >= 0; i--) {
            min = Math.min(min, nums[i + 1]);
            right[i] = min;
        }
        int ans = 0;
        for (int i = 1; i <= n - 2; i++) {
            if (nums[i] > left[i] && nums[i] < right[i]) {
                ans += 2;
            } else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //System.out.println(sumOfBeauties(new int[]{2, 4, 6, 4}));
        //System.out.println(sumOfBeauties(new int[]{1, 2, 3}));
        System.out.println(sumOfBeauties(new int[]{6, 8, 3, 7, 8, 9, 4, 8}));
        //  6,8,3,7,8,9,4,8
        //  0 6 8 8 8 8 9 9
    }
}
