package custom.code_2022_10;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_889
 * @Author Duys
 * @Description
 * @Date 2022/10/12 9:16
 **/
// 889. 根据前序和后序遍历构造二叉树
// 分段递归
public class LeetCode_889 {


    // 前序： 根节点  左节点  右节点
    // 后序： 左节点  右节点  根节点
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {

        return process(preorder, postorder, 0, preorder.length - 1, 0, preorder.length - 1);
    }

    public TreeNode process(int[] pre, int[] post, int l, int r, int d, int t) {
        if (l > r) {
            return null;
        }
        // 构建根节点
        TreeNode root = new TreeNode(pre[l]);
        if (l == r) {
            return root;
        }
        int left = l + 1;
        if (left >= pre.length) {
            return root;
        }
        int i = d;
        // 找到左节点再后续遍历的位置，左节点再前序遍历的位置，这区间就是左树
        for (; i <= t; i++) {
            if (post[i] == pre[left]) {
                break;
            }
        }
        // 1,2,4,5,3,6,7
        // 4,5,2,6,7,3,1
        // 构建左树
        root.left = process(pre, post, left, left + i - d, d, i);
        // 构建右树
        root.right = process(pre, post, left + i - d + 1, r, i + 1, t - 1);
        return root;
    }
}
