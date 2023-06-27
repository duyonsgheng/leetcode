package custom.code_2023_05;

/**
 * @ClassName LeetCode_1754
 * @Author Duys
 * @Description
 * @Date 2023/5/19 9:24
 **/
// 1754. 构造字典序最大的合并字符串
public class LeetCode_1754 {
    // 每次选两个中最大的
    public String largestMerge(String word1, String word2) {
        StringBuffer buffer = new StringBuffer();
        int i1 = 0, i2 = 0;
        while (i1 < word1.length() || i2 < word2.length()) {
            // 如果word1开头剩下的串比word2要大，那么选择word1
            if (i1 < word1.length() && word1.substring(i1).compareTo(word2.substring(i2)) > 0) {
                buffer.append(word1.charAt(i1));
                i1++;
            } else {
                buffer.append(word2.charAt(i2));
                i2++;
            }
        }
        return buffer.toString();
    }
}
