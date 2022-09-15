package duys_code.day_03;

import java.util.Arrays;

/**
 * @ClassName Code_04_MaxPairNumber
 * @Author Duys
 * @Description
 * @Date 2021/9/22 9:45
 **/
public class Code_04_MaxPairNumber {
    /**
     * 给定一个数组arr，代表每个人的能力值。再给定一个非负数k
     * 如果两个人能力差值正好为k，那么可以凑在一起比赛
     * 一局比赛只有两个人
     * 返回最多可以同时有多少场比赛
     */

    // 纯暴力方法
    public static int maxPair1(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k < 0) {
            return 0;
        }
        return process(arr, 0, k);
    }

    public static int process(int[] arr, int index, int k) {
        int ans = 0;
        // 最后一个位置了
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i += 2) {
                if (arr[i] - arr[i - 1] == k) {
                    ans++;
                }
            }
        } else {
            for (int r = index; r < arr.length; r++) {
                swap(arr, r, index);
                ans += Math.max(ans, process(arr, index + 1, k));
                // 还原现场
                swap(arr, index, r);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 最优解答 N*logN
    public static int maxPair(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k < 0) {
            return 0;
        }
        // 先排序
        // [7,2,6,3,4,8,5] K=9
        Arrays.sort(arr);
        // [2,3,4,5,6,7,8] K =9
        // 然后使用窗口
        int ans = 0;
        int N = arr.length;
        int L = 0;
        int R = 0;
        boolean[] used = new boolean[N];
        while (L < N && R < N) {
            if (used[L]) {
                L++;
            } else if (L >= R) {
                R++;
            } else {
                int dis = arr[R] - arr[L];
                // 如果相等那么就L 和 R 都++ 并且标记R位置数已经使用过了
                if (dis == k) {
                    ans++;
                    L++;
                    used[R++] = true;
                } else if (dis < k) {
                    R++;
                } else {
                    L++;
                }
            }
        }
        return ans;
    }
}
