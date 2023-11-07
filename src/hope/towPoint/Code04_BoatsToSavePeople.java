package hope.towPoint;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code04_BoatsToSavePeople
 * @date 2023年10月31日 9:55
 */
// 救生艇
// 给定数组 people
// people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit
// 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit
// 返回 承载所有人所需的最小船数
// 测试链接 : https://leetcode.cn/problems/boats-to-save-people/
public class Code04_BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0, l = 0, r = people.length - 1, sum = 0;
        while (l <= r) {
            sum = l == r ? people[l] : people[l] + people[r];
            if (sum > limit) {
                r--; // 单独一条船
            } else {
                r--;
                l++;
            }
            ans++;
        }
        return ans;
    }
}
