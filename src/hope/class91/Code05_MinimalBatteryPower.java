package hope.class91;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code05_MinimalBatteryPower
 * @date 2024年09月03日 下午 04:58
 */
// 执行所有任务的最少初始电量
// 每一个任务有两个参数，需要耗费的电量、至少多少电量才能开始这个任务
// 返回手机至少需要多少的初始电量，才能执行完所有的任务
// 来自真实大厂笔试，没有在线测试，对数器验证
public class Code05_MinimalBatteryPower {
    // 希望最后的电量是0
    // 随意就倒着推
    // 倒着推，需要一个指标，那么这个指标就可以去尝试了，打表看看
    public static int atLeast(int[][] jobs) {
        // 耗电量越小，阈值越大的，可以先推，因为这样可以让更大的阈值，更小的耗电量，覆盖到更多的范围
        // 数学上这种情况作差来拉出一个指标
        // jobs[i][0] : 耗费
        // jobs[i][1] : 至少电量
        Arrays.sort(jobs, (a, b) -> (b[0] - b[1]) - (a[0] - a[1]));
        int ans = 0;
        for (int[] job : jobs) {
            ans = Math.max(ans + job[0], job[1]);
        }
        return ans;
    }
}
