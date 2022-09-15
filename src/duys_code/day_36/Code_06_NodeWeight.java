package duys_code.day_36;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_06_NodeWeight
 * @Author Duys
 * @Description
 * @Date 2021/12/13 13:45
 **/
public class Code_06_NodeWeight {
    // 来自美团
    // 有一棵树，给定头节点h，和结构数组m，下标0弃而不用
    // 比如h = 1, m = [ [] , [2,3], [4], [5,6], [], [], []]
    // 表示1的孩子是2、3; 2的孩子是4; 3的孩子是5、6; 4、5和6是叶节点，都不再有孩子
    // 每一个节点都有颜色，记录在c数组里，比如c[i] = 4, 表示节点i的颜色为4
    // 一开始只有叶节点是有权值的，记录在w数组里，
    // 比如，如果一开始就有w[i] = 3, 表示节点i是叶节点、且权值是3
    // 现在规定非叶节点i的权值计算方式：
    // 根据i的所有直接孩子来计算，假设i的所有直接孩子，颜色只有a,b,k
    // w[i] = Max {
    //              (颜色为a的所有孩子个数 + 颜色为a的孩子权值之和),
    //              (颜色为b的所有孩子个数 + 颜色为b的孩子权值之和),
    //              (颜色为k的所有孩子个数 + 颜色k的孩子权值之和)
    //            }
    // 请计算所有孩子的权值并返回

    // 当前来到的节点 h
    // h 的直接孩子 m[h] = {a,b,c,d}
    // 每个节点的颜色 c
    // 每个节点的权重 w
    public static void w(int h, int[][] m, int[] w, int[] c) {
        if (m[h] == null) {// 来到叶节点了，因为题目说了，叶节点权重已经存在了
            return;
        }
        // 所有孩子的颜色
        Map<Integer, Integer> mapColor = new HashMap<>();
        // 每个孩子的权重
        Map<Integer, Integer> mapWeight = new HashMap<>();
        for (int child : m[h]) {
            // 每一个孩子都去收集自己的
            w(child, m, w, c);
            // 颜色收集
            mapColor.put(c[child], mapColor.getOrDefault(c[child], 0) + 1);
            // 权重收集
            mapWeight.put(c[child], mapWeight.getOrDefault(c[child], 0) + w[child]);
        }
        for (int color : mapColor.keySet()) {
            w[h] = Math.max(w[h], mapColor.get(color) + mapWeight.get(color));
        }
    }
}
