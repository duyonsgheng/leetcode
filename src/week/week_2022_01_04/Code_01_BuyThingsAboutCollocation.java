package week.week_2022_01_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Code_01_BuyThingsAboutCollocation
 * @Author Duys
 * @Description
 * @Date 2022/3/30 13:49
 **/

// things是一个N*3的二维数组，商品有N件，商品编号从1~N
// 比如things[3] = [300, 2, 6]
// 代表第3号商品：价格300，重要度2，它是6号商品的附属商品
// 再比如things[6] = [500, 3, 0]
// 代表第6号商品：价格500，重要度3，它不是任何附属，它是主商品
// 每件商品的收益是价格*重要度，花费就是价格
// 如果一个商品是附属品，那么只有它附属的主商品购买了，它才能被购买
// 任何一个附属商品，只会有1个主商品
// 任何一个主商品的附属商品数量，不会超过2件
// 主商品和附属商品的层级最多有2层
// 给定二维数组things、钱数money，返回整体花费不超过money的情况下，最大的收益总和
// 测试链接 : https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4
// 请把如下的代码的主类名改为"Main", 可以直接通过
public class Code_01_BuyThingsAboutCollocation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int money = sc.nextInt();
            int size = sc.nextInt();
            List<List<int[]>> things = new ArrayList<>();
            things.add(new ArrayList<>()); // 商品编号从1开始
            for (int i = 0; i < size; i++) {
                List<int[]> cur = new ArrayList<>();
                cur.add(new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt()});
                things.add(cur);
            }
            int n = clean(things, size);
            int ans = maxScore(things, n, money);
            System.out.println(ans);
        }
    }

    // 这就是一个背包问题
    public static int maxScore(List<List<int[]>> things, int n, int money) {
        int[][] dp = new int[n][money + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= money; j++) {
                dp[i][j] = -2;
            }
        }
        return process(things, n, 0, money, dp);
    }

    public static int process(List<List<int[]>> things, int n, int index, int rest, int[][] dp) {
        if (rest < 0) {
            return -1;
        }
        // 决定都已经做完了
        if (index == n) {
            return 0;
        }
        if (dp[index][rest] != -2) {
            return dp[index][rest];
        }
        List<int[]> cur = things.get(index);
        // 因为一个主商品最多两个附属商品
        int[] master = cur.get(0);
        int[] slave1 = cur.size() > 1 ? cur.get(1) : null;
        int[] slave2 = cur.size() > 2 ? cur.get(2) : null;
        // 可能性分析
        // 1. 不要当前的主商品，那么从商品也不能要
        int p1 = process(things, n, index + 1, rest, dp);
        // 2.要当前主商品，不要从商品
        int p2 = process(things, n, index + 1, rest - master[0], dp);
        if (p2 != -1) { // 如果后续是有效的，那么算上我的
            p2 += master[0] * master[1];
        }
        // 3.要了当前主商品，并且选择了其中1这个附属商品
        int p3 = slave1 != null ? process(things, n, index + 1, rest - master[0] - slave1[0], dp) : -1;
        if (p3 != -1) {
            p3 += master[0] * master[1] + slave1[0] * slave1[1];
        }
        // 4.要了当前主商品，并且选择了其中2这个附属商品
        int p4 = slave2 != null ? process(things, n, index + 1, rest - master[0] - slave2[0], dp) : -1;
        if (p4 != -1) {
            p4 += master[0] * master[1] + slave2[0] * slave2[1];
        }
        // 5.要了当前主商品，并且其中2个附属商品
        int p5 = slave2 != null && slave1 != null ? process(things, n, index + 1, rest - master[0] - slave1[0] - slave2[0], dp) : -1;
        if (p5 != -1) {
            p5 += master[0] * master[1] + slave2[0] * slave2[1] + slave2[0] * slave2[1];
        }
        int ans = Math.max(Math.max(p1, p2), Math.max(p3, Math.max(p4, p5)));
        dp[index][rest] = ans;
        return ans;
    }

    // 洗参数 ，然后把商品关系挂靠上
    public static int clean(List<List<int[]>> things, int size) {
        for (int i = 1; i <= size; i++) {
            int[] cur = things.get(i).get(0);
            if (cur[2] != 0) { // 附属商品
                things.get(i).clear();
                things.get(cur[2]).add(cur);
            }
        }
        int n = 0;
        for (int i = 0; i <= size; i++) {
            if (!things.get(i).isEmpty()) {
                things.set(n++, things.get(i));
            }
        }
        return n;
    }
}
