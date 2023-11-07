package hope.binarySearch;

import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code06_WaitingTime
 * @date 2023年11月03日 16:25
 */
// 计算等位时间
// 给定一个数组arr长度为n，表示n个服务员，每服务一个人的时间
// 给定一个正数m，表示有m个人等位，如果你是刚来的人，请问你需要等多久？
// 假设m远远大于n，比如n <= 10^3, m <= 10^9，该怎么做是最优解？
// 谷歌的面试，这个题连考了2个月
// 找不到测试链接，所以用对数器验证
public class Code06_WaitingTime {
    // 堆模拟
    public static int waitingTime1(int[] arr, int m) {
        // [醒来的时间，服务客人的时长]
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            heap.add(new int[]{0, arr[i]});
        }
        // 如果m很大的情况下，会超时。
        for (int i = 0; i < m; i++) {
            int[] cur = heap.poll();
            cur[0] += cur[1];
            heap.add(cur);
        }
        return heap.peek()[0];
    }

    // 二分
    public static int waitingTime2(int[] arr, int m) {
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            min = Math.min(min, i);
        }
        int ans = 0;
        for (int l = 0, r = min * m, mid; l <= r; ) {
            mid = l + ((r - l) >> 1);
            if (process(arr, mid) >= m + 1) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }

    // 如果每个服务器工作时间都是time，则可以服务多少客人
    public static int process(int[] arr, int time) {
        int ans = 0;
        for (int i : arr) {
            ans += (time / i) + 1;
        }
        return ans;
    }
}
