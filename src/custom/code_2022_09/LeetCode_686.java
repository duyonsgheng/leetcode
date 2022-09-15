package custom.code_2022_09;

/**
 * @ClassName LeetCode_686
 * @Author Duys
 * @Description
 * @Date 2022/9/7 11:42
 **/
// 686. 重复叠加字符串匹配
public class LeetCode_686 {
    //
    public int repeatedStringMatch(String a, String b) {
        int an = a.length();
        int bn = b.length();
        int i = a.indexOf(b);
        if (i == -1) {
            return -1;
        }
        if (an - i >= bn) {
            return 1;
        }
        return (bn + i - an - 1) / an + 2;
    }

    public static int strStr(String a, String b) {
        int an = a.length();
        int bn = b.length(); // 如果需要匹配的字符串是空的，没蛋扯
        if (bn == 0) {
            return 0;
        }
        int[] pi = new int[bn];
        for (int i = 1, j = 0; i < bn; i++) {
            while (j > 0 && b.charAt(i) != b.charAt(j)) {
                j = pi[j - 1];
            }
            if (b.charAt(i) == b.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        for (int i = 0, j = 0; i - j < an; i++) {
            while (j > 0 && a.charAt(i % an) != b.charAt(j)) {
                j = pi[j - 1];
            }
            if (a.charAt(i % an) == b.charAt(j)) {
                j++;
            }
            if (j == bn) {
                return i - bn + 1;
            }
        }
        return -1;
    }

}
