package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2134
 * @date 2023年08月30日
 */
// 2134. 最少交换次数来组合所有的 1 II
// https://leetcode.cn/problems/minimum-swaps-to-group-all-1s-together-ii/
public class LeetCode_2134 {
    public int minSwaps(int[] nums) {
        int cnt = 0;
        for (int i : nums) {
            cnt += i == 1 ? 1 : 0;
        }
        // 滑动窗口
        int n = nums.length, ans = n;
        for (int i = 0, zcnt = 0; i < n * 2; i++) {
            if (i >= cnt) { // 窗口收缩
                ans = Math.min(ans, zcnt);
                if (nums[(i - cnt) % n] == 0) {
                    zcnt--;
                }
            }
            if (nums[i % n] == 0) {
                zcnt++;
            }
        }
        return ans;
    }
}
