package week.week_2022_09_04;

import java.util.Arrays;

/**
 * @ClassName Code_01_RobotDeliverGoods
 * @Author Duys
 * @Description
 * @Date 2022/9/29 9:33
 **/
// 智能机器人要坐专用电梯把货物送到指定地点
// 整栋楼只有一部电梯，并且由于容量限制智能机器人只能放下一件货物
// 给定K个货物，每个货物都有所在楼层(from)和目的楼层(to)
// 假设电梯速度恒定为1，相邻两个楼层之间的距离为1
// 例如电梯从10层去往19层的时间为9
// 机器人装卸货物的时间极快不计入
// 电梯初始地点为第1层，机器人初始地点也是第1层
// 并且在运送完所有货物之后，机器人和电梯都要回到1层
// 返回智能机器人用电梯将每个物品都送去目标楼层的最快时间
// 注意：如果智能机器人选了一件物品，则必须把这个物品送完，不能中途丢下
// 输入描述：
// 正数k，表示货物数量，1 <= k <= 16
// from、to数组，长度都是k，1 <= from[i]、to[i] <= 10000
// from[i]表示i号货物所在的楼层
// to[i]表示i号货物要去往的楼层
// 返回最快的时间
// 状态压缩专题
public class Code_01_RobotDeliverGoods {
    // 用一个状态 status 的二进制来表示哪些位置选了，哪些位置没选，并且记录，最后一次是在哪一个楼层

    public static int minCost(int[] from, int[] to, int k) {
        int[][] dp = new int[1 << k][k];
        for (int i = 0; i < 1 << k; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(from, to, k, 0, 0, dp);
    }

    public static int process(int[] from, int[] to, int k, int status, int last, int[][] dp) {
        if (dp[status][last] != -1) {
            return dp[status][last];
        }
        int ans = Integer.MAX_VALUE;
        // 所有位置都选择了，其他位置全部都是1
        if (status == (1 << k) - 1) {
            ans = to[last] - 1; // 我上最后一次所在的楼层，回到1层
        } else {
            // 这么多货物，选择
            for (int i = 0; i < k; i++) {
                // 当前位置没有选，才可以选择
                if ((status & (1 << i)) == 0) {
                    // 电梯初始在1层，如果之前选择过了，我先要从上一次停在的楼层，去往本次货物所在的楼层
                    int start = Math.abs(from[i] - (status == 0 ? 1 : to[last]));
                    // 送货
                    int work = Math.abs(from[i] - to[i]);
                    // 本次送完了，后续调用递归
                    int next = process(from, to, k, status | (1 << i), i, dp);
                    ans = Math.min(ans, start + work + next);
                }
            }
        }
        dp[status][last] = ans;
        return ans;
    }
}
