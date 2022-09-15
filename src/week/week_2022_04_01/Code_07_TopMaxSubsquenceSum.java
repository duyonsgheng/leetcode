package week.week_2022_04_01;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName Code_07_TopMaxSubsquenceSum
 * @Author Duys
 * @Description
 * @Date 2022/4/7 13:33
 **/
// 来自Amazon
// 给定一个数组arr，含有n个数字，可能有正、有负、有0
// 给定一个正数k
// 返回所有子序列中，累加和最大的前k个子序列累加和
// 假设K不大，怎么算最快？
public class Code_07_TopMaxSubsquenceSum {

    // 那么我们遇到负数该怎么算呢
    // 本题问的是最大的

    /**
     * 1.那么毫无疑问，最大的是所有的正数子序列形成的累加和
     * 2.然后把所有的负数变成正数，来求前k-1最小的，然后用最大的-依次获得最小的，就是整个数组的前k大的
     */

    public static int[] toMaxSum(int[] arr, int k) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                sum += arr[i];
            } else {
                arr[i] = -arr[i];
            }
        }
        int[] ans = toMinSum(arr, k);
        for (int i = 0; i < ans.length; i++) {
            ans[i] = sum - arr[i];
        }
        return ans;
    }

    public static int[] toMinSum(int[] arr, int k) {
        Arrays.sort(arr);
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        heap.add(new int[]{0, arr[0]});
        int[] ans = new int[k];
        for (int i = 1; i < k; i++) {
            int[] cur = heap.poll();
            int last = cur[0];
            int sum = cur[1];
            ans[i] = sum;
            if (last + 1 < arr.length) {
                heap.add(new int[]{last + 1, sum - arr[last] + arr[last + 1]});
                heap.add(new int[]{last + 1, sum + arr[last + 1]});
            }
        }
        return ans;
    }
}
