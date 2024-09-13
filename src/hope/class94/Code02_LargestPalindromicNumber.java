package hope.class94;

/**
 * @author Mr.Du
 * @ClassName Code02_LargestPalindromicNumber
 * @date 2024年09月13日 下午 04:06
 */
// 最大回文数字
// 给你一个仅由数字（0 - 9）组成的字符串num
// 请你找出能够使用num中数字形成的最大回文整数
// 并以字符串形式返回，该整数不含前导零
// 你无需使用num中的所有数字，但你必须使用至少一个数字，数字可以重新排列
// 测试链接 : https://leetcode.cn/problems/largest-palindromic-number/
public class Code02_LargestPalindromicNumber {
    // 词频统计
    // 词频分析
    // 特殊位置分析
    public static String largestPalindromic(String num) {
        int n = num.length();
        // '0' ~ '9'字符对应整数为48 ~ 57
        int[] cnts = new int[58];
        for (char a : num.toCharArray()) {
            cnts[a]++;
        }
        char[] ans = new char[n];
        int leftSize = 0; // 左边填到哪儿了
        char mid = 0; // 最中间的字符是什么
        for (char i = '9'; i >= '1'; i--) {
            if ((cnts[i] & 1) == 1 && mid == 0) { // 如果比较大的字符是奇数个，并且中间的位置没有设置过
                mid = i;
            }
            // 开始填，因为要的是回文，所以只填一半
            for (int j = cnts[i] / 2; j > 0; j--) {
                ans[leftSize++] = i;
            }
        }
        // 1 -9 都遍历完了，如果leftSize还是0，说明没有其他字符，遇到的全部是0，或者只有一个1-9的字符
        if (leftSize == 0) {
            if (mid == 0) {
                return "0";
            } else {
                return String.valueOf(mid);
            }
        }
        // 现在来考虑填0
        for (int j = cnts['0'] / 2; j > 0; j--) {
            ans[leftSize++] = '0';
        }
        int len = leftSize;
        // 把中点放进去
        if (mid == 0 && (cnts['0'] & 1) == 1) {
            mid = '0';
        }
        if (mid != 0) {
            ans[len++] = mid;
        }
        // 把左部分倒序到右边去
        for (int j = leftSize - 1; j >= 0; j--) {
            ans[len++] = ans[j];
        }
        return String.valueOf(ans);
    }
}
