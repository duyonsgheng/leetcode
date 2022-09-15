package week.week_2022_03_04;

import java.util.Scanner;

/**
 * @ClassName Code_02_BuyGoodsHaveDiscount
 * @Author Duys
 * @Description
 * @Date 2022/3/25 9:28
 **/
public class Code_02_BuyGoodsHaveDiscount {

// 来自字节内部训练营
// 某公司游戏平台的夏季特惠开始了，你决定入手一些游戏。现在你一共有X元的预算。
// 该平台上所有的 n 个游戏均有折扣，标号为 i 的游戏的原价a_i元，现价只要b_i元
// 也就是说该游戏可以优惠 a_i - b_i，并且你购买该游戏能获得快乐值为 w_i
// 由于优惠的存在，你可能做出一些冲动消费导致最终买游戏的总费用超过预算，
// 只要满足 : 获得的总优惠金额不低于超过预算的总金额
// 那在心理上就不会觉得吃亏。
// 现在你希望在心理上不觉得吃亏的前提下，获得尽可能多的快乐值。
// 测试链接 : https://leetcode-cn.com/problems/tJau2o/
// 提交以下的code，将主类名字改成"Main"
// 可以直接通过

    /**
     * 思路：
     * 1.比如a商品 原价 10 折后 6   预算 6
     * 2.那么我们节省的钱 = 打折前 - 打折后
     * 3.那么我们的获得的好处 = 节省的钱 - 打折后的
     * 4.如果获得的好处 >=0 意思是可以推高我们的预算
     * 5.如果我们获得的好处 < 那么我们就需要和我们的预算抵扣，当预算为0的时候，获取到最大的欢乐值
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // 总共商品
            int n = sc.nextInt();
            // 预算
            int money = sc.nextInt();
            int[] costs = new int[n];
            long[] values = new long[n];
            int size = 0;
            long ans = 0;
            for (int i = 0; i < n; i++) {
                // 原价
                int price = sc.nextInt();
                // 折后价格
                int pos = sc.nextInt();
                // 欢乐值
                int happy = sc.nextInt();
                // 节省的钱 = 原价 - 折后价
                int save = price - pos;
                // 带来的好处
                int well = save - pos;

                int cost = -well;
                // 带来的好处 如果 >= 0 可以提高预算
                if (well >= 0) {
                    money += well;
                    ans += happy;
                } else {
                    costs[size] = cost;
                    values[size++] = happy;
                }
            }
            long[][] dp = new long[size + 1][money + 1];
            for (int i = 0; i <= size; i++) {
                for (int j = 0; j <= money; j++) {
                    dp[i][j] = -2;
                }
            }
            ans += process(costs, values, size, 0, money, dp);
            System.out.println(ans);
        }
        sc.close();
    }

    // 这就是一个背包问题了
    private static long process(int[] costs, long[] values, int size, int i, int money, long[][] dp) {
        if (money < 0) {
            return -1;
        }
        // 都已经来到越界位置了，说明之前做的决定有效，本次获得0欢乐值
        if (i == size) {
            return 0;
        }
        if (dp[i][money] != -2) {
            return dp[i][money];
        }
        // 不要当前的
        long p1 = process(costs, values, size, i + 1, money, dp);
        long p2 = -1;
        long text = process(costs, values, size, i + 1, money - costs[i], dp);
        if (text != -1) {
            p2 = text + values[i];
        }
        long ans = Math.max(p1, p2);
        dp[i][money] = ans;
        return ans;
    }

}
