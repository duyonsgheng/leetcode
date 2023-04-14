package week.week_2023_03_05;

import java.util.Arrays;

/**
 * @ClassName Code_03_LeetCode_1168
 * @Author Duys
 * @Description
 * @Date 2023/3/30 9:37
 **/
// 最小生成树模板题
// 来自小红书、字节跳动
// 村里面一共有 n 栋房子
// 我们希望通过建造水井和铺设管道来为所有房子供水。
// 对于每个房子 i，我们有两种可选的供水方案：
// 一种是直接在房子内建造水井
// 成本为 wells[i - 1] （注意 -1 ，因为 索引从0开始 ）
// 另一种是从另一口井铺设管道引水
// 数组 pipes 给出了在房子间铺设管道的成本
// 其中每个 pipes[j] = [house1j, house2j, costj]
// 代表用管道将 house1j 和 house2j连接在一起的成本。连接是双向的。
// 请返回 为所有房子都供水的最低总成本 。
// 这道题很高频，引起注意
// 本身也不难，转化一下变成最小生成树的问题即可
// 测试链接 : https://leetcode.cn/problems/optimize-water-distribution-in-a-village/
public class Code_03_LeetCode_1168 {
    public static int MAXN = 10010;

    public static int[][] edges = new int[MAXN << 1][3];

    public static int esize;

    public static int[] father = new int[MAXN];

    public static int[] size = new int[MAXN];

    public static int[] help = new int[MAXN];

    public static void build(int n) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
            size[i] = 1;
        }
    }

    public static int find(int x) {
        int h = 0;
        while (x != father[x]) {
            help[h++] = x;
            x = father[x];
        }
        while (h > 0) {
            father[help[--h]] = x;
        }
        return x;
    }

    public static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa == fb) {
            return false;
        }
        if (size[fa] >= size[fb]) {
            father[fb] = fa;
            size[fa] += size[fb];
        } else {
            father[fa] = fb;
            size[fb] += size[fa];
        }
        return true;
    }

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        esize = 0;
        for (int i = 0; i < n; i++, esize++) {
            edges[esize][0] = 0;
            edges[esize][1] = i + 1;
            edges[esize][2] = wells[i];
        }
        for (int i = 0; i < pipes.length; i++, esize++) {
            edges[esize][0] = pipes[i][0];
            edges[esize][1] = pipes[i][1];
            edges[esize][2] = pipes[i][2];
        }
        // 根据代价排序
        Arrays.sort(edges, 0, esize, (a, b) -> a[2] - b[2]);
        build(n);
        int ans = 0;
        for (int i = 0; i < esize; i++) {
            // 能合并
            if (union(edges[i][0], edges[i][1])) {
                ans += edges[i][2];
            }
        }
        return ans;
    }
}
