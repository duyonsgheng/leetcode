package custom.code_2023_06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_2611
 * @Author Duys
 * @Description
 * @Date 2023/6/7 13:03
 **/
// 2611. 老鼠和奶酪
public class LeetCode_2611 {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int ans = 0;
        int n = reward1.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            ans += reward2[i]; // 第二支老鼠吃掉得分
            queue.add(reward1[i] - reward2[i]); // 如果第一支老鼠吃掉当前位置，那么得分的变化
            if (queue.size() > k) {
                queue.poll(); // 把最小的不要。表示第一只老鼠可以放弃哪些比较小的位置，相当于我吃掉reward1中的，但是需reward2中的需要减去
            }
        }
        while (!queue.isEmpty())
            ans += queue.poll();
        return ans;
    }

}
