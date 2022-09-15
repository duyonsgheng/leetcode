package duys.class_08_17;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SkipListMap
 * @Author Duys
 * @Description 跳表
 * @Date 2021/8/18 13:30
 **/
public class SkipListMap_01 {
    /**
     * 跳表：
     */

    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public List<SkipListNode<K, V>> nextNodes;

        public SkipListNode(K k, V v) {
            key = k;
            value = v;
            nextNodes = new ArrayList<>();
        }

        // node的key是否比otherKey小
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null) || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }

    public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5; // < 0.5 继续做，>=0.5 停
        private int size;
        private int maxLevel;
        private SkipListNode<K, V> head;

        public SkipListMap() {
            head = new SkipListNode<K, V>(null, null);
            head.nextNodes.add(null); // 0
            size = 0;
            maxLevel = 0;
        }

        // 从最高层到底层开始找，从左到右，直到找到小于key的位置，
        // 从最高层开始，一路找下去，
        // 最终，找到第0层的<key的最右的节点
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> cur = head;
            int level = maxLevel;
            while (level >= 0) { // 从上层往下层跳-直到最后一层，因为所有的数据都在第0层是有的
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        // 在level层里，如何往右移动
        // 现在来到的节点是cur，来到了cur的level层，在level层上，找到<key最后一个节点并返回
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {

            // 获取当前节点的下一层的节点
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = next.nextNodes.get(level);
            }
            return cur;

        }

        public boolean containsKey(K key) {
            if (key == null) return false;
            // 小于key的最后一个node
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            // 小于key的最后一个node的下一个，因为所有数据在第0层全部有
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        // 添加值
        public void put(K key, V value) {
            if (key == null) return;
            // 先找到第0层的小于key的最后一个节点（最右的一个）
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            // 说明key存在，是更新值的操作
            if (next != null && next.isKeyEqual(key)) {
                next.value = value;
            } else {
                size++;
                int newNodeLevel = 0;
                // 看看冲到第几层---随机玩儿-随机玩儿-随机玩儿
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                // 如果新得节点已经比之前的层数更高了，那第一个头节点也需要跟上，今次第一个头节点才这样做
                while (newNodeLevel > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                // 构建新得节点
                SkipListNode<K, V> newNode = new SkipListNode(key, value);
                for (int i = 0; i <= newNodeLevel; i++) {
                    // 内部的层数指针先指向空
                    newNode.nextNodes.add(null);
                }
                int curLevel = maxLevel;
                SkipListNode<K, V> pre = head;
                while (curLevel > 0) {
                    // 在每一层找到小于key最右的节点
                    pre = mostRightLessNodeInLevel(key, pre, curLevel);
                    // 比如当前新得节点是6层，之前总共是10层，那么我不需要7-10层的，所系跳过
                    if (curLevel <= newNodeLevel) {
                        // 我接在找到的那个小于我的key的后面，我的后面接找到那个小与我的最后一个node的下一个
                        newNode.nextNodes.set(curLevel, pre.nextNodes.get(curLevel));
                        pre.nextNodes.set(curLevel, newNode);
                    }
                    curLevel--;
                }
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.value : null;
        }

        public void remove(K key) {
            if (!containsKey(key)) {
                return;
            }
            size--;
            int curLevel = maxLevel;
            // 从头部开始
            SkipListNode<K, V> pre = head;
            while (curLevel >= 0) {
                pre = mostRightLessNodeInTree(key);
                SkipListNode<K, V> next = pre.nextNodes.get(curLevel);
                if (next != null && next.isKeyEqual(key)) {
                    // 如果next直接是要删除的key，那么直接把next的下一个赋给pre的当前层就好了
                    pre.nextNodes.set(curLevel, next.nextNodes.get(curLevel));
                }
                // 如果这一层删除了后，只剩下一个节点了，就是默认节点head了，那么没必要存在这一层了
                if (curLevel != 0 && pre == head && pre.nextNodes.get(curLevel) == null) {
                    maxLevel--;
                    head.nextNodes.remove(curLevel);
                }
                curLevel--;
            }
        }
    }
}
