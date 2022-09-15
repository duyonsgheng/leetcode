package week.week_2022_02_03;

import java.util.HashMap;

/**
 * @ClassName Code_02_LeetCode_1553
 * @Author Duys
 * @Description
 * @Date 2022/3/29 13:40
 **/
public class Code_02_LeetCode_1553 {
    // 厨房里总共有 n个橘子，你决定每一天选择如下方式之一吃这些橘子：
    //吃掉一个橘子。
    //如果剩余橘子数 n能被 2 整除，那么你可以吃掉 n/2 个橘子。
    //如果剩余橘子数n能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
    //每天你只能从以上 3 种方案中选择一种方案。
    //请你返回吃掉所有 n个橘子的最少天数。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/minimum-number-of-days-to-eat-n-oranges

    public static int minDays(int n) {
        if (n <= 1) {
            return n;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int ans = 0;
        // 1.把橘子吃成2的整数倍，然后直接吃掉一半，剩下的去跑递归
        int p1 = n % 2 + 1 + minDays(n / 2);
        // 2.把橘子吃成3的整数倍，然后吃掉 2/3 ，剩下的去跑递归
        int p2 = n % 3 + 1 + minDays(n / 3);
        ans = Math.min(p1, p2);
        dp.put(n, ans);
        return ans;
    }

    public static HashMap<Integer, Integer> dp = new HashMap<>();
}
