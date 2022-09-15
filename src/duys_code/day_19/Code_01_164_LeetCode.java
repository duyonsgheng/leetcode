package duys_code.day_19;

import java.util.HashMap;

/**
 * @ClassName Code_01_164_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/lru-cache/
 * @Date 2021/11/3 14:29
 **/
public class Code_01_164_LeetCode {
    /**
     * 实现一个LRU - 最近最少使用策略
     * hashMap + 双端链表
     */
    static class LRUCache {
        private MyCache<Integer, Integer> cache;

        public LRUCache(int capacity) {
            cache = new MyCache<>(capacity);
        }

        public int get(int key) {
            Integer ans = cache.get(key);
            return ans == null ? -1 : ans;
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }

        static class MyCache<K, V> {
            private HashMap<K, Node<K, V>> keyNodeMap;
            private NodeDoubleLinkedList<K, V> nodeList;
            private final int capacity;

            public MyCache(int capacity) {
                keyNodeMap = new HashMap<>();
                nodeList = new NodeDoubleLinkedList<>();
                this.capacity = capacity;
            }

            public V get(K key) {
                if (keyNodeMap.containsKey(key)) {
                    Node<K, V> kvNode = keyNodeMap.get(key);
                    nodeList.moveNodeToTail(kvNode);
                    return kvNode.value;
                }
                return null;
            }

            public void put(K key, V value) {
                // 修改
                if (keyNodeMap.containsKey(key)) {
                    Node<K, V> kvNode = keyNodeMap.get(key);
                    kvNode.value = value;
                    nodeList.moveNodeToTail(kvNode);
                }
                // 新增
                else {
                    Node<K, V> node = new Node(key, value);
                    keyNodeMap.put(key, node);
                    nodeList.addNode(node);
                    // 超过容量就需要干掉最老的
                    if (keyNodeMap.size() == capacity + 1) {
                        Node<K, V> kvNode = nodeList.removeHead();
                        keyNodeMap.remove(kvNode.key);
                    }
                }
            }
        }

        // 实际的数据
        static class Node<K, V> {
            public K key;
            public V value;
            public Node<K, V> last;
            public Node<K, V> next;

            public Node(K k, V v) {
                this.key = k;
                this.value = v;
            }
        }

        // 这就是我们缓存中的双端队列。每一次操作的都会放到队尾，每次需要淘汰的都从队首淘汰
        static class NodeDoubleLinkedList<K, V> {
            private Node<K, V> head;
            private Node<K, V> tail;

            public NodeDoubleLinkedList() {
                head = null;
                tail = null;
            }

            // 把新得节点挂到尾巴上去
            public void addNode(Node<K, V> newNode) {
                if (newNode == null) {
                    return;
                }
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.next = newNode;
                    newNode.last = tail;
                    tail = newNode;
                }
            }

            // 把当前node的左右连好，然后当前node挂到最后去
            public void moveNodeToTail(Node<K, V> node) {
                if (node == tail) {
                    return;
                }
                // 整体是。先把当前节点踢出来
                // 然后左右连好
                // 最后把当前节点挂到最后去
                // 当前是头节点
                if (node == head) {
                    head = node.next;
                    head.last = null;
                } else {
                    node.last.next = node.next;
                    node.next.last = node.last;
                }
                node.last = tail;
                node.next = null;
                tail.next = node;
                tail = node;
            }

            public Node<K, V> removeHead() {
                if (head == null) {
                    return null;
                }
                Node<K, V> res = head;
                // 只有一个节点了
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = res.next;
                    res.next = null;
                    head.last = null;
                }
                return res;
            }
        }
    }


}
