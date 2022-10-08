package custom.code_2022_09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_846
 * @Author Duys
 * @Description
 * @Date 2022/9/29 17:19
 **/
// 846. 一手顺子
public class LeetCode_846 {
    // hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand == null || hand.length < groupSize) {
            return false;
        }
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : hand) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        for (int x : hand) {
            if (!map.containsKey(x)) {
                continue;
            }
            // hand排序了
            // x出现了，说明当前x可能是还没有分组种最小的额
            for (int i = 0; i < groupSize; i++) {
                int cur = x + i;
                if (!map.containsKey(cur)) {
                    return false;
                }
                map.put(cur, map.get(cur) - 1);
                if (map.get(cur) == 0) {
                    map.remove(cur);
                }
            }
        }
        return true;
    }
}
