package duys_code.day_08;

/**
 * @ClassName Code_03_FindWord
 * @Author Duys
 * @Description
 * @Date 2021/10/11 16:11
 **/
public class Code_03_FindWord {
    /**
     * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
     * 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
     * char[][] m = { { 'a', 'b', 'z' },
     * { 'c', 'd', 'o' },
     * { 'f', 'e', 'o' }
     * },
     * 设定1：可以走重复路的情况下，返回能不能找到
     * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
     * 设定2：不可以走重复路的情况下，返回能不能找到
     * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
     */

    // 问题1：可以走重复路
    public static boolean findWord1(char[][] m, String word) {
        if (word == null || word.equals("")) {
            return true;
        }
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }
        char[] str = word.toCharArray();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (process1(m, i, j, str, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 从m[i][j] 字符出发，能不能找到str[k]往后的所有字符串
    public static boolean process1(char[][] m, int i, int j, char[] str, int k) {
        // 如果需要搞定的是一个空的，那么之前的决定是有效的
        if (k == str.length) {
            return true;
        }
        if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != str[k]) {
            return false;
        }
        boolean ans = false;
        // 往上下左右四个方向走
        if (process1(m, i - 1, j, str, k + 1) || process1(m, i + 1, j, str, k + 1)
                || process1(m, i, j - 1, str, k + 1) || process1(m, i, j + 1, str, k + 1)) {
            ans = true;
        }
        return ans;
    }

    // 问题1的动态规划：可以走重复路
    public static boolean findWord1Dp(char[][] m, String word) {
        if (word == null || word.equals("")) {
            return true;
        }
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }

        char[] str = word.toCharArray();
        int N = m.length;
        int M = m[0].length;
        int len = str.length;
        boolean[][][] dp = new boolean[N][M][len];
        // dp[i][j][k]表示 m[i][j]位置的字符能不能搞定str[0...k]这个前缀串
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = m[i][j] == str[0];
            }
        }
        // 位置依赖是啥呢。依赖自己的下一层，也就是K
        for (int k = 1; k < len; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][k] = m[i][j] == str[k] && checkPrevious(dp, i, j, k);
                }
            }
        }
        // 这里看看我的最后一层数据，有没有满足要求的
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j][len - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkPrevious(boolean[][][] dp, int i, int j, int k) {
        boolean up = i > 0 ? (dp[i - 1][j][k - 1]) : false;
        boolean down = i < dp.length - 1 ? (dp[i + 1][j][k - 1]) : false;
        boolean left = j > 0 ? (dp[i][j - 1][k - 1]) : false;
        boolean right = j < dp[0].length - 1 ? (dp[i][j + 1][k - 1]) : false;
        return up || down || left || right;
    }

    // 问题2：不能走重复的路
    public static boolean findWord2(char[][] m, String word) {
        if (word == null || word.equals("")) {
            return true;
        }
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }
        char[] str = word.toCharArray();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (process1(m, i, j, str, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 来一个深度优先的遍历
    public static boolean process2(char[][] m, int i, int j, char[] str, int k) {
        // 如果需要搞定的是一个空的，那么之前的决定是有效的
        if (k == str.length) {
            return true;
        }
        if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != str[k]) {
            return false;
        }
        boolean ans = false;
        // 因为不能重复，所以子过程进行之前，需要把走过的路全部改成 0
        m[i][j] = 0;
        // 往上下左右四个方向走
        if (process1(m, i - 1, j, str, k + 1) || process1(m, i + 1, j, str, k + 1)
                || process1(m, i, j - 1, str, k + 1) || process1(m, i, j + 1, str, k + 1)) {
            ans = true;
        }
        // 每一个子过程结束后，恢复现场，便于下一个子过程开始后，不出错
        m[i][j] = str[k];
        return ans;
    }
}
