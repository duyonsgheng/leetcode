package custom.code_2022_08;

import custom.base.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_449
 * @Author Duys
 * @Description
 * @Date 2022/8/11 16:13
 **/
// 449. 序列化和反序列化二叉搜索树
public class LeetCode_449 {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "#";
            }
            StringBuilder builder = new StringBuilder();
            process(builder, root);
            return builder.toString();
        }

        // 使用先序遍历
        public void process(StringBuilder builder, TreeNode cur) {
            if (cur == null) {
                builder.append("#,");
            } else {
                builder.append(cur.val + ",");
                process(builder, cur.left);
                process(builder, cur.right);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            // 说明就一个 #,空树
            if (data.length() < 2) {
                return null;
            }
            // 转到链表里
            LinkedList<String> es = new LinkedList<>(Arrays.asList(data.split(",")));
            return process(es);
        }

        public TreeNode process(LinkedList<String> es) {
            // 当前遇到了 # 说明没了
            if (es.get(0).equals("#")) {
                es.remove(0);
                return null;
            }
            // 当前作为头
            TreeNode cur = new TreeNode(Integer.valueOf(es.get(0)));
            es.remove(0);
            // 跑递归去
            cur.left = process(es);
            cur.right = process(es);
            return cur;
        }
    }
}
