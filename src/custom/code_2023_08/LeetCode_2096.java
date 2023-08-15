package custom.code_2023_08;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2096
 * @date 2023年08月14日
 */
// 2096. 从二叉树一个节点到另一个节点每一步的方向
// https://leetcode.cn/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
public class LeetCode_2096 {
    private StringBuilder getStartPath = new StringBuilder();
    private StringBuilder getDestPath = new StringBuilder();

    //  从根结点找到start路径
    private void dfsStart(TreeNode root, int value, StringBuilder path) {
        if (root == null) {
            return;
        }
        // 剪枝：如果已找到 返回
        if (this.getStartPath.length() != 0) {
            return;
        }
        if (root.val == value) {
            this.getStartPath.append(path.toString());
            return;
        }
        dfsStart(root.left, value, path.append('L'));
        path.deleteCharAt(path.length() - 1);
        dfsStart(root.right, value, path.append('R'));
        path.deleteCharAt(path.length() - 1);
    }

    // 从根节点找到end路径
    private void dfsDest(TreeNode root, int value, StringBuilder path) {
        if (root == null) {
            return;
        }
        // 剪枝：如果已找到 返回
        if (this.getDestPath.length() != 0) {
            return;
        }
        if (root.val == value) {
            this.getDestPath.append(path.toString());
            return;
        }
        dfsDest(root.left, value, path.append('L'));
        path.deleteCharAt(path.length() - 1);
        dfsDest(root.right, value, path.append('R'));
        path.deleteCharAt(path.length() - 1);
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        dfsStart(root, startValue, new StringBuilder());
        dfsDest(root, destValue, new StringBuilder());
        // 如果起始位置为根节点
        if (getStartPath.equals("")) {
            return getDestPath.toString();
        }
        // 如果同一位置
        if (getStartPath.equals(getDestPath)) {
            return "";
        }

        int index = 0;
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        // L L
        // R L
        while (index < getStartPath.length() && index < getDestPath.length()) {
            if (getStartPath.charAt(index) != getDestPath.charAt(index)) {
                break;
            } else {
                index++;
            }
        }
        for (int i = index; i < getStartPath.length(); i++) {
            start.append('U');
        }
        end.append(getDestPath.substring(index, getDestPath.length()));
        return start.toString() + end.toString();
    }

    public static void main(String[] args) {
        LeetCode_2096 lc = new LeetCode_2096();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(8);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(2);
        String directions = lc.getDirections(root, 5, 6);
        System.out.println(directions);
    }
}
