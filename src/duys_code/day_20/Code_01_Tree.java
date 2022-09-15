package duys_code.day_20;

import java.util.HashMap;

/**
 * @ClassName Code_01_Tree
 * @Author Duys
 * @Description
 * @Date 2021/11/5 10:09
 **/
public class Code_01_Tree {
    /**
     * 题意：
     * 如果只给定一个二叉树前序遍历数组pre和中序遍历数组in，
     * 能否不重建树，而直接生成这个二叉树的后序数组并返回
     * 已知二叉树中没有重复值
     */
    /**
     * 1.根据先序和中序找到头节点
     */
    public static int[] zuo(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        int n = pre.length;
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inMap.put(in[i], i);
        }
        int[] pos = new int[n];
        process(pre, 0, n - 1, in, 0, n - 1, pos, 0, n - 1, inMap);
        return pos;
    }

    // l1 ~ r1  和 l2 ~ r2 ，l3 ~ r3 都是等长的

    /**            10
     *         7      12
     *      5   6   13  17
     *    1  2   11
     */
    // pre 10 7 5 1  2 6 11 12 13 17
    // in  1  5 2 7  6 11 10 13 12 17
    // pos 1  2 5 11 6  7 13 17 12 10
    // 当前来到先序，先找到头节点。在先序中 头节点往后的多少个节点是左树节点呢》？在中续遍历中，头节点之前的全部都是左树
    public static void process(int[] pre, int l1, int r1, int[] in, int l2, int r2, int[] pos, int l3, int r3, HashMap<Integer, Integer> inMap) {
        if (l1 > r1) {
            return;
        }
        if (l1 == r1) {
            pos[l3] = pre[l1];
        } else {
            // 先序遍历的头一定是后续遍历的尾
            pos[r3] = pre[l1];
            // 找到头在先序遍历的什么位置
            int index = inMap.get(pre[l1]);
            // 左树右树分开来
            // 比如当前 per中 x 是 7 整体是  7  8  9  10 11
            //                           l1          r1
            // 比如在  in中  x 是 13 整体是 10 11 12 13 14
            //                           l2          r2
            // 所以在pre中是 l1+index-l2 这个下标全部是左树
            process(pre, l1 + 1, l1 + index - l2, in, l2, index - 1, pos, l3, l3 + index - l2 - 1, inMap);
            // 右树
            process(pre, l1 + index - l2 + 1, r1, in, index + 1, r2, pos, l3 + index - l2, r3 - 1, inMap);
        }
    }

}
