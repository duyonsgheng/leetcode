package custom.code_2022_11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1048
 * @Author Duys
 * @Description
 * @Date 2022/11/7 17:30
 **/
// 1048. 最长字符串链
public class LeetCode_1048 {

    public int longestStrChain(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        // 短的在前面
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int ans = 0;
        for (String word : words) {
            int cur = 0;
            for (int i = 0; i < word.length(); i++) {
                // 枚举
                String pre = word.substring(0, i) + word.substring(i + 1);
                cur = Math.max(cur, map.getOrDefault(pre, 0) + 1);
            }
            map.put(word, cur);
            ans = Math.max(ans, cur);
        }
        return ans;
    }
}
