package custom.code_2022_10;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_958
 * @Author Duys
 * @Description
 * @Date 2022/10/24 9:00
 **/
// 958. 二叉树的完全性检验
public class LeetCode_958 {
    public boolean isCompleteTree(TreeNode root) {
        Info process = process(root);
        return process.isCBT;
    }

    public Info process(TreeNode head) {
        if (head == null) {
            return new Info(true, true, 0);
        }
        Info left = process(head.left);
        Info right = process(head.right);

        int h = Math.max(left.height, right.height) + 1;

        boolean full = left.isFull && right.isFull && left.height == right.height;

        boolean cbt = full;
        if (full) {
            cbt = true;
        } else {
            // 如果不是满的
            if (left.isCBT && right.isCBT) {
                // 左边是平衡的，右边是满的，并且左边高度等于右边+1
                if (left.isCBT && right.isFull && left.height == right.height + 1) {
                    cbt = true;
                }
                // 左右都是满的，但是高度差1
                if (left.isFull && right.isFull && left.height == right.height + 1) {
                    cbt = true;
                }
                // 左边是满的，右边是平衡，且高度相等
                if (left.isFull && right.isCBT && left.height == right.height) {
                    cbt = true;
                }

            }
        }
        return new Info(full, cbt, h);
    }

    class Info {
        public boolean isFull; // 是否满
        public boolean isCBT; // 是否平衡
        public int height;   // 高度

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }
}
