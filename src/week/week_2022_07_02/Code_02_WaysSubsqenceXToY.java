package week.week_2022_07_02;

/**
 * @ClassName Code_02_WaysSubsqenceXToY
 * @Author Duys
 * @Description
 * @Date 2022/7/14 10:02
 **/
// 来自SnowFlake
// 给定一个正数n，比如6
// 表示数轴上有 0,1,2,3,4,5,6
// <0 或者 >6 的位置认为无法到达
// 给定两个数字x和y，0<= x，y <= n
// 表示小人一开始在x的位置，它的目的地是y的位置，比如x = 1, y = 3
// 给定一个字符串s，比如 : rrlrlr
// 任何一个s的子序列，对应着一种运动轨迹，r表示向右，l表示向左
// 比如一开始小人在1位置，"rlr"是s的一个子序列
// 那么运动轨迹是：1 -> 2 -> 1 -> 2
// 求，s中有多少个字面值不同的子序列，能让小人从x走到y，
// 走的过程中完全不走出0到n的区域。
// 比如，s = "rrlrlr", n = 6, x = 1, y = 3
// 有如下5个字面值不同的子序列
// rr : 1 -> 2 -> 3
// rrlr : 1 -> 2 -> 3 -> 2 -> 3
// rrrl : 1 -> 2 -> 3 -> 4 -> 3
// rlrr : 1 -> 2 -> 1 -> 2 -> 3
// rrlrlr : 1 -> 2 -> 3 -> 2 -> 3 -> 2 -> 3
// 注意：一定要是字面值不同的子序列！相同字面值的子序列算一种
// 比如s中，有很多个rr的子序列，但是算一个
// 数据规模 : s串长度 <= 1000, x,y,n <= 2500
public class Code_02_WaysSubsqenceXToY {

    // 不要求去重问题
    public static int ways1(int n, int x, int y, String s) {
        return process1(s.toCharArray(), n, y, 0, x);
    }

    // index - 指令来到的位置
    // cur -当前来到数轴上的位置
    public static int process1(char[] str, int n, int aim, int index, int cur) {
        if (index == str.length) {
            return cur == aim ? 1 : 0;
        }

        // 不要当前指令
        int p1 = process1(str, n, aim, index + 1, cur);
        // 要当前指令
        int p2 = 0;
        if (str[index] == 'l') {
            // 往左
            if (cur >= 1) {
                p2 = process1(str, n, aim, index + 1, cur - 1);
            }
        } else {
            // 往右
            if (cur <= n - 1) {
                p2 = process1(str, n, aim, index + 1, cur + 1);
            }
        }
        // 选择和不选则当前的是两种不通的情况
        return p1 + p2;
    }

    // 题意要求去重
    // 最优解
    // 思路来自：大厂刷题班，17节，Code05，DistinctSubseqValue问题
    // 如果字符串长度为m，位置数量n
    // 时间复杂度O(m * n)
    public static int ways2(String s, int n, int x, int y) {
        // 当小人来到 i位置不同字面值的子序列数量
        int[] all = new int[n + 1];
        // 当来到i位置是l结尾的不同字面值的子序列数量
        int[] l = new int[n + 1];
        // 当来到i位置是r结尾的不同字面值的子序列数量
        int[] r = new int[n + 1];
        //
        int[] add = new int[n + 1];
        // 一开始再x位置，所以x啥也不选，就是1，因为空集
        all[x] = 1;
        for (char c : s.toCharArray()) {
            if (c == 'r') {
                // 往右走
                for (int i = 0; i < n; i++) {
                    add[i + 1] += all[i];
                }
                for (int i = 0; i <= n; i++) {
                    // 更新为纯新增
                    add[i] -= r[i];
                    all[i] += add[i];
                    r[i] += add[i];
                    add[i] = 0;
                }
            } else {
                // 往左走
                for (int i = 1; i <= n; i++) {
                    add[i - 1] += all[i];
                }
                for (int i = 0; i <= n; i++) {
                    // 更新为纯新增
                    add[i] -= l[i];
                    all[i] += add[i];
                    l[i] += add[i];
                    add[i] = 0;
                }
            }
        }
        return all[y];
    }
}
