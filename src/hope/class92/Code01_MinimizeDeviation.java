package hope.class92;

import java.util.TreeSet;

/**
 * @author Mr.Du
 * @ClassName Code01_MinimizeDeviation
 * @date 2024年09月10日 下午 03:30
 */
// 数组的最小偏移量
// 给你一个由n个正整数组成的数组nums
// 你可以对数组的任意元素执行任意次数的两类操作：
// 如果元素是偶数，除以2
// 例如如果数组是[1,2,3,4]
// 那么你可以对最后一个元素执行此操作，使其变成[1,2,3,2]
// 如果元素是奇数，乘上2
// 例如如果数组是[1,2,3,4]
// 那么你可以对第一个元素执行此操作，使其变成[2,2,3,4]
// 数组的偏移量是数组中任意两个元素之间的最大差值
// 返回数组在执行某些操作之后可以拥有的最小偏移量
// 测试链接 : https://leetcode.cn/problems/minimize-deviation-in-array/
public class Code01_MinimizeDeviation {
    // 把所有的奇数都乘以2
    // 然后从最大的数开始除以2，如果最大的数都是奇数了，流程就结束了
    public static int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                set.add(num);
            } else set.add(num * 2);
        }
        int ans = set.last() - set.first();
        while (ans > 0 && set.last() % 2 == 0) {
            int max = set.last();
            set.remove(max);
            set.add(max / 2);
            ans = Math.min(ans, set.last() - set.first());
        }
        return ans;
    }
}
