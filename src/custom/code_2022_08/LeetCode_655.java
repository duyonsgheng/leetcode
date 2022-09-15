package custom.code_2022_08;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_655
 * @Author Duys
 * @Description
 * @Date 2022/8/22 9:26
 **/
//655. 输出二叉树
public class LeetCode_655 {

    public static List<List<String>> printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        int height = findHeight(root)-1;
        int row = height + 1;
        int col = (int) Math.pow(2, height + 1) - 1;
        // 一行一行的处理
        for (int i = 0; i < row; i++) {
            List<String> res = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                res.add("");
            }
            ans.add(res);
        }
        ans.get(0).set(col / 2, root.val + "");
        processLeft(root.left, height, 0, col / 2, ans);
        processRight(root.right, height, 0, col / 2, ans);
        return ans;
    }

    public static void processLeft(TreeNode cur, int h, int preRow, int preCol, List<List<String>> ans) {
        if (cur == null) {
            return;
        }
        int curRow = preRow + 1;
        int curCol = preCol - (int) Math.pow(2, h - preRow - 1);
        ans.get(curRow).set(curCol, cur.val + "");
        processLeft(cur.left, h, curRow, curCol, ans);
        processRight(cur.right, h, curRow, curCol, ans);
    }

    public static void processRight(TreeNode cur, int h, int preRow, int preCol, List<List<String>> ans) {
        if (cur == null) {
            return;
        }
        int curRow = preRow + 1;
        int curCol = preCol + (int) Math.pow(2, h - preRow - 1);
        ans.get(curRow).set(curCol, cur.val + "");
        processLeft(cur.left, h, curRow, curCol, ans);
        processRight(cur.right, h, curRow, curCol, ans);
    }

    public static int findHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = findHeight(root.left);
        int right = findHeight(root.right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        List<List<String>> lists1 = printTree(root1);
        System.out.println(lists1);


        /*TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        List<List<String>> lists2 = printTree(root2);
        System.out.println(lists2);*/
    }
}
