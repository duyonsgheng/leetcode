package duys_code.day_05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName Code_04_DeleteMinCost
 * @Author Duys
 * @Description
 * @Date 2021/9/24 16:39
 **/
public class Code_04_DeleteMinCost {
    /**
     * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
     * 比如 s1 = "abcde"，s2 = "axbc"
     */

    //解法1：先把s2的所有字串全部搞出来，然后和s1进行一个KMP---java中的Sting 的indexOf底层使用的就是KMP
    // 搞定一个字符串所有的字串问题，就是从左往右的尝试，当前位置要和不要
    @Deprecated
    public static int minCost1(String s1, String s2) {
        if (s1 == null || s2 == null) {
            // 无效参数，返回搞不定
            return Integer.MAX_VALUE;
        }
        List<String> subChildStr = new ArrayList<>();
        process1(s2.toCharArray(), 0, "", subChildStr);
        subChildStr.sort(new MyComparator());
        for (String str : subChildStr) {
            if (s1.indexOf(str) != -1) {
                return s2.length() - str.length();
            }
        }
        // 否则直接就是删除所有的
        return s2.length();
    }

    public static void process1(char[] str2, int index, String path, List<String> list) {
        if (index == str2.length) {
            list.add(path);
            return;
        }
        // 要当前位置
        process1(str2, index + 1, path, list);
        // 不要当前位置
        process1(str2, index + 1, path + str2[index], list);
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            // 字符串长度越大的排在前面
            return o2.length() - o1.length();
        }
    }

    // 方法2：就是我们的最短编辑距离问题解答
    public static int minCost2(String s1, String s2) {
        if (s1 == null || s2 == null || (s2.length() > s1.length())) {
            // 无效参数，返回搞不定
            return Integer.MAX_VALUE;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        // dp[i][j] 含义 s1的前 0到i-1所有字符，变成 s2 的0 到j-1所有字符的样子，最少要删除几个
        int[][] dp = new int[N + 1][M + 1];
        // 初始化，因为我们后面要求最小值，所以这里给一个初始化
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        // s1 空的，s2也是空的，那么需要删除0个
        dp[0][0] = 0;
        // 这里就是当我们s1要变成s2 的空，我们需要删除多少个字符
        for (int i = 1; i <= N; i++) {
            // 一路都需要删除
            dp[i][0] = i;
        }
        // 普遍位置
        // 依赖分析：还是样本对应模型，以结束位置讨论
        /**
         * 当s1 来到 i 的时候 s2 来到j ，
         * 可能性1：我们s1的0到i-2 就已经是 s2的0到j-1了，那么s1需要删除最后一个字符
         * 可能性2：我们s1的i-1和s2的j-1的字符都是相等的，那么答案就是之前的决定
         * // 这里我们s2的长度不能大于s1 ，也就进行可能性剪枝了
         */
        for (int i = 1; i <= N; i++) {
            // 这里我们要想一个问题，如果s2的长度都已经大于了s1了，那么s1无论怎么删除都搞不定
            for (int j = 1; j <= Math.min(M, i); j++) {
                int p1 = Integer.MAX_VALUE;
                int p2 = Integer.MAX_VALUE;
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    p1 = dp[i - 1][j] + 1;
                }
                if (dp[i - 1][j - 1] != Integer.MAX_VALUE && str1[i - 1] == str2[j - 1]) {
                    p2 = dp[i - 1][j - 1];
                }
                dp[i][j] = Math.min(p1, p2);
            }
        }
        // 这里注意是长度，不是位置
        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {
        String s1 = "achgsik12asddfh56";
        String s2 = "ahk";
        System.out.println(" " + minCost1(s1, s2) + "  " + minCost2(s1, s2));
    }

}
