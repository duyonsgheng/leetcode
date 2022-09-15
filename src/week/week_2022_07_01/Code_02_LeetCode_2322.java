package week.week_2022_07_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_02_LeetCode_2322
 * @Author Duys
 * @Description
 * @Date 2022/7/7 17:29
 **/
// https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree/
// 给定一个棵树
// 树上每个节点都有自己的值，记录在数组nums里
// 比如nums[4] = 10，表示4号点的值是10
// 给定树上的每一条边，记录在二维数组edges里
// 比如edges[8] = {4, 9}表示4和9之间有一条无向边
// 可以保证输入一定是一棵树，只不过边是无向边
// 那么我们知道，断掉任意两条边，都可以把整棵树分成3个部分。
// 假设是三个部分为a、b、c
// a部分的值是：a部分所有点的值异或起来
// b部分的值是：b部分所有点的值异或起来
// c部分的值是：c部分所有点的值异或起来
// 请问怎么分割，能让最终的：三个部分中最大的异或值 - 三个部分中最小的异或值，最小
// 返回这个最小的差值
public class Code_02_LeetCode_2322 {

    // dfn 序
    public static int ctl;

    public static int minimumScore(int[] nums, int[][] edges) {
        //
        int n = nums.length;
        // 建立图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 无向图
        for (int[] arr : edges) {
            graph.get(arr[0]).add(arr[1]);
            graph.get(arr[1]).add(arr[0]);
        }
        // 每个编号的dfn编号是多少
        int[] dfn = new int[n];
        // 每i点为头的树的整体亦或结果
        int[] xor = new int[n];
        //以i为头的树有几个节点
        int[] size = new int[n];
        ctl = 1;
        // 认为整棵树从0号节点开始
        dfs(nums, graph, 0, dfn, xor, size);
        int ans = Integer.MAX_VALUE, m = edges.length, cut1, cut2, pre, pos, part1, patr2, part3, max, min;
        // 当前选择了一条边，从下一个位置开始选择另外一条边
        for (int i = 0; i < m; i++) {
            // dfn 谁大，谁就是删掉之后的树的头！cut1
            cut1 = dfn[edges[i][0]] < dfn[edges[i][1]] ? edges[i][1] : edges[i][0];
            for (int j = i + 1; j < m; j++) {
                cut2 = dfn[edges[j][0]] < dfn[edges[j][1]] ? edges[j][1] : edges[j][0];
                // 谁在前面
                pre = dfn[cut1] > dfn[cut2] ? cut2 : cut1;
                pos = pre == cut1 ? cut2 : cut1;

                part1 = xor[pos];
                // 看看是哪一种分裂情况，是否pos是pre的子树分裂出去的
                if (dfn[pos] < dfn[pre] + size[pre]) { // pos为头的树是pre的子树
                    patr2 = xor[pre] ^ xor[pos];
                    part3 = xor[0] ^ xor[pre];
                } else {// pos为头的树不是pre的子树
                    patr2 = xor[pre];
                    part3 = xor[0] ^ xor[pre] ^ xor[pos];
                }
                max = Math.max(Math.max(part1, part3), patr2);
                min = Math.min(Math.min(part1, part3), patr2);
                ans = Math.min(ans, max - min);
            }
        }
        return ans;
    }

    // 跑一个深度优先，每一个节点为头
    public static void dfs(int[] nums, List<List<Integer>> graph, int cur, int[] dfn, int[] xor, int[] size) {
        dfn[cur] = ctl++;
        xor[cur] = nums[cur];
        size[cur] = 1;
        for (int next : graph.get(cur)) {
            if (dfn[next] != 0) {
                continue;
            }
            dfs(nums, graph, next, dfn, xor, size);
            xor[cur] ^= xor[next];
            size[cur] += size[next];
        }
    }
}
