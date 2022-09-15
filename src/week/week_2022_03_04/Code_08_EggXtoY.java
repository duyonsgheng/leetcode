package week.week_2022_03_04;

/**
 * @ClassName Code_08_EggXtoY
 * @Author Duys
 * @Description
 * @Date 2022/3/28 13:39
 **/
// 大妈一开始手上有x个鸡蛋，她想让手上的鸡蛋数量变成y
// 操作1 : 从仓库里拿出1个鸡蛋到手上，x变成x+1个
// 操作2 : 如果手上的鸡蛋数量是3的整数倍，大妈可以直接把三分之二的鸡蛋放回仓库，手里留下三分之一
// 返回从x到y的最小操作次数
// 1 <= x,y <= 10^18
// 练一下，用平凡解做限制
public class Code_08_EggXtoY {
    public static void main(String[] args) {
        System.out.println(minTimes1(2313123,19220));
    }

    // 平凡的解答
    public static int minTimes1(int x, int y) {
        if (x <= y) {// 如果手里的少了，直接去拿
            return y - x;
        }
        // 用贪心找一个限制
        int limit = minTimes2(x, y);
        System.out.println(limit);
        int[][] dp = new int[x + limit + 1][limit + 1];
        return process(x, y, 0, limit, dp);
    }

    // cur - 当前鸡蛋数
    // aim - 目标鸡蛋数
    // pre - 之前操作了多少次
    // limit - 限制是多少，也就是最多操作到哪里
    public static int process(int cur, int aim, int pre, int limit, int[][] dp) {
        if (pre > limit) {
            return Integer.MAX_VALUE;
        }
        if (dp[cur][pre] != 0) {
            return dp[cur][pre];
        }
        int ans = 0;
        if (cur == aim) {
            ans = pre;
        } else {
            // 去拿一个
            int p1 = process(cur + 1, aim, pre + 1, limit, dp);
            int p2 = Integer.MAX_VALUE;
            if (cur % 3 == 0) {
                p2 = process(cur / 3, aim, pre + 1, limit, dp);
            }
            ans = Math.min(p1, p2);
        }
        dp[cur][pre] = ans;
        return ans;
    }

    // 完全的贪心
    public static int minTimes2(int x, int y) {
        if (x <= y) {
            return y - x;
        }
        // 如果手里的多了。我要放2/3到仓库去
        int mod = y % 3;
        // 看看是否需要从仓库补充一个或者两个，变成3的整数倍，然后继续往下走
        int need = mod == 0 ? 0 : (mod == 1 ? 2 : 1);
        return need + 1 + minTimes2((x + 2) / 3, y);
    }
}
