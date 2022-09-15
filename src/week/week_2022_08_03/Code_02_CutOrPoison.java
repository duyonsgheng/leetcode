package week.week_2022_08_03;

/**
 * @ClassName Code_02_CutOrPoison
 * @Author Duys
 * @Description
 * @Date 2022/8/18 14:19
 **/
// 给定怪兽的血量为hp
// 第i回合如果用刀砍，怪兽在这回合会直接掉血，没有后续效果
// 第i回合如果用毒，怪兽在这回合不会掉血，
// 但是之后每回合都会掉血，并且所有中毒的后续效果会叠加
// 给定的两个数组cuts、poisons，两个数组等长，长度都是n
// 表示你在n回合内的行动，
// 每一回合的刀砍的效果由cuts[i]表示
// 每一回合的中毒的效果由poisons[i]表示
// 如果你在n个回合内没有直接杀死怪兽，意味着你已经无法有新的行动了
// 但是怪兽如果有中毒效果的话，那么怪兽依然会在hp耗尽的那回合死掉
// 返回你最快能在多少回合内将怪兽杀死
// 数据范围 :
// 1 <= n <= 10的5次方
// 1 <= hp <= 10的9次方
// 1 <= cuts[i]、poisons[i] <= 10的9次方
public class Code_02_CutOrPoison {
    // 首先看数据量
    // 使用dp的话，没戏，绝对超时
    // 那么根据数据量猜解法：首先想到的是二分，怪兽死亡的回合绝对不会超过 hp+1 这多回合，
    // 那么就依次二分找到一个回合，看看能不能死亡。
    public static int fast(int[] cuts, int[] poisons, int hp) {
        int l = 1;
        int r = hp + 1;
        int m = 0;
        int ans = hp + 1;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            // 如果能杀死
            if (canKill(cuts, poisons, hp, m)) {
                r = m - 1;
                ans = m;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static boolean canKill(int[] cuts, int[] poisons, long hp, int limit) {
        int n = Math.min(cuts.length, limit);
        for (int i = 0, j = 1; i < n; i++, j++) {
            // 当前回合选择直接砍 还是选择毒，如果回合确定了
            // 直接砍就直接掉血，如果选择毒，当前不掉血，下回合开始一直掉血
            hp -= Math.max((long) cuts[i], (long) (limit - j) * (long) poisons[i]);
            if (hp <= 0) {
                return true;
            }
        }
        return false;
    }
}
