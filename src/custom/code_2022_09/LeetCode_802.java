package custom.code_2022_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_802
 * @Author Duys
 * @Description
 * @Date 2022/9/22 14:20
 **/
// 802. 找到最终的安全状态
public class LeetCode_802 {

    // 拓扑排序
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        // 反向图
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
        }
        // 没有出边，是安全的，一个点的出边全部连着安全节点的，也是安全的
        int[] ins = new int[n];
        for (int i = 0; i < n; i++) {
            for (int x : graph[i]) {
                res.get(x).add(i);
            }
            // 入度记录，反向的，那么当前节点的入度就是原来的出度长度
            ins[i] = graph[i].length;
        }
        // 入度为0的加入，从入度为0的开始
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (ins[i] == 0) {
                queue.offer(i);
            }
        }
        // graph = [[1,2],[2,3],[5],[0],[5],[],[]]
        //            0     1    2   3   4  5  6
        // 5 6
        // 弹出6
        // 弹出5 把 2拉进来
        // 把2拉进来，然后依次往后传递
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 从反向图种拿到，当前节点可以从哪些点过来
            for (int last : res.get(cur)) {
                if (--ins[last] == 0) {
                    queue.offer(last);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (ins[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
