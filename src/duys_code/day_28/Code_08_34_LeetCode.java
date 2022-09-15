package duys_code.day_28;

/**
 * @ClassName Code_08_34_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @Date 2021/11/23 15:38
 **/
public class Code_08_34_LeetCode {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return new int[]{-1, -1};
        }
       /* int su = -1;
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                su = i;
            }
        }
        if (su == -1) {
            return new int[]{-1, -1};
        }*/
        int l = find(nums, target) + 1;
        if (l == nums.length || nums[l] != target) {
            return new int[]{-1, -1};
        }
        // 找到了target的左边界，右边界就是 target+1
        return new int[]{l, find(nums, target + 1)};
    }

    // 在数组中找到 < target的最右侧区域
    public static int find(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        int m = 0;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (arr[m] < target) {
                l = m + 1;
                ans = m;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }
}
