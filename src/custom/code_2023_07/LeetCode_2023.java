package custom.code_2023_07;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2023
 * @date 2023年07月31日
 */
// 2023. 连接后等于目标字符串的字符串对
public class LeetCode_2023 {
    public static int numOfPairs(String[] nums, String target) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String str : nums) {
            cnt.put(str, cnt.getOrDefault(str, 0) + 1);
        }
        int ans = 0;
        for (int i = 1; i < target.length(); i++) {
            String pre = target.substring(0, i);
            String suf = target.substring(i);
            ans += cnt.getOrDefault(pre, 0) * cnt.getOrDefault(suf, 0);
            if (pre.equals(suf)) {
                ans -= cnt.getOrDefault(suf, 0);
            }
        }
        return ans;
    }

}
