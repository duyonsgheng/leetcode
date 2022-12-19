package custom.code_2022_12;

import custom.base.ListNode;
import custom.base.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_1367
 * @Author Duys
 * @Description
 * @Date 2022/12/14 15:26
 **/
// 1367. 二叉树中的链表
public class LeetCode_1367 {
    public static boolean isSubPath1(ListNode head, TreeNode root) {
        Set<String> set = new HashSet<>();
        StringBuffer buffer = new StringBuffer();
        ListNode cur = head;
        while (cur != null) {
            buffer.append(cur.val);
            cur = cur.next;
        }
        build(root, new StringBuffer(), set);
        boolean ans = false;
        for (String str : set) {
            if (str.contains(buffer.toString())) {
                ans = true;
            }
        }
        return ans;
    }

    public static void build(TreeNode root, StringBuffer path, Set<String> set) {

        path.append(root.val);
        if (root.left == null && root.right == null) {
            set.add(path.toString());
        }
        if (root.left != null) {
            build(root.left, path, set);
            path.deleteCharAt(path.length() - 1);
        }
        if (root.right != null) {
            build(root.right, path, set);
            path.deleteCharAt(path.length() - 1);
        }

    }

    // [4,2,8]
    //[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(8);
        root.right.left.right.left = new TreeNode(1);
        root.right.left.right.right = new TreeNode(3);
        isSubPath1(null, root);
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        if (process(head, root)) {
            return true;
        }
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean process(ListNode cur, TreeNode root) {
        if (cur == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.val == cur.val) {
            return process(cur.next, root.left) || process(cur.next, root.right);
        }
        return false;
    }
}
