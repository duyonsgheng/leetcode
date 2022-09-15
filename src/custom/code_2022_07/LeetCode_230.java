package custom.code_2022_07;

import custom.base.TreeNode;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_230
 * @Author Duys
 * @Description
 * @Date 2022/7/13 11:29
 **/
// 230. 二叉搜索树中第K小的元素
public class LeetCode_230 {

    public static int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> a - b);
        process(heap, root);
        int index = 1;
        int cur = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            cur = heap.poll();
            if (index == k) {
                return cur;
            }
            index++;
        }
        return cur;
    }

    public static void process(PriorityQueue<Integer> heap, TreeNode cur) {
        if (cur == null) {
            return;
        }
        heap.add(cur.val);
        process(heap, cur.left);
        process(heap, cur.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        //root.left = new TreeNode(1);
        //root.left.right = new TreeNode(2);
        root.right = new TreeNode(2);
        int k = 2;
        System.out.println(kthSmallest(root, k));
    }
}
