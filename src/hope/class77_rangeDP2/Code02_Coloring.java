package hope.class77_rangeDP2;

/**
 * @author Mr.Du
 * @ClassName Code02_Coloring
 * @date 2024年03月20日 18:04
 */
// 涂色 & 奇怪打印机
// 假设你有一条长度为5的木板，初始时没有涂过任何颜色
// 你希望把它的5个单位长度分别涂上红、绿、蓝、绿、红
// 用一个长度为5的字符串表示这个目标：RGBGR
// 每次你可以把一段连续的木板涂成一个给定的颜色，后涂的颜色覆盖先涂的颜色
// 例如第一次把木板涂成RRRRR
// 第二次涂成RGGGR
// 第三次涂成RGBGR，达到目标
// 返回尽量少的涂色次数
// 测试链接 : https://www.luogu.com.cn/problem/P4170
// 测试链接 : https://leetcode.cn/problems/strange-printer/
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Code02_Coloring {

    // l 和 r相同，那么就全部刷成一样的
    // l 和 r不相同，那么就l到r之间划分点
    public static int strangePrinter(String str) {
        char[] arr = str.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = 1;
        for (int i = 0; i < n - 1; i++) {
            dp[i][i] = 1; // 只有一个位置
            dp[i][i + 1] = arr[i] == arr[i + 1] ? 1 : 2; // 颜色相同，只需要一次，否则刷两次
        }
        // 从底向上，从左往右
        for (int l = n - 3, ans; l >= 0; l--) {
            for (int r = l + 2; r < n; r++) {
                if (arr[l] == arr[r]) {
                    dp[l][r] = dp[l][r - 1];
                    // dp[l][r] = dp[l + 1][r];
                } else {
                    ans = Integer.MAX_VALUE;
                    for (int m = l; m < r; m++) {
                        // m点划分点
                        ans = Math.min(ans, dp[l][m] + dp[m + 1][r]);
                    }
                    dp[l][r] = ans;
                }
            }
        }
        return dp[0][n - 1];
    }
}
