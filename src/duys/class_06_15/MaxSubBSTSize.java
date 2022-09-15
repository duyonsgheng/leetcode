package duys.class_06_15;

/**
 * @ClassName MaxSubBSTSize
 * @Author Duys
 * @Description 题意：以X为起点的整棵树，不一定是一颗平衡二叉树，
 * 但是他的子树可能是平衡的，求整棵树中平衡的那颗子树的最大节点数
 * @Date 2021/6/24 11:08
 **/
public class MaxSubBSTSize {
    /**
     * 思路：先列出可能性
     * 1.可能性1：经过x
     * 整棵树是否是平衡二叉树，如果是获取整棵树的节点数+1
     * 2.可能性2：不经过x
     * 2.1 左树可能是一颗平衡二叉树
     * 2.2 右树可能是一颗平衡二叉树
     * <p>
     * 列出需要的信息了：
     * 1.问左树要 是否是平很二叉树，如果是 最大的平衡二叉树节点数
     * 2.问左树要 最大值 最小值 平衡二叉树性质，左树的最大值小于当前x的值，右树的最小值大于当前x的值
     * 3.问左树要 所有的节点数
     * 问右树要的信息也是一样，最后收集到得信息
     */
    public static class Info {
        public int maxBSTSize;
        public int max;
        public int min;
        public int allSize;

        public Info(int maxB, int max, int min, int allSize) {
            this.maxBSTSize = maxB;
            this.max = max;
            this.min = min;
            this.allSize = allSize;
        }
    }

    public static int getMaxBSTSize(Node head) {
        Info process = process(head);
        return process.maxBSTSize;
    }

    public static Info process(Node x) {
        // 下层处理
        if (x == null) {
            return null;
        }
        // 二话不说 ，先收集左右树信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // 来收集信息
        int maxBSTSize = 0;
        int max = x.value;
        int min = x.value;
        int allSize = 1;
        // 比较好收集的有max ，min ，allSize
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.max, min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.max, min);
            allSize += rightInfo.allSize;
        }
        // 不好收集的就是，如果某一个子树是平衡二叉树，那么当前树最大的平衡二叉树的节点数
        //
        int max1 = 0;
        if (leftInfo != null) {
            max1 = leftInfo.maxBSTSize;
        }

        int max2 = 0;
        if (rightInfo != null) {
            max2 = rightInfo.maxBSTSize;
        }

        // 判断左右子树是不是平衡二叉树
        int max3 = 0;
        // 如果左树是平衡的，那么左树节点和全部节点数是相等的
        boolean left = leftInfo == null ? true : (leftInfo.maxBSTSize == leftInfo.allSize);
        // 右树同理
        boolean right = rightInfo == null ? true : (rightInfo.maxBSTSize == rightInfo.allSize);
        // 如果左树也是，右树也是，那么看看加上x这个节点，整棵树还是不是
        if (left && right) {
            // 开始判断加上x的整棵树了
            boolean leftMass = leftInfo == null ? true : (leftInfo.max < x.value);
            boolean rightMass = rightInfo == null ? true : (rightInfo.min > x.value);
            // 左树，右树，x 整棵树都满足的话，
            if (leftMass && rightMass) {
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                max3 = leftSize + rightSize + 1;
            }
        }

        maxBSTSize = Math.max(Math.max(max1, max2), max3);
        return new Info(maxBSTSize, max, min, allSize);
    }

}
