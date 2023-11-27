package hope.graph;

import java.util.ArrayList;

/**
 * @author Mr.Du
 * @ClassName Code_01_CreateGraphByList
 * @date 2023年11月17日 10:09
 */
public class Code01_CreateGraphByList {
    public static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    public static void build(int n) {
        graph.clear();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static void directGraph(int[][] edges) {
        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
    }
}
