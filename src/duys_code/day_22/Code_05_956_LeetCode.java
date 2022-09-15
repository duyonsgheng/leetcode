package duys_code.day_22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_05_956_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/tallest-billboard/
 * @Date 2021/11/11 10:40
 **/
public class Code_05_956_LeetCode {
    /**
     * 你正在安装一个广告牌，并希望它高度最大。这块广告牌将有两个钢制支架，两边各一个。每个钢支架的高度必须相等。
     * 你有一堆可以焊接在一起的钢筋 rods。举个例子，如果钢筋的长度为 1、2 和 3，则可以将它们焊接在一起形成长度为 6 的支架。
     * 返回广告牌的最大可能安装高度。如果没法安装广告牌，请返回 0。
     */
    /**
     * 本题确实有点难以想到我们先看看常规解答得流程和复杂度
     * 1.先找到所有不相容得子集
     * 2.所有子集得累加和尽量到，找到两个累加和相等得子集
     * 这样做我们得复杂度至少是 O(N^3)以上
     */
    // 那么我们就要想办法降维，想想在生成子集得过程中就能找到合适得记录
    public static int tallestBillboard(int[] rods) {
        // map 得key 是某一个集合对差值 value 是这个集合对中值较小的，那么原来的集合对的和分别是 {value+key},{value}
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> cur;
        // 首先来一个空集
        map.put(0, 0);
        for (int num : rods) {
            if (num == 0) {
                continue;
            }
            // cur 就是之前算出的差值
            cur = new HashMap<>(map);
            // 当前的num 决定放入哪一个集合
            for (int d : cur.keySet()) {
                int curDidd = cur.get(d);
                // 我们决定把num放入到之前的哪一个集合中。因为map中每一条记录就是两个集合
                map.put(d + num, Math.max(curDidd, map.getOrDefault(num + d, 0)));

                // 当前产生的新的差值
                int newDiff = map.getOrDefault(Math.abs(num - d), 0);
                // 看看能不能在差值一样的情况下，把较小的集合之和推高
                if (d >= num) {
                    map.put(d - num, Math.max(curDidd + num, newDiff));
                } else {
                    map.put(num - d, Math.max(curDidd + d, newDiff));
                }
            }
        }
        return map.get(0);
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int n = arr.length;
        List<List<Integer>> chile = new ArrayList<>();
        List<Integer> cl = new ArrayList<>();
       /* for (int i = 0; i < n; i++) {
            cl = new ArrayList<>();
            cl.add(arr[i]);
            chile.add(new ArrayList<>(cl));
            for (int j = i + 1; j < n; j++) {
                cl.add(arr[j]);
                List<Integer> chl = new ArrayList<>();
                chl.addAll(cl);
                chile.add(chl);
            }
        }*/
        process(arr, 0, cl, chile);
        System.out.println(chile.size());
        for (List<Integer> ids : chile) {
            System.out.println();
            for (Integer id : ids) {
                System.out.print("" + id);
            }
            System.out.println();
        }
    }

    public static void process(int[] arr, int index, List<Integer> pre, List<List<Integer>> alls) {
        if (index == arr.length) {
            return;
        }
        alls.add(new ArrayList<>(pre));
        process(arr, index + 1, new ArrayList<>(pre), alls);
        if (!pre.contains(arr[index])) {
            List<Integer> pre1 = new ArrayList<>(pre);
            pre1.add(arr[index]);
            alls.add(pre1);
            process(arr, index + 1, new ArrayList<>(pre1), alls);
        }

    }

}
