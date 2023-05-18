package custom.code_2023_04;

import custom.base.TreeNode;
import custom.code_2022_05.LeetCode_102;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName LeetCode_1609
 * @Author Duys
 * @Description
 * @Date 2023/4/18 14:02
 **/
// 1609. 奇偶树
public class LeetCode_1609 {
    // 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
    // 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
    public static boolean isEvenOddTree(TreeNode root) {
        // 每次一层，从左到右
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.addLast(root);
        if ((root.val & 1) == 0) {
            return false;
        }
        int level = 0;
        while (!stack.isEmpty()) {
            int size = stack.size();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = stack.pollFirst();
                arr[i] = node.val;
                if (node.left != null) {
                    stack.addLast(node.left);
                }
                if (node.right != null) {
                    stack.addLast(node.right);
                }
                //
                if (i == 0) {
                    if ((level & 1) == 0) {
                        if ((arr[0] & 1) == 0) {
                            return false;
                        }
                    } else {
                        if ((arr[0] & 1) == 1) {
                            return false;
                        }
                    }
                } else {
                    // 偶数层
                    if ((level & 1) == 0) {
                        if ((arr[i] & 1) == 0 || arr[i] <= arr[i - 1]) {
                            return false;
                        }
                    } else {
                        if (arr[i] >= arr[i - 1] || (arr[i] & 1) == 1) {
                            return false;
                        }
                    }
                }
            }
            level++;
        }
        return true;
    }

    // [11,18,14,3,7,null,null,null,null,18,null,6]
    public static void main(String[] args) {
        TreeNode root = new TreeNode(11);
        root.left = new TreeNode(18);
        root.right = new TreeNode(14);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(18);
        root.left.right.left.left = new TreeNode(6);

        System.out.println(isEvenOddTree(root));
    }
}
