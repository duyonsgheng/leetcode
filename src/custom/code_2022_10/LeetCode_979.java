package custom.code_2022_10;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_979
 * @Author Duys
 * @Description
 * @Date 2022/10/26 11:04
 **/
// 979. 在二叉树中分配硬币
public class LeetCode_979 {
    int ans = 0;

    public int distributeCoins(TreeNode root) {
        process(root);
        return ans;
    }

    public int process(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int left = process(head.left);
        int right = process(head.right);
        // 对于当前节点来说只需要留下1个，其他的都需要交换出去
        int sum = head.val + left + right - 1;
        ans += Math.abs(sum);
        return sum;
    }
}
