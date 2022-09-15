package duys_code.day_31;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_04_140_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/word-break-ii/
 * @Date 2021/11/29 17:52
 **/
public class Code_05_140_LeetCode {

    public static void main(String[] args) {
        // "catsanddog"
        //["cat","cats","and","sand","dog"]
        String s = "catsanddog";
        List<String> ws = new ArrayList<>();
        ws.add("cat");
        ws.add("cats");
        ws.add("and");
        ws.add("sand");
        ws.add("dog");
        wordBreak(s, ws);
    }

    // 本体设计到 139问题的一个回溯
    // 1. 前缀树把wordList的全部加载到前缀树中，加速查找
    // 2. 源字符串s形成的每一个位置出发能不能搞定 分割成wordList中的单词 的dp
    // 3. 根据dp回溯每一步产生的可能结果，进行一个深度优先的遍历
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() <= 0 || wordDict == null || wordDict.size() <= 0) {
            return ans;
        }
        // 组装树
        Node root = new Node();
        for (String word : wordDict) {
            fillNode(root, word);
        }
        // 搞到dp
        boolean[] dp = findDp(root, s.toCharArray());
        process(s.toCharArray(), 0, root, dp, new ArrayList<>(), ans);
        return ans;
    }

    // 从index出发
    // path 是每一次的路径是啥
    public static void process(char[] arr, int index, Node root, boolean[] dp, List<String> path, List<String> ans) {
        if (index == arr.length) { // 都来到最后了，收集答案
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                builder.append(path.get(i) + " ");
            }
            builder.append(path.get(path.size() - 1));
            ans.add(builder.toString());
        } else {
            Node cur = root;
            // 看看当前来到的位置开始尝试所有的位置
            for (int end = index; end < arr.length; end++) {
                int road = arr[end] - 'a';
                // 前缀树中都没有这个开始的前缀
                if (cur.nexts[road] == null) {
                    break;
                }
                cur = cur.nexts[road];
                // 当前确实有这个前缀树，并且index+1 也确实能分解
                if (cur.end && dp[end + 1]) {
                    path.add(cur.path);
                    // 已经有一个了，进行深度优先遍历
                    process(arr, end + 1, root, dp, path, ans);
                    // 还原现场
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    // 从str的index出发及其往后所有的位置能不能被分解
    public static boolean[] findDp(Node root, char[] arr) {
        int N = arr.length;
        boolean[] dp = new boolean[N + 1];
        // 主函数是调用的0位置开始，需要返回dp[0]
        // dp[N] 就是空字符串能不能被分解
        dp[N] = true;
        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            // 尝试 i到N的所有位置
            for (int index = i; index < N; index++) {
                int path = arr[index] - 'a';
                // 如果此时都没有后续，那么当前位置出发是不行的
                if (cur.nexts[path] == null) {
                    break;
                }
                cur = cur.nexts[path];
                if (cur.end && dp[index + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp;
    }

    public static void fillNode(Node root, String word) {
        Node cur = root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (cur.nexts[c - 'a'] == null) {
                cur.nexts[c - 'a'] = new Node();
            }
            cur = cur.nexts[c - 'a'];
        }
        cur.end = true;
        cur.path = word;
    }

    public static class Node {
        // 这里为了不在源字符串上截取子字符串，我们把end为ture的位置加上当前单词的完整单词
        public String path;
        public boolean end;
        public Node[] nexts;

        public Node() {
            path = null;
            end = false;
            // 全部都是小写字符
            nexts = new Node[26];
        }
    }
}
