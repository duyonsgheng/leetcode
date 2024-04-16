package hope.class78_treeDP1;

import custom.base.TreeNode;

/**
 * @author Mr.Du
 * @ClassName Code06_BinaryTreeCameras
 * @date 2024年04月08日 上午 10:18
 */
// 监控二叉树
// 给定一个二叉树，我们在树的节点上安装摄像头
// 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象
// 计算监控树的所有节点所需的最小摄像头数量
// 测试链接 : https://leetcode.cn/problems/binary-tree-cameras/
public class Code06_BinaryTreeCameras {
    // 思路：设计流程的时候，选择子节点需要返回什么状态给父节点，父节点根据子节点的状态判断当前是否需要添加相机，增加一些贪心的思路，
    public static int ans = 0;

    public int minCameraCover(TreeNode root) {
        ans = 0;
        // 如果头结点是没有被覆盖的，最后来处理一下
        if (f(root) == 0) {
            ans++;
        }
        return ans;
    }

    // 0状态：x无覆盖，x的下级节点已经全部被覆盖了
    // 1状态：x有覆盖，但是x无相机
    // 2状态：x有覆盖，且x上有相机
    public static int f(TreeNode x) {
        if (x == null) {
            return 1;
        }
        int left = f(x.left);
        int right = f(x.right);
        // 只要我的下级节点有一个没有覆盖的，当前x节点就要放置一个相机
        if (left == 0 || right == 0) {
            ans++;
            return 2;
        }
        // 如果我的下级节点已经被覆盖了，那么我当前节点是不需要放置相机的，
        if (left == 1 && right == 1) {
            return 0;
        }
        return 1;
    }
}
