package custom.code_2022_07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_310
 * @Author Duys
 * @Description
 * @Date 2022/7/14 17:13
 **/
//310. 最小高度树
public class LeetCode_310 {

    /**
     * 思考 给的数据范围是 10^4 如果每一个点为头跑一次dfs，那么是n^2的解答，超时
     * 那么从四周往中间围
     */

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(n - 1);
            return ans;
        }
        // 出度
        int[] out = new int[n];
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            out[edge[0]]++;
            out[edge[1]]++;
            // 无向图，需要两条边
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (out[i] == 1) {
                queue.offer(i);
            }
        }
        // 从出度为1开始
        while (!queue.isEmpty()) {
            // 按曾遍历
            ans = new ArrayList<>();
            int tmp = queue.size();
            for (int i = 0; i < tmp; i++) {
                int cur = queue.poll();
                ans.add(cur);
                // 拉出下一层
                for (int next : map.get(cur)) {
                    // 出度-1，因为当前cur已经弹出了
                    out[next]--;
                    if (out[next] == 1) {
                        queue.offer(next);
                    }
                }
            }
        }
        return ans;
    }
}
