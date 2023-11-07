package hope.binarySearch;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code07_CutOrPoison
 * @date 2023年11月03日 16:43
 */
// 刀砍毒杀怪兽问题
// 怪兽的初始血量是一个整数hp，给出每一回合刀砍和毒杀的数值cuts和poisons
// 第i回合如果用刀砍，怪兽在这回合会直接损失cuts[i]的血，不再有后续效果
// 第i回合如果用毒杀，怪兽在这回合不会损失血量，但是之后每回合都损失poisons[i]的血量
// 并且你选择的所有毒杀效果，在之后的回合都会叠加
// 两个数组cuts、poisons，长度都是n，代表你一共可以进行n回合
// 每一回合你只能选择刀砍或者毒杀中的一个动作
// 如果你在n个回合内没有直接杀死怪兽，意味着你已经无法有新的行动了
// 但是怪兽如果有中毒效果的话，那么怪兽依然会在血量耗尽的那回合死掉
// 返回至少多少回合，怪兽会死掉
// 数据范围 :
// 1 <= n <= 10^5
// 1 <= hp <= 10^9
// 1 <= cuts[i]、poisons[i] <= 10^9
// 本题来自真实大厂笔试，找不到测试链接，所以用对数器验证
public class Code07_CutOrPoison {
    public static int fast1(int[] cuts, int[] poisons, int hp) {
        int sum = Arrays.stream(poisons).sum();
        int[][][] dp = new int[cuts.length][hp + 1][sum + 1];
        return process(cuts, poisons, 0, hp, 0, dp);
    }

    // 当前来到的位置 i - cuts中的
    // 剩余血量rest
    // 之前使用毒的叠加效果
    public static int process(int[] cuts, int[] poisons, int i, int rest, int p, int[][][] dp) {
        rest -= p;
        if (rest <= 0) {
            return i + 1;
        }
        if (i == cuts.length) {
            if (p == 0) {
                return Integer.MAX_VALUE;
            } else {
                // 向下取整
                return cuts.length + 1 + (rest + p - 1) / p;
            }
        }
        if (dp[i][rest][p] != 0) {
            return dp[i][rest][p];
        }
        // 使用刀砍
        int p1 = rest <= cuts[i] ? (i + 1) : process(cuts, poisons, i + 1, rest - cuts[i], p, dp);
        // 本回合用毒
        int p2 = process(cuts, poisons, i + 1, rest, p + poisons[i], dp);
        int ans = Math.min(p1, p2);
        dp[i][rest][p] = ans;
        return ans;
    }

    // 二分
    public static int fast2(int[] cuts, int[] poisons, int hp) {
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = hp + 1, m; l <= r; ) {
            m = l + ((r - l) >> 1);
            if (ok(cuts, poisons, hp, m)) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans;
    }

    // 在limit回合呢
    public static boolean ok(int[] cuts, int[] poisons, int hp, int limit) {
        int n = Math.min(cuts.length, limit);
        for (int i = 0, j = 1; i < n; i++, j++) {
            // 当前回合用刀砍，或者后续使用毒的效果最强的
            hp -= Math.max((long) cuts[i], (long) (limit - j) * (long) poisons[i]);
            if (hp <= 0) {
                return true;
            }
        }
        return false;
    }
}
