package custom.code_2022_11;

import java.util.Stack;

/**
 * @ClassName LeetCode_1014
 * @Author Duys
 * @Description
 * @Date 2022/11/2 16:30
 **/
// 1014. 最佳观光组合
public class LeetCode_1014 {
    // 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j
    // values = [8,1,5,2,6]
    public int maxScoreSightseeingPair(int[] values) {
        // v[i] +i - v[j] -j
        int ans = 0;
        int max = values[0] + 0;
        for (int i = 1; i < values.length; i++) {
            ans = Math.max(ans, max + values[i] - i);
            max = Math.max(values[i] + i, max);
        }
        return ans;
    }
}
