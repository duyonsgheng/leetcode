package custom.code_2022_12;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1371
 * @Author Duys
 * @Description
 * @Date 2022/12/14 17:22
 **/
// 1371. 每个元音包含偶数次的最长子字符串
public class LeetCode_1371 {

    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int[] cnt = new int[1 << 5];
        Arrays.fill(cnt, -1);
        int ans = 0, status = 0;
        cnt[0] = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') {
                status ^= (1 << 0);
            } else if (c == 'e') {
                status ^= (1 << 1);
            } else if (c == 'i') {
                status ^= (1 << 2);
            } else if (c == 'o') {
                status ^= (1 << 3);
            } else if (c == 'u') {
                status ^= (1 << 4);
            }
            if (cnt[status] >= 0) {
                ans = Math.max(ans, i + 1 - cnt[status]);
            } else {
                cnt[status] = i + 1;
            }
        }
        return ans;
    }
}
