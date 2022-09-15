package duys.class_08_17;

/**
 * @ClassName AVLTreeMap
 * @Author Duys
 * @Description 有序表 （O(logN)）
 * @Date 2021/8/17 15:09
 **/
public class AVLTreeMap_01 {
    // 搜索二叉树：对于每一个树的非叶子节点来说，左边的节点都比自己小，右边的节点都比自己大
    // 平衡搜索二叉树：搜索二叉树的左旋和右旋，变成平衡搜索二叉树
    // 搜索二叉树的左旋和右旋，变成平衡搜索二叉树

    // AVL树：任何一个节点左树的高度和右树的高度差不超过1(<2)

    /**
     * 1.增加简单
     * 2.删除麻烦：分4种情况
     * 2.1 要删除的节点无子节点
     * 2.2 要删除的节点有左无右
     * 2.3 要删除的节点无左有右
     * 2.4 要删除的节点有左有右(要删除的接节点右孩子的左得不能再左得节点替换)（也可以用左树上得最右节点替换）
     */

    /**
     * avl树违规类型：
     * 1.LL型，全部挂在左树上了，这时候只需要一次右旋
     * 2.LR型，全部挂在左树上得右节点，左树局部左旋，然后整棵树右旋
     * 3.RR型，全部挂在了右树上了，这时候只需要一次左旋
     * 4.RL型，全部挂在右树上得左节点，右树局部进行右旋，然后整棵树左旋
     *
     *
     * 扩展：
     * 1.LL 和 LR 同时存在得时候，那么按照LL型调整
     * 2.RR 和 RL 同时存在得时候，那么按照RR型调整
     */

    /**
     * 加入一个节点后，从当前加入节点开始，往上每一个节点都查询属于哪一种违规、
     * 删除一个节点，分情况讨论。
     * 左右孩子只有一个的时候，也从删除位置的节点开始，往上开始
     * 左右孩子都有得时候，从替代的哪一个节点(后继节点原来的位置)开始往上
     */

    public static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public AVLNode<K, V> l;
        public AVLNode<K, V> r;
        private int h;

        public AVLNode(K key, V value) {
            k = key;
            v = value;
            h = 1;
        }
    }

    public static class AVLTreeMap<K extends Comparable<K>, V> {
        private AVLNode<K, V> root;
        private int size;

        public AVLTreeMap() {
            root = null;
            size = 0;
        }

        // 在cur为根节点进行右旋
        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            // 当前节点的左节点
            AVLNode<K, V> left = cur.l;
            // 把cur左节点的右节点的给cur节点的左孩子
            cur.l = left.r;
            // 把当前cur节点挂到left的右边去
            left.r = cur;
            // 调整高度
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            left.h = Math.max(left.l != null ? left.l.h : 0, left.r != null ? left.r.h : 0) + 1;
            // 返回新得头部
            return left;
        }

        // 左旋
        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            // 当前节点的左节点
            AVLNode<K, V> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            // 调整高度
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            right.h = Math.max(right.l != null ? right.l.h : 0, right.r != null ? right.r.h : 0) + 1;
            // 返回新得头部
            return right;
        }

        // 检查树是否违规
        // 左树何右树的高度相差不超过1 (<=1)
        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftHeight = cur.l == null ? 0 : cur.l.h;
            int rightHeight = cur.r == null ? 0 : cur.r.h;
            // 违规了，看看属于哪一种违规
            if (Math.abs(leftHeight - rightHeight) > 1) {
                // 左树高度大于右树高度
                if (leftHeight > rightHeight) {
                    int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                    int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
                    // 既有LL 也有 LR 违规  注意是 >= 都按照 LL的进行处理
                    if (leftLeftHeight >= leftRightHeight) {
                        // 一次右旋搞定
                        cur = rightRotate(cur);
                    } else {
                        // LR型违规
                        // 先左树进行左旋，然后整体右旋
                        cur.l = leftRotate(cur.l);
                        cur = rightRotate(cur);
                    }
                }
                // 右树高度大于左树高度
                else {
                    int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
                    int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                    // 同样既有RR 也有RL违规，按照RR的进行处理
                    if (rightRightHeight >= rightLeftHeight) {
                        cur = leftRotate(cur);
                    } else {
                        cur.r = rightRotate(cur.r);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
        }

        // 在cur为头的节点上增加key和value
        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<>(key, value);
            }
            // 看看key在左树还是右树
            if (key.compareTo(cur.k) < 0) {
                cur.l = add(cur.l, key, value);
            } else {
                cur.r = add(cur.r, key, value);
            }
            cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            // 添加完了后，要进行整棵树的平衡调整
            return maintain(cur);
        }

        // 删除
        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            //
            if (key.compareTo(cur.k) > 0) {
                cur.r = delete(cur.r, key);
            } else if (key.compareTo(cur.k) < 0) {
                cur.l = delete(cur.l, key);
            }
            // 找到要删除的节点了，分为四种情况
            else {
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l != null && cur.r == null) {
                    cur = cur.l;
                } else if (cur.l == null && cur.r != null) {
                    cur = cur.r;
                } else {
                    AVLNode<K, V> des = cur.r;
                    // 右节点的最左节点
                    while (des != null) {
                        des = des.l;
                    }
                    // 先在右树上删除右树的最左节点(要替代当前的那个后继节点),已经包含了右子树得调平衡了
                    cur.r = delete(cur.r, des.k);
                    des.l = cur.l;
                    des.r = cur.r;
                    // 替换当前要删除的节点
                    cur = des;
                }
            }
            if (cur != null) {
                cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
            }
            return maintain(cur);
        }

        // 查询key节点
        private AVLNode<K, V> findLastIndex(K key) {
            AVLNode<K, V> pre = root;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                // 找到了
                if (key.compareTo(cur.k) == 0) {
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else if (key.compareTo(cur.k) > 0) {
                    cur = cur.r;
                }
            }
            return pre;
        }

        // 查询小于K的最后一个节点
        private AVLNode<K, V> findLastNoSmallIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    ans = cur;
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return ans;
        }

        // 查询大于key的最后一个节点
        private AVLNode<K, V> findLastNoBigIndex(K key) {
            AVLNode<K, V> ans = null;
            AVLNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                } else {
                    ans = cur;
                    cur = cur.r;
                }
            }
            return ans;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            AVLNode<K, V> lastIndex = findLastIndex(key);
            if (lastIndex != null && key.compareTo(lastIndex.k) == 0) {
                lastIndex.v = value;
            } else {
                size++;
                root = add(root, key, value);
            }
        }

        public boolean containKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastIndex = findLastIndex(key);
            return lastIndex != null && key.compareTo(lastIndex.k) == 0;
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastIndex = findLastIndex(key);
            if (lastIndex != null && key.compareTo(lastIndex.k) == 0) {
                return lastIndex.v;
            }
            return null;
        }
    }
}
