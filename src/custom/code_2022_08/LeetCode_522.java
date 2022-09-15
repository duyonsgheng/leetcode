package custom.code_2022_08;

/**
 * @ClassName LeetCode_522
 * @Author Duys
 * @Description
 * @Date 2022/8/22 10:54
 **/
// 522. 最长特殊序列 II
public class LeetCode_522 {

    public static int findLUSlength(String[] strs) {
        int n = strs.length;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            boolean ok = true;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (check(strs[i], strs[j])) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    public static boolean check(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }
}
