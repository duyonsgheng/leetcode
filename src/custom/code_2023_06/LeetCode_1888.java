package custom.code_2023_06;

/**
 * @ClassName LeetCode_1888
 * @Author Duys
 * @Description
 * @Date 2023/6/16 14:04
 **/
// 1888. 使二进制字符串字符交替的最少反转次数
// 给你一个二进制字符串s。你可以按任意顺序执行以下两种操作任意次：
//类型 1 ：删除 字符串s的第一个字符并将它 添加到字符串结尾。
//类型 2 ：选择 字符串s中任意一个字符并将该字符反转，也就是如果值为'0'，则反转得到'1'，反之亦然。
//请你返回使 s变成 交替 字符串的前提下，类型 2的 最少操作次数。
//我们称一个字符串是 交替的，需要满足任意相邻字符都不同。
//比方说，字符串"010" 和"1010"都是交替的，但是字符串"0100"不是。
//链接：https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating
public class LeetCode_1888 {
    public int minFlips(String s) {
        int len = s.length();
        StringBuffer sb1 = new StringBuffer(); // 偶数位置是0
        StringBuffer sb2 = new StringBuffer(); // 偶数位置是1
        for (int i = 0; i < len; i++) {
            sb1.append(i % 2 == 0 ? '0' : '1');
            sb2.append(i % 2 == 0 ? '1' : '0');
        }
        int[] cnt1 = new int[len];
        int[] cnt2 = new int[len];
        for (int i = 0; i < len; i++) {
            // 是 01010这种模式
            cnt1[i] = (i > 0 ? cnt1[i - 1] : 0) + (s.charAt(i) == sb1.charAt(i) ? 0 : 1);
            // 是 10101这种模式
            cnt2[i] = (i > 0 ? cnt2[i - 1] : 0) + (s.charAt(i) == sb2.charAt(i) ? 0 : 1);
        }
        int ans = Math.min(cnt1[len - 1], cnt2[len - 1]);
        if (len % 2 == 1) { // 奇数长度
            for (int i = 0; i < len; i++) {
                ans = Math.min(cnt1[i] + cnt2[len - 1] - cnt2[i], ans);
                ans = Math.min(cnt2[i] + cnt1[len - 1] - cnt1[i], ans);
            }
        }
        return ans;
    }
}
