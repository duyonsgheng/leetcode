package custom.code_2022_07;

/**
 * @ClassName LeetCode_318
 * @Author Duys
 * @Description
 * @Date 2022/7/19 14:38
 **/
// 318. 最大单词长度乘积
// 给你一个字符串数组words ，找出并返回 length(words[i]) * length(words[j])的最大值，并且这两个单词不含有公共字母。如果不存在这样的两个单词，返回 0 。
//链接：https://leetcode.cn/problems/maximum-product-of-word-lengths
public class LeetCode_318 {

    // 位运算，把每一个单词抓换成一个整型
    public static int maxProduct(String[] words) {
        if (words == null || words.length < 1) {
            return 0;
        }
        int len = words.length;
        // 用来加速两个字符串匹配
        int[] marks = new int[len];
        for (int i = 0; i < len; i++) {
            String word = words[i];
            int wordLen = word.length();
            for (int j = 0; j < wordLen; j++) {
                marks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int ans = 0;
        // 枚举所有的组合情况
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((marks[i] & marks[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}
