package week.week_2022_08_04;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @ClassName Code_04_LeetCode_2384
 * @Author Duys
 * @Description
 * @Date 2022/8/25 13:05
 **/
// 2384. 最大回文数字
// https://leetcode.cn/problems/largest-palindromic-number/
public class Code_04_LeetCode_2384 {

    // 思路，统计一下各个数字的词频，两端的先选择词频数 >2 且最大的数字
    public static String largestPalindromic(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        HashMap<Integer, Integer> count = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int number = s.charAt(i) - '0';
            count.put(number, count.getOrDefault(number, 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(new RecordComparator());
        for (int key : count.keySet()) {
            heap.add(new int[]{key, count.get(key)});
        }
        int[] top = heap.poll();
        if (top[1] == 1) {
            return String.valueOf(top[0]);
        } else if (top[0] == 0) {
            return String.valueOf(heap.isEmpty() ? 0 : heap.peek()[0]);
        } else {
            StringBuilder left = new StringBuilder();
            left.append(top[0]);
            top[1] -= 2;
            if (top[1] > 0) {
                heap.add(top);
            }
            // 选择
            while (!heap.isEmpty() && heap.peek()[1] > 1) {
                top = heap.poll();
                left.append(top[0]);
                top[1] -= 2;
                if (top[1] > 0) {
                    heap.add(top);
                }
            }
            StringBuilder ans = new StringBuilder();
            // 顺着来一遍
            for (int i = 0; i < left.length(); i++) {
                ans.append(left.charAt(i));
            }
            // 中间可以夹一个大的
            if (!heap.isEmpty()) {
                ans.append(heap.peek()[0]);
            }
            // 反着来一遍
            for (int i = left.length() - 1; i >= 0; i--) {
                ans.append(left.charAt(i));
            }
            return ans.toString();
        }
    }

    public static class RecordComparator implements Comparator<int[]> {

        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[1] == 1 && o2[1] > 1) {
                return 1;
            }
            if (o1[1] > 1 && o2[1] == 1) {
                return -1;
            }
            return o2[0] - o1[0];
        }

    }

}
