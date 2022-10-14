package custom.code_2022_10;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_894
 * @Author Duys
 * @Description
 * @Date 2022/10/12 10:30
 **/
//894. 所有可能的真二叉树
public class LeetCode_894 {
    Map<Integer, List<TreeNode>> map = new HashMap<>();

    // 每一棵满二叉树，有三个节点
    public List<TreeNode> allPossibleFBT(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        List<TreeNode> cur = new ArrayList<>();
        if (n == 1) {
            cur.add(new TreeNode(0));
        } else if (n % 2 == 1) {
            for (int i = 0; i < n; i++) {
                int rest = n - i - 1;
                // 左边节点
                for (TreeNode left : allPossibleFBT(i)) {
                    // 右边节点
                    for (TreeNode right : allPossibleFBT(rest)) {
                        TreeNode node = new TreeNode(0);
                        node.left = left;
                        node.right = right;
                        cur.add(node);
                    }
                }
            }
        }
        map.put(n, cur);
        return map.get(n);
    }

}
