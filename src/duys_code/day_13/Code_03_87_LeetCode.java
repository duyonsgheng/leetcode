package duys_code.day_13;

/**
 * @ClassName Code_03_87_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/scramble-string/
 * @Date 2021/10/21 11:16
 **/
public class Code_03_87_LeetCode {
    /**
     * 判断扰乱字符串：s1 和 s2 .
     */
    /**
     * 1.首先是一个样本对应模型
     * 2.如果两个字符串对应的种类以及词频不一样。那么搞不定
     */
    // 纯暴力方法-
    public static boolean isScramble1(String s1, String s2) {
        if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int r1 = str1.length - 1;
        int r2 = str2.length - 1;
        if (!checkStr(str1, str2)) {
            return false;
        }
        return process1(str1, 0, r1, str2, 0, r2);
    }

    // 我们的r1 -l1 == r2-l2。 每次递归一定等长
    // 递归含义 str1的l1 到 r1 能不能搞定 str2的l2到r2
    // 纯暴力的方法，并且有4个参数
    public static boolean process1(char[] str1, int l1, int r1, char[] str2, int l2, int r2) {
        if (l1 == r1) {
            return str1[l1] == str2[l2];
        }
        // 然后尝试所有的划分位置
        for (int leftEnd = l1; leftEnd < r1; leftEnd++) {
            // 可能性1：str1的l1~r1 对应的是 str2 的l2 ~ r2
            // str1 的 l1 =3 leftEnd = 7  -> 7-3 = 4 长度
            // str1 ： [l1...r1..............]
            // str2 的 l2 = 4 也要推4的长度
            // str2 ： [l2...r2..............]
            boolean p1 = process1(str1, l1, leftEnd, str2, l2, l2 + leftEnd - l1)
                    && process1(str1, leftEnd + 1, r1, str2, l2 + leftEnd - l1 + 1, r2);
            if (p1) {
                return p1;
            }
            // 可能性2：str1的l1~r1 对应的是 str2 的l2 ~ r2
            // str1 ： [l1...r1..............]
            // str2 ： [..............l2...r2]
            boolean p2 = process1(str1, l1, leftEnd, str2, r2 - leftEnd + l1, r2)
                    && process1(str1, leftEnd + 1, r1, str2, l2, r2 - leftEnd + l1 - 1);
            if (p2) {
                return p2;
            }
            //return p1 || p2;
        }
        return false;
    }


    public static boolean isScramble2(String s1, String s2) {
        if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        if (!checkStr(str1, str2)) {
            return false;
        }
        return process2(str1, str2, 0, 0, n);
    }

    // 我们的r1 -l1 == r2-l2。 每次递归一定等长
    // 递归含义 str1的l1 到 r1 能不能搞定 str2的l2到r2
    // 纯暴力的方法，参数啊化简
    public static boolean process2(char[] str1, char[] str2, int l1, int l2, int size) {
        // 只有一个字符了
        if (size == 1) {
            return str1[l1] == str2[l2];
        }
        // 枚举划分的长度
        for (int leftEnd = 1; leftEnd < size; leftEnd++) {
            boolean p1 = process2(str1, str2, l1, l2, leftEnd)
                    && process2(str1, str2, l1 + leftEnd, l2 + leftEnd, size - leftEnd);
            if (p1) {
                return p1;
            }
            boolean p2 = process2(str1, str2, l1, l2 + size - leftEnd, leftEnd)
                    && process2(str1, str2, l1 + leftEnd, l2, size - leftEnd);
            if (p2) {
                return p2;
            }
            //return p1 || p2;
        }
        return false;
    }

    // 三个参数+dp
    public static boolean isScramble3(String s1, String s2) {
        if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1.equals(s2)) {
            return true;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int[][][] dp = new int[n + 1][n + 1][n + 1];
        if (!checkStr(str1, str2)) {
            return false;
        }
        return process3(str1, str2, 0, 0, n, dp);
    }

    // 我们的r1 -l1 == r2-l2。 每次递归一定等长
    // 递归含义 str1的l1 到 r1 能不能搞定 str2的l2到r2
    // 纯暴力的方法，参数啊化简
    public static boolean process3(char[] str1, char[] str2, int l1, int l2, int size, int[][][] dp) {
        if (dp[l1][l2][size] != 0) {
            return dp[l1][l2][size] == 1;
        }
        boolean ans = false;
        // 只有一个字符了
        if (size == 1) {
            ans = str1[l1] == str2[l2];
        } else {
            // 枚举划分的长度
            for (int leftEnd = 1; leftEnd < size; leftEnd++) {
                boolean p1 = process3(str1, str2, l1, l2, leftEnd, dp)
                        && process3(str1, str2, l1 + leftEnd, l2 + leftEnd, size - leftEnd, dp);
                if (p1) {
                    ans = p1;
                } else {
                    boolean p2 = process3(str1, str2, l1, l2 + size - leftEnd, leftEnd, dp)
                            && process3(str1, str2, l1 + leftEnd, l2, size - leftEnd, dp);
                    if (p2) {
                        ans = p2;
                    }
                }
                //return p1 || p2;
            }
        }
        dp[l1][l2][size] = ans ? 1 : -1;
        return ans;
    }

    public static boolean checkStr(char[] str1, char[] str2) {
        if (str1.length != str2.length) {
            return false;
        }
        int[] map = new int[256];
        for (int i = 0; i < str1.length; i++) {
            map[str1[i]]++;
        }
        for (int i = 0; i < str2.length; i++) {
            map[str2[i]]--;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
