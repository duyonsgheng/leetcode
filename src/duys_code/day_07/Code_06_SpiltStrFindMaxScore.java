package duys_code.day_07;

import java.util.HashMap;

/**
 * @ClassName Code_06_SpiltStrFindMaxScore
 * @Author Duys
 * @Description
 * @Date 2021/9/28 16:44
 **/
public class Code_06_SpiltStrFindMaxScore {
    /**
     * 题意：
     * String str, int K, String[] parts, int[] record
     * str一定要分割成k个部分，分割出来的每部分在parts里必须得有
     * 那一部分的得分在record里
     * 请问，str切成k个部分，最大得分是多少？
     */
    /**
     * 暴力解答
     */
    public static int maxScore1(String str, int K, String[] parts, int[] record) {
        if (str == null || str.length() < 1 || parts.length != record.length) {
            return 0;
        }
        // 这是词频-分数
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            map.put(parts[i], record[i]);
        }
        return process1(str, 0, K, map);
    }

    // 背包问题了
    public static int process1(String str, int index, int rest, HashMap<String, Integer> map) {
        // 剩下K小于了0了，没办法了，搞不定了
        if (rest < 0) {
            return -1;
        }
        // 来到了字符串得末尾了，剩下得K如果是0.那么之前得就有效，否则无效
        if (index == str.length()) {
            return rest == 0 ? 0 : -1;
        }
        int max = -1;
        for (int i = index; i < str.length(); i++) {
            String first = str.substring(index, i + 1);
            // 如果当前得分数有效，那么就算一个有效切分，否则当前前缀不符合规则
            int cur = map.containsKey(first) ? process1(str, i + 1, rest - 1, map) : -1;
            if (cur != -1) {
                max = Math.max(max, map.get(first) + cur);
            }
        }
        return max;
    }

    // 动态规划的解答
    public static int maxScore2(String str, int K, String[] parts, int[] record) {
        if (str == null || str.length() < 1 || parts.length != record.length) {
            return 0;
        }
        // 这是词频-分数
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            map.put(parts[i], record[i]);
        }
        int N = str.length();
        // dp[i][j] 含义是 0~i的前缀，搞定分割成j部分最大得分
        int[][] dp = new int[N + 1][K + 1];
        // dp[N][0] = 0;
        for (int i = 1; i <= K; i++) {
            dp[N][i] = -1;
        }
        // 根据暴力解；格子依赖关系是依赖自己的下一行和左边的值，所以从下往上。从左往右填
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= K; rest++) {
                int max = -1;
                for (int end = index; index < N; index++) {
                    String first = str.substring(index, end + 1);
                    // 如果当前得分数有效，那么就算一个有效切分，否则当前前缀不符合规则
                    int cur = map.containsKey(first) ? dp[index + 1][rest - 1] : -1;
                    if (cur != -1) {
                        max = Math.max(max, map.get(first) + cur);
                    }
                }
                dp[index][rest] = max;
            }
        }
        return dp[0][K];
    }

    // 动态规划的解答 + 前缀树
    public static int maxScore3(String str, int K, String[] parts, int[] record) {
        if (str == null || str.length() < 1 || parts.length != record.length) {
            return 0;
        }
        Node root = createNode(parts, record);
        char[] strs = str.toCharArray();
        int N = str.length();
        int[][] dp = new int[N + 1][K + 1];
        // dp[N][0] = 0;
        for (int i = 1; i <= K; i++) {
            dp[N][i] = -1;
        }
        // 根据暴力解；格子依赖关系是依赖自己的下一行和左边的值，所以从下往上。从左往右填
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= K; rest++) {
                int max = -1;
                Node cur = root;
                for (int end = index; index < N; index++) {
                    // 从前缀树中去看看，有没有当前的路，如果有，那么继续，否则直接跳出这个查询前缀的循环
                    int nextPath = strs[end] - 'a';
                    if (cur.next[nextPath] == null) {
                        break;
                    }
                    cur = cur.next[nextPath];
                    int curScore = rest > 0 && cur.score != -1 ? dp[index + 1][rest - 1] : -1;
                    if (curScore != -1) {
                        max = Math.max(max, curScore);
                    }
                }
                dp[index][rest] = max;
            }
        }
        return dp[0][K];
    }

    public static Node createNode(String[] parts, int[] record) {
        Node root = new Node();
        for (int i = 0; i < parts.length; i++) {
            char[] str = parts[i].toCharArray();
            Node cur = root;
            for (int j = 0; i < str.length; j++) {
                if (cur.next[str[j] - 'a'] == null) {
                    cur.next[str[j] - 'a'] = new Node();
                }
                cur = cur.next[str[j] - 'a'];
            }
            cur.score = record[i];
        }
        return root;
    }

    /**
     * 依然使用前缀树
     */
    public static class Node {
        public int score;
        public Node[] next;

        public Node() {
            score = -1;
            next = new Node[26];
        }
    }
}
