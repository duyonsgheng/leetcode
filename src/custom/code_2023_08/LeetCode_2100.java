package custom.code_2023_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2100
 * @date 2023年08月16日
 */
// 2100. 适合打劫银行的日子
// https://leetcode.cn/problems/find-good-days-to-rob-the-bank/
public class LeetCode_2100 {
    // 5,3,3,3,5,6,2  time = 2  [2,3]
    // 前 - 非递增
    // 后 - 非递减
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        List<Integer> ans = new ArrayList<>();
        int[] left = new int[n], right = new int[n];
        for (int i = 1; i < n; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
            if (security[n - i - 1] <= security[n - i]) {
                right[n - i - 1] = right[n - i] + 1;
            }
        }
        for (int i = time; i < n - time; i++) {
            if (left[i] >= time && right[i] >= time) {
                ans.add(i);
            }
        }
        return ans;
    }
}
