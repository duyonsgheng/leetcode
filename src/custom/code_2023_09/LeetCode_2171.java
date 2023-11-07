package custom.code_2023_09;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2171
 * @date 2023年09月06日
 */
// 2171. 拿出最少数目的魔法豆
// https://leetcode.cn/problems/removing-minimum-number-of-magic-beans/
public class LeetCode_2171 {
    // 选择一个x，把小于等于x的都清空，把大于x的变成x
    public long minimumRemoval(int[] beans) {
        int n = beans.length;
        Arrays.sort(beans);
        long sum = 0;
        for (int i : beans) {
            sum += i;
        }
        long ans = sum;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, sum - (long) beans[i] * (n - i));
        }
        return ans;
    }
}
