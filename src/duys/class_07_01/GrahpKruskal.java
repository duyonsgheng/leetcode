package duys.class_07_01;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * @ClassName GrahpKruskal
 * @Author Duys
 * @Description Kruskal最小生成树，要求无环，最小的边开始，使用并查集
 * @Date 2021/7/9 9:39
 **/
public class GrahpKruskal {

    /**
     * 使用并查集
     */
    public static class UnionFind {
        private Map<Node, Node> parentMap;
        private Map<Node, Integer> sizeMap;

        public UnionFind() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void init(Collection<Node> nodes) {
            parentMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findParent(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != parentMap.get(node)) {
                stack.add(node);
                node = parentMap.get(node);
            }
            while (!stack.isEmpty()) {
                // 所有遍历过程中遇到的节点。把他们的父设置同一个node
                parentMap.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isParent(Node a, Node b) {
            return findParent(a) == findParent(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aP = findParent(a);
            Node bP = findParent(b);
            if (aP == bP) {
                return;
            }
            int aS = sizeMap.get(aP);
            int bS = sizeMap.get(bP);
            if (aS >= bS) {
                parentMap.put(bP, aP);
                sizeMap.put(aP, aS + bS);
                sizeMap.remove(bP);
            } else {
                parentMap.put(aP, bP);
                sizeMap.put(bP, aS + bS);
                sizeMap.remove(aP);
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public static Set<Edge> kruskal(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.init(graph.nodes.values());
        // 小根堆，根据边的权重，越小的再上边
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        Set<Edge> r = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge poll = queue.poll();
            // 如果已经联通了，那么就不能算了
            if (unionFind.isParent(poll.from, poll.to)) {
                continue;
            }
            unionFind.union(poll.from, poll.to);
            r.add(poll);
        }
        return r;
    }
}
