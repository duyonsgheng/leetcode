package custom.code_2022_09;

import custom.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_662
 * @Author Duys
 * @Description
 * @Date 2022/9/5 16:56
 **/
public class LeetCode_662 {

    // 同层编号
    public int widthOfBinaryTree(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        return dfs(root, 1, 1, map);
    }

    public int dfs(TreeNode cur, int dept, int index, Map<Integer, Integer> map) {
        if (cur == null) {
            return 0;
        }
        // 每一层先访问左节点
        if (!map.containsKey(dept)) {
            map.put(dept, index);
        }
        // 每一层的最大编号，减去最小编号+1；
        int curLevel = index - map.get(dept) + 1;
        int left = dfs(cur.left, dept + 1, index * 2, map);
        int right = dfs(cur.right, dept + 1, index * 2 + 1, map);
        return Math.max(curLevel, Math.max(left, right));
    }

}
