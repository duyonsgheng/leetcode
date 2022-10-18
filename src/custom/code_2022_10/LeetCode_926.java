package custom.code_2022_10;

/**
 * @ClassName LeetCode_926
 * @Author Duys
 * @Description
 * @Date 2022/10/18 13:41
 **/
// 926. 将字符串翻转到单调递增
public class LeetCode_926 {
    public static int minFlipsMonoIncr(String s) {
        int[][] dp = new int[s.length()][2];
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        return process(s.toCharArray(), 0, '0', dp);
    }

    public static int process(char[] arr, int index, char pre, int[][] dp) {
        if (index == arr.length) {
            return 0;
        }
        if (dp[index][pre - '0'] != -1) {
            return dp[index][pre - '0'];
        }
        int ans = 0;
        if (pre == '0') {
            ans = Math.min(process(arr, index + 1, arr[index], dp), 1 + process(arr, index + 1, arr[index] == '0' ? '1' : '0', dp));
        } else {
            ans = arr[index] == '0' ? 1 + process(arr, index + 1, '1', dp) : process(arr, index + 1, '1', dp);
        }
        dp[index][pre - '0'] = ans;
        return ans;
    }

    public static int minFlipsMonoIncr1(String s) {
        int n = s.length();
        int dp0 = 0, dp1 = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int dp0New = dp0, dp1New = Math.min(dp0, dp1);
            if (c == '1') {
                dp0New++;
            } else {
                dp1New++;
            }
            dp0 = dp0New;
            dp1 = dp1New;
        }
        return Math.min(dp0, dp1);
    }

    public static void main(String[] args) {
        String s = "00011000";
        System.out.println(minFlipsMonoIncr(s));
    }
}
