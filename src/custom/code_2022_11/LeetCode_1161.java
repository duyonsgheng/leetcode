package custom.code_2022_11;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName LeetCode_1161
 * @Author Duys
 * @Description
 * @Date 2022/11/17 20:42
 **/
// 1161. 最大层内元素和
public class LeetCode_1161 {

    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Map<Integer, PriorityQueue<Integer>> count = new HashMap<>();
        int level = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int max = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                sum += poll.val;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            max = Math.max(sum, max);
            if (!count.containsKey(sum)) {
                count.put(sum, new PriorityQueue<>());
            }
            count.get(sum).add(level);
            level++;
        }
        return count.get(max).poll();
    }


}
