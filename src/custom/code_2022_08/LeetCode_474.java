package custom.code_2022_08;

/**
 * @ClassName LeetCode_474
 * @Author Duys
 * @Description
 * @Date 2022/8/15 10:57
 **/
// 474. 一和零
public class LeetCode_474 {

    // 背包问题。
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            int[] count = cnt(strs[i]);
            int zc = count[0];
            int oc = count[1];
            for (int once = n; once >= oc; once--) {
                for (int zero = m; zero >= zc; zero--) {
                    // 可以选择了，选择了当前的 i位置的字符串。那么就需要减去当前字符串包含的1和0
                    dp[zero][once] = Math.max(dp[zero][once], dp[zero - zc][once - oc] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static int[] cnt(String str) {
        int[] arr = new int[2];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                arr[0]++;
            } else {
                arr[1]++;
            }
        }
        return arr;
    }
}
