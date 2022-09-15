package duys.class_06_15;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName IsCBT
 * @Author Duys
 * @Description 判断一颗二叉树是否是满二叉树(完全二叉树)
 * @Date 2021/6/22 20:34
 **/
public class IsCBT {


    // 不适用递归
    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        // 是否遇到过左右不双全的节点
        boolean leaf = false;// 初始记为没遇到过
        Node left = null;
        Node right = null;

        while (!queue.isEmpty()) {
            head = queue.poll();
            left = head.left;
            right = head.right;

            if (
                // 如果遇到过左右不双全的节点，并且还有左右子节点，那么就不满足了，
                // 因为如果遇到了左右不双全的节点，接下来只能看叶子节点了，如果当前节点还存在左右节点，就已经不满足了
                    leaf && (left != null || right != null)
                            ||
                            // 这种情况，很明显，如果存在一个节点，没有左孩子节点，但是有右孩子节点，明显不满足
                            (left == null && right != null)
            ) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (left == null || right == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean fu, boolean cb, int he) {
            isFull = fu;
            isCBT = cb;
            height = he;
        }
    }

    /**
     * 思路：列出可能性
     * 1.左树是满的，右树也是满的，并且左树高度 = 右树高度
     * 2.左树是完全，右树是满的，并且左树高度 = 右树高度+1
     * 3.左树是满的，右树是完全，并且左树高度 = 右树高度
     * 4.左树是满的，右树也是满的，并且左树高度 = 右树高度+1
     */
    public static Info process(Node x) {
        if (x == null) { // base case
            // 空树，是满的，也是完全的，高度是0
            return new Info(true, true, 0);
        }
        // 收集信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // 左高，右高取最大的，然后加上x当前的节点高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 满的这件事也很好说，左边满的，右边满的，并且高度一样，那整棵子树就是满的
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        // 情况1
        if (isFull) {
            isCBT = true;
        } else {
            if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                isCBT = true;
            } else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                isCBT = true;
            } else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                isCBT = true;
            }
        }
        return new Info(isFull, isCBT, height);
    }
}
