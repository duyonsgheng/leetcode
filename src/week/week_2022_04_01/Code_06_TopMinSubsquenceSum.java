package week.week_2022_04_01;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName Code_06_TopMinSubsquenceSum
 * @Author Duys
 * @Description
 * @Date 2022/4/7 13:24
 **/


// 给定一个数组arr，含有n个数字，都是非负数
// 给定一个正数k
// 返回所有子序列中，累加和最小的前k个子序列累加和
// 假设K不大，怎么算最快？
public class Code_06_TopMinSubsquenceSum {
    // 分析：我们需要找到前k个最小的子序列 的累加和，那我们需要列出前k小的子序列
    // 用堆，然后收集前K个

    // 我们分裂子序列
    // 先排序
    // 然后第一小的是空集 0
    // 然后就是 {arr[0]} , 根据{arr[0]} 又可以分裂出两个 {arr[1]} ,{arr[0],arr[1]}，然后压入栈，每次弹出最小的，然后又接着分裂
    public static int[] topMinSum(int[] arr, int k) {
        Arrays.sort(arr);
        // int[2] -> 0 - 上一次分裂的最后的位置，1 -上一次分裂后的累加和
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        heap.add(new int[]{0, arr[0]}); // 空集
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int[] cur = heap.poll();
            int last = cur[0];
            int sum = cur[1];
            ans[i] = sum;
            // 不越界
            if (last + 1 < arr.length) {
                heap.add(new int[]{last + 1, sum - arr[last] + arr[last + 1]});
                heap.add(new int[]{last + 1, sum + arr[last + 1]});
            }
        }
        return ans;
    }
}
