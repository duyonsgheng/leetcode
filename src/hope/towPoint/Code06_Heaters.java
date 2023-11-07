package hope.towPoint;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code06_Heaters
 * @date 2023年10月31日 10:07
 */
// 供暖器
// 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
// 在加热器的加热半径范围内的每个房屋都可以获得供暖。
// 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置
// 请你找出并返回可以覆盖所有房屋的最小加热半径。
// 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
// 测试链接 : https://leetcode.cn/problems/heaters/
public class Code06_Heaters {
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        Arrays.sort(houses);
        int ans = 0;
        for (int i = 0, j = 0; i < houses.length; i++) {
            while (!best(houses, heaters, i, j)) {
                j++;
            }
            // 从所有房屋最优供暖方案中选出记录最大的
            ans = Math.max(ans, Math.abs(heaters[j] - houses[i]));
        }
        return ans;
    }

    // i号房屋由j号供暖，和j+1号供暖，谁最优
    public static boolean best(int[] houses, int[] heaters, int i, int j) {
        return j == heaters.length - 1 || Math.abs(houses[i] - heaters[j]) < Math.abs(houses[i] - heaters[j + 1]);
    }
}
