package custom.code_2023_02;

/**
 * @ClassName LeetCode_1493
 * @Author Duys
 * @Description
 * @Date 2023/2/2 15:47
 **/
// 1493. 删掉一个元素以后全为 1 的最长子数组
public class LeetCode_1493 {
    // 每个0位置的左右收集一下连续1的长度
    public static int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        // 0,1,1,1,0,1,1,0,1
        for (int i = 1, p = nums[0] == 1 ? 1 : 0; i < n; i++) {
            left[i] = p;
            if (nums[i] == 0) {
                p = 0;
            } else {
                p++;
            }
        }
        for (int i = n - 2, p = nums[n - 1] == 1 ? 1 : 0; i >= 0; i--) {
            right[i] = p;
            if (nums[i] == 0) {
                p = 0;
            } else {
                p++;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(left[i] + right[i], ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{1, 1, 0, 1}));
        System.out.println(longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1}));
        System.out.println(longestSubarray(new int[]{1, 1, 1}));
    }
}
