package custom.code_2022_06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_337
 * @Author Duys
 * @Description
 * @Date 2022/6/13 10:56
 **/
// 337. 打家劫舍 III
// 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
//除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
//如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
//给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
//链接：https://leetcode.cn/problems/house-robber-iii
public class LeetCode_337 {


    // 二叉树递归套路
    public static int rob(TreeNode root) {
        Info info = process(root);
        return Math.max(info.select, info.notSelect);
    }

    public static Info process(TreeNode cur) {
        if (cur == null) {
            return new Info(0, 0);
        }
        Info left = process(cur.left);
        Info right = process(cur.right);

        // 选择了当前节点了，那么我不能要我的子节点已经选择了的信息了
        int select = cur.val + left.notSelect + right.notSelect;
        // 我不要当前节点，那我不管我子节点选不选，我就选他们最大的相加
        int noselect = Math.max(left.select, left.notSelect) + Math.max(right.select, right.notSelect);
        return new Info(select, noselect);
    }

    public static class Info {
        public int select;
        public int notSelect;

        public Info(int s, int n) {
            select = s;
            notSelect = n;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
