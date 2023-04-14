package custom.code_2023_03;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1578
 * @Author Duys
 * @Description
 * @Date 2023/3/24 14:54
 **/
// 1578. 使绳子变成彩色的最短时间
public class LeetCode_1578 {
    // 希望两个连续的气球涂着相同的颜色
    // 保留成本最高的
    public static int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int ans = 0;
        // aabaa
        // 12341
        for (int i = 0, j = 0, sum = 0, max = 0; i < n; ) {
            while (j < n && colors.charAt(j) == colors.charAt(i)) {
                sum += neededTime[j];
                max = Math.max(max, neededTime[j]);
                j++;
            }
            // i到j之间有几个位置
            if (j - i > 1) {
                ans += sum - max;
            }
            i = j;
            sum = 0;
            max = 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minCost("abaac", new int[]{1, 2, 3, 4, 5}));
        System.out.println(minCost("abc", new int[]{1, 2, 3}));
        System.out.println(minCost("aabaa", new int[]{1, 2, 3, 4, 1}));
    }
}
