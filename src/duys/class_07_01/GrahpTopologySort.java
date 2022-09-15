package duys.class_07_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @ClassName GrahpTopologySort
 * @Author Duys
 * @Description 程序发布/文件编译类型的题目，求图的拓扑序
 * @Date 2021/7/8 11:29
 **/
public class GrahpTopologySort {
    /**
     * 思路，例如文件的编译顺序，那么可以考虑。没有谁依赖的，可以最先编译，
     * 没有谁依赖的意思就是入度为0，那么，编译了入度为0的，就在相邻节点中把入度减去1，再在相邻节点中寻找入度为0的
     * 如此往复操作，整个流程结束
     */
    public static List<Node> sortedTopology(Graph graph) {

        // 节点  - 节点剩余的入度
        Map<Node, Integer> inMap = new HashMap<>();

        // 只有入度为0的才能进入此队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node poll = zeroInQueue.poll();
            result.add(poll);
            for (Node node : poll.nexts) {
                inMap.put(node, inMap.get(node) - 1);
                if (inMap.get(node) - 1 == 0) {
                    zeroInQueue.add(node);
                }
            }
        }
        return result;
    }
}
