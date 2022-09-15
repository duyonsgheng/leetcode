package duys.class_08_23;

import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName SnacksWays
 * @Author Duys
 * @Description
 * @Date 2021/8/24 14:24
 **/
public class SnacksWays {
    /**
     * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]，背包容量为w。
     * 牛牛想知道在总体积不超过背包容量的情况下,
     * 一共有多少种零食放法，体积为0也算一种放法
     * 1 <= n <= 30, 1 <= w <= 2 * 10^9
     * v[i] (0 <= v[i] <= 10^9）
     */
    public static int ways1(int[] arr, int w) {
        if (arr.length <= 0 || w < 0) {
            return -1;
        }
        return process1(arr, 0, w);
    }

    // rest -背包剩余容量
    // arr-零食重量
    // index-当前来到第几号零食
    public static int process1(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;// 没有任何方案了
        }
        // 已经没有零食了，背包还没满，说明有一种
        if (index == arr.length) {
            return 1;
        }
        //要不要当前位置的零食
        int p1 = process1(arr, index + 1, rest);
        int p2 = process1(arr, index + 1, rest - arr[index]);
        if (p2 == -1) {
            p2 = 0;
        }
        return p1 + p2;
    }

    // 经典的动态规划。
    // 但是，如果我们的arr长度和w都非常大，那么我们填这张dp表，会超时
    public static int ways2(int[] arr, int w) {
        if (arr.length <= 0 || w < 0) {
            return -1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][w + 1];
        for (int j = 0; j < N; j++) {
            dp[N][j] = 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= w; j++) {
                int p1 = dp[i + 1][j];
                int p2 = 0;
                if (j - arr[i] > 0) {
                    p2 = dp[i + 1][j - arr[i]];
                }
                dp[i][j] = p1 + p2;
            }
        }
        return dp[0][w];
    }

    //本文件是Code02_SnacksWays问题的牛客题目解答
    //但是用的分治的方法
    //这是牛客的测试链接：
    //https://www.nowcoder.com/questionTerminal/d94bb2fa461d42bcb4c0f2b94f5d4281
    // 当w和arr里的项都非常大的时候，使用的是分治
    public static long ways3(int[] arr, int w) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        // 什么也不选，算一种
        if (arr.length == 1) {
            return arr[0] <= w ? 2 : 1;
        }
        // 然后来左右分治
        int mid = arr.length - 1 >> 1;
        TreeMap<Long, Long> leftMap = new TreeMap<>();
        long ans = process3(arr, 0, mid, 0, w, leftMap);
        TreeMap<Long, Long> rightMap = new TreeMap<>();
        ans += process3(arr, mid + 1, arr.length - 1, 0, w, rightMap);
        // 这里已经把左右两边分开的情况都已经算到了ans里面了
        // 下面只需要算，左边和右边结合的情况
        // 比如之前的rightMap 记录的是 前缀和 (1 - 2) ,(3 -5), (7 - 9) 的方法数
        // 那么现在这个preRightMap中记录的是 (1 -2),( 3 - 7),(7 - 16)
        TreeMap<Long, Long> preRightSum = new TreeMap<>();
        long preSum = 0;
        for (Map.Entry<Long, Long> entry : rightMap.entrySet()) {
            preSum += entry.getValue();
            preRightSum.put(entry.getKey(), preSum);
        }
        for (Map.Entry<Long, Long> entry : leftMap.entrySet()) {
            Long lSum = entry.getKey();
            Long lWays = entry.getValue();
            Long rightKey = preRightSum.floorKey(w - lSum);
            if (rightKey != null) {
                Long rightWays = preRightSum.get(rightKey);
                ans += lWays * rightWays;
            }
        }
        // 这里单独处理，什么都不选
        return ans + 1;
    }

    public static long process3(int[] arr, int index, int end, long sum, int w, TreeMap<Long, Long> map) {
        if (sum > w) {// 说明已经装满了
            return 0;
        }
        // 已经没有零食了
        if (index > end) {
            if (sum != 0) {// 虽然已经没有零食了，但是此时我之前做过的选择，不为0，而且背包还没满，那么也要给我算，是有效的，
                map.put(sum, map.get(sum) == null ? 1 : map.get(sum) + 1);
                return 1;
            } else {
                // 什么也不选的情况，最后算
                return 0;
            }
        } else {
            // 两种情况讨论，要不要当前位置
            long ans = process3(arr, index + 1, end, sum, w, map);
            ans += process3(arr, index + 1, end, sum + arr[index], w, map);
            return ans;
        }
    }
}
