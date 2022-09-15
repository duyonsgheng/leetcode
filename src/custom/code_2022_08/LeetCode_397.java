package custom.code_2022_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_397
 * @Author Duys
 * @Description
 * @Date 2022/8/8 16:01
 **/
//
public class LeetCode_397 {
    Map<Long, Integer> map = new HashMap<>();

    public int integerReplacement(int n) {
        return dfs(n * 1L);
    }

    public int dfs(long num) {
        if (num == 1) {
            return 0;
        }
        if (map.containsKey(num)) {
            return map.get(num);
        }
        int ans = (num % 2 == 0 ? dfs(num / 2) : Math.min(dfs(num + 1), dfs(num - 1))) + 1;
        map.put(num, ans);
        return ans;
    }
}
