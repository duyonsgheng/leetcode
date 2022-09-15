package duys.class_06_15;

/**
 * @ClassName IsBST
 * @Author Duys
 * @Description 判断一棵树是否是搜索二叉树
 * 搜索二叉树定义：左树每一个节点的值都比自己小，右树的每一个节点的值都比自己大
 * 很简单的一个做法：就是中序遍历时候，整个遍历结果是升序的
 * @Date 2021/6/22 20:51
 **/
public class IsBST {

    /**
     * 思路：
     * 1.左树每一个节点的值都比自己小，问我的左树要的信息 -> a.是否是搜索二叉树; b.最大值
     * 2.右树每一个节点的值都比自己大，问我的右树要的信息 -> a.是否是搜索二叉树; b.最小值
     * ps: 此时问我的左树和右树要的信息不一样，那么我们的结构体应该是包含全集
     */
    public static class Info {
        public boolean isBST;
        public int min;
        public int max;

        public Info(boolean i, int min, int max) {
            isBST = i;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean isBST(Node head) {
        // 空树，是一颗搜索二叉树
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    // 在递归的过程中，为空这个条件已经被处理过了
    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        // 准备这三个东西，进行递归传递
        boolean isBST = true;
        int min = head.value;
        int max = head.value;
        /***************是否是搜索二叉树*****************/
        // 左树不为空，并且左树不是搜索二叉树，那么整个树就不是了
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        // 右树不为空，并且右树不是搜索二叉树，那么整个树就不是了
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        // 左树不为空，并且呢，我的左树最大值都已经当前的值，违背了搜索二叉树定义
        if (leftInfo != null && leftInfo.max >= head.value) {
            isBST = false;
        }
        // 左树不为空，并且呢，我的左树最大值都已经当前的值，违背了搜索二叉树定义
        if (rightInfo != null && rightInfo.min <= head.value) {
            isBST = false;
        }
        /***************最大值*****************/
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        /***************最小值*****************/
        if (rightInfo != null) {
            min = Math.min(max, rightInfo.min);
        }
        if (leftInfo != null) {
            min = Math.min(max, leftInfo.min);
        }
        return new Info(isBST, min, max);
    }
}
