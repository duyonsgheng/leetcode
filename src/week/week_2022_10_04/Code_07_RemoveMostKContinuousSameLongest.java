package week.week_2022_10_04;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ClassName Code_07_RemoveMostKContinuousSameLongest
 * @Author Duys
 * @Description
 * @Date 2022/10/27 15:50
 **/
// 来自亚马逊
// 给定一个数组arr，和一个正数k
// 你可以随意删除arr中的数字，最多删除k个
// 目的是让连续出现一种数字的长度尽量长
// 返回这个尽量长的长度
// 比如数组arr = { 3, -2, 3, 3, 5, 6, 3, -2 }, k = 3
// 你可以删掉-2、5、6(最多3个)，这样数组arr = { 3, 3, 3, 3, -2 }
// 可以看到连续出现3的长度为4
// 这是所有删除方法里的最长结果，所以返回4
// 1 <= arr长度 <= 3 * 10^5
// -10^9 <= arr中的数值 <= 10^9
// 0 <= k <= 3 * 10^5
public class Code_07_RemoveMostKContinuousSameLongest {
    // 给每一个数字准备一个窗口
    public static int longest(int[] arr, int k) {
        Map<Integer, Deque<Integer>> valueQueues = new HashMap<>();
        int ans = 1;
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            if (!valueQueues.containsKey(value)) {
                valueQueues.put(value, new LinkedList<>());
            }
            Deque<Integer> queue = valueQueues.get(value);
            // k=5 当前数是 7 来到的20位置，第一个7出现在7位置，从7到20位置出现了4个7，那么就是20-7-4 还余下7个不是7，但是只有5个可以操作，说明7位置的7不能要了
            while (!queue.isEmpty() && i - queue.peekFirst() - queue.size() > k) {
                queue.pollFirst();
            }
            queue.addLast(i);
            ans = Math.max(ans, queue.size());
        }
        return ans;
    }
}
