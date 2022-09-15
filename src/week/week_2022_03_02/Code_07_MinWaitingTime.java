package week.week_2022_03_02;

import java.util.PriorityQueue;

/**
 * @ClassName Code_07_MinWaitingTime
 * @Author Duys
 * @Description
 * @Date 2022/3/23 13:46
 **/
public class Code_07_MinWaitingTime {

// 来自谷歌
// 给定一个数组arr，长度为n
// 表示n个服务员，每个人服务一个人的时间
// 给定一个正数m，表示有m个人等位
// 如果你是刚来的人，请问你需要等多久？
// 假设：m远远大于n，比如n<=1000, m <= 10的9次方，该怎么做？

    // 普通的解答，小根堆来实现
    public static int minWaitingTime1(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 根据每一个服务员可以开始为别人服务来排序
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        int n = arr.length;
        // 第一轮的时候，每个服务员的开始时间和结束时间是啥
        for (int i = 0; i < n; i++) {
            heap.add(new int[]{0, arr[i]});
        }
        for (int i = 0; i < m; i++) {
            int[] cur = heap.poll();
            cur[0] += cur[1]; // 跟新下一次可以开始服务的时间
            heap.add(cur);
        }
        return heap.peek()[0];
    }

    // 看到 假设：m远远大于n，比如n<=1000, m <= 10的9次方 这个数据量，准备使用二分
    public static int minWaitTime2(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 找到我们的服务最快的哪一个人。
        int bast = Integer.MAX_VALUE;
        for (int num : arr) {
            bast = Math.min(bast, num);
        }

        // 来二分答案。
        int left = 0;
        // 假设我们就排在服务最快的哪一个人后面。最多需要等多久
        int right = bast * m;
        int mid = 0;
        int near = 0;
        while (left <= right) {
            mid = left + (right - left) >> 1;
            int cover = 0;
            // 如果使用当前时间作为答案，看看总共要排多少人
            for (int num : arr) {
                cover += (mid / num) + 1;
            }
            if (cover >= m + 1) {
                near = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return near;
    }
}
