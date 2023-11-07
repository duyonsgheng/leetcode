package custom.code_2023_08;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2146
 * @date 2023年08月31日
 */
// 2146. 价格范围内最高排名的 K 样物品
// https://leetcode.cn/problems/k-highest-ranked-items-within-a-price-range/
public class LeetCode_2146 {

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> deque = new LinkedList<>();
        deque.offerLast(new int[]{start[0], start[1]});
        // 按照题目顺序确定的优先级队列。。
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) ->
                a[3] == b[3] ? (a[2] == b[2] ? (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]) : a[2] - b[2]) : a[3] - b[3]
        );
        int step = 0;
        int count = 0;
        // 起点有可能也是商品，将起点加入！
        if (grid[start[0]][start[1]] >= pricing[0] && grid[start[0]][start[1]] <= pricing[1]) {
            //pq.add(new Node(start[0], start[1], grid[start[0]][start[1]], step));
            queue.add(new int[]{start[0], start[1], grid[start[0]][start[1]], step});
        }
        grid[start[0]][start[1]] = -1;
        while (!deque.isEmpty()) {
            count = deque.size();
            step++;
            for (; count > 0; --count) {
                int[] pos = deque.pollFirst();
                for (int[] dir : directions) {
                    int newX = pos[0] + dir[0], newY = pos[1] + dir[1];
                    // 下一个点出边界 / 障碍物 / 遍历过
                    if (newX < 0 || newY < 0 || newX >= m || newY >= n || grid[newX][newY] == 0 || grid[newX][newY] == -1) {
                        continue;
                    }
                    // 下一个点在价格区间内
                    if (grid[newX][newY] >= pricing[0] && grid[newX][newY] <= pricing[1]) {
                        queue.add(new int[]{newX, newY, grid[newX][newY], step});
                    }
                    // 将点置为-1表示遍历过
                    grid[newX][newY] = -1;
                    deque.offerLast(new int[]{newX, newY});
                }
            }
        }
        // 结果处理
        int size = Math.min(k, queue.size());
        for (int i = 0; i < size; i++) {
            int[] cur = queue.poll();
            res.add(new ArrayList<>(Arrays.asList(cur[0], cur[1])));
        }
        return res;
    }
}


