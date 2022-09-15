package duys_code.day_52;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName Code_03_1488_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/avoid-flood-in-the-city/
 * @Date 2021/11/8 17:02
 **/
public class Code_03_1488_LeetCode {
    /**
     * 你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n个湖泊下雨的时候，如果第 n个湖泊是空的，那么它就会装满水，否则这个湖泊会发生洪水。你的目标是避免任意一个湖泊发生洪水。
     * 给你一个整数数组rains，其中：
     * rains[i] > 0表示第 i天时，第 rains[i]个湖泊会下雨。
     * rains[i] == 0表示第 i天没有湖泊会下雨，你可以选择 一个湖泊并 抽干这个湖泊的水。
     * <p>
     * 请返回一个数组ans，满足：
     * ans.length == rains.length
     * 如果rains[i] > 0 ，那么ans[i] == -1。
     * 如果rains[i] == 0，ans[i]是你第i天选择抽干的湖泊。
     * 如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
     */
    // rains[i] = j 第i天轮到j号湖泊下雨
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        int[] invalid = new int[0];
        // key : 某个湖
        // value : 这个湖在哪些位置降雨
        // 4 : {3,7,19,21}
        // 1 : { 13 }
        // 2 : {4, 56}
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                continue;
            }
            if (!map.containsKey(rains[i])) {
                map.put(rains[i], new LinkedList<>());
            }
            map.get(rains[i]).addLast(i);
        }
        // 哪些湖下雨，如果有重复的，就不能继续了
        // 某个湖如果满了，加入到set里
        // 某个湖被抽干了，从set中移除
        Set<Integer> set = new HashSet<>();
        // 这个堆的堆顶表示最先处理的湖是哪个，小根堆
        PriorityQueue<Work> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] != 0) {
                if (set.contains(rains[i])) {
                    return invalid;
                }
                // 放入到没抽干的集合去
                set.add(rains[i]);
                // 弹出当前已经下过雨的天数了
                map.get(rains[i]).pollFirst();
                // 紧接着，把这个湖下一次下雨的天数加到需要工作的队列去
                if (!map.get(rains[i]).isEmpty()) {
                    queue.add(new Work(rains[i], map.get(rains[i]).peekFirst()));
                }
                // 如果下雨呢，就是啥也不干
                ans[i] = -1;
            } else {
                // 开始抽水.
                // 如果当前没有可以工作的，那么就是1，题目第四个示例
                if (queue.isEmpty()) {
                    ans[i] = 1;
                } else {
                    // 需要清理的最近要下雨的湖
                    Work poll = queue.poll();
                    set.remove(poll.lake);
                    ans[i] = poll.lake;
                }
            }
        }
        return ans;
    }

    public static class Work implements Comparable<Work> {
        public int lake;
        public int nextRain;

        public Work(int l, int p) {
            lake = l;
            nextRain = p;
        }

        @Override
        public int compareTo(Work o) {
            return nextRain - o.nextRain;
        }
    }
}
