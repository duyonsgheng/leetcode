package duys_code.day_07;

/**
 * @ClassName Code_02_MinCamer
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/binary-tree-cameras/
 * @Date 2021/9/27 16:54
 **/
public class Code_02_MinCamera {
    /**
     * 相机最小覆盖问题：
     * 二叉树的递归套路
     */
    /**
     * 可能性分析：
     * 1.x节点有相机，x被覆盖了，而且x的孩子节点全部被覆盖
     * 2.x节点无相机，x被覆盖的，而且x的孩子节点全部被覆盖
     * 3.x节点无相机，x没有被覆盖，但是x的孩子节点全部被覆盖
     */

    public static int minCameraCover(TreeNode root) {
        Info info = process1(root);
        // 如果是自己没有被覆盖，也无相机，那么给自己按一个相机，所以+1
        return (int) Math.min(info.unCovered + 1, Math.min(info.coveredHasCamera, info.coveredNoCamera));
    }

    // 潜台词：x下方的所有节点，都必须被覆盖，因为x节点能被自己的父节点补救，那么x的所有子节点是不能被补救的
    public static Info process1(TreeNode x) {
        // 当x为空了，必须要被覆盖，并且无相机
        if (x == null) {
            return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }
        Info left = process1(x.left);
        Info right = process1(x.right);

        // 自己的信息
        // 自己没有被覆盖，且没有相机，那么自己的节点是要被覆盖的，而且是没有相机的，
        // 原因：我们的潜台词是x下方所有的节点都被覆盖了；
        // 1.如果孩子有相机，自己肯定是被覆盖的；2.如果孩子没有被覆盖，自己也没相机，那么就出错了，
        // 因为自己没相机，自己没有被覆盖，这时候还有自己的孩子节点没有被覆盖的，出问题了
        long unCovered = left.coveredNoCamera + right.coveredNoCamera;

        // 自己被覆盖，切无相机
        long coveredNoCamera = 0;
        // 1. 孩子节点有相机切被覆盖，那自己会被覆盖，且不需要相机
        long p1 = left.coveredHasCamera + right.coveredHasCamera;
        // 2. 左孩子有相机，右被覆盖，且无相机
        long p2 = left.coveredHasCamera + right.coveredNoCamera;
        // 3. 左孩子无相机且被覆盖，右孩子有相机
        long p3 = left.coveredNoCamera + right.coveredHasCamera;
        coveredNoCamera = Math.min(p1, Math.min(p2, p3));

        // 自己被覆盖，且有相机,自己都已经有相机了，孩子被覆盖和不被覆盖，都不是问题
        long coveredHasCamera = 0;
        long m1 = Math.min(left.unCovered, Math.min(left.coveredHasCamera, left.coveredNoCamera));
        long m2 = Math.min(right.unCovered, Math.min(right.coveredNoCamera, right.coveredHasCamera));
        coveredHasCamera = m1 + m2 + 1; // +上当前x有一个相机
        return new Info(unCovered, coveredNoCamera, coveredHasCamera);
    }


    public static class Info {
        // 三种可能性
        public long unCovered; // 没有被覆盖，也没有相机。，至少需要几个相机
        public long coveredNoCamera; // 被覆盖了，无相机，至少需要几个相机
        public long coveredHasCamera; // 被覆盖了，有相机，至少需要几个相机

        public Info(long un, long cn, long ch) {
            unCovered = un;
            coveredNoCamera = cn;
            coveredHasCamera = ch;
        }
    }

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
    }

}
