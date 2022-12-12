package custom.code_2022_12;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1297
 * @Author Duys
 * @Description
 * @Date 2022/12/5 15:44
 **/
// 1297. 子串的最大出现次数
public class LeetCode_1297 {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        char[] arr = s.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        int ans = 0;
        int l = 0, r = 0;
        int cnt = 0;
        int[] count = new int[26];
        while (r < n) {
            count[arr[r] - 'a']++;
            if (count[arr[r] - 'a'] == 1) {
                cnt++;
            }
            r++;
            int len = r - l;
            // 需要缩进窗口
            while (cnt > maxLetters || len > minSize) {
                count[arr[l] - 'a']--;
                if (count[arr[l] - 'a'] == 0) {
                    cnt--;
                }
                l++;
                len--;
            }
            // 满足了
            if (cnt <= maxLetters) {
                if (len >= minSize && len <= maxSize) {
                    String cur = s.substring(l, r);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        for (int num : map.values()) {
            ans = Math.max(ans, num);
        }
        return ans;
    }

    public int maxFreq1(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        char[] arr = s.toCharArray();
        Map<String, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int size = minSize; size <= maxSize; size++) {
            int kind = 0;
            int[] hash = new int[26];
            for (int i = 0; i < n; i++) {
                if (hash[arr[i] - 'a'] == 0) {
                    kind++;
                }
                hash[arr[i] - 'a']++;
                int l = i - size + 1;
                // 已经形成了窗口
                if (l >= 0) {
                    if (kind <= maxLetters) {
                        String cur = new String(arr, l, size);
                        cnt.put(cur, cnt.getOrDefault(cur, 0) + 1);
                        ans = Math.max(ans, cnt.get(cur));
                    }
                    // 窗口开始缩进一个
                    hash[arr[l] - 'a'] --;
                    if (hash[arr[l] - 'a'] == 0) {
                        kind--;
                    }
                }
            }
        }
        return ans;
    }
}
