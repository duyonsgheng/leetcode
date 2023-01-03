package custom.code_2022_12;

/**
 * @ClassName LeetCode_1400
 * @Author Duys
 * @Description
 * @Date 2022/12/27 15:34
 **/
// 1400. 构造 K 个回文字符串
public class LeetCode_1400 {
    // 统计哪些为奇数的字符串的个数，
    public boolean canConstruct(String s, int k) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        int n = 0;
        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                n++;
            }
        }
        int min = Math.max(n, 1);
        int max = s.length();
        return k >= min && k <= max;
    }

}
