package custom.code_2023_05;

/**
 * @ClassName LeetCode_1737
 * @Author Duys
 * @Description
 * @Date 2023/5/16 11:28
 **/
// 1737. 满足三条件之一需改变的最少字符数
public class LeetCode_1737 {
    //
    public int minCharacters(String a, String b) {
        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();
        int al = a.length();
        int bl = b.length();
        int ans = Integer.MAX_VALUE;
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (char c : arr1) {
            cnt1[c - 'a']++;
        }
        for (char c : arr2) {
            cnt2[c - 'a']++;
        }
        for (int i = 0; i < 26 && ans != 0; i++) {
            // 1.变成a和b中的字符一样
            int ca = al - cnt1[i];
            int cb = bl - cnt2[i];
            ans = Math.min(ans, ca + cb);
            if (i == 0) {
                continue;
            }
            // 2.a中的字符全部都小于 i位置的字符，b中字符都大于等于 i位置字符
            int ra = 0, rb = 0;
            for (int j = i; j < 26; j++)
                ra += cnt1[j];
            for (int j = 0; j < i; j++)
                ra += cnt2[j];
            // 3.跟2相反
            for (int j = i; j < 26; j++)
                rb += cnt2[j];
            for (int j = 0; j < i; j++)
                rb += cnt1[j];
            ans = Math.min(ans, Math.min(ra, rb));
        }
        return ans;
    }
}
