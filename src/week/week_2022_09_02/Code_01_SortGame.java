package week.week_2022_09_02;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @ClassName Code_01_SortGame
 * @Author Duys
 * @Description
 * @Date 2022/9/14 22:24
 **/
// 来自百度
// 二狗买了一些小兵玩具，和大胖一起玩
// 一共有n个小兵，这n个小兵拍成一列
// 第i个小兵战斗力为hi，然后他们两个开始对小兵进行排列
// 一共进行m次操作，二狗每次操作选择一个数k，将前k个小兵战斗力从小到大排列
// 大胖每次操作选择一个数k，将前k个小兵战斗力从大到小排列
// 问所有操作结束后，排列顺序什么样
// 给定一个长度为n的数组arr，表示每个小兵的战斗力
// 给定一个长度为m的数组op,
// op[i] = { k , 0 }, 表示对前k个士兵执行从小到大的操作
// op[i] = { k , 1 }, 表示对前k个士兵执行从大到小的操作
// 返回数组ans，表示最终的排列
// 1 <= n, m <= 2 * 10^5
// - 10^9 <= arr[i] <= + 10^9
public class Code_01_SortGame {
    /**
     * 1.对于多个操作来说，如果 第一次是 0-20的区间，第二次是0-40的区间，那么第一次0-20的区间就没必要了，所以单调栈，保持小压大
     * 2.有序表，因为单调栈保证了从前往后的操作，范围是越来越小的，
     * 例如整个长度是100 第一个操作是0-30 区间操作，那么31-100 就没操作了，直接拷贝，
     * 所以两个操作之间存在没有操作的区间
     */
    public static int[] game(int[] arr, int[][] op) {
        int n = arr.length;
        int m = op.length;
        // 栈，始终保持小压大
        int[] stack = new int[m];
        int r = 0;
        for (int i = 0; i < m; i++) {
            // 如果栈顶小于等于当前操作的范围，那么就依次弹出，直到栈顶大于当前操作的范围
            while (r >= 0 && op[stack[r - 1]][0] <= op[i][0]) {
                r--;
            }
            stack[r++] = i;
        }
        int[] ans = new int[n];
        int end = n - 1;
        int l = 0;
        // 不需要处理的部分，直接拷贝
        for (; end >= op[stack[l]][0]; end--) {
            ans[end] = arr[end];
        }
        TreeSet<Number> treeSet = new TreeSet<>(new NumberComparator());
        for (int i = 0; i < op[stack[l]][0]; i++) {
            treeSet.add(new Number(arr[i], i));
        }
        while (l < r) {
            int[] cur = op[stack[l++]];
            // true 表示升序，false表示降序
            boolean mode = cur[0] == 1 ? true : false;
            if (l < r) { // 如果不止一条指令
                int[] next = op[stack[l]];
                int diff = cur[0] - next[0];
                for (int i = 0; i < diff; i++) {
                    // 如果是升序，直接从大到小往前拷贝，如果是降序，从小打到往前拷贝
                    ans[end--] = mode ? treeSet.pollLast().value : treeSet.pollFirst().value;
                }
            } else {// 指令只有一条了
                while (!treeSet.isEmpty()) {
                    ans[end--] = mode ? treeSet.pollLast().value : treeSet.pollFirst().value;
                }
            }
        }
        return ans;
    }

    static class Number {
        public int value;
        public int index;

        public Number(int va, int in) {
            value = va;
            index = in;
        }
    }

    static class NumberComparator implements Comparator<Number> {

        @Override
        public int compare(Number o1, Number o2) {
            if (o1.value != o2.value) {
                return o1.value - o2.value;
            } else {
                return o1.index - o2.index;
            }
        }
    }
}
