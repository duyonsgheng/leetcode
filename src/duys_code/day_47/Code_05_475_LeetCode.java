package duys_code.day_47;

import java.util.Arrays;

/**
 * @ClassName Code_05_475_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/heaters/
 * @Date 2021/10/20 17:10
 **/
public class Code_05_475_LeetCode {
    /**
     * 冬季已经来临。你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
     * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
     * 现在，给出位于一条水平线上的房屋houses 和供暖器heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
     * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
     */

    // 两个数组排序 ， 供热的数组不回退，遇到相同的就跳往下一个
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0, j = 0; i < houses.length; i++) {
            while (!best(houses, heaters, i, j)) {
                j++;
            }
            ans = Math.max(ans, Math.abs(heaters[j] - houses[i]));
        }
        return ans;
    }

    // 函数的意义：
    // houses[i] 由heaters[j]来供暖
    // 如果由j来供暖产生的半径是a，由j+1 供暖产生的半径是b
    // 如果 a < b j 是最优 不应该跳下一个位置
    // 如果 a > b 则说明j+1才是最优，需要跳下一个
    //  1 4 6 9
    //  2 3 5
    public static boolean best(int[] houses, int[] heaters, int i, int j) {
        return j == heaters.length - 1 || Math.abs(heaters[j] - houses[i]) < Math.abs(heaters[j + 1] - houses[i]);
    }
}
