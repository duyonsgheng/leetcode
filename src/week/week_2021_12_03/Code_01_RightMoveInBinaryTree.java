package week.week_2021_12_03;

/**
 * @ClassName Code_01_RightMoveInBinaryTree
 * @Author Duys
 * @Description
 * @Date 2022/4/14 17:16
 **/

// 现有一棵个节点构成的二叉树，请你将每一层的节点向右循环位移位。某层向右位移一位(即)的含义为：
//1.若当前节点为左孩子节点，会变成当前节点的双亲节点的右孩子节点。
//2.若当前节点为右儿子，会变成当前节点的双亲节点的右边相邻兄弟节点的左孩子节点。(如果当前节点的双亲节点已经是最右边的节点了，则会变成双亲节点同级的最左边的节点的左孩子节点)
//3.该层的每一个节点同时进行一次位移。
//4.是从最下面的层开始位移，位移完每一层之后，再向上，直到根节点，位移完毕。
public class Code_01_RightMoveInBinaryTree {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode cyclic(TreeNode root, int k) {
        TreeNode[] queue = new TreeNode[300000];
        int[] ends = new int[50];
        int l = 0;
        int r = 0;
        queue[r++] = root;
        int level = 0;
        // 把所有的节点全部加载到queue中。
        // 把每一层的开始节点记录到ends中去
        while (l != r) {
            ends[level] = r;
            while (l < ends[level]) {
                TreeNode cur = queue[l++];
                if (cur != null) {
                    queue[r++] = cur.left;
                    queue[r++] = cur.right;
                }
            }
            level++;
        }

        for (int i = level - 1; i > 0; i--) {
            // 当前层：curLeft ... curRight
            // 下一层：downLeft ... downRight
            // downIndex:下一层需要跟胡k和下一层长度，来右移，右移之后，从哪一个位置开始分配给当前层第一个不为空的节点
            int downLeft = ends[i - 1];
            int downRight = ends[i] - 1;
            int downRightSize = k % (downRight - downLeft + 1);
            int downIndex = downRightSize == 0 ? downLeft : (downRight - downRightSize + 1);
            int curLeft = i - 2 > 0 ? ends[i - 2] : 0;
            int curRight = ends[i - 1] - 1;
            for (int j = curLeft; j <= curRight; j++) {
                if (queue[j] != null) {
                    queue[j].left = queue[downIndex];
                    downIndex = nextIndex(downIndex, downLeft, downRight);
                    queue[j].right = queue[downIndex];
                    downIndex = nextIndex(downIndex, downLeft, downRight);
                }
            }
        }
        return root;
    }

    public static int nextIndex(int i, int l, int r) {
        return i == r ? l : l + i;
    }
}
