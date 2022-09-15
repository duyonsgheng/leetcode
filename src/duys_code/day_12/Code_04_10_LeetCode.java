package duys_code.day_12;

/**
 * @ClassName Code_04_10_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/regular-expression-matching/
 * @Date 2021/10/19 15:57
 **/
public class Code_04_10_LeetCode {
    /**
     * str1 不含有 . *
     * str2 含有 . *
     *  .可以代表任意一个字符，但是 . 不能变成 空
     *  *可以代表任意字符多个 一位置 * 的前面必须是字符 不能是* ；如果是 .* 那么可以配一切字符串
     */
    /**
     * 问：能不能把str2变成str1
     */
    // 样本对应模型 或者 行列模型
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exe = p.toCharArray();
        return isValid(str, exe) && process(str, exe, 0, 0);
    }

    // s里面不能有 . 或者 *
    // p 里面的 * 不能相邻，也不能在开头
    public static boolean isValid(char[] s, char[] p) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '.' || s[i] == '*') {
                return false;
            }
        }
        for (int i = 0; i < p.length; i++) {
            if (p[i] == '*' && (i == 0 || p[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    // str从si出发 能不能被 exe从ei出发 配出来
    public static boolean process(char[] str, char[] exe, int si, int ei) {
        if (ei == exe.length) {
            // 如果匹配字符串都来到末尾，那么我目标串不能剩下字符
            return si == str.length;
        }
        // ei还有字符
        // 可能性分析，ei+1 是否是 *
        // 如果不是* 那么str[si] == exe[ei] || exe[ei]=='.' 满足就和process(str,exe,si+1,ei+1) 进行 && 否则直接返回了，因为ei和si 对应搞不定
        // 如果是*
        // 1.str[si]!=exe[ei] -> 只能让 ei+1 和ei 变成 空，这时候*=0 -> process(str,exe,si,ei+2)
        // 2.str[si]==exe[ei] -> 1> process(str,exe,si,ei+2); 2> process(str,exe,si+1,ei+2) 3> process(str,exe,si+2,ei+2) 4> process(str,exe,si+3,ei+2) ...如果下面字符相同则这里就往后延多少位置

        // exe[ei]还有字符
        // ei + 1位置的字符，不是*
        if (ei + 1 == exe.length || exe[ei + 1] != '*') {
            // 必须要si还有字符
            return si != str.length && (str[si] == exe[ei] || exe[ei] == '.') && process(str, exe, si + 1, ei + 1);
        }

        // exe[ei]还有字符
        // ei + 1位置的字符，是*
        while (si != str.length && (str[si] == exe[ei] || exe[ei] == '.')) {
            // 如果当前来到的位置字符相同或者exe[ei]是. 那么尝试把si对应的位置变成空
            if (process(str, exe, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process(str, exe, si, ei + 2);
    }

    // 样本对应模型 或者 行列模型
    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exe = p.toCharArray();
        if (!isValid(str, exe)) {
            return false;
        }
        // dp[i][j] = 0 需要去算； = 1 结果是true；= -1 结果是false
        int[][] dp = new int[str.length + 1][exe.length + 1];
        return process2(str, exe, 0, 0, dp);
    }

    // str从si出发 能不能被 exe从ei出发 配出来
    public static boolean process2(char[] str, char[] exe, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0) {
            return dp[si][ei] == 1;
        }
        boolean ans = false;
        if (ei == exe.length) {
            // 如果匹配字符串都来到末尾，那么我目标串不能剩下字符
            ans = si == str.length;
        } else {
            // 两个分支：ei+1 是不是 *
            // 然后利用当前的si和ei对应的字符是否相同，可以使用exe中的ei+1位置是*变成 空字符串 或者 解决str中的一个或者多个字符，然后后续走不同位置的流程
            if (ei + 1 == exe.length || exe[ei + 1] != '*') {
                // 必须要si还有字符
                ans = si != str.length && (str[si] == exe[ei] || exe[ei] == '.') && process2(str, exe, si + 1, ei + 1, dp);
            } else {
                if (si == str.length) { // ei+1是* 那么利用*把ei和ei+1 变成空
                    ans = process2(str, exe, si, ei + 2, dp);
                } else { // str 也还有字符，si没来到最后位置
                    if (str[si] != exe[ei] && exe[ei] != '.') { // 利用ei+1的* 把ei和ei+1 变成空，后续从si ei+2 继续
                        ans = process2(str, exe, si, ei + 2, dp);
                    } else { // 相等或者是 . 那么可以把目标串的字符解决一个或者多个然后si+1 ei 后续继续 || 当前的 ei+1的 * 把ei和ei+1 变空，si不动，ei+2 后续继续
                        ans = process2(str, exe, si + 1, ei, dp) || process2(str, exe, si, ei + 2, dp);
                    }
                }
            }
        }
        dp[si][ei] = ans ? 1 : -1;
        return ans;
    }

    // dp[i][j] str1从i出发到最后所有的字符串 能不能被str2从j出发到最后所有的字符串 配出来
    // 动态规划
    // 位置依赖是啥

    /**
     * 当前来到str的13位置， exe的29位置
     * 依赖哪些位置：(13,31),(14,31),(15,31)....直到si++  ei字符不等位置结束
     * 如果来到str的12位置， exe的29位置
     * 依赖哪些位置：(12,31),(13,31),(14,31),(15,31)....直到si++  ei字符不等位置结束
     * 我们递归从(0,0)开始，意味着我们的dp从下往上，从右往左填，所以填 (13,29)的时候已经填了部分位置，(12,29)的时候就可以使用(13,29)代替这一部分
     */
    public static boolean isMatch3(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] exe = p.toCharArray();
        if (!isValid(str, exe)) {
            return false;
        }
        int N = str.length;
        int M = exe.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        // 当str来到越界位置，exe也来到越界位置，表示已经结束了。前面的都是设置了的
        dp[N][M] = true;
        // 如果str只有一个字符了
        for (int j = M - 1; j >= 0; j--) {
            // 当前来到j位置，如果j+1位置是*。那么我们可以利用 * 把j 和 j+1 变成 空。所以答案就是 j+2 号位置的答案
            dp[N][j] = (j + 1 < M && exe[j + 1] == '*') && dp[N][j + 2];
        }
        if (N > 0 && M > 0) {
            // 如果都只剩下一个字符。除了两个位置相等以外，还可能exe的最后一个字符是 . 因为.可以代替任何字符
            dp[N - 1][M - 1] = str[N - 1] == exe[M - 1] || exe[M - 1] == '.';
        }
        // 普遍位置依赖
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                // 还是下一个位置是不是 * 讨论
                if (exe[j + 1] != '*') {
                    // 如果当前 i 和 j 两个串中对应字符相同，或者 exe的j位置是. 那么我当前位置无论如何都可以搞定了，就是依赖我的下一个位置
                    dp[i][j] = (str[i] == exe[j] || exe[j] == '.') && dp[i + 1][j + 1];
                } else {
                    // 如果当前 i 和 j 两个串中对应字符相同，或者 exe的j位置是. 那么我当前位置无论如何都可以搞定了，看看我下一个字符能不能被 当前的 j+1 位置的*搞定
                    if ((str[i] == exe[j] || exe[j] == '.') && dp[i + 1][j]) {
                        dp[i][j] = true;
                    } else {
                        // 否则，当前j 和 j+1 利用 j+1的位置变空。然后exe 往j+2去
                        dp[i][j] = dp[i][j + 2];
                    }
                }
            }
        }

        return dp[0][0];
    }
}
