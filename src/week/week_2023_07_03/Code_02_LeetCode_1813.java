package week.week_2023_07_03;

/**
 * @author Mr.Du
 * @ClassName Code_02_LeetCode_1813
 * @date 2023年07月20日
 */
// 1813. 句子相似性 III
// https://leetcode.cn/problems/sentence-similarity-iii/
public class Code_02_LeetCode_1813 {
    // 反手就是双指针
    public static boolean areSentencesSimilar(String s1, String s2) {
        String[] word1 = s1.split(" ");
        String[] word2 = s2.split(" ");
        int i = 0, j = 0, n1 = word1.length, n2 = word2.length;
        // 有相同的就开始跳过
        while (i < n1 && i < n2 && word1[i].equals(word2[i])) {
            i++;
        }
        // 从后往前如果有相同的就一直跳过
        while (n1 - j > i && n2 - j > i && word1[n1 - 1 - j].equals(word2[n2 - 1 - j])) {
            j++;
        }
        // i ... j 这之间就是需要插入的部分
        return i + j == Math.min(n1, n2);
    }
}
