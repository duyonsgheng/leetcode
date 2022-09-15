package duys.class_06_15;

/**
 * @ClassName MaxPublicParentNdeo
 * @Author Duys
 * @Description 公共祖先
 * @Date 2021/6/29 16:12
 **/
public class MinPublicParentNode {
    /**
     * 题意：给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b的最低公共祖先
     */
    /**
     * 可能性分析：
     * 1.与x有关
     * 1.1 x就是公共祖先节点
     * 1.2 x的左树发现了a或者b x的右树上发现了a或者b
     * 2.与x无关
     * 2.1 x的左树上发现了a b 和公共祖先
     * 2.2 x的右树上发现了a b 和公共祖先
     */

    public static class Info {
        public boolean findA;
        public boolean findB;
        public Node ans;

        public Info(boolean fa, boolean fb, Node an) {
            findA = fa;
            findB = fb;
            ans = an;
        }
    }

    public static Node minPublicParentNode(Node head, Node a, Node b) {
        return process(head, a, b).ans;
    }

    public static Info process(Node x, Node a, Node b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        // 左树上发现了a或者右树上发现了a，或者 x本身就是a
        boolean findA = leftInfo.findA || rightInfo.findA || x == a;
        boolean findB = leftInfo.findB || rightInfo.findB || x == b;

        Node ans = null;
        // 左树上发现了
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        }
        // 右树上发现了
        else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }
        // 左树 右树都没发现
        else {
            // 如果当前以x为头，找到了a 也找到了b，那么x就是最低公共祖先了
            if (findB && findA) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }
}
