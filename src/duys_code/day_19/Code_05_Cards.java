package duys_code.day_19;

import java.util.LinkedList;

/**
 * @ClassName Code_05_Cards
 * @Author Duys
 * @Description
 * @Date 2021/11/5 10:34
 **/
public class Code_05_Cards {
    /*
     * 一张扑克有3个属性，每种属性有3种值（A、B、C）
     * 比如"AAA"，第一个属性值A，第二个属性值A，第三个属性值A
     * 比如"BCA"，第一个属性值B，第二个属性值C，第三个属性值A
     * 给定一个字符串类型的数组cards[]，每一个字符串代表一张扑克
     * 从中挑选三张扑克，一个属性达标的条件是：这个属性在三张扑克中全一样，或全不一样
     * 挑选的三张扑克达标的要求是：每种属性都满足上面的条件
     * 比如："ABC"、"CBC"、"BBC"
     * 第一张第一个属性为"A"、第二张第一个属性为"C"、第三张第一个属性为"B"，全不一样
     * 第一张第二个属性为"B"、第二张第二个属性为"B"、第三张第二个属性为"B"，全一样
     * 第一张第三个属性为"C"、第二张第三个属性为"C"、第三张第三个属性为"C"，全一样
     * 每种属性都满足在三张扑克中全一样，或全不一样，所以这三张扑克达标
     * 返回在cards[]中任意挑选三张扑克，达标的方法数
     *
     * */
    public static int ways2(String[] cards) {
        // 先算出买一种牌有多少张，总共27中组合的牌面
        int[] counts = new int[27];
        for (String s : cards) {
            char[] str = s.toCharArray();
            // 我们认为A是3的0次方，就是一个3进制
            counts[(str[0] - 'A') * 9 + (str[1] - 'A') * 3 + (str[2] - 'A') * 1]++;
        }
        int ways = 0;
        // 看看相同牌面的有多少张
        for (int i = 0; i < 27; i++) {
            int n = counts[i];
            if (n > 2) {
                // 从n中选出3张的任意组合
                ways += n == 3 ? 1 : n * (n - 1) * (n - 2) / 6;
            }
        }
        LinkedList<Integer> path = new LinkedList<>();
        for (int i = 0; i < 27; i++) {
            if (counts[i] != 0) {
                path.addLast(i);
                ways += process2(counts, i, path);
                path.pollLast();
            }
        }
        return ways;
    }

    // path 之前的牌拿了一些。轮到继续做选择，path中存的一定是牌面越来越大，就是之前3进制算的结果越来越大
    // pre之前拿到的牌面是哪一个，在counts数组中的什么位置
    public static int process2(int[] counts, int pre, LinkedList<Integer> path) {
        if (path.size() == 3) {
            return getWays2(counts, path);
        }
        int ways = 0;
        // 从之前加入牌面最大的位置开始往后选择
        for (int i = pre + 1; pre < 27; pre++) {
            if (counts[i] != 0) {
                path.addLast(i);
                ways += process2(counts, i, path);
                path.pollLast();
            }
        }
        return ways;
    }

    public static int getWays2(int[] counts, LinkedList<Integer> path) {
        int v1 = path.get(0);
        int v2 = path.get(1);
        int v3 = path.get(2);
        // 看看是否满足需求
        for (int i = 9; i > 0; i /= 3) {
            int cur1 = v1 / i;
            int cur2 = v2 / i;
            int cur3 = v3 / i;
            v1 %= i;
            v2 %= i;
            v3 %= i;
            // 每一种牌面相等或者不等
            if ((cur1 == cur2 && cur2 == cur3) || (cur1 != cur2 && cur1 != cur3 && cur2 != cur3)) {
                continue;
            }
            // 有任何不满足的。直接返回0
            return 0;
        }
        v1 = path.get(0);
        v2 = path.get(1);
        v3 = path.get(2);
        return counts[v1] * counts[v2] * counts[v3];
    }
}
