package duys_code.day_47;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @ClassName Code_04_465_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/optimal-account-balancing/
 * @Date 2021/10/20 16:36
 **/
public class Code_04_465_LeetCode {
    /**
     * 平账问题
     */
    /**
     * transactions ->
     * [1,2,5],意思是 1号人向2号人转帐5块
     * [1,0,5],意思是 1号人向0号人转帐5块
     * [0,2,5],意思是 0号人向2号人转帐5块
     */
    public static int minTransfers1(int[][] transactions) {
        if (transactions == null || transactions[0].length < 2) {
            return -1;
        }
        // 只有帐不是0的才进入到这个数组里
        int[] debt = debts(transactions);
        // 总共需要多少次
        int N = debt.length;
        // 这里开的时候sum一定=0
        return N - process1(debt, (1 << N) - 1, 0, N);
    }

    // status 意思是哪些位置有1就代表几号人在，sum是又status决定的
    // 返回值含义 : set这个集合中，最多能划分出多少个小集合累加和是0，返回累加和是0的小集合最多的数量
    public static int process1(int[] debt, int status, int sum, int N) {
        // 当前集合里只有一个人了，划分不了了
        if ((status & (status - 1)) == 1) {
            return 0;
        }
        int curValue = 0;
        int max = 0;
        // 尝试每一个位置的人最后加入
        for (int i = 0; i < N; i++) {
            curValue = debt[i];
            // 当前i号人确实在集合里，在递归之前，我们确定了debt的数组里面都没有为0的。这里如果拿掉了一个，意味着我的子过程的sum一定是不=0的。所以后面需要返回的时候+1
            if (((1 << i) & status) != 0) {
                // 当前 第 i 号人 使用了，那么把status对应的i的位置置为0，并且sum减去当前的，意思就是我当前考虑的这个i号帐最后来算
                max = Math.max(max, process1(debt, status ^ (1 << i), sum - curValue, N));
            }
        }
        // 最后sum确实平掉了，那么返回
        // todo 有点疑问。
        // 解惑
        // 不管怎么说 从递归开始整体累加和sum 一定=0 。 我中途拿的哪些位置都是确定有数的。不管是正数或者负数。那么意味着我拿掉了后。下一个递归的sum一定会不平。所以需要结束后，补一刀
        return sum == 0 ? max + 1 : max;
    }


    // 上面的尝试过程 + 记忆化搜索
    // 最优解
    public static int minTransfers2(int[][] transactions) {
        int[] debt = debts(transactions);
        int N = debt.length;
        int sum = 0;
        for (int num : debt) {
            sum += num;
        }
        int[] dp = new int[1 << N];
        // dp[i] = -1 表示没算过
        Arrays.fill(dp, -1);
        return N - process2(debt, (1 << N) - 1, sum, N, dp);
    }

    public static int process2(int[] debt, int set, int sum, int N, int[] dp) {
        if (dp[set] != -1) {
            return dp[set];
        }
        int ans = 0;
        if ((set & (set - 1)) != 0) {
            int value = 0;
            int max = 0;
            for (int i = 0; i < N; i++) {
                value = debt[i];
                if ((set & (1 << i)) != 0) {
                    max = Math.max(max, process2(debt, set ^ (1 << i), sum - value, N, dp));
                }
            }
            // todo 有点疑问。
            // 解惑
            // 不管怎么说 从递归开始整体累加和sum 一定=0 。 我中途拿的哪些位置都是确定有数的。不管是正数或者负数。那么意味着我拿掉了后。下一个递归的sum一定会不平。所以需要结束后，补一刀
            ans = sum == 0 ? max + 1 : max;
        }
        dp[set] = ans;
        return ans;
    }


    public static int[] debts(int[][] transactions) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] tr : transactions) {
            // 0位置给1位置的人 2位置的钱数
            // o位置的人钱减少
            map.put(tr[0], map.getOrDefault(tr[0], 0) - tr[2]);
            // 1位置的人钱增加
            map.put(tr[1], map.getOrDefault(tr[1], 0) + tr[2]);
        }
        int N = 0;
        for (int money : map.values()) {
            if (money != 0) {
                N++;
            }
        }
        int[] debt = new int[N];
        int index = 0;
        for (int money : map.values()) {
            if (money != 0) {
                debt[index++] = money;
            }
        }
        return debt;
    }
}
