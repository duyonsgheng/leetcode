package duys_code.day_49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_02_446_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence/
 * @Date 2021/10/27 13:15
 **/
public class Code_02_446_LeetCode {
    /**
     * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
     */
    /**
     * 每一个位置跟前面的位置进行比较。看看差值是每一个位置的时候，等差数列个数推到哪里
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int N = nums.length;
        int ans = 0;
        List<Map<Integer, Integer>> maps = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            maps.add(new HashMap<>());
            for (int j = i - 1; j >= 0; j--) {
                long diff = nums[i] - nums[j];
                if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) {
                    continue;
                }
                int dif = (int) diff;
                int curDiffCount = maps.get(j).getOrDefault(dif, 0);
                ans += curDiffCount;
                // 重新设置值，留待下一次使用
                maps.get(i).put(dif, maps.get(i).getOrDefault(dif, 0) + curDiffCount + 1);
            }
        }
        return ans;
    }
}
