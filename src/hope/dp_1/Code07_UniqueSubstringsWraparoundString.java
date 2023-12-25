package hope.dp_1;

/**
 * @author Mr.Du
 * @ClassName Code07_UniqueSubstringsWraparoundString
 * @date 2023年12月05日 9:32
 */
// 环绕字符串中唯一的子字符串
// 定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串
// 所以 base 看起来是这样的：
// "..zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd.."
// 给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现
// 测试链接 : https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
public class Code07_UniqueSubstringsWraparoundString {
    public static int findSubstringInWraproundString(String str) {
        int n = str.length();
        int[] arr = new int[n];
        // 转为数字，后续好处理
        for (int i = 0; i < n; i++) {
            arr[i] = str.charAt(i) - 'a';
        }
        // 必须以 a...z字符结尾的情况下，往左能扩展的最大长度
        int[] dp = new int[26];
        dp[arr[0]] = 1;
        for (int i = 1, cur, pre, len = 1; i < n; i++) {
            cur = arr[i];
            pre = arr[i - 1];
            // 看看当前字符能不能和前面的链接起来
            if ((pre == 25 && cur == 0) || pre + 1 == cur) {
                len++;
            } else {
                len = 1;
            }
            // 当前字符结尾的取最大的，会起到过滤的作用
            dp[cur] = Math.max(dp[cur], len);
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += dp[i];
        }
        return ans;
    }
}
