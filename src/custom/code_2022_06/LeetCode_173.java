package custom.code_2022_06;

import custom.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_173
 * @Author Duys
 * @Description
 * @Date 2022/7/13 9:30
 **/
// 173. 二叉搜索树迭代器
public class LeetCode_173 {

    public static class BSTIterator {

        List<TreeNode> list;
        int next;

        public BSTIterator(TreeNode root) {
            list = new ArrayList<>();
            next = 0;
            build(root);
        }

        public int next() {
            if (next > list.size() - 1) {
                return -1;
            }
            return list.get(next++).val;
        }

        public boolean hasNext() {
            if (next > list.size() - 1) {
                return false;
            }
            return list.get(next) != null;
        }

        private void build(TreeNode cur) {
            if (cur == null) {
                return;
            }
            build(cur.left);
            list.add(cur);
            build(cur.right);
        }
    }

    public static void main(String[] args) {
        // ["BSTIterator","next","next","hasNext","next","hasNext","next","hasNext","next","hasNext"]
        //[[[7,3,15,null,null,9,20]],[],[],[],[],[],[],[],[],[]]
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.left.left = null;
        root.left.right = null;
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}
