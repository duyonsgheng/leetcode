package week.week_2023_02_03;

import custom.base.TreeNode;

/**
 * @ClassName Code_02_LeetCode_1028
 * @Author Duys
 * @Description
 * @Date 2023/2/16 13:15
 **/
// 1028. 从先序遍历还原二叉树
public class Code_02_LeetCode_1028 {
    // 思路。递归肯定是要递归的
    // 先把这个字符串结构化了再递归

    // 成对出现的，0是层号，1是数字，2是层号 3是数字。。。。
    public int MAXN = 2001;

    public int[] queue = new int[MAXN];

    public int l, r;

    public TreeNode recoverFromPreorder(String traversal) {
        int num = 0;
        int level = 0;
        boolean pickLevel = true;
        // 1-2--3--4-5--6--7
        for (int i = 0; i < traversal.length(); i++) {
            if (traversal.charAt(i) != '-') {
                if (pickLevel) { // 如果是新的一层了
                    queue[r++] = level;
                    level = 0;
                    pickLevel = false;
                }
                num = num * 10 + traversal.charAt(i) - '0';
            } else {
                if (!pickLevel) {
                    queue[r++] = num;
                    num = 0;
                    pickLevel = true;
                }
                level++;
            }
        }
        queue[r++] = num;
        return process();
    }

    public TreeNode process() {
        int level = queue[l++];
        TreeNode head = new TreeNode(queue[l++]);
        // 还有位置没去，并且下一个层号是大于当前的，说明还有左孩子
        if (l < r && queue[l] > level) {
            head.left = process();
        }
        if (l < r && queue[l] > level) {
            head.right = process();
        }
        return head;
    }

    class Node {
        int level;
        int val;

        public Node(int l, int v) {
            level = l;
            val = v;
        }
    }
}
