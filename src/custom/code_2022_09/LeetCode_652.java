package custom.code_2022_09;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_652
 * @Author Duys
 * @Description
 * @Date 2022/9/5 15:18
 **/
// 652. 寻找重复的子树
public class LeetCode_652 {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // 把所有的子树都序列化
        Map<String, TreeNode> map = new HashMap<>();
        Set<TreeNode> set = new HashSet<>();
        dfs(root, map, set);
        return new ArrayList<>(set);
    }

    public String dfs(TreeNode root, Map<String, TreeNode> map, Set<TreeNode> set) {
        if (root == null) {
            return "#";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(root.val);
        builder.append("(");
        builder.append(dfs(root.left, map, set));
        builder.append(")(");
        builder.append(dfs(root.right, map, set));
        builder.append(")");
        if (map.containsKey(builder.toString())) {
            set.add(map.get(builder.toString()));
        } else {
            map.put(builder.toString(), root);
        }
        return builder.toString();
    }
}
