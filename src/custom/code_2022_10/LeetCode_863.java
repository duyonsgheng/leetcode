package custom.code_2022_10;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_863
 * @Author Duys
 * @Description
 * @Date 2022/10/9 11:13
 **/
//
public class LeetCode_863 {

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 转换 把每个节点相邻的节点全部
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= 501; i++) {
            graph.add(new ArrayList<>());
        }
        build(root, graph);
        // 从target出发
        boolean[] visited = new boolean[501];
        //
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        queue.add(new int[]{target.val, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int from = cur[0];
            int dist = cur[1];
            if (visited[from]) {
                continue;
            }
            visited[from] = true;
            if (dist == k) {
                ans.add(from);
                continue;
            }
            // 能去的路
            List<Integer> list = graph.get(from);
            for (Integer next : list) {
                queue.offer(new int[]{next, dist + 1});
            }
        }
        return ans;
    }

    public static void build(TreeNode root, List<List<Integer>> graph) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            graph.get(root.val).add(root.left.val);
            graph.get(root.left.val).add(root.val);
            build(root.left, graph);
        }
        if (root.right != null) {
            graph.get(root.val).add(root.right.val);
            graph.get(root.right.val).add(root.val);
            build(root.right, graph);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        List<Integer> list = distanceK(root, new TreeNode(5), 3);
        System.out.println(list);
    }
}
