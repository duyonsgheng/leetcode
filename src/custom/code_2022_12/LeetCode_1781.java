package custom.code_2022_12;

/**
 * @ClassName LeetCode_1781
 * @Author Duys
 * @Description
 * @Date 2022/12/12 11:26
 **/
// 1781. 所有子字符串美丽值之和
public class LeetCode_1781 {
    public int beautySum(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            int max = 0;
            // 字串了
            for (int j = i; j < n; j++) {
                cnt[s.charAt(j) - 'a']++;
                max = Math.max(max, cnt[s.charAt(j) - 'a']);
                int min = n;
                for (int x = 0; x < 26; x++) {
                    if (cnt[x] > 0) {
                        min = Math.min(cnt[x], min);
                    }
                }
                ans += max - min;
            }
        }
        return ans;
    }
}
