package duys.class_06_15;

/**
 * @ClassName IsBalanced
 * @Author Duys
 * @Description 判断一棵树是否是平衡二叉树
 * 对于每一棵子树来说。他们的左右子树高度的绝对值不超过1
 * @Date 2021/6/22 20:55
 **/
public class IsBalanced {
    /**
     * 列出可能性
     * 1.问我的左树要是否是平衡二叉树，以及高度
     * 2.问我的右树要是否是平衡二叉树，以及高度
     */
    public static class Info {
        public boolean isB;
        public int height;

        public Info(boolean i, int h) {
            isB = i;
            height = h;
        }
    }

    public static boolean isBalanced(Node node) {

        Info info = process(node);
        return info.isB;
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(true, 0);
        }
        // 左树要的信息
        Info leftInfo = process(node.left);
        // 问我的右树要的信息
        Info rightInfo = process(node.right);

        // 当前树的整体高度,左树的高度和右树的高度求一个最大，并且要加上当前节点的高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isB = true;
        // 来进行筛选
        // 如果左树不是平衡的，整个树不平
        if (!leftInfo.isB) {
            isB = false;
        }
        // 如果右树是不平衡的，整个树不平
        if (!rightInfo.isB) {
            isB = false;
        }
        // 如果左右的高度差大于了1，也是不满足的
        if (Math.abs(rightInfo.height - leftInfo.height) > 1) {
            isB = false;
        }
        return new Info(isB, height);
    }
}
