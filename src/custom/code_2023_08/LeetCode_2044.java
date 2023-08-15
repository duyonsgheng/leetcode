package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2044
 * @date 2023年08月03日
 */
// 2044. 统计按位或能得到最大值的子集数目
// https://leetcode.cn/problems/count-number-of-maximum-bitwise-or-subsets/
public class LeetCode_2044 {
    // n的大小是16，可以使用状态压缩
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length, mask = 1 << n;
        int max = 0, ans = 0;
        // 枚举每一种状态
        for (int state = 0; state < mask; state++) {
            int cur = 0;
            // 当前状态下选择了哪些？
            for (int i = 0; i < n; i++) {
                // 当前位置的数选择了
                if (((state >> i) & 1) == 1) {
                    cur |= nums[i];
                }
            }
            if (cur > max) {
                max = cur;
                ans = 1;
            } else if (cur == max)
                ans++;
        }
        return ans;
    }
}
