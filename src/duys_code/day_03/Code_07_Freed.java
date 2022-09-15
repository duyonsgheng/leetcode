package duys_code.day_03;

import java.util.ArrayList;

/**
 * @ClassName Code_07_Freed
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/freedom-trail/
 * @Date 2021/9/22 13:22
 **/
public class Code_07_Freed {
    /**
     * 自由之路：比如之前的老式电话，转盘拨号的那种，
     * 比如从指针最开始再 0，现在有一个 135456 号码 1到0需要 转1 + 1次确认 ，指针就到了1
     * 3到1 需要2 +1次确认 。。。
     * // 现在我们先根据源，看看所有的字符再什么位置，然后枚举从所有的位置出发，来搞定key。需要最少的代价
     */

    // 如果需要进行空间上的优化，那么就把HashMap<Character, ArrayList<Integer>> 这个结构换成 Arraylist<Integer> [] indexs = new ArrayList<>[26];
    public static int findRotateSteps(String ring, String key) {
        char[] source = ring.toCharArray();
        int N = source.length;
        //HashMap<Character, ArrayList<Integer>> indexMap = new HashMap<>();
        ArrayList<Integer>[] indexs = new ArrayList[26];
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> curI = indexs[source[i] - 'a'];
            if (curI == null) {
                indexs[source[i] - 'a'] = new ArrayList<>();
            }
           /* if (!indexMap.containsKey(source[i])) {
                indexMap.put(source[i], new ArrayList<>());
            }
            indexMap.get(source[i]).add(i);*/
            indexs[source[i] - 'a'].add(i);
        }
        char[] target = key.toCharArray();
        int M = target.length;
        int[][] dp = new int[N][M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = -1;
            }
        }
        return process(0, 0, target, /*indexMap*/indexs, N, dp);
    }

    public static int process(int pre, int index, char[] target, /*HashMap<Character, ArrayList<Integer>> indexMap*/ ArrayList<Integer>[] indexs, int N, int[][] dp) {
        if (dp[pre][index] != -1) {
            return dp[pre][index];
        }

        int ans = Integer.MAX_VALUE;
        // 如果来到了需要搞定的字符串的最后位置了，需要0的代价
        if (index == target.length) {
            ans = 0;
        } else {
            char cur = target[index];
            ArrayList<Integer> targetIndexs = indexs[cur - 'a'];//indexMap.get(cur);
            for (int targetIndex : targetIndexs) {
                // 这是从pre位置到targetIndex位置拨号的最小代价，还要+1 因为需要确认。确认的代价就是1
                int cost = dail(pre, targetIndex, N) + 1;
                // 这是后续，以当前要去额某一个位置，求一个后续
                int next = process(targetIndex, index + 1, target, /*indexMap*/indexs, N, dp);
                ans = Math.min(ans, cost + next);
            }
        }
        dp[pre][index] = ans;
        return ans;
    }

    // 因为是环形的，所以需要计算
    public static int dail(int cur, int to, int size) {
        return Math.min(Math.abs(cur - to), Math.min(cur, to) + size - Math.max(cur, to));
    }
}
