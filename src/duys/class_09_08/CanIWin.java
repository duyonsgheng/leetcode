package duys.class_09_08;

/**
 * @ClassName CanIWin
 * @Author Duys
 * @Description 力扣得原题 https://leetcode-cn.com/problems/can-i-win/
 * @Date 2021/9/8 9:51
 **/
public class CanIWin {
    // 状态压缩：可变参数得类型突破了整型，那么动态规划会出问题。所以需要进行状态压缩

    /**
     * 题意：在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到或超过 100 的玩家，即为胜者。
     * 如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
     * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
     * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
     * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
     */
    // 最早让对手面对0或者负数得话，那么当前选手就赢了
    //1~choose 表示拥有得数
    // total 表示每一个流程开始得时候还剩余多少
    // 返回先手会不会赢
    public static boolean canIWin0(int choose, int total) {
        if (total == 0) {
            // 先手去拿，发现没有剩余了，自己赢了
            return true;
        }
        // 比如给一个10 剩余给一个100完，无论怎么拿都不能搞定
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        // 表示一个缓存
        int[] arr = new int[choose];
        for (int i = 0; i < choose; i++) {
            arr[i] = i + 1;
        }
        // arr[i] = -1 表示i位置数已经被使用了，不等于-1的时候，表示可以选择
        return process0(arr, total);
    }

    // 当前轮到先手来拿
    // 从arr中拿还剩下得数字，还剩下rest这么多值
    public static boolean process0(int[] arr, int rest) {
        // 先手进来，面临得一个0或者负数，那么先手输了
        if (rest <= 0) {
            return false;
        }
        // 现在去arr里面拿，先手拿了后，让后手去拿
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                continue;
            }
            int cur = arr[i];
            arr[i] = -1;
            // 在子过程中，如果子过程得先手赢不了，那么表示当前先手赢了，
            // 子过程得后手就是当前这个先手，子过程得先手，就是当前过程得后手
            // 需要还原现场的意思是：这个意思就是当前选择了i位置的数，然后当前位置已经选了，然后子过程拿着已经选择了i位置的数组往下递归所有的可能性
            boolean houshou = process0(arr, rest - arr[i]);
            // 还原现场，其他过程得时候还需要用，其他过程使用
            arr[i] = cur;
            // 当前过程的后手输了，也就是当前过程的先手赢了
            if (!houshou) {
                return true;
            }
        }
        return false;
    }

    // 状态压缩的暴力解
    public static boolean canIWin1(int choose, int total) {
        if (total == 0) {
            // 先手去拿，发现没有剩余了，自己赢了
            return true;
        }
        // 比如给一个10 剩余给一个100完，无论怎么拿都不能搞定
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }

        return process1(choose, 0, total);
    }

    public static boolean process1(int choose, int status, int rest) {
        if (rest <= 0) {
            return false;
        }
        for (int i = 1; i <= choose; i++) {
            if (((1 << i) & status) == 0) {
                if (!process1(choose, status | (1 << i), rest - i)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 状态压缩的暴力递归改动态规划
    public static boolean canIWin2(int choose, int total) {
        if (total == 0) {
            // 先手去拿，发现没有剩余了，自己赢了
            return true;
        }
        // 比如给一个10 剩余给一个100完，无论怎么拿都不能搞定
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        // dp[status]的意思是，dp[status] = 1 表示true，dp[status] = -1 ，表示 false，dp[status] = 0，表示还没有计算过，需要去计算
        int[] dp = new int[1 << (choose + 1)];

        return process2(choose, 0, total, dp);
    }

    // status如果对应的i的位置为0，表示需要算，就是使用了一个整型来使用二进制位置来标识当前位置是否使用了
    public static boolean process2(int choose, int status, int rest, int[] dp) {
        if (dp[status] != 0) {
            return dp[status] == 1 ? true : false;
        }
        boolean ans = false;
        // 当前先手进来，面对的是0或者负数，那么就是false
        if (rest <= 0) {
            return ans;
        }
        // 在子过程中，如果子过程得先手赢不了，那么表示当前先手赢了，
        // 子过程得后手就是当前这个先手，子过程得先手，就是当前过程得后手
        // 需要还原现场的意思是：这个意思就是当前选择了i位置的数，然后当前位置已经选了，然后子过程拿着已经选择了i位置的数组往下递归所有的可能性
        for (int i = 1; i <= choose; i++) {
            // 如果当前位置为0，表示没有算过
            if (((1 << i) & status) == 0) {
                // 当前过程的后手输了，那么就是当前过程的先手赢了
                // 把status的第i位变成1
                if (!process2(choose, (status | (1 << i)), rest - i, dp)) {
                    ans = true;
                    break;
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }

}
