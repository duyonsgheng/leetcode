package custom.code_2022_07;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName LeetCode_365
 * @Author Duys
 * @Description
 * @Date 2022/7/20 13:33
 **/
// 365. 水壶问题
// 有两个水壶，容量分别为jug1Capacity和 jug2Capacity 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到targetCapacity 升。
//如果可以得到targetCapacity升水，最后请用以上水壶中的一或两个来盛放取得的targetCapacity升水。
//链接：https://leetcode.cn/problems/water-and-jug-problem
public class LeetCode_365 {

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if (jug1Capacity + jug2Capacity < targetCapacity) {
            return false;
        }
        if (jug1Capacity == 0 || jug2Capacity == 0) {
            return targetCapacity == 0 || jug2Capacity + jug1Capacity == targetCapacity;
        }
        return targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : (gcd(b, a % b));
    }

    public boolean canMeasureWater1(int x, int y, int z) {
        if (x < 0 || x + y < z) {
            return false;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur + x <= x + y && !visited.contains(cur + x)) {
                queue.offer(cur + x);
                visited.add(cur + x);
            }
            if (cur + y <= x + y && !visited.contains(cur + y)) {
                queue.offer(cur + y);
                visited.add(cur + y);
            }
            // 空了
            if (cur - x >= 0 && !visited.contains(cur - x)) {
                queue.offer(cur - x);
                visited.add(cur - x);
            }
            if (cur - y >= 0 && !visited.contains(cur - y)) {
                queue.offer(cur - y);
                visited.add(cur - y);
            }
            if (visited.contains(z)) {
                return true;
            }
        }
        return false;
    }

}
