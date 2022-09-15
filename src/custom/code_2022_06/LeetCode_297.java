package custom.code_2022_06;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_297
 * @Author Duys
 * @Description
 * @Date 2022/6/13 9:47
 **/
//297. 二叉树的序列化与反序列化
// 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
//请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
//提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
//链接：https://leetcode.cn/problems/serialize-and-deserialize-binary-tree
public class LeetCode_297 {
    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "#";
            }
            StringBuilder builder = new StringBuilder();
            process(builder, root);
            return builder.toString();
        }

        public static void process(StringBuilder builder, TreeNode node) {
            if (node == null) {
                builder.append("#,");
            } else {
                builder.append(node.val + ",");
                process(builder, node.left);
                process(builder, node.right);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() < 2) {
                return null;
            }
            LinkedList<String> es = new LinkedList<>(Arrays.asList(data.split(",")));
            return process(es);
        }

        public static TreeNode process(LinkedList<String> es) {
            if (es.get(0).equals("#")) {
                es.remove(0);
                return null;
            }
            TreeNode cur = new TreeNode(Integer.valueOf(es.get(0)));
            es.remove(0);
            cur.left = process(es);
            cur.right = process(es);
            return cur;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
