package duys.class_08_17;

/**
 * @ClassName SizeBalancedTreeMap
 * @Author Duys
 * @Description
 * @Date 2021/8/18 13:15
 **/
public class SizeBalancedTreeMap_01 {
    /**
     * sbT 在删除的时候不做调整，只在添加的时候调整，所以在删除的时候，哪怕删成了一个链表，那么最大的还是O(logN) N -是没删除之前的最大节点数
     */
    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public SBTNode<K, V> l;
        public SBTNode<K, V> r;
        public int size; // 不同的key的数量

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
        public SBTNode<K, V> root;

        // 有序表该有的，实现类也应该有，
        // 左旋
        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return rightNode;
        }

        // 右旋
        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return leftNode;
        }

        // t调整树的平衡
        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;

            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            /**
             *  1.LL型，全部挂在左树上了，这时候只需要一次右旋
             *  2.LR型，全部挂在左树上得右节点，左树局部左旋，然后整棵树右旋
             *  3.RR型，全部挂在了右树上了，这时候只需要一次左旋
             *  4.RL型，全部挂在右树上得左节点，右树局部进行右旋，然后整棵树左旋
             */
            // 也是四种违规
            // 第一种：LL 型
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                // 有两个节点的子节点发生了变化，需要递归来调整发生了变化了的节点
                // 右旋的时候，cur节点和cur的右节点
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            // 第二种：LR型
            else if (leftRightSize > rightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                // 哪些节点的子节点发生了变化
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            // 第三种：RR型
            else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }
            // 第四种：RL型
            else if (rightLeftSize > leftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SBTNode<>(key, value);
            }
            cur.size++;
            if (key.compareTo(cur.key) < 0) {
                cur.l = add(cur.l, key, value);
            } else {
                cur.r = add(cur.r, key, value);
            }
            return maintain(cur);
        }

        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) < 0) {
                cur.l = delete(cur.l, key);
            } else if (key.compareTo(cur.key) > 0) {
                cur.r = delete(cur.r, key);
            } else {
                // 还是四种情况
                // 无左 无右
                if (cur.l == null && cur.r == null) {
                    cur = null;
                }
                // 有左 无右
                else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                }
                // 无左 有右
                else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                }
                // 有左 有右
                else if (cur.l != null && cur.r != null) {
                    SBTNode<K, V> pre = null;
                    SBTNode<K, V> des = cur.r;
                    des.size--;
                    while (des != null) {
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    if (pre != null) {
                        // 把cur右树上的最左节点拿到cur来，进行替换
                        pre.l = des.r;
                        des.r = cur.r;
                    }
                    des.l = cur.l;
                    des.size = des.l.size + (des.r != null ? des.r.size : 0) + 1;
                    cur = des;
                }
            }
            // 不做调整
            return cur;
        }

        private SBTNode<K, V> findLastIndex(K key) {
            SBTNode<K, V> pre = root;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return pre;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            SBTNode<K, V> lastIndex = findLastIndex(key);
            if (lastIndex != null && key.compareTo(lastIndex.key) == 0) {
                lastIndex.value = value;
            } else {
                root = add(root, key, value);
            }
        }

        public boolean containKey(K key) {
            if (key == null) {
                return false;
            }
            SBTNode<K, V> lastIndex = findLastIndex(key);
            return lastIndex != null && key.compareTo(lastIndex.key) == 0;
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containKey(key)) {
                root = delete(root, key);
            }
        }
    }

}
