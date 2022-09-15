package duys.class_06_30;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName UnionFind
 * @Author Duys
 * @Description 并查集, 利用map实现
 * @Date 2021/6/30 10:37
 **/
public class UnionFindCommon_01 {
    // 并查集。
    public static class Node<V> {
        public V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {

        // 元素封装成Node节点的关系表
        public HashMap<V, Node<V>> nodes;
        // 元素的父级节点
        public HashMap<Node<V>, Node<V>> parents;
        // 代表节点下的，所有节点数
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v : values) {
                Node<V> curNode = new Node<>(v);
                nodes.put(v, curNode);
                // 初始的时候，自己就是自己的父级节点
                parents.put(curNode, curNode);
                // 当前自己是自己的代表节点，所以节点个数为1
                sizeMap.put(curNode, 1);
            }
        }

        // 寻找与自己的节点相关的代表节点，也就是往上得不能再往上得节点
        public Node<V> findParent(Node<V> cur) {
            // 这里使用栈，进行路径压缩。把自己寻找父级节点这个链路上所有的节点，全部都直接挂到代表节点下
            Stack<Node<V>> path = new Stack<>();
            // 一直往上找到代表节点
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // 开始进行路径压缩
            while (!path.isEmpty()) {
                // 沿途经过的节点，全部挂到代表节点下去
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        // 返回两个元素是不是再同一个代表节点下
        public boolean isSameSet(V a, V b) {
            return findParent(nodes.get(a)) == findParent(nodes.get(b));
        }

        // 合并两个元素，包括他们所在的所有节点，就是节点数少的那一个代表节点的重定向
        public void union(V a, V b) {
            Node<V> aHead = findParent(nodes.get(a));
            Node<V> bHead = findParent(nodes.get(b));
            if (aHead != bHead) {
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                Node<V> big = aSize >= bSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, bSize + aSize);
                sizeMap.remove(small);
            }
        }
    }
}
