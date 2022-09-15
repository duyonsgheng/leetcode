package week.week_2022_04_02;

/**
 * @ClassName Code_02_MaxMoneyMostMin
 * @Author Duys
 * @Description
 * @Date 2022/4/13 14:18
 **/


// 来自快手
// 某公司年会上，大家要玩一食发奖金游戏，一共有n个员工，
// 每个员工都有建设积分和捣乱积分
// 他们需要排成一队，在队伍最前面的一定是老板，老板也有建设积分和捣乱积分
// 排好队后，所有员工都会获得各自的奖金，
// 该员工奖金 = 排在他前面所有人的建设积分乘积 / 该员工自己的捣乱积分，向下取整
// 为了公平(放屁)，老板希望 : 让获得奖金最高的员工，所获得的奖金尽可能少
// 所以想请你帮他重新排一下队伍，返回奖金最高的员工获得的、尽可能少的奖金数额
// 1 <= n <= 1000, 1<= 积分 <= 10000;
public class Code_02_MaxMoneyMostMin {

    // a 老板的积分
    // b 老板的捣乱
    public static long mostMin1(int a, int b, int[] value, int[] trou) {
        return process1(a, value, trou, 0);
    }

    public static long process1(int boss, int[] value, int[] trou, int index) {
        if (index == value.length) {
            long all = boss;
            long ans = 0;
            for (int i = 0; i < value.length; i++) {
                ans = Math.max(all, all / trou[i]);
                all *= value[i];
            }
            return ans;
        } else {
            long ans = Long.MAX_VALUE;
            for (int i = index; i < value.length; i++) {
                swap(value, trou, index, i);
                ans = Math.min(ans, process1(boss, value, trou, index + 1));
                swap(value, trou, index, i);
            }
            return ans;
        }
    }

    public static void swap(int[] v, int[] t, int i, int j) {
        int tmp = v[i];
        v[i] = v[j];
        v[j] = tmp;

        tmp = t[i];
        t[i] = t[j];
        t[j] = tmp;
    }
}
