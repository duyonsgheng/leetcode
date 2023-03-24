package week.week_2023_02_01;

import java.util.TreeSet;

/**
 * @ClassName Code_01_LeetCode_1675
 * @Author Duys
 * @Description
 * @Date 2023/2/2 9:14
 **/
// 1675. 数组的最小偏移量
public class Code_01_LeetCode_1675 {

    // 思路：要想收集全所有的答案，然后找出最小的
    // 先把奇数乘以2，都变成偶数，然后从偶数开始往下调整
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num % 2 == 0 ? num : num * 2);
        }
        // 差值
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
