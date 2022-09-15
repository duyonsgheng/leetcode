package duys_code.day_10;

/**
 * @ClassName Code_04_TreeToNode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 * @Date 2021/10/14 17:01
 **/
public class Code_04_TreeToNode {
    /**
     * 给定一棵搜索二叉树头节点，转化成首尾相接的有序双向链表
     */
    // 二叉树递归套路
    public static Node treeToDoublyList(Node head) {
        if (head == null) {
            return null;
        }
        Info process = process(head);
        // 首尾相接
        process.end.right = process.start;
        process.start.left = process.end;
        return process.start;
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(null, null);
        }
        Info linfo = process(x.left);
        Info rinfo = process(x.right);
        // 如果我的左孩子信息得结束节点不为空，那么结束节点得右节点挂我

        // 把自己加到中间去。并且指定左右指针分别是：
        // 左边信息的结束的右节点挂到当前x
        if (linfo.end != null) {
            linfo.end.right = x;
        }
        // x的left是l左信息得结束；
        x.left = linfo.end;
        // 右边信息的开始节点的做指针挂到x
        if (rinfo.start != null) {
            rinfo.start.left = x;
        }
        // x的right是我右边的开始
        x.right = rinfo.start;

        return new Info(linfo.start != null ? linfo.start : x, rinfo.end != null ? rinfo.end : x);
    }

    public static class Info {
        public Node start;
        public Node end;

        public Info(Node s, Node e) {
            start = s;
            end = e;
        }
    }


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
}
