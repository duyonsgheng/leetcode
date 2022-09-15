package custom.code_2022_04;

import duys_code.day_26.Code_01_212_LeetCode;
import week.week_2022_03_02.Code_03_AiFullWord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_30
 * @Author Duys
 * @Description
 * @Date 2022/4/27 14:20
 **/
// 给定一个字符串s和一些 长度相同 的单词words 。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
//
//注意子串要与words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑words中单词串联的顺序。
//链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
public class LeetCode_30 {

    // 前缀树
    // 词频统计
    public static List<Integer> findSubstring(String s, String[] words) {
        // 来到i位置，还剩下的单词
        //TrieNode root = new TrieNode();
        //for (String word : words) {
        //fillTrieNode(root, word);
        //}

        List<Integer> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        int n = chars.length;
        // 从每一个位置出发，然后去一
        for (int i = 0; i < n; i++) {
            // 看看接下来能不能搞定一个单词
            List<String> wds = copy(words);
            if (isOk(chars, i, wds)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static List<String> copy(String[] wordss) {
        List<String> words = new ArrayList<>();
        for (String word : wordss)
            words.add(word);
        return words;
    }

    // 从index位置出发，依次看看能不能搞定，直到rest不剩下东西，说明可以搞定
    public static boolean isOk(char[] str, int index, List<String> rest) {
        // 最重要的剪枝----？？？
        // 每次判断---
        String cur = "";
        for (int i = index; i < str.length; i++) {
            if (rest.isEmpty()) {
                return true;
            }
            cur += str[i];
            if (rest.contains(cur)) {
                rest.remove(cur);
                cur = "";
            }
        }
        return rest.isEmpty() ? true : false;
    }

    // dfs
/*    public static boolean isOk(TrieNode cur, char[] str, int index, List<String> rest) {
        if (index == str.length) {
            return rest.isEmpty() ? true : false;
        }
        TrieNode curNode = cur;
        int r = 0;
        String curWord = null;
        while (curNode != null && index < str.length) {
            curNode = curNode.nexts[str[index++] - 'a'];
            if (curNode != null && curNode.end) {
                curWord = curNode.word;
                rest.remove(curWord);
            }
        }
    }*/

    private static void fillTrieNode(TrieNode root, String word) {
        char[] str = word.toCharArray();
        TrieNode cur = root;
        for (char ch : str) {
            if (cur.nexts == null) {
                cur.nexts = new TrieNode[26];
            }
            if (cur.nexts[ch - 'a'] == null) {
                cur.nexts[ch - 'a'] = new TrieNode();
            }
            cur = cur.nexts[ch - 'a'];
        }
        cur.end = true;
        cur.word = word;
    }

    public static class TrieNode {
        public TrieNode[] nexts;
        //public int pass; // 意思就是有多少单词通过本节点，因为收集得时候要求不重复，所以收集过了，就不需要重复搞了
        public boolean end;
        public String word;

        public TrieNode() {
            nexts = new TrieNode[26];
            //pass = 0;
            end = false;
        }
    }


    // 滑动窗口 + 双hashMap
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() <= 0 || words == null || words.length <= 0) {
            return res;
        }
        //字符串长度
        int n = s.length();
        // 单词数
        int num = words.length;
        // 当前单词长度
        int len = words[0].length();
        // 单词的总共长度
        int allLen = num * len;
        // 如果单词的总长度都大于了字符串长度了，搞不定
        if (allLen > n) {
            return res;
        }
        // 记录每一种单词出现的次数
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            // 在s中没有这个单词，说明搞不定
            if (s.indexOf(word) == -1) {
                return res;
            }
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // 遍历所有的字符串位置
        for (int i = 0; i < len; i++) {
            // 左边界
            int left = i;
            // 右边界
            int right = i;
            // 当前窗口内的单词数
            int count = 0;
            // 本次窗口内的单词出现次数
            Map<String, Integer> hasWords = new HashMap<>();
            // 开始进行窗口的滑动
            while (right + len <= n) {
                // 当前的单词
                String cur = s.substring(right, right + len);
                right += len;
                if (wordCount.containsKey(cur)) {
                    hasWords.put(cur, hasWords.getOrDefault(cur, 0) + 1);
                    count++;
                    while (hasWords.get(cur) > wordCount.get(cur)) {
                        String del = s.substring(left, left + len);
                        hasWords.put(del, hasWords.get(del) - 1);
                        left += len;
                        count--;
                    }
                } else {
                    left = right;
                    hasWords.clear();
                    count = 0;
                }
                if (count == num) {
                    res.add(left);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String str = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};
        List<Integer> substring = findSubstring(str, words);
        for (int i : substring) {
            System.out.print(" " + i);
        }
    }
}
