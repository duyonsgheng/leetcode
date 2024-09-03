package hope.class89;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code02_TwoCityScheduling
 * @date 2024年08月22日 上午 10:28
 */
// 两地调度
// 公司计划面试2n个人，给定一个数组 costs
// 其中costs[i]=[aCosti, bCosti]
// 表示第i人飞往a市的费用为aCosti，飞往b市的费用为bCosti
// 返回将每个人都飞到a、b中某座城市的最低费用
// 要求每个城市都有n人抵达
// 测试链接 : https://leetcode.cn/problems/two-city-scheduling/
public class Code02_TwoCityScheduling {
    // 讲解023中的题目2
    // 先所有的人全部去a点，然后计算从a点到b点的花销
    public static int twoCitySchedCost(int[][] costs) {
        int n = costs.length;
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += costs[i][0];
            arr[i] = costs[i][1] - costs[i][0];
        }
        Arrays.sort(arr);
        int m = n / 2;
        for (int i = 0; i < m; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
