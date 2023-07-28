package custom.code_2023_07;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1996
 * @date 2023年07月25日
 */
// 1996. 游戏中弱角色的数量
// https://leetcode.cn/problems/the-number-of-weak-characters-in-the-game/
public class LeetCode_1996 {
    // 就是一个排序规则
    // 先按照某一个值排序，从小到大
    public int numberOfWeakCharacters(int[][] properties) {
        // 按照攻击大的在前面
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        int max = 0;
        int ans = 0;
        // max的时候一定是攻击也比当前的大，而且防御也比当前的大
        for (int[] p : properties) {
            if (p[1] < max) {
                ans++;
            } else {
                max = p[1];
            }
        }
        return ans;
    }
}
