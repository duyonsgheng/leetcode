package week.week_2022_11_01;

import java.util.TreeSet;

/**
 * @ClassName Code_01_DessertPriceClosedTarget
 * @Author Duys
 * @Description
 * @Date 2022/11/3 9:19
 **/

// 来自华为
// 做甜点需要购买配料，目前共有n种基料和m种配料可供选购
// 制作甜点需要遵循以下几条规则：
// 必须选择1种基料；可以添加0种、1种或多种配料，每种类型的配料最多添加2份
// 给定长度为n的数组base, base[i]表示第i种基料的价格
// 给定长度为m的数组topping, topping[j]表示第j种配料的价格
// 给定一个正数target，表示你做的甜点最终的价格要尽量接近这个数值
// 返回最接近这个数值的价格是多少
// 如果有多个方案，都最接近target，返回价格最小的那个答案
// 1 <= n,m <= 10
// 1 <= base[i], topping[j] <= 10 ^ 4
// 1 <= target <= 10 ^ 4
public class Code_01_DessertPriceClosedTarget {

    // 思路：
    // 1.先按照规则生成所有的配料价格
    // 2.然后从每一种主料开始，按需去配对配料
    public static int closedTarget(int[] base, int[] topping, int target) {
        TreeSet<Integer> set = new TreeSet<>();
        build(topping, 0, 0, set);
        int ans = Integer.MAX_VALUE;
        for (int num : base) {
            int cur = num;
            // 如果当前主料的价格都大于了 target，还选鸡毛啊
            if (num < target) {
                int rest = target - num;
                // 小于等于rest的最大
                Integer floor = set.floor(rest);
                // 大于等rest的最小
                Integer ceiling = set.ceiling(rest);
                if (floor == null || ceiling == null) {
                    cur += floor == null ? ceiling : floor;
                } else {
                    cur += rest - floor <= ceiling - rest ? floor : ceiling;
                }
            }
            // 返回价格尽可能小的哪个方案
            // 得出了最接近，且最小的。与答案想比，看看能不能更小
            if (Math.abs(cur - target) < Math.abs(ans - target) || (Math.abs(cur - target) == Math.abs(ans - target) && cur < ans)) {
                ans = cur;
            }
        }
        return ans;
    }

    public static void build(int[] topping, int index, int preSum, TreeSet<Integer> set) {
        if (index == topping.length) {
            set.add(preSum);
        } else {
            // 不选当前配料
            build(topping, index + 1, preSum, set);
            // 当前配料选1份
            build(topping, index + 1, preSum + topping[index], set);
            // 当前配料选2份
            build(topping, index + 1, preSum + (1 << topping[index]), set);
        }
    }
}
