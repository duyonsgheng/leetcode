package week.week_2021_12_04;

/**
 * @ClassName Code_05_SplitApples
 * @Author Duys
 * @Description
 * @Date 2022/4/14 16:33
 **/
//有m个同样的苹果，认为苹果之间无差别
//有n个同样的盘子，认为盘子之间也无差别
//还有，比如5个苹果如果放进3个盘子，
//那么1、3、1和1、1、3和3、1、1的放置方法，也认为是一种方法
//如上的设定下，返回有多少种放置方法
public class Code_05_SplitApples {
    public static int ways1(int n, int m) {
        return process1(0, m, n);
    }

    // 我们规定pre : 上一个盘子分到的苹果数量，当前的盘子分到的数量不能小于pre
    // apples 还剩下几个苹果
    // plates 还有几个盘子
    public static int process1(int pre, int apples, int plates) {
        if (apples == 0) {
            return 1; // 有一种有效的方式
        }
        // 还有苹果，但是没有盘子了
        if (plates == 0) {
            return 0;
        }
        // 违反了我们的规定
        if (pre > apples) {
            return 0;
        }
        int ways = 0;
        for (int cur = pre; cur < apples; cur++) {
            ways += process1(cur, apples - cur, plates - 1);
        }
        return ways;
    }

    // 新奇思路：

    // 新的尝试，最优解
    // 苹果有apples个，盘子有plates个
    // 返回有几种摆法
    // 如果苹果数为0，有1种摆法：什么也不摆
    // 如果苹果数不为0，但是盘子数为0，有0种摆法（做不到）
    // 如果苹果数不为0，盘子数也不为0，进行如下的情况讨论：
    // 假设苹果数为apples，盘子数为plates
    // 可能性 1) apples < plates
    // 这种情况下，一定有多余的盘子，这些盘子完全没用，所以砍掉
    // 后续是f(apples, apples)
    // 可能性 2) apples >= plates
    // 在可能性2)下，讨论摆法，有如下两种选择
    // 选择a) 不是所有的盘子都使用
    // 选择b) 就是所有的盘子都使用
    // 对于选择a)，既然不是所有盘子都使用，那么后续就是f(apples, plates - 1)
    // 意思是：既然不是所有盘子都使用，那盘子减少一个，然后继续讨论吧！
    // 对于选择b)，既然就是所有的盘子都使用，那么先把所有盘子都摆上1个苹果。
    // 剩余苹果数 = apples - plates
    // 然后继续讨论，剩下的这些苹果，怎么摆进plates个盘子里，
    // 所以后续是f(apples - plates, plates)

    // 这个方式就是把三个可变参数变成了2个了，可以做记忆化搜索了
    public static long ways2(int apples, int plates) {
        if (apples == 0) {
            return 1;
        }
        if (plates == 0) {
            return 0;
        }
        // 盘子多了。
        if (plates > apples) {
            return ways2(apples, apples);
        } else {
            // 我每次都少用一个盘子，看看有多少种
            // 我先把每一个盘子都摆一个苹果剩下苹果apples - plates ,继续去摆
            return ways2(apples, plates - 1) + ways2(apples - plates, plates);
        }
    }
}
