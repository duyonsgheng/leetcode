package custom.code_2023_08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2080
 * @date 2023年08月11日
 */
// https://leetcode.cn/problems/range-frequency-queries/
// 2080. 区间内查询数字的频率
// 请你设计一个数据结构，它能求出给定子数组内一个给定值的 频率 。
//子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。
//请你实现 RangeFreqQuery 类：
//RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
//int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
//一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。
public class LeetCode_2080 {
    static class RangeFreqQuery {
        // [12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]
        //
        Map<Integer, List<Integer>> indexMap;

        public RangeFreqQuery(int[] arr) {
            indexMap = new HashMap<>();
            //
            for (int i = 0; i < arr.length; i++) {
                indexMap.putIfAbsent(arr[i], new ArrayList<>());
                indexMap.get(arr[i]).add(i);
                // 33 - 1 9
            }
        }

        public int query(int left, int right, int value) {
            if (!indexMap.containsKey(value)) {
                return 0;
            }
            // 位置有序的
            // 可以二分
            List<Integer> list = indexMap.get(value);
            // 2 15 32 33 36
            // 3-34
            int l = binarySearch(list, 0, list.size() - 1, left);
            // 如果左侧的结果不满足，则没有答案
            if (list.get(l) < left || list.get(l) > right) {
                return 0;

            }
            int r = binarySearch(list, 0, list.size() - 1, right);
            // 如果右侧的不满足，则减少一个
            if (list.get(r) > right) {
                r--;
            }
            return r - l + 1;
        }

        // 找到大于等于 t的最左位置
        private int binarySearch(List<Integer> list, int l, int r, int t) {
            while (l < r) {
                int m = (l + r) / 2;
                if (list.get(m) < t) {
                    l = m + 1;
                } else r = m;
            }
            return l;
        }
    }

    public static void main(String[] args) {
        RangeFreqQuery rf = new RangeFreqQuery(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        System.out.println(rf.query(1, 2, 4));
        System.out.println(rf.query(0, 11, 33));

        rf = new RangeFreqQuery(new int[]{2, 2, 1, 2, 2});
        System.out.println(rf.query(2, 4, 1));
        rf = new RangeFreqQuery(new int[]{1, 1, 1, 2, 2});
        System.out.println(rf.query(0, 1, 2));
        /*List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 100; i += 3) {
            ans.add(i);

        }

        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + "\t");
        }
        System.out.println();
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(i + "\t");
        }

        int left = 34, right = 77;
        int l = 0, r = ans.size() - 1;
        int ln, rn = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            // 30 > 10
            if (ans.get(m) > left) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        ln = l;
        System.out.println();
        System.out.println("ln : " + ln);
        r = ans.size() - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            // 46 < 70
            // 小于等的46最接近的
            if (ans.get(m) > right) {
                r = m - 1;
            } else {
                l = m + 1;
                rn = m;
            }
        }
        System.out.println(" r :" + rn);*/

    }
}
