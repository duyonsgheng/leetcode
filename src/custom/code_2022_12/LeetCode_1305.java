package custom.code_2022_12;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1305
 * @Author Duys
 * @Description
 * @Date 2022/12/6 9:57
 **/
// 1305. 两棵二叉搜索树中的所有元素
public class LeetCode_1305 {

    // 搜索二叉树 - 中序遍历 根据二叉树得性质，中序遍历就已经有序了。所以只需要归并一次就可以了
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        process(root1, list1);
        List<Integer> list2 = new ArrayList<>();
        process(root2, list2);
        // 谁小拷贝谁
        List<Integer> ans = new ArrayList<>();
        int l1 = 0;
        int l2 = 0;
        // 谁小拷贝谁
        while (l1 < list1.size() && l2 < list2.size())
            ans.add(list1.get(l1) <= list2.get(l2) ? list1.get(l1++) : list2.get(l2++));

        while (l1 < list1.size())
            ans.add(list1.get(l1++));

        while (l2 < list2.size())
            ans.add(list2.get(l2++));
        return ans;
    }

    public void process(TreeNode head, List<Integer> ans) {
        if (head == null) {
            return;
        }
        process(head.left, ans);
        ans.add(head.val);
        process(head.right, ans);
    }
}
