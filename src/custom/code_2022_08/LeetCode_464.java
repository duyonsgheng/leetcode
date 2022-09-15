package custom.code_2022_08;

/**
 * @ClassName LeetCode_464
 * @Author Duys
 * @Description
 * @Date 2022/8/12 17:50
 **/
// 464. 我能赢吗
public class LeetCode_464 {

    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0 || desiredTotal <= maxChoosableInteger) {
            return true;
        }
        // desiredTotal 非常大，也不行
        if (maxChoosableInteger * (maxChoosableInteger + 1) >> 1 < desiredTotal) {
            return false;
        }
        int[] arr = new int[maxChoosableInteger];
        for (int i = 0; i < maxChoosableInteger; i++) {
            arr[i] = i + 1;
        }
        return process1(arr, desiredTotal);
    }

    public static boolean process1(int[] arr, int rest) {
        if (rest <= 0) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                continue;
            }
            int cur = arr[i];
            arr[i] = -1;
            boolean next = process1(arr, rest - cur);
            arr[i] = cur;
            // 只有后手失败了，先手赢了
            if (!next) {
                return true;
            }
        }
        return false;
    }

    // 状态压缩
    public static boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0 || desiredTotal <= maxChoosableInteger) {
            return true;
        }
        // desiredTotal 非常大，也不行
        if (maxChoosableInteger * (maxChoosableInteger + 1) >> 1 < desiredTotal) {
            return false;
        }
        return process2(maxChoosableInteger, 0, desiredTotal);
    }

    public static boolean process2(int source, int status, int rest) {
        if (rest <= 0) {
            return false;
        }
        // 开始选数字
        for (int i = 1; i <= source; i++) {
            // 当前数字没选，
            if (((1 << i) & status) == 0) {
                // 并且后手没赢
                if (!process2(source, status | (1 << i), rest - i)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean canIWin3(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0 || desiredTotal <= maxChoosableInteger) {
            return true;
        }
        // desiredTotal 非常大，也不行
        if (maxChoosableInteger * (maxChoosableInteger + 1) >> 1 < desiredTotal) {
            return false;
        }
        int[] dp = new int[1 << (maxChoosableInteger + 1)];
        return process3(maxChoosableInteger, 0, desiredTotal, dp);
    }

    public static boolean process3(int source, int status, int rest, int[] dp) {
        if (rest <= 0) {
            return false;
        }
        if (dp[status] != 0) {
            return dp[status] == 1 ? true : false;
        }
        // 开始选数字
        boolean ans = false;
        for (int i = 0; i <= source; i++) {
            // 当前数字没选，
            if (((1 << i) & status) == 0) {
                // 并且后手没赢
                if (!process3(source, status | (1 << i), rest - i, dp)) {
                    ans = true;
                    break;
                }
            }
        }
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}
