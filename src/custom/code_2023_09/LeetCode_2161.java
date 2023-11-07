package custom.code_2023_09;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2161
 * @date 2023年09月05日
 */
// 2161. 根据给定数字划分数组
// https://leetcode.cn/problems/partition-array-according-to-given-pivot/?envType=daily-question&envId=2023-09-01
public class LeetCode_2161 {
    public static int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] ans = new int[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > pivot) {
                r++;
            }
        }
        int ri = n - r;
        for (int i = 0; i < n; i++) {
            if (nums[i] < pivot) {
                ans[l++] = nums[i];
            }
            if (nums[i] > pivot) {
                ans[ri++] = nums[i];
            }
        }
        while (l != n - r) {
            ans[l++] = pivot;
        }
        return ans;
    }

    public static void main(String[] args) {
        pivotArray(new int[]{9, 12, 5, 10, 14, 3, 10}, 10);
    }
}
