package duys.class_08_17;

/**
 * @ClassName RedBlackTree_01
 * @Author Duys
 * @Description 红黑树
 * @Date 2021/8/19 17:29
 **/
public class RedBlackTree_01 {

    public static class RBTNode<T extends Comparable<T>> {
        public T value;
        public boolean color;
        public RBTNode<T> parent;
        public RBTNode<T> left;
        public RBTNode<T> right;

        public RBTNode(T value, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public RBTNode(T value) {
            this.value = value;
        }

        public boolean isLeftChild() {
            return this == this.parent.left;
        }

        public boolean isRightChild() {
            return this == this.parent.right;
        }

        public boolean isRoot() {
            return this.parent == null;
        }
    }

    public static class RedBlackTree<T extends Comparable<T>> {
        private RBTNode<T> root;
        private static final boolean RED = false;
        private static final boolean BLACK = true;

        // 对cur节点进行左旋
        private void leftRotate(RBTNode<T> cur) {
            RBTNode<T> right = cur.right;
            cur.right = right.left;
            if (right.left != null) {
                right.left.parent = cur;
            }
            right.parent = cur.parent;
            if (cur.parent == null) {
                this.root = right;
            } else {
                if (cur.parent.left == cur) { // 如果cur是他父亲节点的左孩子
                    cur.parent.left = right;
                } else {// 如果cur是他父亲节点的右孩子
                    cur.parent.right = right;
                }
            }
            right.left = cur;
            cur.parent = right;
        }

        private void rightRotate(RBTNode<T> cur) {
            if (cur == null) {
                return;
            }
            RBTNode<T> left = cur.left;
            // 把左孩子的右孩子给当前节点的左孩子
            cur.left = left.right;
            left.right = cur;
            left.parent = cur.parent;
            if (left.right != null) {
                left.right.parent = cur;
            }
            if (cur.parent == null) {
                this.root = left;
            } else if (cur.parent.left == cur) {
                cur.parent.left = left;
            } else {
                cur.parent.right = left;
            }
            cur.parent = left;
        }

        private void add(T value) {
            // 根节点如果为空，则默认黑色
            if (root == null) {
                root = new RBTNode<>(value);
                return;
            }
            RBTNode<T> cur = root;
            RBTNode<T> pre = root;
            int cmp = 0;
            while (cur != null) {
                pre = cur;
                cmp = value.compareTo(cur.value);
                if (cmp < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            RBTNode<T> newNode = new RBTNode<>(value, pre, null, null);
            if (cmp > 0) {
                pre.right = newNode;
            } else {
                pre.left = newNode;
            }
        }

        private RBTNode<T> parentOf(RBTNode<T> cur) {
            return cur == null ? null : cur.parent;
        }

        private RBTNode<T> grandfatherOf(RBTNode<T> cur) {
            return parentOf(parentOf(cur));
        }

        private RBTNode<T> leftOf(RBTNode<T> cur) {
            return cur == null ? null : cur.left;
        }

        private RBTNode<T> rightOf(RBTNode<T> cur) {
            return cur == null ? null : cur.right;
        }

        private boolean colorOf(RBTNode<T> cur) {
            return cur != null ? cur.color : BLACK;
        }

        private void setColor(RBTNode<T> cur, boolean c) {
            if (cur != null) {
                cur.color = c;
            }
        }

        private void fixAfterInsertion(RBTNode<T> cur) {
            // 调整的情况如下
            // 1. cur节点不是根节点，根节点的话不需要调整
            // 2. cur节点是根节点，那么只需要将根节点设置成红色
            // 3. cur节点的父节点和叔叔节点都是红色，这时候只需要将cur的父节点和叔叔节点设置成黑色，祖父节点设置成红色。然后将祖父节点进行递归处理
            // 4. cur节点叔叔节点是黑色，这时候需要旋转
            // 新增节点设置成红色
            cur.color = RED;

            while (cur != null && cur.parent != null && cur.parent.color == RED) {
                // cur的父节点是cur的祖父节点的左节点
                if (cur.parent == cur.parent.parent.left) {
                    // cur 的叔叔节点是右子节点
                    RBTNode<T> uncle = rightOf(grandfatherOf(cur));
                    if (colorOf(uncle) == RED) {
                        // 叔叔和父亲节点都是红色
                        setColor(parentOf(cur), BLACK);
                        setColor(uncle, BLACK);
                        setColor(grandfatherOf(cur), RED);
                        cur = grandfatherOf(cur);
                    } else {
                        // cur 的父亲节点是右节点
                        if (cur.isRightChild()) {
                            cur = parentOf(cur);
                            // cur和cur的父节点不在同一条直线链上，对cur的父亲节点进行左旋
                            leftRotate(cur);
                        }
                    }
                }
            }
        }
    }
}
