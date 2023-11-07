package hope.binarySearch;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code05_MaximumRunningTimeOfNComputers
 * @date 2023年11月03日 15:53
 */
// 同时运行N台电脑的最长时间
// 你有 n 台电脑。给你整数 n 和一个下标从 0 开始的整数数组 batteries
// 其中第 i 个电池可以让一台电脑 运行 batteries[i] 分钟
// 你想使用这些电池让 全部 n 台电脑 同时 运行。
// 一开始，你可以给每台电脑连接 至多一个电池
// 然后在任意整数时刻，你都可以将一台电脑与它的电池断开连接，并连接另一个电池，你可以进行这个操作 任意次
// 新连接的电池可以是一个全新的电池，也可以是别的电脑用过的电池
// 断开连接和连接新的电池不会花费任何时间。
// 注意，你不能给电池充电。
// 请你返回你可以让 n 台电脑同时运行的 最长 分钟数。
// 测试链接 : https://leetcode.cn/problems/maximum-running-time-of-n-computers/
public class Code05_MaximumRunningTimeOfNComputers {
    // 纯二分
    public long maxRunTime1(int n, int[] batteries) {
        long sum = 0;
        for (int i : batteries) {
            sum += i;
        }
        long ans = 0;
        for (long l = 0, r = sum, m; l <= r; ) {
            m = l + ((r - l) >> 1);
            // 如果可以，那么继续往右，看看最长多少
            if (ok(batteries, n, m)) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        return ans;
    }

    // 剪枝
    public long maxRunTime2(int n, int[] batteries) {
        long sum = 0;
        int max = 0;
        for (int i : batteries) {
            max = Math.max(max, i);
            sum += i;
        }
        if (sum > (long) max * n) {
            return sum / n;
        }
        long ans = 0;
        for (long l = 0, r = max, m; l <= r; ) {
            m = l + ((r - l) >> 1);
            // 如果可以，那么继续往右，看看最长多少
            if (ok(batteries, n, m)) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        return ans;
    }

    // 让num台电脑同时运行 time分钟
    public boolean ok(int[] arr, int num, long time) {
        long sum = 0;
        for (int i : arr) {
            if (i > time) { // 如果当前电池的剩余量
                num--;
            } else {
                sum += i;
            }
            // 如果碎电池的总计能满足
            if (sum >= (long) num * time) {
                return true;
            }
        }
        return false;
    }
}
