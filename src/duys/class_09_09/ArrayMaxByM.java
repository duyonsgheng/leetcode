package duys.class_09_09;


import java.util.LinkedList;

/**
 * @ClassName ArrayMaxByM
 * @Author Duys
 * @Description
 * @Date 2021/9/13 13:21
 **/
public class ArrayMaxByM {
    /**
     * 给定一个数组arr，和一个正数M，
     * 返回在arr的子数组在长度不超过M的情况下，最大的累加和
     */
    // 暴力解答
    public static int max1(int[] arr, int M) {
        if (arr == null || arr.length < 1 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                if (j - i + 1 > M) {
                    break;
                }
                sum += arr[j];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    // 窗口内最大值的更新结构来
    public static int max2(int[] arr, int M) {
        if (arr == null || arr.length < 1 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int[] sum = new int[N];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        LinkedList<Integer> qmax = new LinkedList();
        int i = 0;
        int end = Math.min(N, M);
        // 生成第一个窗口内的最大值是什么位置
        for (; i < end; i++) {
            while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
                qmax.pollLast();
            }
            qmax.add(i);
        }
        // L 是窗口的左边界
        int L = 0;
        int max = qmax.peekFirst();// 当前第一个窗口的最大值
        for (; i < N; L++, i++) {
            // 如果当前窗口的最大值就在L位置，那么L需要向右扩一个位置，所以这里需要弹出
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
                qmax.pollLast();
            }
            qmax.add(i);
            max = Math.max(max, sum[qmax.peekLast()] - sum[L]);
        }
        // 这里是比较最后哪一个窗口内的
        for (; L < N - 1; L++) {
            if (qmax.peekFirst() == L) {
                qmax.peekFirst();
            }
            max = Math.max(max, sum[qmax.peekFirst()] - sum[L]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, -2, 5, -4, 6, 3, -9, 11, -7, 9, 8};
        int M = 5;
        System.out.println(max1(arr, M));
        System.out.println(max2(arr, M));
    }
}
