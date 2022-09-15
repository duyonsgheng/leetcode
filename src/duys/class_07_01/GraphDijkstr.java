package duys.class_07_01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName GraphDijkstr
 * @Author Duys
 * @Description 单元路径最短的
 * @Date 2021/7/9 13:38
 **/
public class GraphDijkstr {

    /**
     * 在一张图中，需要张表，这张表，是从某一个点出发，所有的最短路径
     */

    public static HashMap<Node, Integer> dijkstr(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        // 自己到自己距离是0
        distanceMap.put(from, 0);
        // 已经挑选过的点
        Set<Node> chooseSet = new HashSet<>();
        // 这里选出来肯定是自己-from
        Node mindDistance = getMindDistance(distanceMap, chooseSet);
        while (mindDistance != null) {
            int distance = distanceMap.get(mindDistance);
            // 相邻的边
            for (Edge edge : mindDistance.edges) {
                Node to = edge.to;
                if (!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance + edge.weight);
                } else {
                    // 如果包含了，就算一个小的距离出来
                    distanceMap.put(to, Math.min(distanceMap.get(to), distance + edge.weight));
                }
            }
            // 每次都从map中选了一个最小的
            chooseSet.add(mindDistance);
            mindDistance = getMindDistance(distanceMap, chooseSet);
        }
        return distanceMap;
    }

    // 从距离表中选一个最小的，并且没有被选过
    public static Node getMindDistance(Map<Node, Integer> distanceMap, Set<Node> chooseSet) {
        Node mindNode = null;
        int mindDictce = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node key = entry.getKey();
            Integer value = entry.getValue();
            if (!chooseSet.contains(key) && value < mindDictce) {
                mindNode = key;
                mindDictce = value;
            }
        }
        return mindNode;
    }

    /*******************************************************************************************/

    // 这个记录的意思是-从开始节点（也就是下面的from节点 -到本节点 的距离）
    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    // 加强堆来一个
    // 加强堆四要素，元素，反向索引，堆大小，比较器
    public static class NodeHeap {
        //
        private Node[] nodes;
        // 反向索引，也就是该node在 node数组中的位置
        private HashMap<Node, Integer> indexMap;
        // 堆大小
        private int heapSize;
        // 本题特有的，从源节点出发，到key(node)的最短距离(value)，类似是一个比较器，用这个来进行比较两者之间的大小
        private HashMap<Node, Integer> distanceMap;

        public NodeHeap(int size) {
            nodes = new Node[size];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        private boolean isEntered(Node node) {
            return indexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && indexMap.get(node) != -1;
        }

        // 发现有一个节点node，从起源节点到该node的距离为distance
        // 判断需要更新补，需要就更新，没有就插入，不需要更新就忽略
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                // 如果更新了，因为我们的比较器是距离
                heapInsert(indexMap.get(node));
            }

            // 如果不在
            if (!isEntered(node)) {
                nodes[heapSize] = node;
                indexMap.put(node, heapSize);
                distanceMap.put(node, distance);
                heapInsert(heapSize++);
            }
        }

        public NodeRecord pop() {
            // 取走了堆顶的位置
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            //把最后一个位置的元素 补到堆顶
            swap(0, heapSize - 1);
            // 在索引位置上把最后一个位置置为-1，表示不存在了
            indexMap.put(nodes[heapSize - 1], -1);
            distanceMap.remove(nodes[heapSize - 1]);
            // 在实际存放元素的数组中干掉
            nodes[heapSize - 1] = null;
            // 把最后一个位置上的元素调整到首位来了，需要堆进行向下调整
            heapify(0, --heapSize);
            return nodeRecord;
        }

        // index位置开始向上调整正确
        public void heapInsert(int index) {
            // 向上调整呢，index开始，他的父节点(index -1 )/2
            while (distanceMap.get(index) < distanceMap.get(index - 1) / 2) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 从 index位置开始，大小是size的堆，向下调整正确
        public void heapify(int index, int size) {
            // 先找到左孩子
            int left = index * 2 + 1;
            while (left < size) {
                // 选择左右较小的孩子开始整
                // 如果我的两个孩子，谁小就拿谁的
                int small = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(left) ? left + 1 : left;

                // 孩子比较出了大小，现在轮到我和最小的孩子进行比较了
                small = distanceMap.get(small) < distanceMap.get(index) ? small : index;
                // 比较结束，发现index是最小的，完成调整
                if (small == index) {
                    break;
                }
                // 否则进行交换，然后继续
                swap(small, index);
                index = small;
                left = index * 2 + 1;
            }
        }

        public void swap(int index1, int index2) {
            // 首先索引位置交换
            indexMap.put(nodes[index1], index2);
            indexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    // 厉害的dijkstr算法，使用加强堆。
    public static HashMap<Node, Integer> dijkstrPlus(Node from, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(from, 0);
        HashMap<Node, Integer> res = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord pop = nodeHeap.pop();
            Node node = pop.node;
            int distance = pop.distance;
            for (Edge edge : node.edges) {
                // 这里 是需要把 form -> from的距离 + from 到to的距离。因为整张表是源节点到每一个节点的距离
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            res.put(node, distance);
        }
        return res;
    }


}
