package duys.class_08_23;

/**
 * @ClassName MoneyQuertion
 * @Author Duys
 * @Description TODO
 * @Date 2021/8/23 17:07
 **/
public class MoneyQuestion {
    /**
     * int[] d，d[i]：i号怪兽的能力
     * int[] p，p[i]：i号怪兽要求的钱
     * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
     * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
     * 返回通过所有的怪兽，需要花的最小钱数。
     */
    // 先来递归版本
    public static long fun1(int[] d, int[] p) {
        return process(d, p, 0, 0);
    }

    // 递归，nengli-当前的能力值，index是来到了哪一个怪兽面前
    public static long process(int[] d, int[] p, int nengli, int index) {
        // 如果已经来到了最后的了-通关了，不需要花钱了
        if (index == d.length) {
            return 0;
        }
        // 当前的能力值小于了目前的index位置的怪兽能力，必须要花钱
        if (nengli < d[index]) {
            return p[index] + process(d, p, nengli + d[index], index + 1);
        } else {
            // k可以选择花钱，也可以不花钱，求二者最小的
            return Math.min(p[index] + process(d, p, nengli + d[index], index + 1),
                    process(d, p, nengli, index + 1));
        }
    }

    // 根据上面的暴力过程改一个动态规划
    public static long process2(int[] d, int[] p) {
        // dp[i][j] -表示啥：表示 从0-i获得j这么多能力，需要的钱数
        int sum = 0;
        // 获取一下总共的能力值
        for (int num : d) {
            sum += num;
        }
        long[][] dp = new long[d.length][sum + 1];
        // 那么我i=0的时候，不管能力是多少，钱都是0，不需要花钱
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = 0; j <= sum; j++) {
                // 如果当前的能力值 + 之前的能力值都已经大于了总体的，
                if (j + d[i] > sum) {
                    continue;
                }
                // 当前的能力值小于了目前的index位置的怪兽能力，必须要花钱
                if (j < d[i]) {
                    dp[i][j] = p[i] + dp[i + 1][j + d[i]];
                } else {
                    // k可以选择花钱，也可以不花钱，求二者最小的
                    dp[i][j] = Math.min(p[i] + dp[i + 1][j + d[i]],
                            dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }
}
