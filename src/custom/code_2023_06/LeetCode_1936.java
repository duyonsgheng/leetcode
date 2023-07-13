package custom.code_2023_06;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1936
 * @date 2023年07月05日
 */
// 1936. 新增的最少台阶数
// https://leetcode.cn/problems/add-minimum-number-of-rungs/
public class LeetCode_1936 {
    public int addRungs(int[] rungs, int dist) {
        int ans = 0;
        int pre = 0;
        // 已经有序了
        for (int cur : rungs) {
            ans += (cur - pre - 1) / dist;
            pre = cur;
        }
        return ans;
    }
}
