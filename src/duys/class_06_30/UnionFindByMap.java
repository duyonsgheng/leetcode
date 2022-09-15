package duys.class_06_30;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName UnionFindMap
 * @Author Duys
 * @Description TODO
 * @Date 2021/6/30 17:24
 **/
public class UnionFindByMap<V> {


    public HashMap<V, Node<V>> nodes;
    public HashMap<Node<V>, Node<V>> parents;
    public HashMap<Node<V>, Integer> sizeMap;

    public UnionFindByMap(List<V> values) {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();
        for (V v : values) {
            Node<V> value = new Node<>(v);
            // 初始时，自己是自己父，自己的节点个数加+1
            nodes.put(v, value);
            parents.put(value, value);
            sizeMap.put(value, 1);
        }
    }

    public Node<V> findParent(Node<V> cur) {
        // 路径压缩
        Stack<Node<V>> path = new Stack<>();
        while (cur != parents.get(cur)) {
            path.push(cur);
            cur = parents.get(cur);
        }
        while (!path.isEmpty()) {
            parents.put(path.pop(), cur);
        }
        return cur;
    }

    public void union(V a, V b) {
        Node<V> aH = findParent(nodes.get(a));
        Node<V> bH = findParent(nodes.get(b));
        if (aH != bH) {
            int asize = sizeMap.get(aH);
            int bsize = sizeMap.get(bH);
            Node<V> big = asize >= bsize ? aH : bH;
            Node<V> small = big == aH ? bH : aH;
            parents.put(small, big);
            sizeMap.put(big, asize + bsize);
            sizeMap.remove(small);
        }
    }

    public int getStes() {
        return sizeMap.size();
    }


    public static class Node<V> {

        V value;

        public Node(V v) {
            value = v;
        }

    }

}
