package custom.code_2023_06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1921
 * @date 2023年06月30日
 */
// 1921. 消灭怪物的最大数量
// https://leetcode.cn/problems/eliminate-maximum-number-of-monsters/
public class LeetCode_1921 {
    // 距离 / 速度 就是到达城市的时间
    public static int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        for (int i = 0; i < n; i++) {
            if (dist[i] % speed[i] == 0) {
                dist[i] = dist[i] / speed[i];
            } else dist[i] = dist[i] / speed[i] + 1;
        }
        // 怪物达到城市的时间
        Arrays.sort(dist);
        for (int i = 0; i < n; i++) {
            // 如果怪物达到的时间比我消灭它的时间晚，那么就可以消灭
            if (dist[i] < i + 1) {
                return i;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(eliminateMaximum(new int[]{1, 3, 4}, new int[]{1, 1, 1}));
        //dist = [1,1,2,3], speed = [1,1,1,1]
        System.out.println(eliminateMaximum(new int[]{1, 1, 2, 3}, new int[]{1, 1, 1, 1}));
        // dist = [3,2,4], speed = [5,3,2]
        System.out.println(eliminateMaximum(new int[]{3, 2, 4}, new int[]{5, 3, 2}));
    }
}
