package custom.code_2023_06;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2178
 * @date 2023年07月06日
 */
// 2178. 拆分成最多数目的正偶数之和
// https://leetcode.cn/problems/maximum-split-of-positive-even-integers/
public class LeetCode_2178 {
    public static List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if (finalSum % 2 > 0) {
            return ans;
        }
        for (long i = 2; i <= finalSum; i += 2) {
            ans.add(i);
            finalSum -= i;
        }
        // 修正最后一个数
        ans.set(ans.size() - 1, ans.get(ans.size() - 1) + finalSum);
        return ans;
    }

    public static void main(String[] args) {
        long a = 36;
        maximumEvenSplit(a);
    }
}
