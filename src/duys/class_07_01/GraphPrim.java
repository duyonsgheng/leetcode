package duys.class_07_01;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName GraphPrim
 * @Author Duys
 * @Description 最小生成树-Prim算法，用点去解锁相邻的边，边又去解锁相邻的点
 * @Date 2021/7/9 10:53
 **/
public class GraphPrim {
    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边，进入小根堆
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        // 解锁的点进入set
        Set<Node> set = new HashSet<>();

        // 结果
        Set<Edge> result = new HashSet<>();

        // 随便找一个节点开始
        for (Node node : graph.nodes.values()) {
            if (set.contains(node)) {
                continue;
            }
            set.add(node);
            // 开始解锁所有的边
            for (Edge edge : node.edges) {
                queue.add(edge);
            }
            while (!queue.isEmpty()) {
                // 先弹出一个最小的边
                Edge poll = queue.poll();
                // 直接相邻的点
                Node to = poll.to;
                if (set.contains(to)) {
                    continue;
                }
                // 如果相邻的点还没有被扫描过
                set.add(to);
                // 要了这条边
                result.add(poll);
                // 继续去感染他的相邻边
                for (Edge t : to.edges) {
                    queue.add(t);
                }
            }
        }
        return result;
    }

    // graph[i][j] 表示i 到 j的距离
    // 返回最小连通图的路径和
    public static int prim(int[][] graph) {
        int size = graph.length;
        int dictSize[] = new int[size];
        boolean visit[] = new boolean[size];
        //先来到0位置
        visit[0] = true;
        // 初始化
        for (int i = 0; i < size; i++) {
            dictSize[i] = graph[0][i];
        }
        //
        int sum = 0;
        for (int i = 0; i < size; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && dictSize[j] < min) {
                    min = dictSize[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {
                return sum;
            }
            visit[minIndex] = true;
            sum += min;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && dictSize[j] > graph[minIndex][j]) {
                    dictSize[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }

}
