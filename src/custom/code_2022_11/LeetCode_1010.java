package custom.code_2022_11;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1010
 * @Author Duys
 * @Description
 * @Date 2022/11/2 15:29
 **/
// 1010. 总持续时间可被 60 整除的歌曲
public class LeetCode_1010 {
    public static int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Long> count = new HashMap<>();
        long ans = 0;
        for (int i : time) {
            count.put(i % 60, count.getOrDefault(i % 60, 0L) + 1);
        }
        ans += count.getOrDefault(0, 0L) * (count.getOrDefault(0, 0L) - 1) / 2;
        ans += count.getOrDefault(30, 0L) * (count.getOrDefault(30, 0L) - 1) / 2;
        count.remove(0);
        count.remove(30);
        for (int i = 0; i < 60; i++) {
            if (i == 0 || i == 30) {
                continue;
            }
            if (count.containsKey(i) && count.containsKey(60 - i)) {
                long c = count.get(i);
                long t = count.get(60 - i);
                ans += c * t;
                count.remove(60 - i);
                count.remove(i);
            }

        }
        return (int) ans;
    }

    public static void main(String[] args) {
        System.out.println(143 % 60);
        System.out.println(40 % 60);
    }
}
