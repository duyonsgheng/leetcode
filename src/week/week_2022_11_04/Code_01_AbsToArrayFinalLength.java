package week.week_2022_11_04;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_01_AbsToArrayFinalLength
 * @Author Duys
 * @Description
 * @Date 2022/11/24 16:13
 **/
// 来自国外题目论坛
// 给定一个非负数组arr
// 任何两个数差值的绝对值，如果arr中没有，都要加入到arr里
// 然后新的arr继续，任何两个数差值的绝对值，如果arr中没有，都要加入到arr里
// 一直到arr大小固定
// 请问最终arr长度是多少
// 1 <= arr的长度 <= 10^5
// 0 <= arr的数值 <= 10^5
public class Code_01_AbsToArrayFinalLength {
    // 思路
    // 1: 首先在一个没有0的非负数组内，元素个数满足题意是多少
    // 2: 然后算0单独算
    // 求出所有元素的最小公约数
    public static int finalLen(int[] arr) {
        int max = 0;
        int gcd = 0;
        for (int num : arr) {
            max = Math.max(max, num);
            if (num != 0) {
                gcd = num;
            }
        }
        int n = arr.length;
        // 所有元素都是0
        if (gcd == 0) {
            return n;
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : arr) {
            if (num != 0) {
                gcd = gcd(num, gcd);
            }
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        // 没0的时候总共有多少
        int ans = max / gcd;
        ans += count.getOrDefault(0, 0);
        boolean add = false;// 如果没0，那么需要再有重复元素的时候把0加上
        for (int key : count.keySet()) {
            if (key != 0) {
                // 重复的元素需要加上 -1 次，因为没有重复的时候已经算了一个了
                ans += count.get(key) - 1;
            }
            if (!add && count.get(key) > 1 && !count.containsKey(0)) {
                ans++;
                add = true;
            }
        }
        return ans;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
