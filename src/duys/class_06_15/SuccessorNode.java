package duys.class_06_15;

/**
 * @ClassName SuccessorNode
 * @Author Duys
 * @Description 后继节点问题提，后续几点的定义：再二叉树的中序遍历中，x节点的后一个节点就是x的后继节点
 * @Date 2021/6/22 14:31
 **/
public class SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 随便给出一个节点，求这个节点的后继节点
     * 1.中序遍历中，如果该节点有右子树，那么该节点的后继节点是该节点的右子树的最左节点
     * 2.中序遍历中，如果该节点没有右子树，那么需要找到父级以上的节点，并且当前节点是其父节点的右孩子节点，
     * 我们要找的是，其中有一个父亲节点满足，他的左孩子是当前节点的父节点（也就是当前节点需要再某一级父节点上是左孩子的
     * 分支下的节点，这样才能有后继节点，否则没有）
     *
     * @param node
     * @return
     */
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        // 如果有右孩子，那么只需要找到当前右孩子的最左节点就好了
        if (node.right != null) {
            return getLeftNode(node.right);
        }
        // 如果没有右孩子
        else {
            // 向上串，找到父级
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                // 如果满足了，继续往上走
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * 一直找当前节点最左孩子节点
     *
     * @param node
     * @return
     */
    public static Node getLeftNode(Node node) {
        if (node == null)
            return node;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
