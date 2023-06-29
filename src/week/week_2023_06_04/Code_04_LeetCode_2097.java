package week.week_2023_06_04;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName Code_04_LeetCode_2097
 * @date 2023年06月29日
 */
//2097.合法重新排列数对
// https://leetcode.cn/problems/valid-arrangement-of-pairs/
public class Code_04_LeetCode_2097 {

    // 欧拉路径问题
    public int[][] validArrangement(int[][] pairs) {
        // 建图
        Map<Integer, LinkedList<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> degrees = new HashMap<>();
        for (int[] pair : pairs) {
            graph.putIfAbsent(pair[0], new LinkedList<>());
            graph.putIfAbsent(pair[1], new LinkedList<>());
            degrees.putIfAbsent(pair[0], 0);
            degrees.putIfAbsent(pair[1], 0);
        }
        for (int[] pair : pairs) {
            // 0 到 1有一条边
            graph.get(pair[0]).add(pair[1]);
            // 出度+1
            degrees.put(pair[0], degrees.get(pair[0]) + 1);
            // 入度-1
            degrees.put(pair[1], degrees.get(pair[1]) - 1);
        }
        // 找到开始点
        int from = pairs[0][0];
        for (int cur : degrees.keySet()) {
            if (degrees.get(cur) == 1) {
                from = cur;
                break;
            }
        }
        List<int[]> record = new ArrayList<>();
        dfs(from, graph, record);
        int n = record.size();
        int[][] ans = new int[n][2];
        for (int i = n - 1, j = 0; j < n; j++, i--) {
            ans[i] = record.get(j);
        }
        return ans;
    }

    // 欧拉路 : 从图中某个点出发，遍历整个图，每条边通过且只通过一次
    // 欧拉回路 : 起点和终点相同的欧拉路
    // 怎么判断是否存在欧拉路
    // 1) 判断联通性，用dfs或者并查集来检查
    // 2) 无向连通图存在欧拉路的判断
    // 在无向图中 : 一个点上连接边的数量，称为这个点的度数，
    // 度数为奇数，称这个点为奇点；度数为偶数，称这个点为偶点。
    // 如果图中的点都是偶点，则存在欧拉回路，任意点都可以成为起点或终点
    // 如果图中只有两个奇点，则存在欧拉路，其中一个奇点是起点，另一个是终点
    // 3) 有向连通图存在欧拉路的判断
    // 在有向图中 : 分为出度和入度，出度记为+1，入度记为-1，
    // 那么每个点自己的入度与出度相加后的值，叫做该点的度数
    // 一个有向图存在欧拉回路的条件 :
    // 必须该图中所有点的度数为0，起点可以为任意点
    // 一个有向图存在欧拉路的条件 :
    // 只有一个点度数为+1，只有一个点度数为-1，其他点度数为0
    // 并且起点是度数+1的点，终点是度数-1的点
    // 如上的判断，非常容易写代码，只要了解图结构，代码都不难，不再赘述
    // 本题目既有有向图欧拉路的判断，但重点是欧拉路的第二个常考类型 :
    // 怎么输出一个欧拉路
    // 实在没有什么难度，就是利用剪枝的dfs，代码硬记，也没多少
    // 获得欧拉通路
    // 这里一定得是LinkedList
    public void dfs(int from, Map<Integer, LinkedList<Integer>> graph, List<int[]> record) {
        LinkedList<Integer> next = graph.get(from);
        while (!next.isEmpty()) {
            int to = next.poll();
            dfs(to, graph, record);
            record.add(new int[]{from, to});
        }
    }
}