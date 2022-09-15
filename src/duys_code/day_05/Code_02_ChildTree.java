package duys_code.day_05;

/**
 * @ClassName Code_02_ChildTree
 * @Author Duys
 * @Description
 * @Date 2021/9/23 11:22
 **/
public class Code_02_ChildTree {

    /**
     * 如果一个节点X，它左树结构和右树结构完全一样
     * 那么我们说以X为头的树是相等树
     * 给定一棵二叉树的头节点head
     * 返回head整棵树上有多少棵相等子树
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;
    }

    // 方法1：O(N*logN)的代价 -> 2*O(N/2)+O(N) -> master公式
    //  2*O(N/2)+O(N) 左树和右树都遍历的总共复杂度是 2*O(N/2)，O(N)是左树右树比对的产生的复杂度
    public static int sameNumber1(Node head) {
        if (head == null) {
            return 0;
        }
        // 可能性1：左树相等的节点数
        // 可能性2：右树相等的节点数
        // 可能性3：左树和右树加当前head节点是否是相等的，是就+1，不是就+0
        return sameNumber1(head.left) + sameNumber1(head.right) + (same1(head.left, head.right) ? 1 : 0);
    }

    public static boolean same1(Node left, Node right) {
        if (left == null ^ right == null) { //等价于 (left == null && right!=null) ||(left!=null && right==null)
            return false;
        }
        // 空树，认为相等
        if (left == null && right == null) {
            return true;
        }
        // 两个节点都不为空，相等判断，值相等，并且节点1的左树和节点2的左树相等，并且节点1的右树和节点2的右树相等
        return left.value == right.value && same1(left.left, right.left) && same1(left.right, right.right);
    }

    // 方法2：O(N) ，把左右树比对改良，使用二叉树的序列化
    public static int seamNumber2(Node head) {
        if (head == null) {
            return 0;
        }
        MyHash myHash = new MyHash("SHA-256");
        return process(head, myHash).x;
    }

    // 把左树和右树序列化，变成hash值进行最终的比对
    public static Info process(Node head, MyHash hash) {
        if (head == null) {
            return new Info(0, hash.hashCode("#,"));
        }
        Info leftInfo = process(head.left, hash);
        Info rightInfo = process(head.right, hash);
        // 左树等于右树，那么相等节点树+1，因为自己算在内
        int ans = (leftInfo.str).equals(rightInfo.str) ? 1 : 0 + leftInfo.x + rightInfo.x;
        String curStr = hash.hashCode(head.value + "," + leftInfo.str + rightInfo.str);
        return new Info(ans, curStr);
    }

    public static class Info {
        private int x;
        private String str;

        public Info(int x, String s) {
            this.x = x;
            this.str = s;
        }
    }
}
