package custom.code_2023_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1833
 * @Author Duys
 * @Description
 * @Date 2023/5/26 9:48
 **/
// 1833. 雪糕的最大数量
public class LeetCode_1833 {
    public static int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int n = costs.length;
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + costs[i];
        }
        // 二分,找到最右的位置
        //  1,1,2,3,4   7
        //0 1,2,4,7,11
        int l = 0, r = n, m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (pre[m] <= coins) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(maxIceCream(new int[]{1, 3, 2, 4, 1}, 7));
        System.out.println(maxIceCream(new int[]{1, 6, 3, 1, 2, 5}, 20));
    }
}
