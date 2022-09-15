package duys_code.day_07;

/**
 * @ClassName Code_02_MinCameraTX
 * @Author Duys
 * @Description 相机最小覆盖问题得贪心策略
 * @Date 2021/9/28 9:53
 **/
public class Code_02_MinCameraTX {
    /**
     * 思考：
     * 1.如果当来是叶子节点 - X节点了，X的左孩子为空，X的右孩子为空，那我们当前节点X不需要放相机，把放相机得权力较给当前X节点得父节点去，
     * 此时这里存在一个贪心：如果我当前位置放相机，那么这个相机最多只能管两个节点，一个是当前X节点，一个是X节点的父节点，
     * 就不如X的父节点放相机来的好，因为X的父节点放相机的话，至少可以管 X父节点的父节点，X的兄弟节点
     * 2.如果来到的一个节点X是非叶子节点：那么如果我的左孩子或者右孩子存在某一个孩子被覆盖且有相机，那么我自己都不用放相机，还是把放相机的权力交给我的父节点去，和1中的贪心策略一样
     * 3.如果来到的节点是非叶子节点：如果我的左孩子和右孩子有一个没有被覆盖，且无相机，那么我当前位置一定要放一个相机，要解决我的孩子没有被覆盖的问题（这里不存在贪心问题，因为我的孩子节点只能我来补救）
     * 4.如果我的左右孩子都被覆盖了，且都没有相机，那么我自己就不用被覆盖，且不用放相机，继续把放相机的权力给我的父节点，贪心策略
     */
    public static int minCameraCover(TreeNode root) {
        Info info = process(root);
        // 如果root没有被覆盖，那么需要一个相机
        if (info.status == Status.UN_COVERED) {
            return info.num + 1;
        }
        return info.num;
    }

    public static Info process(TreeNode X) {
        if (X == null) {
            return new Info(0, Status.COVERED_NO_CAMERA);
        }
        Info left = process(X.left);
        Info right = process(X.right);

        // 当前节点的最小需要的相机
        int curNum = left.num + right.num;
        // 2. 左右孩子有一个没有被覆盖，且无相机，那么自己一定要放一个相机，来补救我的孩子节点，不属于贪心
        if (left.status == Status.UN_COVERED || right.status == Status.UN_COVERED) {
            return new Info(curNum + 1, Status.COVERED_HAS_CAMERA);
        }

        // 1. 左右节点只要有一个有相机的，自己不需要放相机，且自己已经被覆盖了，贪心策略，相机自己就不放了，较给自己的父亲去抉择
        if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
            return new Info(curNum, Status.COVERED_NO_CAMERA);
        }

        // 其他的情况，就是自己的左右还有被覆盖了，又没有放相机，那么我自己可以不用覆盖，也可以不妨相机，贪心策略，交给自己的父亲节点来抉择
        return new Info(curNum, Status.UN_COVERED);
    }


    public static class Info {
        public int num;
        public Status status;

        public Info(int num, Status status) {
            this.num = num;
            this.status = status;
        }
    }


    public enum Status {
        // 没有被覆盖，也无相机
        UN_COVERED,
        // 被覆盖，无相机
        COVERED_NO_CAMERA,
        // 被覆盖，有相机
        COVERED_HAS_CAMERA
    }


    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
    }

}
