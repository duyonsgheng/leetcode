package custom.code_2023_08;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2049
 * @date 2023年08月03日
 */
// 2049. 统计最高分的节点数目
// https://leetcode.cn/problems/count-nodes-with-the-highest-score/
public class LeetCode_2049 {
    // 拓扑排序
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        int[] out = new int[n];// 出度
        for (int i = 1; i < n; i++) {
            out[parents[i]]++;
        }
        // 出度为0 的
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (out[i] == 0) {
                deque.addLast(i);
            }
        }
        int[] l = new int[n]; // 节点i的左子节点数
        int[] r = new int[n]; // 节点i的右子节点数
        // 树 从下往上
        while (!deque.isEmpty()) {
            int cur = deque.pollFirst();
            int p = parents[cur];
            out[p]--;
            // 看看当前节点是父的左孩子还是右孩子
            if (l[p] == 0) {
                l[p] = l[cur] + r[cur] + 1;
            } else {
                r[p] = l[cur] + r[cur] + 1;
            }
            if (out[p] == 0 && p != 0) {
                deque.addLast(p);
            }
        }
        long max = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long cur = Math.max(l[i], 1) * Math.max(r[i], 1);
            if (i != 0) {
                cur *= (n - (l[i] + r[i] + 1));
            }
            if (cur > max) {
                max = cur;
                ans = 1;
            } else if (cur == max) {
                ans++;
            }
        }
        return ans;
    }
}
