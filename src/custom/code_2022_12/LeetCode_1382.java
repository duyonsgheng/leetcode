package custom.code_2022_12;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_1382
 * @Author Duys
 * @Description
 * @Date 2022/12/26 14:44
 **/
// 1382. 将二叉搜索树变平衡
public class LeetCode_1382 {

    // 中序遍历，搜索二叉树是升序的
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        getvalue(list, root);
        return process(list, 0, list.size() - 1);
    }

    public TreeNode process(List<Integer> list, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return new TreeNode(list.get(l));
        }
        int m = (l + r) / 2;
        TreeNode head = new TreeNode(list.get(m));
        head.left = process(list, l, m - 1);
        head.right = process(list, m + 1, r);
        return head;
    }

    public void getvalue(List<Integer> ans, TreeNode cur) {
        if (cur == null) {
            return;
        }
        getvalue(ans, cur.left);
        ans.add(cur.val);
        getvalue(ans, cur.right);
    }
}
