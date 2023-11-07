package custom.code_2023_08;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2126
 * @date 2023年08月28日
 */
// 2126. 摧毁小行星
// https://leetcode.cn/problems/destroying-asteroids/submissions/
public class LeetCode_2126 {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long sum = mass;
        for (int i = 0; i < asteroids.length; i++) {
            if (sum < asteroids[i]) {
                return false;
            }
            sum += asteroids[i];
        }
        return true;
    }
}
