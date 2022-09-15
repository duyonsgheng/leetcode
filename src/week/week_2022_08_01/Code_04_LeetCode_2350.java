package week.week_2022_08_01;

import java.util.Arrays;

/**
 * @ClassName Code_04_LeetCode_2350
 * @Author Duys
 * @Description
 * @Date 2022/8/8 13:44
 **/
// 2350. 不可能得到的最短骰子序列
public class Code_04_LeetCode_2350 {
    // 脑经急转弯的题
    // 我们先看看整个数组能不能分成几个1-6的区间
    public static int shortestSequence(int[] rolls, int k) {
        boolean[] set = new boolean[k + 1];
        // 当前这一套收集了几个了
        int size = 0;
        int ans = 0;
        for (int num : rolls) {
            if (!set[num]) {
                set[num] = true;
                size++;
            }
            // 收集满了，就下一轮
            if (size == k) {
                ans++;
                Arrays.fill(set, false);
                size = 0;
            }
        }
        // 第几轮就出来了，那么下一轮就一定是不能搞定的
        return ans + 1;
    }
}
