package duys_code.day_39;

/**
 * @ClassName Code_01_AddValue
 * @Author Duys
 * @Description
 * @Date 2021/12/20 16:10
 **/
public class Code_01_AddValue {
    // 来自腾讯
    // 给定一个只由0和1组成的字符串S，假设下标从1开始，规定i位置的字符价值V[i]计算方式如下 :
    // 1) i == 1时，V[i] = 1
    // 2) i > 1时，如果S[i] != S[i-1]，V[i] = 1
    // 3) i > 1时，如果S[i] == S[i-1]，V[i] = V[i-1] + 1
    // 你可以随意删除S中的字符，返回整个S的最大价值
    // 字符串长度<=5000

    // 从左往右的尝试
    public static int max1(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] arr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = str[i] == '0' ? 0 : 1;
        }
        return process(arr, 0, 0, 0);
    }

    // index位置上做选择
    // preValue 前一个位置是1还是0
    // preSum 之前做出决定的最大值
    public static int process(int[] arr, int index, int preValue, int preSum) {
        if (index == arr.length) {
            return 0;
        }
        // 当前位置要和不要
        int curValue = arr[index] == preValue ? (preSum + 1) : 1;
        // 不要
        int next1 = process(arr, index + 1, preValue, preSum);

        // 要
        int next2 = process(arr, index + 1, arr[index], curValue);

        return Math.max(curValue + next2, next1);
    }

    // 改动态规划
    public static int max2(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int[] arr = new int[str.length];
        int N = arr.length;
        for (int i = 0; i < str.length; i++) {
            arr[i] = str[i] == '0' ? 0 : 1;
        }
        // index - 0.。。4999
        // preValue - 0 和 1
        // curValue - 1到5000
        int[][][] dp = new int[N + 1][N + 1][2];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                for (int m = 0; m < 2; m++) {
                  /*  int curValue = arr[i] == arr[i + 1] ? (preSum + 1) : 1;
                    // 不要
                    int next1 = dp[i + 1][m][j];

                    // 要
                    int next2 =dp[i + 1][m][j]; process(arr, index + 1, arr[index], curValue);

                    return Math.max(curValue + next2, next1);*/
                }
            }
        }
        return dp[0][0][0];
    }
}
