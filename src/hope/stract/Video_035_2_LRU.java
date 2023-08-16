package hope.stract;

import java.util.HashMap;

/**
 * @author Mr.Du
 * @ClassName Video_035_2_LRU
 * @date 2023年08月16日
 */
// 146. LRU 缓存
// https://leetcode.cn/problems/lru-cache/
public class Video_035_2_LRU {
    // 双端链表+Map
    class LRUCache {

        private HashMap<Integer, DoubleNode> keyNodeMap;

        private DoubleList nodeList;

        private final int capacity;

        public LRUCache(int cap) {
            keyNodeMap = new HashMap<>();
            nodeList = new DoubleList();
            capacity = cap;
        }

        public int get(int key) {
            if (keyNodeMap.containsKey(key)) {
                DoubleNode ans = keyNodeMap.get(key);
                nodeList.moveNodeToTail(ans);
                return ans.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (keyNodeMap.containsKey(key)) {
                DoubleNode node = keyNodeMap.get(key);
                node.val = value;
                nodeList.moveNodeToTail(node);
            } else {
                if (keyNodeMap.size() == capacity) {
                    keyNodeMap.remove(nodeList.removeHead().key);
                }
                DoubleNode newNode = new DoubleNode(key, value);
                keyNodeMap.put(key, newNode);
                nodeList.addNode(newNode);
            }
        }

        class DoubleNode {
            int key, val;
            DoubleNode last, next;

            public DoubleNode(int k, int v) {
                key = k;
                val = v;
            }
        }

        class DoubleList {
            DoubleNode head, tail;

            public DoubleList() {
                head = null;
                tail = null;
            }

            public void addNode(DoubleNode node) {
                if (node == null) {
                    return;
                }
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    tail.next = node;
                    node.last = tail;
                    tail = node;
                }
            }

            public void moveNodeToTail(DoubleNode node) {
                if (tail == node) {
                    return;
                }
                if (head == node) {
                    head = head.next;
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

            public DoubleNode removeHead() {
                if (head == null) {
                    return null;
                }
                DoubleNode ans = head;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = ans.next;
                    ans.next = null;
                    head.last = null;
                }
                return ans;
            }
        }
    }

}
