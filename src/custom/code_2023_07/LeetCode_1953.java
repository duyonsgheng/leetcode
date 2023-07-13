package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1953
 * @date 2023年07月13日
 */
// 1953. 你可以工作的最大周数
// https://leetcode.cn/problems/maximum-number-of-weeks-for-which-you-can-work/
public class LeetCode_1953 {
    public long numberOfWeeks(int[] milestones) {
        long ans = 0;
        int max = 0;
        for (int i : milestones) {
            ans += i;
            max = Math.max(max, i);
        }
        return 2 * max > ans + 1 ? 2 * (ans - max) + 1 : ans;
    }
}
