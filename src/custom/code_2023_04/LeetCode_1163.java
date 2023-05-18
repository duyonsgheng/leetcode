package custom.code_2023_04;

/**
 * @ClassName LeetCode_1163
 * @Author Duys
 * @Description
 * @Date 2023/4/24 10:03
 **/
// 1163. 按字典序排在最后的子串
public class LeetCode_1163 {

    public String lastSubstring(String s) {
        int i = 0, j = 1, n = s.length();
        // abab
        while (j < n) {
            int k = 0;
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                int t = i;
                i = j;
                j = Math.max(j + 1, t + k + 1);
            } else {
                j = j + k + 1;
            }
        }
        return s.substring(i);
    }
}
