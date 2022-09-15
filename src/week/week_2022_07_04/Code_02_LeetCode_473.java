package week.week_2022_07_04;

/**
 * @ClassName Code_02_LeetCode_473
 * @Author Duys
 * @Description
 * @Date 2022/7/28 9:28
 **/
// 473. 火柴拼正方形
// 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i个火柴棒的长度。你要用 所有的火柴棍拼成一个正方形。
//你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
//如果你能使这个正方形，则返回 true ，否则返回 false 。
//链接：https://leetcode.cn/problems/matchsticks-to-square
public class Code_02_LeetCode_473 {

    // 一个很典型的状态压缩

    public static boolean makesquare1(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }
        long sum = 0;
        for (int a : matchsticks) {
            sum += a;
        }
        if (sum % 4 != 0) {
            return false;
        }
        long len = sum >> 2;
        return process1(matchsticks, 0, 0, len, 4);
    }

    // status 哪些边使用了，都在status里
    // 当前算的这条边，长度达到curLen
    // 每条边的长度
    // 还剩几条边没拼凑
    public static boolean process1(int[] arr, int status, long curLen, long len, int edges) {
        if (edges == 0) {
            // 当边没有要计算的了，那么看看是不是所有的边都用上了
            return status == (1 << arr.length) - 1 ? true : false;
        }
        boolean ans = false;
        // 选择边
        for (int i = 0; i < arr.length; i++) {
            if (((1 << i) & status) != 0) {
                continue;
            }
            if (curLen + arr[i] > len) {
                continue;
            }
            if (curLen + arr[i] == len) {
                // 搞定了一条边了，看后续
                ans = process1(arr, status | (1 << i), 0, len, edges - 1);
            } else {
                ans = process1(arr, status | (1 << i), curLen + arr[i], len, edges);
            }
            if (ans) {
                return ans;
            }
        }
        return ans;
    }

    public static boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4) {
            return false;
        }
        long sum = 0;
        for (int a : matchsticks) {
            sum += a;
        }
        if (sum % 4 != 0) {
            return false;
        }
        long len = sum >> 2;
        // 状态总共多少位 (1<< n )-1
        int[] dp = new int[1 << matchsticks.length];
        return process2(matchsticks, 0, 0, len, 4, dp);
    }

    public static boolean process2(int[] arr, int status, long curLen, long len, int edges, int[] dp) {
        if (edges == 0) {
            // 当边没有要计算的了，那么看看是不是所有的边都用上了
            return status == (1 << arr.length) - 1 ? true : false;
        }
        if (dp[status] != 0) {
            return dp[status] == 1;
        }
        boolean ans = false;
        // 选择边
        for (int i = 0; i < arr.length; i++) {
            if (((1 << i) & status) != 0) {
                continue;
            }
            if (curLen + arr[i] > len) {
                continue;
            }
            if (curLen + arr[i] == len) {
                // 搞定了一条边了，看后续
                ans = process2(arr, status | (1 << i), 0, len, edges - 1, dp);
            } else {
                ans = process2(arr, status | (1 << i), curLen + arr[i], len, edges, dp);
            }
            if (ans) {
                break;
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
