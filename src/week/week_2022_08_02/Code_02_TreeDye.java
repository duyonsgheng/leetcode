package week.week_2022_08_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_02_TreeDye
 * @Author Duys
 * @Description
 * @Date 2022/8/11 9:16
 **/
// 来自米哈游
// 给定一个正数n，表示有多少个节点
// 给定一个二维数组edges，表示所有无向边
// edges[i] = {a, b} 表示a到b有一条无向边
// edges一定表示的是一个无环无向图，也就是树结构
// 每个节点可以染1、2、3三种颜色
// 要求 : 非叶节点的相邻点一定要至少有两种和自己不同颜色的点
// 返回一种达标的染色方案，也就是一个数组，表示每个节点的染色状况
// 1 <= 节点数量 <= 10的5次方
public class Code_02_TreeDye {

    /**
     * 题意给出是无向图
     * 那么对于一个子节点大于=2的节点来说  向左 可以是 1 2 3 1 2 3交替 向右是 1 3 2 1 3 2
     * d  c  b  a  e  f  g  h
     * 1  3  2  1  3  2  1  3
     * 就能满足所有节点颜色交替
     */
    public static int[] rule1 = {1, 2, 3};
    public static int[] rule2 = {1, 3, 2};

    public static int[] dye(int n, int[][] edges) {
        //1.先用邻接表创建图
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            // 双向的
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // 需要找一个头节点，头节点需要满足至少有两个子节点
        int head = -1;
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() >= 2) {
                head = i;
                break;
            }
        }
        int[] colors = new int[n];
        if (head == -1) {// 说明只有两个节点
            Arrays.fill(colors, 1);
        } else {
            colors[head] = 1;
            // 左边的节点使用第一种方案染色，
            dfs(graph, graph.get(head).get(0), 1, rule1, colors);
            // 右边或者其他方向的节点使用第二种方案
            for (int i = 1; i < graph.get(head).size(); i++) {
                dfs(graph, graph.get(head).get(i), 1, rule2, colors);
            }
        }
        return colors;
    }

    // 当前的头是啥，当前是第几层，使用的什么规则
    // 层使用 0 1 2 对应方案中的下标
    public static void dfs(List<List<Integer>> graph, int head, int level, int[] rule, int[] colors) {
        colors[head] = rule[level % 3];
        for (int next : graph.get(head)) {
            if (colors[next] == 0) { // 当前节点没有被填过颜色，可以填颜色，被填过颜色了，说明可能是其他节点子孩子
                dfs(graph, next, level + 1, rule, colors);
            }
        }
    }
}
