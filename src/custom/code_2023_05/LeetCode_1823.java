package custom.code_2023_05;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName LeetCode_1823
 * @Author Duys
 * @Description
 * @Date 2023/5/24 9:38
 **/
// 1823. 找出游戏的获胜者
public class LeetCode_1823 {
    public int findTheWinner(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) queue.add(i);
        while (!queue.isEmpty()) {
            for (int i = 1; i < k; i++) {
                queue.add(queue.poll());
            }
            // 把第k个给poll掉
            queue.poll();
        }
        return queue.peek();
    }
}
