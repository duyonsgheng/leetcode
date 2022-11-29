package custom.code_2022_11;

/**
 * @ClassName LeetCode_1234
 * @Author Duys
 * @Description
 * @Date 2022/11/28 16:39
 **/
//
public class LeetCode_1234 {
    public int balancedString(String s) {
        int l = 0;
        int r = 0;
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'A']++;
        }
        int m = n / 4;
        int min = Integer.MAX_VALUE;
        if (cnt['Q' - 'A'] == m && cnt['W' - 'A'] == m && cnt['E' - 'A'] == m && cnt['R' - 'A'] == m) {
            return 0;
        }
        while (r < n) {
            cnt[s.charAt(r++) - 'A']--;
            while (cnt['Q' - 'A'] <= m && cnt['W' - 'A'] <= m && cnt['E' - 'A'] <= m && cnt['R' - 'A'] <= m) {
                min = Math.min(min, r - l);
                cnt[s.charAt(l++) - 'A']++;
            }
        }
        return min;
    }
}
