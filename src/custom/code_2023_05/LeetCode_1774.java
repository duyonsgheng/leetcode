package custom.code_2023_05;

import java.util.TreeSet;

/**
 * @ClassName LeetCode_1774
 * @Author Duys
 * @Description
 * @Date 2023/5/22 9:29
 **/
// 1774. 最接近目标价格的甜点成本
public class LeetCode_1774 {
    // 1.按照规则生成所有的配料价格
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        TreeSet<Integer> sum = new TreeSet<>();
        // 生成好所有的配料关系
        build(toppingCosts, 0, 0, sum);
        int ans = Integer.MAX_VALUE;
        for (int base : baseCosts) {
            int cur = base;
            if (cur < target) {
                int diff = target - cur;
                // 小于等于diff最大的。也就是以diff作为天花板
                Integer floor = sum.floor(diff);
                // 大于等于diff最小的，也就是以diff作为地板
                Integer ceiling = sum.ceiling(diff);
                if (floor == null || ceiling == null) {
                    cur += floor == null ? ceiling : floor;
                } else {
                    // 看看谁更接近
                    cur += diff - floor <= ceiling - diff ? floor : ceiling;
                }
            }
            if (Math.abs(cur - target) < Math.abs(ans - target) || (Math.abs(cur - target) == Math.abs(ans - target) && cur < ans)) {
                ans = cur;
            }
        }
        return ans;
    }

    public void build(int[] top, int index, int preSum, TreeSet<Integer> set) {
        // 都没得选了，之前的决定加入进来
        if (index == top.length) {
            set.add(preSum);
        } else {
            // 三种选择，
            // 不选当前配料
            build(top, index + 1, preSum, set);
            // 选一种当前配料
            build(top, index + 1, preSum + top[index], set);
            // 选两种当前配料
            build(top, index + 1, preSum + (top[index] << 1), set);
        }
    }
}
