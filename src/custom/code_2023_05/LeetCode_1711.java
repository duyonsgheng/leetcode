package custom.code_2023_05;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_1711
 * @Author Duys
 * @Description
 * @Date 2023/5/6 16:23
 **/
// 1711. 大餐计数
public class LeetCode_1711 {


    public static int countPairs(int[] deliciousness) {
        int mod = 1_000_000_007;
        Map<Integer, Integer> cnt = new HashMap<>();
        int max = 0;
        for (int num : deliciousness) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
            max = Math.max(max, num);
        }
        long ans = 0;
        for (int x : cnt.keySet()) {
            // 枚举
            for (int i = 1; i < (max << 1) + 1; i <<= 1) {
                int t = i - x;
                if (cnt.containsKey(t)) {
                    long cur = (long) cnt.get(x);
                    if (t == x) ans += (cur - 1) * cur;
                    else ans += cur * cnt.get(t);
                }
            }
        }
        // 之前算的时候多算了部分，最后需要处理掉，因为 (a,b)这一组，a算了两次，b也算了两次
        ans >>= 1;
        return (int) (ans % mod);
    }

    public static void main(String[] args) {
        System.out.println(countPairs(new int[]{1, 3, 5, 7, 9}));
    }
}
