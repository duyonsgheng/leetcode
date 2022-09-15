package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_100
 * @Author Duys
 * @Description
 * @Date 2022/5/18 13:20
 **/
// 100. 相同的树
// 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
public class LeetCode_100 {
    // 直接验证中序遍历是不是一样的就行了
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        List<String> a1 = new ArrayList<>();
        fastOrder(p, a1);

        List<String> a2 = new ArrayList<>();
        fastOrder(q, a2);
        if (a1.size() != a2.size()) {
            return false;
        }
        for (int i = 0; i < a1.size(); i++) {
            if (!a1.get(i).equals(a2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void fastOrder(TreeNode head, List<String> ans) {
        if (head == null) {
            ans.add("nil");
            return;
        }
        ans.add(head.val + "");
        fastOrder(head.left, ans);
        fastOrder(head.right, ans);
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode1_1 = new TreeNode(1);
        treeNode1.left = treeNode1_1;

        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode2_1 = new TreeNode();
        TreeNode treeNode2_2 = new TreeNode(1);
        treeNode2.left = null;
        treeNode2.right = treeNode2_2;
        System.out.println(isSameTree(treeNode1, treeNode2));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
