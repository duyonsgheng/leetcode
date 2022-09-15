package custom.code_2022_08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_554
 * @Author Duys
 * @Description
 * @Date 2022/8/23 17:02
 **/
// 554. 砖墙
public class LeetCode_554 {

    // 我们统计
    public int leastBricks(List<List<Integer>> wall) {
        int n = wall.size();
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0, sum = 0; i < n; i++, sum = 0) {
            for (int j : wall.get(i)) {
                sum += j;
                count.put(sum, count.getOrDefault(sum, 0) + 1);
            }
            // 开头和结束都不能选
            count.remove(sum);
        }
        int ans = n;
        // 总共n行，如果出现次数一样的前缀，那么就是减去，剩下的就是穿过的砖块
        for (int i : count.keySet()) {
            int cur = count.get(i);
            ans = Math.min(ans, n - cur);
        }
        return ans;
    }
}
