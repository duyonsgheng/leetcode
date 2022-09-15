package duys_code.day_07;

import java.util.HashSet;

/**
 * @ClassName Code_05_SplitStr
 * @Author Duys
 * @Description
 * @Date 2021/9/28 14:57
 **/
public class Code_05_SplitStr {
    /**
     * 题意：
     * 假设所有字符都是小写字母
     * 大字符串是str
     * arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次.
     * 使用arr中的单词有多少种拼接str的方式. 返回方法数.
     */

    // 从左往右的尝试模型
    public static int splitNums1(String str, String[] arr) {
        if (str == null || str.length() < 1 || arr == null || arr.length < 1) {
            return -1;
        }
        HashSet<String> dict = new HashSet<>();
        for (String s : arr) {
            dict.add(s);
        }
        return process1(str, 0, dict);
    }

    public static int process1(String str, int index, HashSet<String> dict) {
        // 当我们要处理的位置来到字符串的末尾了，说明我们整个字符串在之前做的决定有效了
        if (index == str.length()) {
            return 1;
        }
        int ans = 0;
        // 当前来到index位置开始，看看从index开始，所有的index到index+1，+2，... str.length所有的前缀，在dict中如果有，就继续算后续，然后所偶的方法加起来
        // [index....end]
        for (int end = index; end < str.length(); end++) {
            if (dict.contains(str.substring(index, end + 1))) {
                ans += process1(str, end + 1, dict);
            }
        }
        return ans;
    }

    // 做记忆化搜索
    public static int splitNums2(String str, String[] arr) {
        if (str == null || str.length() < 1 || arr == null || arr.length < 1) {
            return -1;
        }
        int N = str.length();
        HashSet<String> dict = new HashSet<>();
        for (String s : arr) {
            dict.add(s);
        }
        int[] dp = new int[N + 1];
        // 根据暴力解的base case
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int end = i; end < N; end++) {
                if (dict.contains(str.substring(i, end + 1))) {
                    dp[i] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    /// 前缀树来优化迭代过程
    public static int splitNums3(String str, String[] arr) {
        if (str == null || str.length() < 1 || arr == null || arr.length < 1) {
            return -1;
        }
        // 建立前缀树
        Node root = new Node();
        for (String s : arr) {
            Node cur = root;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (cur.nexts[chars[i] - 'a'] == null) {
                    cur.nexts[chars[i] - 'a'] = new Node();
                }
                cur = cur.nexts[chars[i] - 'a'];
            }
            cur.isEnd = true;
        }
        return process2(str.toCharArray(), root, 0);
    }

    public static int process2(char[] str, Node root, int index) {
        if (index == str.length) {
            return 1;
        }
        Node cur = root;
        int ans = 0;
        for (int i = index; i < str.length; i++) {
            int curIndex = str[i] - 'a';
            if (cur.nexts[curIndex] == null) {
                break;
            }
            cur = cur.nexts[curIndex];
            if (cur.isEnd) {
                // 是除了当前i位置，后面所有的字符求一个后续
                ans += process2(str, root, i + 1);
            }
        }
        return ans;
    }

    public static class Node {
        public boolean isEnd;
        public Node[] nexts;

        public Node() {
            isEnd = false;
            nexts = new Node[26];
        }
    }
}
