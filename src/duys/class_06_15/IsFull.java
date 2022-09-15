package duys.class_06_15;

/**
 * @ClassName IsFull
 * @Author Duys
 * @Description 判断二叉树是不是满二叉树
 * @Date 2021/6/24 17:37
 **/
public class IsFull {
    /**
     * 满二叉树的性质：如果高度为h 那么节点数满足 2^h -1
     */
    /**
     * 思路：以x为头节点，左树的高度，右树的高度，左树的节点数，右树的节点数，最后根据信息，看看是否满足 节点数 = 2^h -1
     */
    public static class Info {
        public int nodes;
        public int height;

        public Info(int n, int h) {
            nodes = n;
            height = h;
        }
    }

    public static boolean isFull(Node x) {
        Info process = process(x);
        return (1 << process.height - 1) == process.nodes;
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 算上x节点
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(nodes, height);
    }

}
