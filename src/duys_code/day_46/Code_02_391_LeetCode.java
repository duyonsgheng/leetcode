package duys_code.day_46;

import java.util.HashMap;

/**
 * @ClassName Code_02_391
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/perfect-rectangle/
 * @Date 2021/10/15 15:15
 **/
public class Code_02_391_LeetCode {
    /**
     * 题意:
     * 给一个二维数组 其中每一个数组长度是4，例如[1,1,2,2] 代表一个矩形，左下角得坐标是(1,1) 右上角得坐标是(2,2) 言外之意有四个点 左下(1,1) 左上(1,2),右下(2,1),右上(2,2)
     * 根据给出的这个二维数组，看看是不是所有的矩形能严丝合缝的粘在一起
     */

    /**
     * 思路：
     * 性质1：记录最左，最右，最下，最上的四个位置，这四个点只出现一次，其他的点出现的次数都是偶数次
     * 性质2：记录所有的面积，然后和总面积是否相等
     */
    public static boolean isRectangleCover(int[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return false;
        }
        // 最左
        int l = Integer.MAX_VALUE;
        // 最右
        int r = Integer.MIN_VALUE;
        // 最上
        int u = Integer.MIN_VALUE;
        // 最下
        int d = Integer.MAX_VALUE;

        // 用来记录我们依次的点出现的次数
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        int area = 0;
        for (int[] arr : matrix) {
            // [1,1,3,4]-> (1,1),(1,3),(3,1),(3,4)
            // [1,1,3,4]->((arr[0],arr[1]),(arr[0],arr[3]),(arr[2],arr[1],(arr[2],arr[3])))
            // 左下的点
            add(map, arr[0], arr[1]);
            // 左上的点
            add(map, arr[0], arr[3]);
            // 右下的点
            add(map, arr[2], arr[1]);
            // 右下的点
            add(map, arr[2], arr[3]);
            // 每一个矩形的面积
            area += (arr[2] - arr[0]) * (arr[3] - arr[1]);
            l = Math.min(l, arr[0]);
            r = Math.max(r, arr[2]);
            u = Math.max(u, arr[3]);
            d = Math.min(d, arr[1]);
        }
        return check(map, l, r, u, d) && area == (r - l) * (u - d);
    }

    public static boolean check(HashMap<Integer, HashMap<Integer, Integer>> map, int l, int r, int u, int d) {
        // 这四个点只出现了依一次
        if (map.get(l).getOrDefault(d, 0) != 1
                || map.get(l).getOrDefault(u, 0) != 1
                || map.get(r).getOrDefault(d, 0) != 1
                || map.get(r).getOrDefault(u, 0) != 1) {
            return false;
        }
        map.get(l).remove(d);
        map.get(l).remove(u);
        map.get(r).remove(d);
        map.get(r).remove(u);
        for (int key : map.keySet()) {
            for (int count : map.get(key).values()) {
                if ((count & 1) != 0) {// 不是偶数就GG
                    return false;
                }
            }
        }
        return true;
    }

    // 记录当前的点出现的次数
    public static void add(HashMap<Integer, HashMap<Integer, Integer>> map, int row, int col) {
        if (!map.containsKey(row)) {
            map.put(row, new HashMap<>());
        }
        map.get(row).put(col, map.get(row).getOrDefault(col, 0) + 1);
    }
}
