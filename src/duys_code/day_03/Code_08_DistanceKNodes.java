package duys_code.day_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName Code_08_DistanceKNodes
 * @Author Duys
 * @Description
 * @Date 2021/9/22 14:14
 **/
public class Code_08_DistanceKNodes {
    /**
     * 题意：
     * 给定三个参数：
     * 二叉树的头节点head，树上某个节点target，正数K
     * 从target开始，可以向上走或者向下走
     * 返回与target的距离是K的所有节点
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static List<Node> distanceNodes(Node root, Node target, int k) {
        // 建立一张父节点的map
        HashMap<Node, Node> parents = new HashMap<>();
        parents.put(root, null);
        createParentMap(root, parents);
        // 宽度优先遍历，按层遍历
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.offer(root);
        set.add(root);
        int curLevel = 0;
        List<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 当前层有多少个节点，每一次都会处理一层的
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                if (curLevel == k) {
                    ans.add(cur);
                }
                if (cur.left != null && !set.contains(cur.left)) {
                    queue.offer(cur.left);
                    set.add(cur.left);
                }
                if (cur.right != null && !set.contains(cur.right)) {
                    queue.offer(cur.right);
                    set.add(cur.right);
                }
                if (parents.get(cur) != null && !set.contains(parents.get(cur))) {
                    queue.offer(parents.get(cur));
                    set.add(parents.get(cur));
                }
            }
            curLevel++;
            if (curLevel > k) {
                break;
            }
        }
        return ans;
    }

    public static void createParentMap(Node cur, HashMap<Node, Node> parents) {
        if (cur == null) {
            return;
        }
        if (cur.left != null) {
            parents.put(cur.left, cur);
            createParentMap(cur.left, parents);
        }
        if (cur.right != null) {
            parents.put(cur.right, cur);
            createParentMap(cur.right, parents);
        }
    }
}
