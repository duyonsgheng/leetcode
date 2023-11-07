package custom.code_2023_09;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2196
 * @date 2023年09月11日
 */
// 2196. 根据描述创建二叉树
// https://leetcode.cn/problems/create-binary-tree-from-descriptions/
public class LeetCode_2196 {

    public static TreeNode createBinaryTree(int[][] descriptions) {
        // descriptions[i] = [parenti, childi, isLefti]
        Map<Integer, TreeNode> maps = new HashMap<>();
        Map<Integer, Integer> parents = new HashMap<>();
        List<Integer> all = new ArrayList<>();
        for (int[] desc : descriptions) {
            parents.put(desc[1], desc[0]);
            all.add(desc[0]);
            all.add(desc[1]);
        }
        for (int[] desc : descriptions) {
            int par = desc[0];
            int child = desc[1];
            int left = desc[2];

            if (!maps.containsKey(par)) {
                maps.put(par, new TreeNode(par));
            }
            TreeNode parent = maps.get(par);
            if (!maps.containsKey(child)) {
                maps.put(child, new TreeNode(child));
            }
            TreeNode cur = maps.get(child);
            maps.put(child, cur);
            if (left == 1) {
                parent.left = cur;
            } else {
                parent.right = cur;
            }
        }
        TreeNode root = null;
        for (Integer cur : all) {
            if (parents.get(cur) == null) {
                root = maps.get(cur);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode binaryTree1 = createBinaryTree(new int[][]{{20, 15, 1}, {20, 17, 0}, {50, 20, 1}, {50, 80, 0}, {80, 19, 1}});
        TreeNode binaryTree2 = createBinaryTree(new int[][]{{1, 2, 1}, {2, 3, 0}, {3, 4, 1}});
        System.out.println();
    }
}
