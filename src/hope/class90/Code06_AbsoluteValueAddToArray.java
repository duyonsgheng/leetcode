package hope.class90;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName Code06_AbsoluteValueAddToArray
 * @date 2024年08月26日 下午 05:37
 */
public class Code06_AbsoluteValueAddToArray {
    // 暴力方法
    // 为了验证
    public static int len1(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            list.add(num);
            set.add(num);
        }
        while (!finish(list, set))
            ;
        return list.size();
    }

    public static boolean finish(ArrayList<Integer> list, HashSet<Integer> set) {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int abs = Math.abs(list.get(i) - list.get(j));
                if (!set.contains(abs)) {
                    list.add(abs);
                    set.add(abs);
                }
            }
        }
        return len == list.size();
    }

    public static int len2(int[] arr) {
        int max = 0;
        int gcd = 0;
        for (int num : arr) {
            max = Math.max(max, num);
            if (num != 0) {
                gcd = num;
            }
        }
        // 全部都是0
        if (gcd == 0) {
            return arr.length;
        }
        // 词频统计
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : arr) {
            if (num != 0) {
                gcd = gcd(num, gcd);
            }
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        int ans = max / gcd; // 最少可以拥有这么多数字
        int maxCount = 0;
        for (int key : count.keySet()) {
            if (key != 0) {
                ans += count.get(key) - 1; // 要少算一个额外增加的
            }
            maxCount = Math.max(maxCount, count.get(key));
        }
        // 单独算0的部分
        ans += count.getOrDefault(0, maxCount > 1 ? 1 : 0);
        return ans;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
