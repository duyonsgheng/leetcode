package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1946
 * @date 2023年07月12日
 */
// 1946. 子字符串突变后可能得到的最大整数
// https://leetcode.cn/problems/largest-number-after-mutating-substring/
public class LeetCode_1946 {
    public String maximumNumber(String num, int[] change) {
        int n = num.length();
        char[] charArray = num.toCharArray();
        for (int i = 0; i < n; i++) {
            // 从左往右。找到了一个比自己还大的，那么尝试更新后面的
            if (change[charArray[i] - '0'] > charArray[i] - '0') {
                // 如果change中都是大于当前的，那么就更新当前的
                while (i < n && change[charArray[i] - '0'] >= charArray[i] - '0') {
                    charArray[i] = (char) ('0' + change[charArray[i] - '0']);
                    i++;
                }
                break;
            }
        }
        return new String(charArray);
    }
}
