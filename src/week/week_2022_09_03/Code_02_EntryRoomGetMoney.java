package week.week_2022_09_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_02_EntryRoomGetMoney
 * @Author Duys
 * @Description
 * @Date 2022/9/22 9:24
 **/
// 来自美团
// 某天小美进入了一个迷宫探险，根据地图所示，这个迷宫里有无数个房间
// 序号分别为1、2、3、...入口房间的序号为1
// 任意序号为正整数x的房间，都与序号 2*x 和 2*x + 1 的房间之间各有一条路径
// 但是这些路径是单向的，即只能从序号为x的房间去到序号为 2*x 或 2*x+1 的房间
// 而不能从 2*x 或 2*x+1 的房间去到序号为x的房间
// 在任何时刻小美都可以选择结束探险并离开迷宫，但是离开之后将无法再次进入迷宫
// 小美还提前了解了迷宫中宝藏的信息
// 已知宝藏共有n个，其中第i个宝藏在序号为pi的房间，价值为wi
// 且一个房间中可能有多个宝藏
// 小美为了得到更多的宝藏，需要精心规划路线，她找到你帮忙
// 想请你帮她计算一下，能获得的宝藏价值和最大值为多少
// 第一行一个正整数n，表示宝藏数量。
// 第二行为n个正整数p1, p2,...... pn，其中pi表示第 i 个宝藏在序号为pi的房间。
// 第三行为n个正整数w1, w2,...... wn，其中wi表示第i个宝藏的价值为wi。
// 1 <= n <= 40000, 1 <= pi < 2^30, 1 <= wi <= 10^6。
public class Code_02_EntryRoomGetMoney {

    public static int maxMoney(int n, int[] p, int[] w) {
        int[][] romes = new int[n][2];// romes[i][0] -房间号 romes[i][1] 宝藏的价值
        for (int i = 0; i < n; i++) {
            romes[i][0] = p[i];
            romes[i][1] = w[i];
        }
        // 根据房间号排个序，房间号小的再前面
        Arrays.sort(romes, (a, b) -> a[0] - b[0]);
        // 根据题意：整个流程是一棵树，那么房间号大的/2看看能不能找到之前的房间号，
        Map<Integer, Integer> count = new HashMap<>();
        List<List<Integer>> graph = new ArrayList<>();
        // 建图
        // 把宝藏所在的房间所在的那些路给挂好，最差情况，n个宝藏，n条路
        for (int i = 0; i < n; i++) {
            int to = romes[i][0];
            while (to > 0) {
                if (count.containsKey(to)) {
                    graph.get(to).add(i);
                    break;
                } else {
                    // 依次除以2
                    to >>= 1;
                }
            }
            graph.add(new ArrayList<>());
            if (!count.containsKey(romes[i][0])) {
                count.put(romes[i][0], i);
            }
        }
        //从首节点开始遍历哪些已经挂在一起的链路
        int ans = 0;
        int[] dp = new int[n];
        // 这些宝藏所在的路
        for (int i = n - 1; i >= 0; i--) {
            int post = 0;
            for (int next : graph.get(i)) {
                // 如果多条支路，每一次选大的走
                if (romes[next][0] == romes[i][0]) {
                    dp[i] += dp[next];
                } else {
                    post = Math.max(post, dp[next]);
                }
            }
            // 每一个支路汇总
            dp[i] += post + romes[i][1];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
