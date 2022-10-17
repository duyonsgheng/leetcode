package custom.code_2022_10;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_916
 * @Author Duys
 * @Description
 * @Date 2022/10/17 16:40
 **/
// 916. 单词子集
public class LeetCode_916 {

    // 如果使用前缀树和单词排序的话，复杂度太高了，需要抓住有用信息，就是所有的单词只有小写字母。一下就降低了复杂度
    // 需要简化流程，我们把words2合并成一个大的单词。统计words2中所有的字母出现次数最多的，并且记录
    public static List<String> wordSubsets(String[] words1, String[] words2) {
        int[] maxCount = new int[26]; // 记录b中最大的
        for (String word2 : words2) {
            int[] cur = count(word2);
            for (int i = 0; i < 26; i++) {
                maxCount[i] = Math.max(maxCount[i], cur[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (String word1 : words1) {
            int[] cur = count(word1);
            boolean flag = true;
            for (int i = 0; i < 26; i++) {
                if (cur[i] < maxCount[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans.add(word1);
            }
        }
        return ans;
    }

    public static int[] count(String s) {
        int[] ans = new int[26];
        for (char c : s.toCharArray()) {
            ans[c - 'a']++;
        }
        return ans;
    }

}
